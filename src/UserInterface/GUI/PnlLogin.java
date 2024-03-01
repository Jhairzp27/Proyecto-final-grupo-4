package UserInterface.GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;

public class PnlLogin extends JFrame {
    private UsuarioBL usuarioBL = new UsuarioBL();
    private UsuarioDTO usuarioLogeado = null;

    private List<Runnable> loginSuccessListeners = new ArrayList<>();

    public PnlLogin() throws Exception {
        setTitle("Polibank Login/Register");
        setSize(730, 592);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public UsuarioDTO getUsuarioLogeado() {
        return usuarioLogeado;
    }

    private void placeComponents(JPanel panel) throws Exception {
        panel.setLayout(null);

        // Fuente
        Font textFont = new Font("Arial", Font.PLAIN, 20);
        Font labelFont = new Font("Arial", Font.BOLD, 20);

        // Color para texto en modo oscuro
        Color textColor = new Color(0, 100, 00);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setBounds(10, 20, 200, 25);
        userLabel.setFont(labelFont);
        userLabel.setForeground(textColor);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(200, 20, 200, 40);
        userText.setForeground(Color.white);
        userText.setBackground(new Color(80, 80, 80)); // Fondo oscuro para el campo de texto

        userText.setFont(textFont);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(10, 85, 200, 25);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(textColor);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 80, 200, 40);
        passwordText.setForeground(Color.white);
        passwordText.setBackground(new Color(80, 80, 80)); // Fondo oscuro para el campo de contraseña
        passwordText.setFont(textFont);
        panel.add(passwordText);

        Button loginButton = new Button("Iniciar Sesión");
        configurarDarkButton(loginButton);
        loginButton.setBounds(10, 130, 150, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para iniciar sesión
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                try {
                    handleLogin(username, password);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Error al cargar recursos");
                }

            }
        });
        panel.add(loginButton);

        Button registerButton = new Button("Registrarse");
        configurarDarkButton(registerButton);
        registerButton.setBounds(200, 130, 150, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir la ventana de registro
                PnlRegistrationForm registrationForm = new PnlRegistrationForm(PnlLogin.this);
                registrationForm.setVisible(true);
                setVisible(false); // Ocultar la ventana principal al abrir la ventana de registro
            }
        });
        panel.add(registerButton);
        // Carga la imagen de fondo
        ImageIcon backgroundImage = new ImageIcon(
                getClass().getResource("/UserInterface/Resource/FondoLogin.png"));

        // Crea un JLabel con la imagen de fondo
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Establece que el JLabel sea el fondo del panel
        panel.add(backgroundLabel);
    }

    private void configurarDarkButton(Button button) {
        button.setBackground(new Color(47, 79, 79));
        button.setForeground(Color.green);
    }

    public void addLoginSuccessListener(Runnable listener) {
        loginSuccessListeners.add(listener);
    }

    private void handleLogin(String username, String password) throws Exception {
        // Lógica de inicio de sesión
        boolean loginSuccessful = usuarioBL.logear(username, password);
        usuarioLogeado = usuarioBL.leerPorUsername(username);
        if (loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. ¡Bienvenido!");
            for (Runnable listener : loginSuccessListeners) {
                listener.run();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos.");
        }
    }

}

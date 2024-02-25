package UserInterface.Form;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import DataAccess.UserDAO;

public class Login extends JFrame {
    public Login() {
        setTitle("Polibank Login/Register");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        // Dark mode
        panel.setBackground(new Color(40, 40, 40)); // Fondo oscuro

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Fuente
        Font font = new Font("MesloLGL NerdFont", Font.BOLD, 20);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Color para texto en modo oscuro
        Color textColor = new Color(200, 200, 200);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setBounds(10, 20, 80, 25);
        userLabel.setFont(labelFont);
        userLabel.setForeground(textColor);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        userText.setForeground(textColor);
        userText.setBackground(new Color(80, 80, 80)); // Fondo oscuro para el campo de texto

        userText.setFont(textFont);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(10, 50, 80, 25);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(textColor);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        passwordText.setForeground(textColor);
        passwordText.setBackground(new Color(80, 80, 80)); // Fondo oscuro para el campo de contraseña
        passwordText.setFont(textFont);
        panel.add(passwordText);

        JButton loginButton = new JButton("Iniciar Sesión");
        configurarDarkButton(loginButton);
        loginButton.setBounds(10, 80, 150, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para iniciar sesión
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                handleLogin(username, password);
            }
        });
        panel.add(loginButton);

        JButton registerButton = new JButton("Registrarse");
        configurarDarkButton(registerButton);
        registerButton.setBounds(180, 80, 150, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir la ventana de registro
                RegistrationForm registrationForm = new RegistrationForm(Login.this);
                registrationForm.setVisible(true);
                setVisible(false); // Ocultar la ventana principal al abrir la ventana de registro
            }
        });
        panel.add(registerButton);
    }

    private void configurarDarkButton(JButton button) {
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.orange);
        button.setBorderPainted(false);
    }

    private void handleLogin(String username, String password) {
        // Lógica de inicio de sesión
        boolean loginSuccessful = UserDAO.loginUsuario(username, password);
        if (loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. ¡Bienvenido!");
        } else {
            JOptionPane.showMessageDialog(this, "Nombre de usuario o contraseña incorrectos.");
        }
    }

}

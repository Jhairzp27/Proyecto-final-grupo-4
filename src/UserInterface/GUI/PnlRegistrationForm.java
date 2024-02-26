package UserInterface.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import DataAccess.Genero;
import DataAccess.UserDAO;
import UserInterface.CustomerControl.DarkComboBoxUI;

public class PnlRegistrationForm extends JFrame {
    private PnlLogin parentLogin;
    private JTextField nameField;
    private JTextField cedulaField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JComboBox<Genero> generoComboBox;

    public PnlRegistrationForm(PnlLogin parentLogin) {
        this.parentLogin = parentLogin;
        setTitle("Registro de Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Dark mode
        panel.setBackground(new Color(40, 40, 40));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Fuente
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Color textColor = new Color(200, 200, 200);

        nameField = new JTextField();
        cedulaField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();

        JButton registerButton = new JButton("Registrarse");
        configureDarkButton(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        JButton loginButton = new JButton("Iniciar Sesión");
        configureDarkButton(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para mostrar la ventana de inicio de sesión
                parentLogin.setVisible(true);
                dispose();
            }
        });

        generoComboBox = new JComboBox<>();
        generoComboBox.addItem(new Genero("Masculino", 1));
        generoComboBox.addItem(new Genero("Femenino", 2));
        generoComboBox.addItem(new Genero("Otro", 3));
        generoComboBox.setUI(new DarkComboBoxUI());

        generoComboBox.setBackground(new Color(40,40,40));

        panel.add(createLabelWithField("Nombre:", nameField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Cedula:", cedulaField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Nombre de Usuario:", usernameField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Contraseña:", passwordField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Email:", emailField, labelFont, textFont, textColor));
        panel.add(createLabelWithComboBox("Genero:", generoComboBox, labelFont, textFont, textColor));
        panel.add(registerButton);
        panel.add(loginButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        configureDarkButton(registerButton);
        configureDarkButton(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        panel.add(buttonPanel);
        buttonPanel.setBackground(new Color(40, 40, 40));

        setLocationRelativeTo(null);
    }

    private void configureDarkButton(JButton button) {
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false); // No pintar el borde del botón
    }

    private JPanel createLabelWithField(String labelText, JTextField textField, Font labelFont, Font textFont, Color textColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(textColor);
        panel.add(label);

        panel.add(Box.createHorizontalStrut(10));

        textField.setFont(textFont);
        textField.setForeground(textColor);
        textField.setBackground(new Color(80, 80, 80));
        textField.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60))); // Añadir borde

        panel.add(textField);

        panel.setBackground(new Color(40, 40, 40));

        return panel;
    }

    private JPanel createLabelWithComboBox(String labelText, JComboBox<Genero> comboBox, Font labelFont, Font textFont, Color textColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(textColor);
        panel.add(label);

        panel.add(Box.createHorizontalStrut(10));

        comboBox.setFont(textFont);
        comboBox.setForeground(textColor);
        comboBox.setBackground(new Color(80, 80, 80));
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60))); // Añadir borde

        panel.add(comboBox);

        panel.setBackground(new Color(40, 40, 40));

        return panel;
    }

    private void registerUser() {
        String name = nameField.getText();
        String cedula = cedulaField.getText();
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String email = emailField.getText();
        Genero selectedGenero = (Genero) generoComboBox.getSelectedItem();
        int idGenero = selectedGenero.getIdGenero();

        if (isStrongPassword(password) && isValidEmail(email)) {
            // Lógica para registrar al usuario en la base de datos
            UserDAO.registrarUsuario(name, cedula, username, password, email, idGenero);
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
            parentLogin.setVisible(true);  // Hacer visible la ventana principal al cerrar esta ventana de registro
            dispose(); // Cerrar la ventana después de registrar al usuario
        } else {
            JOptionPane.showMessageDialog(this, "La contraseña o el correo electrónico no cumplen con los requisitos.");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 && password.matches(".*[0-9].*") && password.matches(".*[a-zA-Z].*");
    }
}
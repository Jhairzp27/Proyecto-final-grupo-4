/*
|----------------------------------------|
| (©) 2K24 EPN-FIS, All rights reserved. |
| jhair.zambrano@epn.edu.ec   Jhairzp27  |
|----------------------------------------|
Autor : Blurryfacce - Jhairzp27
Fecha : 29.feb.2k24
*/
package UserInterface.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import BusinessLogic.SexoBL;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.SexoDTO;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.DarkComboBoxUI;

public class PnlRegistrationForm extends JFrame {
    private PnlLogin parentLogin;
    private JTextField nameField;
    private JTextField cedulaField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private UsuarioBL usuarioBL = new UsuarioBL();
    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private SexoBL sexoBL = new SexoBL();
    private JComboBox<SexoDTO> sexoComboBox;

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

        // Configuración del tema oscuro para JOptionPane
        UIManager.put("OptionPane.background", new Color(40, 40, 40));
        UIManager.put("Panel.background", new Color(40, 40, 40));
        UIManager.put("OptionPane.messageForeground", new Color(200, 200, 200));

        // Fuente
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Color textColor = new Color(200, 200, 200);

        nameField = new JTextField();
        cedulaField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();

        // CONTROL CEDULA
        cedulaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = cedulaField.getText();
                if (!isValidCedula(input)) {
                    mostrarError("La cédula debe contener solo números y tener máximo 10 dígitos.");
                    cedulaField.setText(""); // Limpia el campo sin no se cumple con los requisitos
                } else {
                    // Lógica para cédula válida, si es necesario
                }
            }
        });

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

        try {
            sexoComboBox = new JComboBox<>(); // Cambio de Genero a SexoDTO
            sexoComboBox.addItem(sexoBL.leerPor(1));
            sexoComboBox.addItem(sexoBL.leerPor(2));
            sexoComboBox.addItem(sexoBL.leerPor(3));

            sexoComboBox.setRenderer(new DefaultListCellRenderer());
            sexoComboBox.setUI(new DarkComboBoxUI());
            sexoComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                        boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof SexoDTO) {
                        SexoDTO sexoDTO = (SexoDTO) value;
                        setText(sexoDTO.getNombre());
                    }
                    return this;
                }
            });
        } catch (Exception e) {
        }

        sexoComboBox.setBackground(new Color(40, 40, 40));

        panel.add(createLabelWithField("Nombre:", nameField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Cedula:", cedulaField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Nombre de Usuario:", usernameField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Contraseña:", passwordField, labelFont, textFont, textColor));
        panel.add(createLabelWithField("Email:", emailField, labelFont, textFont, textColor));
        panel.add(createLabelWithComboBox("Sexo:", sexoComboBox, labelFont, textFont, textColor));
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

    private JPanel createLabelWithField(String labelText, JTextField textField, Font labelFont, Font textFont,
            Color textColor) {
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

    private JPanel createLabelWithComboBox(String labelText, JComboBox<SexoDTO> comboBox, Font labelFont, Font textFont,
            Color textColor) {
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
        SexoDTO selectedSexo = (SexoDTO) sexoComboBox.getSelectedItem();
        int idSexo = selectedSexo.getIdSexo();

        if (!isValidCedula(cedula)) {
            mostrarError("La cédula debe contener solo números y tener máximo 10 dígitos.");
        } else if (!isStrongPassword(password)) {
            mostrarError("La contraseña debe tener al menos 8 caracteres y contener al menos un número.");
        } else if (!isValidEmail(email)) {
            mostrarError("El correo electrónico debe ser válido y contener '@' y '.'.");
        } else {
            // Lógica para registrar al usuario en la base de datos
            usuarioDTO.setNombre(name);
            usuarioDTO.setCedula(cedula);
            usuarioDTO.setUsername(username);
            usuarioDTO.setClave(password);
            usuarioDTO.setEmail(email);
            usuarioDTO.setIdSexo(idSexo);

            try {
                usuarioBL.registrar(usuarioDTO);
                mostrarMensaje("Usuario registrado correctamente.", "Registro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
                parentLogin.setVisible(true); // Hacer visible la ventana principal al cerrar esta ventana de registro
                dispose(); // Cerrar la ventana después de registrar al usuario
            } catch (Exception e) {
                mostrarError("Error al registrarse");
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 && password.matches(".*[0-9].*") && password.matches(".*[a-zA-Z].*");
    }

    private boolean isValidCedula(String cedula) {
        return cedula.matches("\\d{1,10}");
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, crearDarkPanel(mensaje), titulo, tipoMensaje);
    }

    private void mostrarError(String mensaje) {
        mostrarMensaje(mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private JPanel crearDarkPanel(String message) {
        JPanel darkPanel = new JPanel();
        darkPanel.setBackground(new Color(40, 40, 40));
        darkPanel.setForeground(new Color(200, 200, 200));
    
        JLabel label = new JLabel(message);
        label.setForeground(new Color(200, 200, 200));
        darkPanel.add(label);
    
        return darkPanel;
    }
    
}

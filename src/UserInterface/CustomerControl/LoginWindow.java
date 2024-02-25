package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
    private JLabel lblTitulo = new JLabel("Inicio de Sesión");
    private JLabel lblUsuario = new JLabel("Usuario:");
    private JLabel lblPassword = new JLabel("Contraseña:");
    private JTextField txtUsuario = new JTextField(20);
    private JPasswordField txtPassword = new JPasswordField(20);
    private JButton btnLogin = new JButton("Iniciar Sesión");

    public LoginWindow() {
        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        // Configurar el fondo negro
        getContentPane().setBackground(Color.BLACK);

        // Diseño y la disposición de los componentes
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        lblTitulo.setForeground(Color.WHITE);
        lblUsuario.setForeground(Color.WHITE);
        lblPassword.setForeground(Color.WHITE);
        btnLogin.setBackground(Color.WHITE);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        //Componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitulo, gbc);

        gbc.gridy = 1;
        add(lblUsuario, gbc);

        gbc.gridy = 2;
        add(txtUsuario, gbc);

        gbc.gridy = 3;
        add(lblPassword, gbc);

        gbc.gridy = 4;
        add(txtPassword, gbc);

        gbc.gridy = 5;
        add(btnLogin, gbc);

        setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }

}

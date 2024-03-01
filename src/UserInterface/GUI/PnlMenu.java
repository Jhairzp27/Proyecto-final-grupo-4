package UserInterface.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.Button;
import UserInterface.CustomerControl.Estilo;
import UserInterface.CustomerControl.Label;

public class PnlMenu extends JPanel {
    private Label lblSaldo;
    private UsuarioDTO usuarioDTO;

    /**
     * Botones del menú
     */
    public Button btnRecarga = new Button("Recargar"),
            btnTransferencia = new Button("Transferir"),
            btnMovimientos = new Button("Ver Movimientos"),
            btnVerEstado = new Button("Ver Estado de Cuenta"),
            btnImprimirEstado = new Button("Imprimir Estado de Cuenta"),
            btnCambiarContrasena = new Button("Cambiar Contraseña"),
            btnBorrarCuenta = new Button("Borrar Cuenta"),
            btnCerrarSesion = new Button("Cerrar Sesión");

    /**
     * Constructor para el panel de menú.
     * 
     * @param usuarioDTO El DTO del usuario actual.
     */
    public PnlMenu(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
        customizeComponent();
    }

    /**
     * Personaliza los componentes del panel de menú.
     */
    private void customizeComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(250, getHeight()));

        // Añadir logo
        try {
            Image logo = ImageIO.read(Estilo.URL_LOGO);
            logo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(logo)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Añadir saldo
        add(Box.createVerticalStrut(10)); // Espacio en blanco vertical
        lblSaldo = new Label("Saldo actual: $" + usuarioDTO.getSaldo());
        lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaldo.setFont(lblSaldo.getFont().deriveFont(15f)); // Establecer el tamaño de la fuente a 15 puntos
        lblSaldo.setBackground(new Color(0, 200, 0)); // Establecer el color de fondo
        lblSaldo.setForeground(Color.white); // Establecer el color de la letra
        add(lblSaldo);
        add(Box.createVerticalStrut(10)); // Espacio en blanco vertical

        // Añadir botones
        add(btnRecarga);
        add(Box.createVerticalStrut(10));
        add(btnTransferencia);
        add(Box.createVerticalStrut(10));
        add(btnMovimientos);
        add(Box.createVerticalStrut(10));
        add(btnVerEstado);
        add(Box.createVerticalStrut(10));
        add(btnImprimirEstado);
        add(Box.createVerticalStrut(10));
        add(btnCambiarContrasena);
        add(Box.createVerticalStrut(10));
        add(btnBorrarCuenta);
        add(Box.createVerticalStrut(10));
        add(btnCerrarSesion);
        add(Box.createVerticalStrut(10));

        add(Box.createVerticalGlue()); // Pegado vertical para posicionar en la parte inferior

        // Añadir copyright
        JLabel lblCopyright = new JLabel("\u00A9 2024 PoliBank");
        lblCopyright.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblCopyright);
    }

    /**
     * Actualiza el saldo mostrado en el panel de menú.
     * 
     * @param nuevoSaldo El nuevo saldo a mostrar.
     */
    public void actualizarSaldo(float nuevoSaldo) {
        lblSaldo.setPreferredSize(new Dimension(2000, 250)); // Ajuste del tamaño preferido del label para evitar truncamiento
        lblSaldo.setText("Saldo actual: $" + nuevoSaldo);
    }

}

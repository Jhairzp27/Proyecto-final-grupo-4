package UserInterface.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
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

    public  Button   
            btnRecarga           = new Button("Recargar"),
            btnTransferencia     = new Button("Transferir"),
            btnMovimientos       = new Button("Ver Movimientos"),
            btnVerEstado         = new Button("Ver Estado de Cuenta"),
            btnImprimirEstado    = new Button("Imprimir Estado de Cuenta"),
            btnCambiarContrasena = new Button("Cambiar Contraseña"),
            btnBorrarCuenta      = new Button("Borrar Cuenta"),
            btnCerrarSesion      = new Button("Cerrar Sesión");

    public PnlMenu(UsuarioDTO usuarioDTO){
        this.usuarioDTO = usuarioDTO;
        customizeComponent();
    }

    private void customizeComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(250, getHeight())); 

        // add-logo
        try {
            Image logo = ImageIO.read(Estilo.URL_LOGO);
            logo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(logo)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add saldo
        lblSaldo = new Label("Saldo actual: $" + usuarioDTO.getSaldo());
        lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblSaldo);

        // add-botones
        add(btnRecarga);
        add(btnTransferencia);
        add(btnMovimientos);
        add(btnVerEstado);
        add(btnImprimirEstado);
        add(btnCambiarContrasena);
        add(btnBorrarCuenta);
        add(btnCerrarSesion);

        // add-copyright
        add(new JLabel("\u00A9 2024 PoliBank"));
    }

    public void actualizarSaldo(float nuevoSaldo) {
        lblSaldo.setText("Saldo actual: $" + nuevoSaldo);
    }
    
}

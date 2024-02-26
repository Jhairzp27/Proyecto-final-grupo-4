package UserInterface.Form;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UserInterface.CustomerControl.Button;

public class MenuPanel extends JPanel {
    public  Button   
            btnRecarga           = new Button("Recargar"),
            btnTransferencia     = new Button("Transferir"),
            btnMovimientos       = new Button("Ver Movimientos"),
            btnVerEstado         = new Button("Ver Estado de Cuenta"),
            btnImprimirEstado    = new Button("Imprimir Estado de Cuenta"),
            btnCambiarContrasena = new Button("Cambiar Contraseña"),
            btnCerrarSesion      = new Button("Cerrar Sesión");

    public MenuPanel(){
        customizeComponent();
    }

    private void customizeComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(250, getHeight())); 

        // add-logo
        /* try {
            Image logo = ImageIO.read(IAStyle.URL_LOGO);
            logo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(logo)));
        } catch (IOException e) {
            e.printStackTrace();
        } */

        // add-botones
        add(btnRecarga);
        add(btnTransferencia);
        add(btnMovimientos);
        add(btnVerEstado);
        add(btnImprimirEstado);
        add(btnCambiarContrasena);
        add(btnCerrarSesion);

        // add-copyright
        add(new JLabel("\u00A9 2024 PoliBank"));
    }
}

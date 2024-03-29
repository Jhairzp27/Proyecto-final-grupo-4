package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DataAccess.DTO.UsuarioDTO;
import UserInterface.GUI.PnlBorrarCuenta;
import UserInterface.GUI.PnlCambiarContrasena;
import UserInterface.GUI.PnlEstadoCuenta;
import UserInterface.GUI.PnlImpresion;
import UserInterface.GUI.PnlLogin;
import UserInterface.GUI.PnlMain;
import UserInterface.GUI.PnlMenu;
import UserInterface.GUI.PnlMovimientos;
import UserInterface.GUI.PnlRecarga;
import UserInterface.GUI.PnlTransferencia;

/**
 * Clase MainForm que representa la ventana principal de la aplicación.
 */
public class MainForm extends JFrame {

    Boolean estadoCuentaVisto = false; // Estado del estado de cuenta
    JPanel pnlMain = new PnlMain(); // Panel principal
    PnlEstadoCuenta pnlEstadoCuenta; // Panel de estado de cuenta
    PnlMenu pnlMenu; // Panel de menú
    UsuarioDTO usuarioDTO; // Objeto que contiene los datos del usuario
    
    /**
     * Constructor de la clase MainForm.
     * @param tilteApp Título de la aplicación.
     * @param usuarioDTO Objeto UsuarioDTO que contiene los datos del usuario.
     * @param login Panel de inicio de sesión.
     */
    @SuppressWarnings("unused")
    public MainForm(String tilteApp, UsuarioDTO usuarioDTO, PnlLogin login) {
        this.usuarioDTO = usuarioDTO;
        customizeComponent(tilteApp);
        setIconImage(
                new ImageIcon(getClass().getResource("/UserInterface/Resource/FondoAcciones.png")).getImage());
        pnlMenu.btnRecarga.addActionListener(e -> setPanel(new PnlRecarga(usuarioDTO, pnlMenu)));
        pnlMenu.btnTransferencia.addActionListener(e -> setPanel(new PnlTransferencia(usuarioDTO, pnlMenu)));
        pnlMenu.btnMovimientos.addActionListener(e -> setPanel(new PnlMovimientos(usuarioDTO)));
        pnlMenu.btnVerEstado.addActionListener(e -> {setPanel(pnlEstadoCuenta = new PnlEstadoCuenta(usuarioDTO));
                                                     estadoCuentaVisto = true;});
        pnlMenu.btnImprimirEstado.addActionListener(e -> new PnlImpresion(estadoCuentaVisto, usuarioDTO));
        pnlMenu.btnCambiarContrasena.addActionListener(e -> setPanel(new PnlCambiarContrasena(usuarioDTO)));
        pnlMenu.btnBorrarCuenta.addActionListener(e -> setPanel(new PnlBorrarCuenta(usuarioDTO, login)));
        pnlMenu.btnCerrarSesion.addActionListener(e -> {
            dispose();
            try {
                PnlLogin login2 = new PnlLogin();
                login2.setVisible(true);
                login2.addLoginSuccessListener(() -> {
                    try {
                        login2.dispose();
                        MainForm mainForm = new MainForm("PoliBank", login2.getUsuarioLogeado(), login);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    /**
     * Método para cambiar el panel principal en la MainForm.
     * @param formularioPanel Panel que se establecerá como panel principal.
     */
    private void setPanel(JPanel formularioPanel) {
        Container container = getContentPane();
        container.remove(pnlMain);
        pnlMain = formularioPanel;
        container.add(pnlMain, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Método para personalizar los componentes de la MainForm.
     * @param tilteApp Título de la aplicación.
     */
    private void customizeComponent(String tilteApp) {
        pnlMenu = new PnlMenu(usuarioDTO);
        setTitle(tilteApp);
        setSize(930, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un contenedor para los dos paneles usando BorderLayout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Agregar los paneles al contenedor
        container.add(pnlMenu, BorderLayout.WEST);
        container.add(pnlMain, BorderLayout.CENTER);
        setVisible(true);
    }
}

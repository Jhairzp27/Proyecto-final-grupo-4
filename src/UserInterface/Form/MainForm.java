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

public class MainForm extends JFrame {
    Boolean estadoCuentaVisto = false;
    JPanel pnlMain = new PnlMain();
    PnlEstadoCuenta pnlEstadoCuenta;
    PnlMenu pnlMenu;
    UsuarioDTO usuarioDTO;
    
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
                new PnlLogin();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void setPanel(JPanel formularioPanel) {
        Container container = getContentPane();
        container.remove(pnlMain);
        pnlMain = formularioPanel;
        container.add(pnlMain, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

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

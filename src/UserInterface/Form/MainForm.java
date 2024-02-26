package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DataAccess.DTO.UsuarioDTO;
import UserInterface.GUI.PnlBorrarCuenta;
import UserInterface.GUI.PnlCambiarContrasena;
import UserInterface.GUI.PnlLogin;
import UserInterface.GUI.PnlMain;
import UserInterface.GUI.PnlMenu;
import UserInterface.GUI.PnlRecarga;

public class MainForm extends JFrame {
    PnlMenu pnlMenu = new PnlMenu();
    JPanel  pnlMain = new PnlMain();

    public MainForm(String tilteApp, UsuarioDTO usuarioDTO, PnlLogin login) {
        customizeComponent(tilteApp);
        pnlMenu.btnRecarga.addActionListener           (e -> setPanel(new PnlRecarga(usuarioDTO))); 
        pnlMenu.btnTransferencia.addActionListener     (e -> setPanel(new PnlMain())); 
        pnlMenu.btnMovimientos.addActionListener       (e -> setPanel(new PnlMain()));  
        pnlMenu.btnVerEstado.addActionListener         (e -> setPanel(new PnlMain())); 
        pnlMenu.btnImprimirEstado.addActionListener    (e -> setPanel(new PnlMain())); 
        pnlMenu.btnCambiarContrasena.addActionListener (e -> setPanel(new PnlCambiarContrasena(usuarioDTO))); 
        pnlMenu.btnBorrarCuenta.addActionListener      (e -> setPanel(new PnlBorrarCuenta(usuarioDTO, login))); 
        pnlMenu.btnCerrarSesion.addActionListener      (e -> {dispose(); 
                                                        try {new PnlLogin();} 
                                                        catch (Exception e1) {e1.printStackTrace();}}); 
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

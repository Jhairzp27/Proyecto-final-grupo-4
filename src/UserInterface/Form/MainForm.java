package UserInterface.Form;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import UserInterface.GUI.PnlLogin;

public class MainForm extends JFrame {
    MenuPanel pnlMenu = new MenuPanel();
    JPanel    pnlMain = new MainPanel();

    public MainForm(String tilteApp) {
        customizeComponent(tilteApp);
        pnlMenu.btnRecarga.addActionListener           (e -> setPanel(new MainPanel())); 
        pnlMenu.btnTransferencia.addActionListener     (e -> setPanel(new MainPanel())); 
        pnlMenu.btnMovimientos.addActionListener       (e -> setPanel(new MainPanel()));  
        pnlMenu.btnVerEstado.addActionListener         (e -> setPanel(new MainPanel())); 
        pnlMenu.btnImprimirEstado.addActionListener    (e -> setPanel(new MainPanel())); 
        pnlMenu.btnCambiarContrasena.addActionListener (e -> setPanel(new MainPanel())); 
        pnlMenu.btnCerrarSesion.addActionListener      (e -> {dispose(); new PnlLogin();}); 
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

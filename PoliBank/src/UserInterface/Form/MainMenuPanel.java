package UserInterface.Form;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UserInterface.CustomerControl.PatButton;
import UserInterface.GUI.PatPnlPersona;

public class MainMenuPanel extends JPanel {
    PatButton btnOp1 = new PatButton("Lista de Usuarios"),
              btnOp2 = new PatButton("Opción 2"),
              btnOp3 = new PatButton("Opción 3");
    
    public MainMenuPanel() {
        customizeComponent();
        btnOp1.addActionListener(e -> showPatPnlPersonaSexo());
        btnOp2.addActionListener(e -> btnOp2Click());
        btnOp3.addActionListener(e -> btnOp3Click());
    }
    
    private void showPatPnlPersonaSexo() {
        try {
            removeAll();
            add(new PatPnlPersona());
            revalidate();
            repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar PatPnlPersonaSexo", 
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnOp2Click() {
        JOptionPane.showMessageDialog(this, "Seleccionaste Opción 3");

    }
    
    private void btnOp3Click() {
        JOptionPane.showMessageDialog(this, "Seleccionaste Opción 3");
    }

    private void customizeComponent() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(btnOp1);
        add(btnOp2);
        add(btnOp3);
    }
}

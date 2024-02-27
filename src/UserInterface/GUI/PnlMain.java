package UserInterface.GUI;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import UserInterface.CustomerControl.Estilo;

public class PnlMain extends JPanel {
    public PnlMain() {
        customizeComponent();
    }

    private void customizeComponent() {
        try {
            ImageIcon imageIcon = new ImageIcon(Estilo.URL_LOGO);
            add(new JLabel(imageIcon));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
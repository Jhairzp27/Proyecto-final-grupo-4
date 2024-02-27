package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.*;

public class LabelText extends JPanel {

    private Label lblEtiqueta = new Label();
    private JLabel txtContenido = new JLabel();

    public LabelText(String etiqueta) {
        setLayout(new BorderLayout());
        lblEtiqueta.setCustomizeComponent(
                etiqueta,
                Estilo.FONT,
                Estilo.COLOR_FONT,
                Estilo.ALIGNMENT_CENTER);

        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
        setOpaque(true); // Hacer que el panel sea opaco
        setBackground(Color.WHITE);
    }
}

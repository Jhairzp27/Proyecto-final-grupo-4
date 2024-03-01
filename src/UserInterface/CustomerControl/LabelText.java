/**
 * Clase que representa un panel con una etiqueta y un texto.
 */
package UserInterface.CustomerControl;

import javax.swing.*;
import java.awt.*;

public class LabelText extends JPanel {

    private Label lblEtiqueta = new Label();
    private JLabel txtContenido = new JLabel();

    /**
     * Constructor que crea un panel con una etiqueta y un texto.
     * 
     * @param etiqueta El texto que se mostrará como etiqueta.
     */
    public LabelText(String etiqueta) {
        setLayout(new BorderLayout());
        // Personalizar la etiqueta con el texto proporcionado y estilos comunes
        lblEtiqueta.setCustomizeComponent(
                etiqueta,
                Estilo.FONT,
                Estilo.COLOR_FONT,
                Estilo.ALIGNMENT_CENTER);

        // Agregar la etiqueta en la parte superior y el texto en el centro del panel
        add(lblEtiqueta, BorderLayout.NORTH);
        add(txtContenido, BorderLayout.CENTER);
        setOpaque(true); // Hacer que el panel sea opaco
        setBackground(Color.WHITE); // Establecer el fondo del panel en blanco
    }
}

/**
 * Clase que representa una etiqueta personalizada que extiende JLabel.
 */
package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

    /**
     * Constructor que crea una etiqueta sin texto y personaliza el componente.
     */
    public Label() {
        customizeComponent();
    }

    /**
     * Constructor que crea una etiqueta con el texto especificado y personaliza el
     * componente.
     * 
     * @param text El texto que mostrará la etiqueta.
     */
    public Label(String text) {
        setText(text);
        customizeComponent();
    }

    /**
     * Método privado para personalizar el componente de etiqueta.
     */
    private void customizeComponent() {
        setCustomizeComponent(getText(), Estilo.FONT_SMALL, Estilo.COLOR_FONT_LIGHT, Estilo.ALIGNMENT_LEFT);
    }

    /**
     * Método para personalizar el componente de etiqueta con el texto, fuente,
     * color y alineación especificados.
     * 
     * @param text      El texto que mostrará la etiqueta.
     * @param font      La fuente que se aplicará al texto.
     * @param color     El color del texto.
     * @param alignment La alineación del texto dentro de la etiqueta.
     */
    public void setCustomizeComponent(String text, Font font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(true); // Hacer que el componente sea opaco
        setBackground(Color.WHITE); // Establecer el fondo del componente en blanco
        setForeground(color);
        setHorizontalAlignment(alignment);
    }
}

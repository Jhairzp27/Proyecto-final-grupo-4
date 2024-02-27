package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

    public Label() {
        customizeComponent();
    }

    public Label(String text) {
        setText(text);
        customizeComponent();
    }

    private void customizeComponent() {
        setCustomizeComponent(getText(), Estilo.FONT_SMALL, Estilo.COLOR_FONT_LIGHT, Estilo.ALIGNMENT_LEFT);
    }

    public void setCustomizeComponent(String text, Font font, Color color, int alignment) {
        setText(text);
        setFont(font);
        setOpaque(true); // Hacer que el componente sea opaco
        setBackground(Color.WHITE); // Establecer el fondo del componente en blanco
        setForeground(color);
        setHorizontalAlignment(alignment);
    }
}
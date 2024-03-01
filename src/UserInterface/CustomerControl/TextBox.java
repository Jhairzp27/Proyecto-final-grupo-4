/**
 * Clase que representa un campo de texto personalizado.
 */
package UserInterface.CustomerControl;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class TextBox extends JTextField {

    /**
     * Constructor que inicializa el campo de texto con la personalización
     * predeterminada.
     */
    public TextBox() {
        customizeComponent();
    }

    /**
     * Método privado para aplicar la personalización al campo de texto.
     */
    private void customizeComponent() {
        setFont(Estilo.FONT_SMALL); // Establecer la fuente
        setForeground(Estilo.COLOR_FONT); // Establecer el color de texto
        setBorder(createBorderRect()); // Establecer el borde
        setCaretColor(Estilo.COLOR_CURSOR); // Establecer el color del cursor
        setMargin(new Insets(10, 10, 10, 10)); // Establecer los márgenes internos
        setOpaque(true); // Hacer que el componente sea opaco
        setBackground(Color.WHITE); // Establecer el fondo en blanco
        setColumns(10); // Ajustar el tamaño para 10 caracteres
    }

    /**
     * Método privado para crear el borde del campo de texto.
     * 
     * @return El borde compuesto creado.
     */
    private Border createBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(Estilo.COLOR_LABEL); // Borde de línea
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5); // Margen interno
        return new CompoundBorder(lineBorder, emptyBorder); // Borde compuesto
    }

    /**
     * Método público para crear un borde de línea en la parte inferior del campo de
     * texto.
     * 
     * @return El borde creado.
     */
    public Border createBorderLine() {
        int thickness = 1; // Grosor del borde
        return BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, thickness, 0), // Borde vacío en la parte superior, izquierda y
                                                                     // derecha
                BorderFactory.createMatteBorder(0, 0, thickness, 0, Estilo.COLOR_LABEL)); // Borde mate en la parte
                                                                                           // inferior
    }
}

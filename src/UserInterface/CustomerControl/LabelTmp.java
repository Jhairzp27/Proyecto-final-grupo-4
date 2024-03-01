/**
 * Clase que representa una etiqueta temporal con personalización adicional.
 */
package UserInterface.CustomerControl;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LabelTmp extends JLabel implements MouseListener {

    /**
     * Constructor que crea una etiqueta temporal con el texto especificado.
     * 
     * @param label El texto que mostrará la etiqueta.
     */
    LabelTmp(String label) {
        super(label);
        setPersonalization();
    }

    /**
     * Constructor que crea una etiqueta temporal con el texto y la imagen
     * especificados.
     * 
     * @param label    El texto que mostrará la etiqueta.
     * @param iconPath La ruta de la imagen que se mostrará en la etiqueta.
     */
    LabelTmp(String label, String iconPath) {
        super();
        setPersonalization();
        setIcon(new ImageIcon(iconPath));
    }

    /**
     * Método privado para aplicar la personalización a la etiqueta.
     */
    void setPersonalization() {
        addMouseListener(this);
        setOpaque(false); // Hacer que la etiqueta sea transparente
        setForeground(Color.blue); // Establecer el color de texto en azul
    }

    // Métodos de la interfaz MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Método que se ejecuta cuando el cursor entra en la etiqueta, estableciendo el
     * cursor en modo mano.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar el cursor a mano
    }

    /**
     * Método que se ejecuta cuando el cursor sale de la etiqueta, restableciendo el
     * cursor predeterminado.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // Restablecer el cursor predeterminado
    }
}

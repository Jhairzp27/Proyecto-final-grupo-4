/**
 * Clase que define una interfaz de usuario (UI) para un JComboBox con un tema oscuro.
 */
package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxButton;
import javax.swing.plaf.metal.MetalComboBoxUI;

public class DarkComboBoxUI extends MetalComboBoxUI {

    /**
     * Método para crear el componente emergente del JComboBox con el tema oscuro.
     * 
     * @return La instancia del componente emergente creado.
     */
    @Override
    protected ComboPopup createPopup() {
        return new BasicComboPopup(comboBox) {
            @Override
            protected JScrollPane createScroller() {
                JScrollPane scroller = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scroller.getVerticalScrollBar().setBackground(new Color(60, 60, 60));
                scroller.getViewport().setBackground(new Color(80, 80, 80));
                return scroller;
            }
        };
    }

    /**
     * Método para crear el botón de flecha del JComboBox con el tema oscuro.
     * 
     * @return El botón de flecha creado.
     */
    @Override
    protected JButton createArrowButton() {
        return new MetalComboBoxButton(comboBox, new DarkComboIcon(), hasFocus, currentValuePane, listBox);
    }

    /**
     * Clase interna que define un icono vacío para el botón de flecha del JComboBox
     * con el tema oscuro.
     */
    private static class DarkComboIcon implements javax.swing.Icon {

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            // Este espacio queda intencionalmente en blanco
        }

        @Override
        public int getIconWidth() {
            return 0;
        }

        @Override
        public int getIconHeight() {
            return 0;
        }
    }
}

/**
 * Clase abstracta que define estilos comunes para la interfaz de usuario.
 */
package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public abstract class Estilo {
    // Colores comunes
    public static final Color COLOR_FONT = new Color(200, 100, 50); // Color de fuente principal
    public static final Color COLOR_FONT_LIGHT = new Color(100, 100, 100); // Color de fuente secundario
    public static final Color COLOR_CURSOR = Color.black; // Color de cursor
    public static final Color COLOR_BORDER = Color.lightGray; // Color de borde
    // Fuentes comunes
    public static final Font FONT = new Font("JetBrains Mono", Font.PLAIN, 14); // Fuente regular
    public static final Font FONT_BOLD = new Font("JetBrains Mono", Font.BOLD | Font.PLAIN, 14); // Fuente negrita
    public static final Font FONT_SMALL = new Font("JetBrains Mono", Font.PLAIN | Font.PLAIN, 10); // Fuente pequeña

    // Alineaciones comunes
    public static final int ALIGNMENT_LEFT = SwingConstants.LEFT; // Alineación izquierda
    public static final int ALIGNMENT_RIGHT = SwingConstants.RIGHT; // Alineación derecha
    public static final int ALIGNMENT_CENTER = SwingConstants.CENTER; // Alineación centrada

    // Cursores comunes
    public static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR); // Cursor de mano
    public static final Cursor CURSOR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR); // Cursor predeterminado

    // URLs de recursos comunes
    public static final URL URL_LOGO = Thread.currentThread().getContextClassLoader()
            .getResource("UserInterface/Resource/FondoMenuPrincipal.png"); // URL del logo
    public static final URL URL_SPLASH = Thread.currentThread().getContextClassLoader()
            .getResource("UserInterface/Resource/SplashLogo.png"); // URL de la imagen de bienvenida
    public static final URL URL_FONDO_ACCIONES = Thread.currentThread().getContextClassLoader()
            .getResource("UserInterface/Resource/FondoAcciones.png"); // URL del fondo de acciones

    /**
     * Crea un borde compuesto con un borde de línea y un borde vacío con márgenes.
     * 
     * @return El borde compuesto creado.
     */
    public static final CompoundBorder createBorderRect() {
        return BorderFactory.createCompoundBorder(new LineBorder(Color.lightGray),
                new EmptyBorder(5, 5, 5, 5));
    }

    /**
     * Muestra un mensaje de información en un cuadro de diálogo.
     * 
     * @param msg El mensaje a mostrar.
     */
    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Polibank", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de error en un cuadro de diálogo.
     * 
     * @param msg El mensaje de error a mostrar.
     */
    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "PoliBank", JOptionPane.OK_OPTION);
    }

    /**
     * Muestra un cuadro de confirmación con opciones "Sí" y "No" en un cuadro de
     * diálogo.
     * 
     * @param msg El mensaje de confirmación a mostrar.
     * @return true si el usuario elige "Sí", false si elige "No".
     */
    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "PoliBank",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
}

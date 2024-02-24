package UserInterface.CustomerControl;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PatTextBox extends JTextField {
    public PatTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(PatStyle.FONT_SMALL);
        setForeground(PatStyle.COLOR_FONT);
        setBorder(createBorderRect());
        setCaretColor(PatStyle.COLOR_CURSOR);
        setMargin(new Insets(10, 10, 10, 10));
        setOpaque(false);
        // setUI(new BasicTextFieldUI()); // Para deshabilitar el subrayado por defecto
        setColumns(10); // Ajustar el tamaño para 10 caracteres
    }

    private Border createBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(PatStyle.COLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5); // Margenes internos
        return new CompoundBorder(lineBorder, emptyBorder);
    }

    public Border createBorderLine() {
        int thickness = 1;
        return BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, thickness, 0),
                BorderFactory.createMatteBorder(0, 0, thickness, 0, PatStyle.COLOR_BORDER));
    }
}


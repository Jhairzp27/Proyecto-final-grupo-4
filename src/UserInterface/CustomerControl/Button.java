package UserInterface.CustomerControl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class Button extends JButton {
    private Color backgroundColor;
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public Button(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(34, 139, 34)); // Color de fondo por defecto
        setFont(new Font("MesloLGL Nerd Font", Font.BOLD, 12)); // Establecer la fuente
        setForeground(Color.WHITE); // Establecer el color del texto como blanco

        // Colores personalizados para el botón
        backgroundColor = new Color(0, 100, 0); // Cambiar a verde
        hoverBackgroundColor = new Color(200, 200, 200);
        pressedBackgroundColor = new Color(150, 150, 150);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(backgroundColor);
        }
        // Dibujar el fondo redondeado
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }

    // Establecer los colores de fondo personalizados
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public void setHoverBackgroundColor(Color color) {
        hoverBackgroundColor = color;
    }

    public void setPressedBackgroundColor(Color color) {
        pressedBackgroundColor = color;
    }
}

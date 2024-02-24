package UserInterface.CustomerControl;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class PatButton  extends JButton implements MouseListener {
    Font font = new Font ("MesloLGL Nerd Font", Font.CENTER_BASELINE | Font.LAYOUT_LEFT_TO_RIGHT, 12);
    public PatButton(String label){
        super(label);
        goStyleLabel();
    }
    public PatButton(String label, String iconPath){
        goStyleMenu(label, iconPath);
    }

    public void goStyleMenu(String label, String iconPath){ 
        addMouseListener(this);
        setText(label);
        setSize(20, 70);
        setBounds(50, 30, 90, 20); 
        
        setOpaque(false);
        setForeground(Color.GRAY);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setIcon(new ImageIcon(iconPath));
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(font);
    }
    public void goStyleLabel() {
        setOpaque(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.darkGray);
        setHorizontalAlignment(SwingConstants.CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(font);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setForeground(Color.BLACK);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setForeground(Color.GRAY);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}

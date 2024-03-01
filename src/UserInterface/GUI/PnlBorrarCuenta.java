package UserInterface.GUI;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;

public class PnlBorrarCuenta extends JPanel implements ActionListener {
    private UsuarioBL usuarioBL = new UsuarioBL();
    private UsuarioDTO usuarioDTO = null;
    private PnlLogin pnlLogin;
    private Image backgroundImage;

    /**
     * Constructor de la clase PnlBorrarCuenta.
     *
     * @param usuarioDTO DTO del usuario actual.
     * @param pnlLogin   Panel de inicio de sesión para regresar después de borrar
     *                   la cuenta.
     */

    public PnlBorrarCuenta(UsuarioDTO usuarioDTO, PnlLogin pnlLogin) {
        this.usuarioDTO = usuarioDTO;
        this.pnlLogin = pnlLogin;
        customizeComponent();
        btnBorrar.addActionListener(this);
        try {
            backgroundImage = ImageIO.read(new File("src\\UserInterface\\Resource\\FondoAcciones.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para manejar eventos de acción, como clics de botón.
     *
     * @param e Evento de acción generado.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBorrar) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas borrar la cuenta?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    if (usuarioBL.eliminar(usuarioDTO.getIdUsuario())) {
                        JOptionPane.showMessageDialog(this, "Cuenta borrada con éxito");
                        pnlLogin.setVisible(true);
                        SwingUtilities.getWindowAncestor(this).dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al borrar la cuenta");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    /************************
     * FormDesign
     ************************/

    private JButton btnBorrar = new JButton("Borrar Cuenta");

    /**
     * Método para personalizar los componentes del panel.
     */
    private void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(btnBorrar, gbc);
    }

     /**
     * Método para pintar el fondo del panel con una imagen.
     *
     * @param g Objeto Graphics utilizado para dibujar.
     */
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

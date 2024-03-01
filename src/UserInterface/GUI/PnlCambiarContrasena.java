package UserInterface.GUI;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;

public class PnlCambiarContrasena extends JPanel implements ActionListener {
    private UsuarioBL usuarioBL = new UsuarioBL();
    private UsuarioDTO usuarioDTO = null;
    private Image backgroundImage;

    /**
     * Constructor para inicializar el panel de cambio de contraseña.
     * 
     * @param usuarioDTO El objeto UsuarioDTO que contiene la información del
     *                   usuario.
     */
    public PnlCambiarContrasena(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
        customizeComponent();
        btnCambiar.addActionListener(this);
        try {
            backgroundImage = ImageIO.read(new File("src\\UserInterface\\Resource\\FondoAcciones.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja las acciones realizadas en la interfaz, como hacer clic en el botón
     * "Cambiar".
     * 
     * @param e El evento de acción que desencadena este método.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCambiar) {
            try {
                String nuevaContrasena = new String(txtNuevaContrasena.getText());
                if (!nuevaContrasena.isEmpty()) {
                    usuarioDTO.setClave(nuevaContrasena);
                    if (usuarioBL.actualizar(usuarioDTO))
                        JOptionPane.showMessageDialog(this, "Contraseña cambiada con éxito");
                    else
                        JOptionPane.showMessageDialog(this, "Error al cambiar la contraseña");
                } else
                    JOptionPane.showMessageDialog(this, "Por favor ingresa una nueva contraseña válida");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Personaliza el diseño del panel con etiquetas, campos de texto y botones.
     */
    private void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(lblNuevaContrasena, gbc);

        gbc.gridx = 1;
        add(txtNuevaContrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCambiar, gbc);
    }

    /**
     * Sobrescribe el método paintComponent de JPanel para dibujar la imagen de
     * fondo en el panel.
     * 
     * @param g El objeto Graphics utilizado para dibujar la imagen de fondo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Componentes de la interfaz gráfica
    private Label lblNuevaContrasena = new Label("Nueva contraseña:");
    private TextField txtNuevaContrasena = new TextField(10);
    private Button btnCambiar = new Button("Cambiar");
}

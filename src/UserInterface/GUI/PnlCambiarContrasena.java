package UserInterface.GUI;

import javax.swing.*;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlCambiarContrasena extends JPanel implements ActionListener {
    private UsuarioBL usuarioBL = new UsuarioBL();
    private UsuarioDTO usuarioDTO = null;

    public PnlCambiarContrasena(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
        customizeComponent();
        btnCambiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCambiar) {
            try {
                String nuevaContrasena = new String(txtNuevaContrasena.getText());
                if (usuarioDTO != null) {
                    usuarioDTO.setClave(nuevaContrasena);
                    if (usuarioBL.actualizar(usuarioDTO))
                        JOptionPane.showMessageDialog(this, "Contraseña cambiada con éxito");
                    else
                        JOptionPane.showMessageDialog(this, "Error al cambiar la contraseña");
                    }
                else
                    JOptionPane.showMessageDialog(this, "Usuario no encontrado");
                }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /************************
     * FormDesign
     ************************/

    private Label lblNuevaContrasena = new Label("Nueva contraseña:");
    private TextField txtNuevaContrasena = new TextField(10);
    private Button btnCambiar = new Button("Cambiar");

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
}

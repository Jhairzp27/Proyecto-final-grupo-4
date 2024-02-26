package UserInterface.GUI;

import javax.swing.*;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlBorrarCuenta extends JPanel implements ActionListener {
    private UsuarioBL usuarioBL = new UsuarioBL();
    private UsuarioDTO usuarioDTO = null;
    private PnlLogin pnlLogin;

    public PnlBorrarCuenta(UsuarioDTO usuarioDTO, PnlLogin pnlLogin) {
        this.usuarioDTO = usuarioDTO;
        this.pnlLogin = pnlLogin;
        customizeComponent();
        btnBorrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBorrar) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas borrar la cuenta?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    if (usuarioDTO != null) {
                        usuarioDTO.setEstado("X");
                        if (usuarioBL.actualizar(usuarioDTO)) {
                            JOptionPane.showMessageDialog(this, "Cuenta borrada con éxito");
                            pnlLogin.setVisible(true);
                            SwingUtilities.getWindowAncestor(this).dispose();
                        } else
                            JOptionPane.showMessageDialog(this, "Error al borrar la cuenta");
                    } else
                        JOptionPane.showMessageDialog(this, "Usuario no encontrado");
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
}
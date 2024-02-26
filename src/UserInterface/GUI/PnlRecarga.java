package UserInterface.GUI;

import javax.swing.*;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlRecarga extends JPanel implements ActionListener {
    private UsuarioBL usuarioBL   = new UsuarioBL();
    private UsuarioDTO usuarioDTO = null;
    private PnlMenu pnlMenu       = null;

    public PnlRecarga(UsuarioDTO usuarioDTO, PnlMenu pnlMenu) {
        this.usuarioDTO = usuarioDTO;
        this.pnlMenu    = pnlMenu;
        customizeComponent();
        btnRecargar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRecargar) {
            try {
                float montoRecarga = Float.parseFloat(txtRecarga.getText());
                if (usuarioDTO != null) {
                    float saldoActual = usuarioDTO.getSaldo(),
                          saldoNuevo = saldoActual + montoRecarga;
                    usuarioDTO.setSaldo(saldoNuevo);
                    if (usuarioBL.actualizar(usuarioDTO)) {
                        JOptionPane.showMessageDialog(this, "Recarga realizada con éxito. Nuevo saldo: $" + usuarioDTO.getSaldo());
                        pnlMenu.actualizarSaldo(usuarioDTO.getSaldo());
                    } else
                        JOptionPane.showMessageDialog(this, "Error al realizar la recarga");
                } else
                    JOptionPane.showMessageDialog(this, "Usuario no encontrado");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa valores válidos para el ID de usuario y el monto de recarga");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    /************************
     * FormDesing
     ************************/

    private Label lblRecarga = new Label("Monto de recarga ($):");
    private TextField txtRecarga = new TextField(10);
    private Button btnRecargar = new Button("Recargar");

    private void customizeComponent() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(lblRecarga, gbc);

        gbc.gridx = 1;
        add(txtRecarga, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnRecargar, gbc);
    }
}

package UserInterface.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;

import BusinessLogic.TransferenciaBL;
import DataAccess.DTO.UsuarioDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PnlRecarga extends JPanel implements ActionListener {
    private TransferenciaBL transferenciaBL = new TransferenciaBL();
    private UsuarioDTO usuarioDTO = null;
    private PnlMenu pnlMenu = null;
    private Image backgroundImage;

    public PnlRecarga(UsuarioDTO usuarioDTO, PnlMenu pnlMenu) {
        this.usuarioDTO = usuarioDTO;
        this.pnlMenu = pnlMenu;
        customizeComponent();
        btnRecargar.addActionListener(this);
        try {
            backgroundImage = ImageIO.read(new File("src\\UserInterface\\Resource\\FondoAcciones.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRecargar) {
            try {
                float montoRecarga = Float.parseFloat(txtRecarga.getText());
                if (transferenciaBL.recargar(usuarioDTO, montoRecarga)) {
                    pnlMenu.actualizarSaldo(usuarioDTO.getSaldo());
                    JOptionPane.showMessageDialog(this, "Recarga realizada con éxito. Nuevo saldo: $" + usuarioDTO.getSaldo());
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese un monto válido");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese solo números");
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

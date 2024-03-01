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

import BusinessLogic.TransferenciaBL;
import DataAccess.DTO.UsuarioDTO;

public class PnlRecarga extends JPanel implements ActionListener {
    // Atributos de la clase
    private TransferenciaBL transferenciaBL = new TransferenciaBL();
    private UsuarioDTO usuarioDTO = null;
    private PnlMenu pnlMenu = null;
    private Image backgroundImage;

    /**
     * Constructor de la clase PnlRecarga.
     * 
     * @param usuarioDTO El objeto UsuarioDTO que representa al usuario actual.
     * @param pnlMenu    El panel de menú al que se actualiza el saldo después de la recarga.
     */
    public PnlRecarga(UsuarioDTO usuarioDTO, PnlMenu pnlMenu) {
        this.usuarioDTO = usuarioDTO;
        this.pnlMenu = pnlMenu;
        customizeComponent(); // Personaliza y configura los componentes del panel
        btnRecargar.addActionListener(this); // Registra el botón de recarga para escuchar eventos de clic

        try {
            // Carga la imagen de fondo del panel
            backgroundImage = ImageIO.read(new File("src\\UserInterface\\Resource\\FondoAcciones.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Maneja las acciones de los botones.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRecargar) { // Si se hizo clic en el botón de recargar
            try {
                // Obtiene el monto de recarga ingresado por el usuario
                float montoRecarga = Float.parseFloat(txtRecarga.getText());
                // Realiza la recarga y actualiza el saldo en el panel de menú
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

    // Componentes de diseño del formulario
    private Label lblRecarga = new Label("Monto de recarga ($):");
    private TextField txtRecarga = new TextField(10);
    private Button btnRecargar = new Button("Recargar");

    /**
     * Personaliza y configura los componentes del panel.
     */
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

    /**
     * Pinta el componente gráfico, incluyendo una imagen de fondo si está disponible.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

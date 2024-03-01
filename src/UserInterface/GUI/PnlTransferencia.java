/**
 * Panel de transferencia que permite al usuario realizar transferencias de dinero a otros usuarios.
 */
package UserInterface.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import BusinessLogic.TransferenciaBL;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.Button;
import UserInterface.CustomerControl.Estilo;
import UserInterface.CustomerControl.Label;
import UserInterface.CustomerControl.TextBox;

public class PnlTransferencia extends JPanel implements ActionListener {
    // Variables de clase
    @SuppressWarnings("unused")
    private Integer idUsuarioRecibe, idMaxUsuario, nroPagina = 1, totalPaginas;
    private TransferenciaBL transferenciaBL = new TransferenciaBL();
    private UsuarioDTO usuarioDTOLogeado = null;
    private ArrayList<UsuarioDTO> usuariosDTOReciben = null;
    private UsuarioBL usuarioBL = new UsuarioBL();
    private PnlMenu pnlMenu = null;
    private Image backgroundImage;

    /**
     * Constructor del panel de transferencia.
     * 
     * @param usuarioDTOLogeado El usuario que ha iniciado sesión.
     * @param pnlMenu           El panel del menú principal.
     */
    public PnlTransferencia(UsuarioDTO usuarioDTOLogeado, PnlMenu pnlMenu) {
        this.usuarioDTOLogeado = usuarioDTOLogeado;
        this.pnlMenu = pnlMenu;
        customerSizeControl();

        try {
            cargarDatos();
            mostrarDatos();
            mostrarTabla();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar recursos");
        }

        // Asignación de eventos a los botones
        btnIni.addActionListener(this);
        btnAnt.addActionListener(this);
        btnSig.addActionListener(this);
        btnFin.addActionListener(this);
        btnTransferencia.addActionListener(this);
        try {
            // Carga de imagen de fondo
            backgroundImage = ImageIO.read(new File("src\\UserInterface\\Resource\\FondoAcciones.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos privados

    /**
     * Carga los datos necesarios para el panel.
     * 
     * @throws Exception Si hay un error durante la carga de datos.
     */
    private void cargarDatos() throws Exception {
        idUsuarioRecibe = 1;
        usuarioBL = new UsuarioBL();
        usuariosDTOReciben = usuarioBL.leerSinUsuarioActual(usuarioDTOLogeado.getIdUsuario());
        idMaxUsuario = usuariosDTOReciben.size();
    }

    /**
     * Muestra la información sobre la paginación.
     */
    private void mostrarDatos() {
        totalPaginas = (idMaxUsuario - 1) / 10 + 1;
        lblTotalReg.setText("Página " + nroPagina + " de " + totalPaginas);
    }

    /**
     * Muestra la tabla de usuarios para realizar la transferencia.
     * 
     * @throws Exception Si hay un error al mostrar la tabla.
     */
    private void mostrarTabla() throws Exception {
        int tamanoPagina = 10,
                startIndex = ((nroPagina - 1) * tamanoPagina) + 1,
                endIndex = startIndex + 9;

        String[] encabezado = { "Id", "Nombre" };
        Object[][] data = new Object[tamanoPagina][2];

        int index = 0;
        for(int i = startIndex; i <= endIndex; i++) {
            try {
                UsuarioDTO u = usuariosDTOReciben.get(i - 1);
                data[index][0] = u.getIdUsuario();
                data[index][1] = u.getNombre();
                index++;
            } catch(Exception e) {
                break;
            }
        }

        JTable table = new JTable(data, encabezado);
        table.setShowHorizontalLines(true);
        table.getTableHeader().setBackground(Estilo.COLOR_BORDER);
        table.setGridColor(Estilo.COLOR_BORDER);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(Label.CENTER);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(Label.CENTER);

        table.setPreferredScrollableViewportSize(new Dimension(170, 160));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        pnlTabla.removeAll();
        pnlTabla.add(scrollPane);
        pnlTabla.revalidate();
        pnlTabla.repaint();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    if (!e.getValueIsAdjusting()) {
                        int row = table.getSelectedRow();
                        if (row != -1) {
                            Object idUsuarioSeleccionado = table.getValueAt(row, 0);
                            txtIdUsuario.setText(idUsuarioSeleccionado.toString());
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }});

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIni)
            nroPagina = 1;
        if (e.getSource() == btnAnt && (nroPagina > 1))
            nroPagina--;
        if (e.getSource() == btnSig && (nroPagina < totalPaginas))
            nroPagina++;
        if (e.getSource() == btnFin)
            nroPagina = totalPaginas;
        if (e.getSource() == btnTransferencia) {
            try {
                String strMonto = txtMonto.getText();
                String strIdUsuarioRecibe = txtIdUsuario.getText();

                if (!strMonto.isEmpty() && !strIdUsuarioRecibe.isEmpty()) {
                    if (transferenciaBL.esNumeroFloatPositivo(strMonto) && transferenciaBL.esNumeroEntero(strIdUsuarioRecibe)) {
                        float monto = Float.parseFloat(strMonto);
                        int idUsuarioRecibe = Integer.parseInt(strIdUsuarioRecibe);

                        boolean transferenciaExitosa = transferenciaBL.transferirDinero(usuarioDTOLogeado, idUsuarioRecibe, monto);

                        if (transferenciaExitosa) {
                            JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito");
                            pnlMenu.actualizarSaldo(usuarioDTOLogeado.getSaldo());
                        } else
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                    } else 
                        JOptionPane.showMessageDialog(null, "Monto o ID de usuario receptor inválidos");
                } else
                    JOptionPane.showMessageDialog(null, "Por favor ingresa el monto y el ID del usuario receptor");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al transferir");
            }
        }

        try {
                cargarDatos();
                mostrarDatos();
                mostrarTabla();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Diseño del formulario

    private Label lblTitulo = new Label("TRANSFERENCIAS"),
            lblIdUsuario = new Label("Id Usuario Seleccionado: "),
            lblMonto = new Label("Monto ($): "),
            lblTotalReg = new Label("  0 de 0  ");
    private TextBox txtIdUsuario = new TextBox(),
            txtMonto = new TextBox();
    private Button btnIni = new Button(" |< "),
            btnAnt = new Button(" << "),
            btnSig = new Button(" >> "),
            btnFin = new Button(" >| "),
            btnTransferencia = new Button("Transferir");
    private JPanel pnlTabla = new JPanel(),
            pnlBtnTransferencia = new JPanel(new FlowLayout()),
            pnlBtnPagina = new JPanel(new FlowLayout());

    /**
     * Personalización del diseño del formulario.
     */
    public void customerSizeControl() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel.Paginacion.Tabla
        pnlBtnPagina.add(btnIni);
        pnlBtnPagina.add(btnAnt);
        pnlBtnPagina.add(lblTotalReg);
        pnlBtnPagina.add(btnSig);
        pnlBtnPagina.add(btnFin);

        // Panel.Transferencia
        pnlBtnTransferencia.add(btnTransferencia);

        // Restricciones generales
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titulo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(lblTitulo, gbc);

        // Separador
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new Label("■ Lista de usuarios: "), gbc);

        // Sección de datos (Tabla)
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnlTabla, gbc);

        // Sección de paginación
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnPagina, gbc);

        // Separador
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(new Label("■ Sección de elección: "), gbc);

        // IdUsuario
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(lblIdUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtIdUsuario, gbc);

        // Nombre
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(lblMonto, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(txtMonto, gbc);

        // Sección de botón de transferencia
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(30, 0, 0, 0);
        add(pnlBtnTransferencia, gbc);
    }
}

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import BusinessLogic.EstadoCuentaBL;
import DataAccess.DTO.EstadoCuentaDTO;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.Button;
import UserInterface.CustomerControl.Estilo;
import UserInterface.CustomerControl.Label;

public class PnlEstadoCuenta extends JPanel implements ActionListener {
    @SuppressWarnings("unused")
    private Integer idMovimiento, idMaxMovimiento,
            nroPagina = 1, totalPaginas;
    private UsuarioDTO usuarioDTOLogeado = null;
    ArrayList<EstadoCuentaDTO> listaEstadoCuentaDTO = null;
    private EstadoCuentaBL estadoCuentaBL = null;
    private Image backgroundImage;
    JTable tabla = null;

    public PnlEstadoCuenta(UsuarioDTO usuarioDTOLogeado) {
        this.usuarioDTOLogeado = usuarioDTOLogeado;
        customerSizeControl();

        try {
            cargarDatos();
            mostrarDatos();
            mostrarTabla();
        } catch (Exception e) {
        }

        btnIni.addActionListener(this);
        btnAnt.addActionListener(this);
        btnSig.addActionListener(this);
        btnFin.addActionListener(this);

        try {
            backgroundImage = ImageIO.read(new File("src\\UserInterface\\Resource\\FondoAcciones.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos() throws Exception {
        idMovimiento = 1;
        estadoCuentaBL = new EstadoCuentaBL();
        listaEstadoCuentaDTO = estadoCuentaBL.leerPorUsuarioActual(usuarioDTOLogeado.getIdUsuario());
        idMaxMovimiento = listaEstadoCuentaDTO.size();
    }

    private void mostrarDatos() {
        totalPaginas = (idMaxMovimiento - 1) / 10 + 1;
        lblTotalReg.setText("Página " + nroPagina + " de " + totalPaginas);
    }

    private void mostrarTabla() throws Exception {
        int tamanoPagina = 10,
                startIndex = ((nroPagina - 1) * tamanoPagina) + 1,
                endIndex = startIndex + 9;

        String[] encabezado = { "Fecha", "Id Movimiento", "Descripción", "Monto ($)", "Saldo ($)" };
        Object[][] data = new Object[tamanoPagina][5];
        int index = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            try {
                EstadoCuentaDTO ec = listaEstadoCuentaDTO.get(i - 1);
                data[index][0] = ec.getFecha();
                data[index][1] = ec.getIdMovimiento();
                data[index][2] = ec.getDescripcion();
                data[index][3] = ec.getMonto();
                data[index][4] = ec.getSaldo();
                index++;
            } catch (Exception e) {
                break;
            }
        }

        tabla = new JTable(data, encabezado);
        tabla.setShowHorizontalLines(true);
        tabla.getTableHeader().setBackground(Estilo.COLOR_BORDER);
        tabla.setGridColor(Estilo.COLOR_BORDER);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);
        for (int i = 0; i < tabla.getColumnCount(); i++)
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tabla.setPreferredScrollableViewportSize(new Dimension(530, 160));
        tabla.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tabla);

        pnlTabla.removeAll();
        pnlTabla.add(scrollPane);
        pnlTabla.revalidate();
        pnlTabla.repaint();
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

        try {
            cargarDatos();
            mostrarDatos();
            mostrarTabla();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /********************************
     * FormDesing
     ********************************/
    private Label lblTitulo = new Label("ESTADO DE CUENTA"),
            lblTotalReg = new Label("  0 de 0  ");
    private Button btnIni = new Button(" |< "),
            btnAnt = new Button(" << "),
            btnSig = new Button(" >> "),
            btnFin = new Button(" >| ");
    private JPanel pnlTabla = new JPanel(),
            pnlBtnPagina = new JPanel(new FlowLayout());

    /************************
     * Customize : Form
     ************************/
    public void customerSizeControl() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel.Paginacion.Tabla
        pnlBtnPagina.add(btnIni);
        pnlBtnPagina.add(btnAnt);
        pnlBtnPagina.add(lblTotalReg);
        pnlBtnPagina.add(btnSig);
        pnlBtnPagina.add(btnFin);

        // Restricciones generales
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Titulo
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        add(lblTitulo, gbc);

        // Sección de datos (Tabla)
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(pnlTabla, gbc);

        // Sección de paginación
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(pnlBtnPagina, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

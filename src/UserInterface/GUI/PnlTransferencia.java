package UserInterface.GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import BusinessLogic.TransferenciaBL;
import BusinessLogic.UsuarioBL;
import DataAccess.DTO.TransferenciaDTO;
import DataAccess.DTO.UsuarioDTO;
import UserInterface.CustomerControl.Button;
import UserInterface.CustomerControl.Estilo;
import UserInterface.CustomerControl.Label;
import UserInterface.CustomerControl.TextBox;

public class PnlTransferencia extends JPanel implements ActionListener {
    private Integer idUsuarioRecibe, idMaxUsuario, 
                    nroPagina = 1, totalPaginas;
    private UsuarioDTO usuarioDTOLogeado      = null;
    ArrayList<UsuarioDTO> usuariosDTOReciben  = null;
    private UsuarioBL  usuarioBL              = null;

    public PnlTransferencia(UsuarioDTO usuarioDTOLogeado) {
        this.usuarioDTOLogeado = usuarioDTOLogeado;
        customerSizeControl();
        
        try {
            cargarDatos();
            mostrarDatos();
            mostrarTabla();
        } catch(Exception e) {}

        btnIni.addActionListener(this);
        btnAnt.addActionListener(this);
        btnSig.addActionListener(this);
        btnFin.addActionListener(this);
        btnTransferencia.addActionListener(this);
    }
    
    private void cargarDatos() throws Exception {
        idUsuarioRecibe        = 1;
        usuarioBL              = new UsuarioBL();
        usuariosDTOReciben     = usuarioBL.leerSinUsuarioActual(usuarioDTOLogeado.getIdUsuario());
        idMaxUsuario           = usuariosDTOReciben.size();
    }

    private void mostrarDatos() {
        totalPaginas  = (idMaxUsuario - 1) / 10 + 1;
        lblTotalReg.setText("Página " + nroPagina + " de " + totalPaginas);
    }

    private void mostrarTabla() throws Exception {
        int tamanoPagina = 10,
            startIndex = ((nroPagina - 1) * tamanoPagina) + 1,
            endIndex = startIndex + 9;
        
        String[] encabezado = {"Id", "Nombre"};
        Object[][] data = new Object[endIndex - startIndex + 1][2];  
    
        ArrayList<UsuarioDTO> usuarios = usuarioBL.leerSinUsuarioActual(usuarioDTOLogeado.getIdUsuario());
    
        int index = 0;
        for(int i = startIndex; i <= endIndex; i++) {
            if (index < usuarios.size()) {
                UsuarioDTO u = usuarios.get(index);
                data[index][0] = u.getIdUsuario();
                data[index][1] = u.getNombre();
            } else {
                data[index][0] = "";
                data[index][1] = "";
            }
            index++;
        }
    
        JTable table  = new JTable(data, encabezado);
        table.setShowHorizontalLines(true);
        table.getTableHeader().setBackground(Estilo.COLOR_BORDER);
        table.setGridColor(Estilo.COLOR_BORDER);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        for (int i = 0; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        table.setPreferredScrollableViewportSize(new Dimension(170, 160));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        
        pnlTabla.removeAll();
        pnlTabla.add(scrollPane);
        pnlTabla.revalidate();
        pnlTabla.repaint();
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Object idUsuarioSeleccionado = table.getValueAt(row, 0);
                        txtIdUsuario.setText(idUsuarioSeleccionado.toString());
                    }
                }
            }
        });
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
                transferirDinero();
            } catch (Exception ex) {
                ex.printStackTrace();
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

    private void transferirDinero() throws Exception {
        String strMonto = txtMonto.getText();
        String strIdUsuarioRecibe = txtIdUsuario.getText();

        if (!strMonto.isEmpty() && !strIdUsuarioRecibe.isEmpty()) {
            float monto = Float.parseFloat(strMonto);
            idUsuarioRecibe = Integer.parseInt(strIdUsuarioRecibe);

            UsuarioDTO usuarioRecibe = usuarioBL.leerPorId(idUsuarioRecibe);

            if (usuarioRecibe != null) {
                float saldoActualUsuarioLogeado = usuarioDTOLogeado.getSaldo();

                if (saldoActualUsuarioLogeado >= monto) {
                    float nuevoSaldoUsuarioLogeado = saldoActualUsuarioLogeado - monto;
                    float saldoActualUsuarioRecibe = usuarioRecibe.getSaldo();
                    float nuevoSaldoUsuarioRecibe = saldoActualUsuarioRecibe + monto;

                    usuarioDTOLogeado.setSaldo(nuevoSaldoUsuarioLogeado);
                    usuarioRecibe.setSaldo(nuevoSaldoUsuarioRecibe);

                    if (usuarioBL.actualizar(usuarioDTOLogeado) && usuarioBL.actualizar(usuarioRecibe)) {
                        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
                        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime actual = LocalDateTime.now();
                        transferenciaDTO.setIdUsuarioEnvia(usuarioDTOLogeado.getIdUsuario());
                        transferenciaDTO.setIdUsuarioRecibe(idUsuarioRecibe);
                        transferenciaDTO.setMonto(monto);
                        transferenciaDTO.setFecha(formatoFecha.format(actual).toString());

                        TransferenciaBL transferenciaBL = new TransferenciaBL();
                        if (transferenciaBL.crear(transferenciaDTO))
                            JOptionPane.showMessageDialog(this, "Transferencia realizada con éxito");
                        else
                            JOptionPane.showMessageDialog(this, "Error al realizar la transferencia");
                    } else
                        JOptionPane.showMessageDialog(this, "Error al actualizar los saldos de los usuarios");
                } else
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente para realizar la transferencia");
            } else
                JOptionPane.showMessageDialog(this, "El usuario receptor no existe");
        } else
            JOptionPane.showMessageDialog(this, "Por favor ingresa el monto y el ID del usuario receptor");
    }

    
/********************************
 * FormDesing
 ********************************/ 
    private Label 
            lblTitulo    = new Label("TRANSFERENCIAS"),
            lblIdUsuario = new Label("Id Usuario Seleccionado: "),
            lblMonto    = new Label("Monto ($): "),
            lblTotalReg  = new Label("  0 de 0  ");
    private TextBox  
            txtIdUsuario = new TextBox (),
            txtMonto    = new TextBox ();
    private Button  
            btnIni     = new Button(" |< "), 
            btnAnt     = new Button(" << "),            
            btnSig     = new Button(" >> "),
            btnFin     = new Button(" >| "),
            btnTransferencia   = new Button("Transferir");            
    private JPanel 
            pnlTabla            = new JPanel(),
            pnlBtnTransferencia = new JPanel(new FlowLayout()),
            pnlBtnPagina        = new JPanel(new FlowLayout());
    
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
        add(new JLabel("■ Lista de usuarios: "), gbc);

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
        add(new JLabel("■ Sección de elección: "), gbc);

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

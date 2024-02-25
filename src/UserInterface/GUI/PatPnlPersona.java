package UserInterface.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import PersonaBL;
import BusinessLogic.*;
import DataAcces.DTO.PersonaDTO;
import UserInterface.CustomerControl.PatButton;
import UserInterface.CustomerControl.PatLabel;
import UserInterface.CustomerControl.PatTextBox;

public class PatPnlPersona extends JPanel implements ActionListener{
    private Integer idPersona, idMaxPersona;
    private PersonaBL  personaBL = null;
    private PersonaDTO persona   = null;
    private JTable table;

    public PatPnlPersona() throws Exception{
        customerSizeControl();
        loadData();
        showData();
        showTable();

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {   
                try {
                    btnActualizarClick(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {   try {
                    btnEliminarClick(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }   
            }
        });
    }
    
    private void loadData() throws Exception {
        idPersona      = 1;
        personaBL      = new PersonaBL();
        persona        = personaBL.leer(idPersona);
        idMaxPersona   = personaBL.leerTodo().size();
    }

    private void showData() {
        int currentPage = (idPersona - 1) / 10 + 1,
            totalPages  = (idMaxPersona - 1) / 10 + 1;
        lblTotalReg.setText("Página " + currentPage + " de " + totalPages);
    }

    private void btnEliminarClick(ActionEvent e) throws Exception {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione al usuario que desea eliminar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        if (JOptionPane.showConfirmDialog(this, "¿Está seguro que desea Eliminar?", "Eliminar...",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    
            if (!personaBL.eliminar(persona.getIdPersona()))
                JOptionPane.showMessageDialog(this, "Error al eliminar...!",
                        "ERROR", JOptionPane.OK_OPTION);
    
            loadData();
            showData();
            showTable();
        }
    }

    // Nueva función para verificar campos en blanco
    private boolean areFieldsEmpty() {
        return txtPersona.getText().trim().isEmpty() ||
               txtSexo.getText().trim().isEmpty() ||
               txtRol.getText().trim().isEmpty() ||
               txtNombre.getText().trim().isEmpty() ||
               txtApellido.getText().trim().isEmpty() ||
               txtCedula.getText().trim().isEmpty() ||
               txtFechaNacimiento.getText().trim().isEmpty();
    }

    // Método para limpiar los campos
    private void clearFields() {
        txtPersona.setText("");
        txtSexo.setText("");
        txtRol.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtFechaNacimiento.setText("");
    }

    
    private void showTable() throws Exception {
        String[] header = {"Id","Sexo","Rol","Nombre","Apellido","Cedula", "Fecha de Nacimiento","Estado"};
        Object[][] data = new Object[personaBL.leerTodo().size()][8];  
        int index = 0;
        for(PersonaDTO s : personaBL.leerTodo()) {
            data[index][0] = s.getIdPersona();
            data[index][1] = s.getIdSexo();
            data[index][2] = s.getIdRol();
            data[index][3] = s.getPersonaNombre();
            data[index][4] = s.getPersonaApellido();
            data[index][5] = s.getPersonaCedula();
            data[index][6] = s.getPersonaFechaNacimiento();
            data[index][7] = s.getEstado();
            index++;
        }

        table  = new JTable(data, header);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.lightGray);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        table.setPreferredScrollableViewportSize(new Dimension(600, 250));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        // Crear el botón
        JButton btnDejarDeSeleccionar = new JButton("Dejar de Seleccionar");
        btnDejarDeSeleccionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.clearSelection();
                persona = null;
            }
        });
    
        // Agregar el botón debajo de la tabla
        pnlTabla.removeAll();
        pnlTabla.setLayout(new BorderLayout());
        pnlTabla.add(scrollPane, BorderLayout.CENTER);
        pnlTabla.add(btnDejarDeSeleccionar, BorderLayout.SOUTH);
        pnlTabla.revalidate();
        pnlTabla.repaint();
    
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int col = 0;
                int row = table.getSelectedRow();
    
                if (row == -1) {
                    // No se ha seleccionado ninguna fila, reiniciar la variable
                    clearFields();
                    return;
                } else {
                    String strIdPersona = table.getModel().getValueAt(row, col).toString();
                    idPersona = Integer.parseInt(strIdPersona);
    
                    try {
                        persona = personaBL.leer(idPersona);
                        // Mostrar datos en campos de texto
                        showDataInFields(persona);
                        showData();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Tabla.Selected: " + strIdPersona);
                }
            }
        });
    }

    private void showDataInFields(PersonaDTO persona) {
        txtPersona.setText(String.valueOf(persona.getIdPersona()));
        txtSexo.setText(String.valueOf(persona.getIdSexo()));
        txtRol.setText(String.valueOf(persona.getIdRol()));
        txtNombre.setText(persona.getPersonaNombre());
        txtApellido.setText(persona.getPersonaApellido());
        txtCedula.setText(persona.getPersonaCedula());
        txtFechaNacimiento.setText(persona.getPersonaFechaNacimiento());
    }
    
    private boolean areFieldsEqual(Object oldValue, Object newValue) {
        if (oldValue == null && newValue == null) {
            return true;
        } else if (oldValue == null || newValue == null) {
            return false;
        } else {
            return oldValue.equals(newValue);
        }
    }

    private void btnActualizarClick(ActionEvent e) throws HeadlessException, Exception {
        boolean personaNull = (persona == null);
    
        if (JOptionPane.showConfirmDialog(this, "¿Seguro que desea actualizar?", (personaNull) ? "Agregar..." : "Actualizar...",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    
            try {
                if (personaNull) {
                    persona = new PersonaDTO();
                }
    
                // Verificar cambios antes de actualizar
                if (!areFieldsEqual(persona.getIdPersona(), Integer.parseInt(txtPersona.getText())) ||
                    !areFieldsEqual(persona.getIdSexo(), Integer.parseInt(txtSexo.getText())) ||
                    !areFieldsEqual(persona.getIdRol(), Integer.parseInt(txtRol.getText())) ||
                    !areFieldsEqual(persona.getPersonaNombre(), txtNombre.getText()) ||
                    !areFieldsEqual(persona.getPersonaApellido(), txtApellido.getText()) ||
                    !areFieldsEqual(persona.getPersonaCedula(), txtCedula.getText()) ||
                    !areFieldsEqual(persona.getPersonaFechaNacimiento(), txtFechaNacimiento.getText())) {
    
                    // Validar campos en blanco
                    if (areFieldsEmpty()) {
                        JOptionPane.showMessageDialog(this, "No se permiten campos en blanco.",
                                "Advertencia", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
    
                    // Al menos un campo ha cambiado, proceder con la actualización
                    persona.setIdPersona(Integer.parseInt(txtPersona.getText()));
                    persona.setIdSexo(Integer.parseInt(txtSexo.getText()));
                    persona.setIdRol(Integer.parseInt(txtRol.getText()));
                    persona.setPersonaNombre(txtNombre.getText());
                    persona.setPersonaApellido(txtApellido.getText());
                    persona.setPersonaCedula(txtCedula.getText());
                    persona.setPersonaFechaNacimiento(txtFechaNacimiento.getText());
                    JOptionPane.showMessageDialog(this, "Se actualizó correctamente la información...!",
                                "ÉXITO", JOptionPane.INFORMATION_MESSAGE);

    
                    // Realizar la actualización
                    if (!personaBL.actualizar(persona.getIdPersona(), persona.getIdSexo(), persona.getIdRol(),
                            persona.getPersonaNombre(), persona.getPersonaApellido(), persona.getPersonaCedula(),
                            persona.getPersonaFechaNacimiento())) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar...!",
                                "ERROR", JOptionPane.OK_OPTION);
                        return;
                    }
    
                    loadData();
                    showData();
                    showTable();
                } else {
                    // No hay cambios
                    JOptionPane.showMessageDialog(this, "No se detectaron cambios en la información.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
    
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(),
                        "ERROR", JOptionPane.OK_OPTION);
            }
        }
    }

    /************************
     * FormDesing : pat_mic
     ************************/ 
    private PatLabel 
            lblTitulo          = new PatLabel("Usuarios"    ),
            lblPersona         = new PatLabel("Id"),
            lblSexo            = new PatLabel("Sexo: "),
            lblRol             = new PatLabel("Rol: "),
            lblNombre          = new PatLabel("Nombre: "),
            lblApellido        = new PatLabel("Apellido: "),
            lblCedula          = new PatLabel("Cedula: "),
            lblFechaNacimiento = new PatLabel("Fecha Nacimiento: "),
            lblTotalReg        = new PatLabel("  0 de 0  ");
    private PatTextBox  
            txtPersona          = new PatTextBox(),
            txtSexo             = new PatTextBox(),
            txtRol              = new PatTextBox(),
            txtNombre           = new PatTextBox(),
            txtApellido         = new PatTextBox(),
            txtCedula           = new PatTextBox(),
            txtFechaNacimiento  = new PatTextBox();
    private PatButton  
            btnIni     = new PatButton(" |< "), 
            btnAnt     = new PatButton(" << "),            
            btnSig     = new PatButton(" >> "),
            btnFin     = new PatButton(" >| ");
    private PatButton
            btnActualizar = new PatButton("Actualizar"),
            btnEliminar= new PatButton("Eliminar");
    private JPanel 
            pnlTabla   = new JPanel(),
            pnlBtnCRUD = new JPanel(new FlowLayout()),
            pnlBtnPage = new JPanel(new FlowLayout());
    private Border  
            line       = new LineBorder(Color.lightGray),
            margin     = new EmptyBorder(5, 5, 5, 5),
            border     = new CompoundBorder(line, margin);
    
    /************************
     * Customize : Form
     ************************/ 
    public void customerSizeControl() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel.Paginacion.Tabla
        pnlBtnPage.add(btnIni);       
        pnlBtnPage.add(btnAnt);  
        pnlBtnPage.add(lblTotalReg);        
        pnlBtnPage.add(btnSig);
        pnlBtnPage.add(btnFin);

        // Panel.CRUD
        // pnlBtnCRUD.add(btnNuevo);
        pnlBtnCRUD.add(btnActualizar);
        pnlBtnCRUD.add(btnEliminar);
        pnlBtnCRUD.setBorder(border);

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
        add(new JLabel("■ Sección de datos: "), gbc);

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
        add(pnlBtnPage, gbc);

        // Separador
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("■ Sección de registro: "), gbc);

        // Persona
        gbc.gridy = 5;
        gbc.gridx = 0;  
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblPersona, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;  // Alinear a la izquierda
        gbc.gridwidth = 2;
        add(txtPersona, gbc);

        // Sexo
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblSexo, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        add(txtSexo, gbc);

        // Rol
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblRol, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        add(txtRol, gbc);

        // Nombre
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        add(txtNombre, gbc);

        // Apellido
        gbc.gridy = 9;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblApellido, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        add(txtApellido, gbc);

        // Cedula
        gbc.gridy = 10;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblCedula, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        add(txtCedula, gbc);

        // Fecha de Nacimiento
        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        add(lblFechaNacimiento, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        add(txtFechaNacimiento, gbc);

        // Sección de botones CRUD
        gbc.gridy = 12;  // Incrementar el número de fila según sea necesario
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;  // Alinear al centro
        gbc.insets = new Insets(30, 0, 0, 0);
        add(pnlBtnCRUD, gbc);
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

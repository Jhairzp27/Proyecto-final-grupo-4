package UserInterface.GUI;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BusinessLogic.EstadoCuentaBL;
import DataAccess.DTO.EstadoCuentaDTO;
import DataAccess.DTO.UsuarioDTO;

public class PnlImpresion extends JPanel {
    private EstadoCuentaBL estadoCuentaBL = new EstadoCuentaBL();
    private UsuarioDTO usuarioDTOLogeado;

    public PnlImpresion(Boolean estadoCuentaVisto, UsuarioDTO usuarioDTOLogeado) {
        this.usuarioDTOLogeado = usuarioDTOLogeado;
        imprimirEstadoCuenta(estadoCuentaVisto);
    }

    private void imprimirEstadoCuenta(Boolean estadoCuentaVisto) {
        if(estadoCuentaVisto == false) {
            JOptionPane.showMessageDialog(this, "Debe revisar su estado de cuenta antes de imprimirlo");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que desea imprimir?",
                                                              "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                ArrayList<EstadoCuentaDTO> listaEstadoCuentaDTO = estadoCuentaBL.leerPorUsuarioActual
                                                                  (usuarioDTOLogeado.getIdUsuario());
                estadoCuentaBL.imprimir(listaEstadoCuentaDTO, usuarioDTOLogeado);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al imprimir");
            }
        }
    }
}

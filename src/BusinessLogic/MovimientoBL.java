package BusinessLogic;

import java.util.ArrayList;

import DataAccess.MovimientoDAO;
import DataAccess.DTO.MovimientoDTO;

/**
 * La clase `MovimientoBL` en Java contiene un método para leer los movimientos del usuario actualmente
 * conectado en función del ID de usuario proporcionado.
 */
public class MovimientoBL {
    private MovimientoDAO movimientoDAO = new MovimientoDAO();

    public MovimientoBL() {}

    /**
     * La función lee los movimientos del usuario actual según la identificación de usuario
     * proporcionada.
     * 
     * @param idUsuarioLogeado El parámetro `idUsuarioLogeado` representa el ID del usuario actualmente
     * conectado. Esta ID se utiliza para recuperar una lista de movimientos asociados con este
     * usuario.
     * @return Se está devolviendo un ArrayList de objetos MovimientoDTO.
     */
    public ArrayList<MovimientoDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return movimientoDAO.leerPorUsuarioActual(idUsuarioLogeado);
    }
}

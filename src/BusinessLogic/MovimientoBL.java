package BusinessLogic;

import java.util.ArrayList;

import DataAccess.MovimientoDAO;
import DataAccess.DTO.MovimientoDTO;

public class MovimientoBL {
    private MovimientoDAO movimientoDAO = new MovimientoDAO();

    public MovimientoBL() {}

    public ArrayList<MovimientoDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return movimientoDAO.leerPorUsuarioActual(idUsuarioLogeado);
    }
}

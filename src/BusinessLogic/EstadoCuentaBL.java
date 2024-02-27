package BusinessLogic;

import java.util.ArrayList;

import DataAccess.EstadoCuentaDAO;
import DataAccess.DTO.EstadoCuentaDTO;

public class EstadoCuentaBL {
    private EstadoCuentaDAO estadoCuentaDAO = new EstadoCuentaDAO();

    public EstadoCuentaBL() {}

    public ArrayList<EstadoCuentaDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return estadoCuentaDAO.leerPorUsuarioActual(idUsuarioLogeado);
    }
}

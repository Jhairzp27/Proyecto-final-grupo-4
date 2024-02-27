package BusinessLogic;

import java.util.List;

import DataAccess.SexoDAO;
import DataAccess.DTO.SexoDTO;

public class SexoBL {
    private SexoDTO sexoDTO;
    private SexoDAO sexoDAO = new SexoDAO();

    public SexoBL() {}
    
    public boolean crear(SexoDTO SexoDTO) throws Exception {
        return sexoDAO.crear(SexoDTO);
    }

    public List<SexoDTO> leerTodo() throws Exception {
        return sexoDAO.leerTodo();
    }

    public SexoDTO leerPor(Integer idSexo) throws Exception {
        sexoDTO = sexoDAO.leerPor(idSexo);
        return sexoDTO;
    }

    public boolean actualizar(SexoDTO SexoDTO) throws Exception {
        return sexoDAO.actualizar(SexoDTO);
    }

    public boolean eliminar(Integer idSexo) throws Exception {
        return sexoDAO.eliminar(idSexo);
    }
}

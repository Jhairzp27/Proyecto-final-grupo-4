package BusinessLogic;

import java.util.List;

import DataAccess.TransferenciaDAO;
import DataAccess.DTO.TransferenciaDTO;

public class TransferenciaBL {
    private TransferenciaDTO transferenciaDTO;
    private TransferenciaDAO transferenciaDAO = new TransferenciaDAO();

    public TransferenciaBL() {}
    
    public boolean crear(TransferenciaDTO transferenciaDTO) throws Exception {
        return transferenciaDAO.crear(transferenciaDTO);
    }

    public List<TransferenciaDTO> leerTodo() throws Exception {
        return transferenciaDAO.leerTodo();
    }

    public TransferenciaDTO leerPor(Integer idTransferencia) throws Exception {
        transferenciaDTO = transferenciaDAO.leerPor(idTransferencia);
        return transferenciaDTO;
    }

    public boolean actualizar(TransferenciaDTO transferenciaDTO) throws Exception {
        return transferenciaDAO.actualizar(transferenciaDTO);
    }

    public boolean eliminar(Integer idTransferencia) throws Exception {
        return transferenciaDAO.eliminar(idTransferencia);
    }

    public int getMaxId() throws Exception {
        return transferenciaDAO.getMaxId();
    }
}
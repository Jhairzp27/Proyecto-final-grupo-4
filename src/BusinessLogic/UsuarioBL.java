package BusinessLogic;

import java.util.List;

import DataAccess.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

public class UsuarioBL {
    private UsuarioDTO usuarioDTO;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioBL() {}
    
    public boolean crear(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.crear(usuarioDTO);
    }

    public List<UsuarioDTO> leerTodo() throws Exception {
        return usuarioDAO.leerTodo();
    }

    public UsuarioDTO leerPor(Integer idUsuario) throws Exception {
        usuarioDTO = usuarioDAO.leerPor(idUsuario);
        return usuarioDTO;
    }

    public boolean actualizar(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.actualizar(usuarioDTO);
    }

    public boolean eliminar(Integer idUsuario) throws Exception {
        return usuarioDAO.eliminar(idUsuario);
    }

    public int getMaxId() throws Exception {
        return usuarioDAO.getMaxId();
    }
}

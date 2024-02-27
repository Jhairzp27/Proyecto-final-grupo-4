package BusinessLogic;

import java.util.ArrayList;
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

    public UsuarioDTO leerPorId(Integer idUsuario) throws Exception {
        usuarioDTO = usuarioDAO.leerPor(idUsuario);
        return usuarioDTO;
    }

    public UsuarioDTO leerPorUsername(String username) throws Exception {
        usuarioDTO = usuarioDAO.leerPorUsername(username);
        return usuarioDTO;
    }

    public ArrayList<UsuarioDTO> leerSinUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return usuarioDAO.leerSinUsuarioActual(idUsuarioLogeado);
    }

    public boolean actualizar(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.actualizar(usuarioDTO);
    }

    public boolean eliminar(Integer idUsuario) throws Exception {
        return usuarioDAO.eliminar(idUsuario);
    }

    public boolean logear(String username, String password) throws Exception {
        return usuarioDAO.loginUsuario(username, password);
    }
}

package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import DataAccess.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

/**
 * La clase `UsuarioBL` contiene métodos para registrar usuarios, leer, actualizar y eliminar usuarios,
 * así como un método para iniciar sesión como usuario llamando a métodos de la clase `UsuarioDAO`.
 */
public class UsuarioBL {
    private UsuarioDTO usuarioDTO;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioBL() {}
    
    /**
     * La función `registrador` en Java registra a un usuario creando un nuevo registro en la base de
     * datos utilizando el objeto `UsuarioDTO` proporcionado.
     * 
     * @param usuarioDTO El parámetro `usuarioDTO` es un objeto de tipo `UsuarioDTO`, que probablemente
     * contiene información sobre un usuario como su nombre, correo electrónico, contraseña, etc. Este
     * objeto se utiliza como entrada al método `registrar` para crear un nuevo usuario en el sistema
     * pasándolo al `
     * @return El método `registrador` devuelve un valor booleano, que es el resultado de llamar al
     * método `crear` en el objeto `usuarioDAO` con el parámetro `usuarioDTO`.
     */
    public boolean registrar(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.crear(usuarioDTO);
    }

    /**
     * La función "readAll" devuelve una lista de objetos UserDTO llamando al método "readAll" de
     * userDAO.
     * 
     * @return Se devuelve una lista de objetos UsuarioDTO.
     */
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

    /**
     * La función `logear` intenta iniciar sesión como usuario con el nombre de usuario y contraseña
     * proporcionados llamando a un método en el objeto `usuarioDAO`.
     * 
     * @param username El parámetro "nombre de usuario" es una cadena que representa el nombre de
     * usuario del usuario que intenta iniciar sesión.
     * @param password El parámetro `contraseña` en el método `logear` es de tipo String. Se utiliza
     * para almacenar la entrada de contraseña proporcionada por el usuario con fines de autenticación.
     * @return El método `logear` devuelve un valor booleano, que es el resultado de llamar al método
     * `loginUsuario` desde el objeto `usuarioDAO` con los parámetros `nombre de usuario` y
     * `contraseña` proporcionados.
     */
    public boolean logear(String username, String password) throws Exception {
        return usuarioDAO.loginUsuario(username, password);
    }
}

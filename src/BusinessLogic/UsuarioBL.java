package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import DataAccess.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

/**
 * La clase `UsuarioBL` actúa como una capa de lógica de negocios para operaciones relacionadas con el
 * usuario al interactuar con un objeto `UsuarioDAO`.
 */
public class UsuarioBL {
    private UsuarioDTO usuarioDTO;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioBL() {}
    
    /**
     * La función "crear" en Java toma un objeto UserDTO como parámetro y llama al método de creación
     * en userDAO para crear un nuevo usuario.
     * 
     * @param usuarioDTO El parámetro `usuarioDTO` es un objeto de tipo `UsuarioDTO` que contiene los
     * datos necesarios para crear un nuevo usuario en el sistema. Es probable que este objeto incluya
     * información como el nombre del usuario, correo electrónico, contraseña y cualquier otro detalle
     * relevante necesario para la creación del usuario.
     * @return El método devuelve un valor booleano, que es el resultado de llamar al método `crear` en
     * el objeto `usuarioDAO` con el parámetro `usuarioDTO`.
     */
    public boolean crear(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.crear(usuarioDTO);
    }

    /**
     * Esta función Java lee todos los datos del usuario de la base de datos utilizando UsuarioDAO y
     * los devuelve como una lista de objetos UsuarioDTO.
     * 
     * @return Se devuelve una lista de objetos UsuarioDTO.
     */
    public List<UsuarioDTO> leerTodo() throws Exception {
        return usuarioDAO.leerTodo();
    }

    /**
     * Esta función de Java lee un usuario por su ID utilizando un objeto de transferencia de datos
     * (DTO) y un objeto de acceso a datos (DAO).
     * 
     * @param idUsuario El parámetro `idUsuario` es un valor Entero que representa el identificador
     * único de un usuario en el sistema.
     * @return El método devuelve un objeto de tipo UsuarioDTO.
     */
    public UsuarioDTO leerPorId(Integer idUsuario) throws Exception {
        usuarioDTO = usuarioDAO.leerPor(idUsuario);
        return usuarioDTO;
    }

    /**
     * Esta función Java lee un usuario por su nombre de usuario desde un objeto de acceso a datos y
     * devuelve un objeto UserDTO.
     * 
     * @param username El método `leerPorUsername` toma un parámetro `String` llamado `username`, que
     * se utiliza para buscar un usuario en la base de datos según su nombre de usuario. Luego, el
     * método recupera la información del usuario en forma de objeto `UsuarioDTO` y la devuelve.
     * @return El método devuelve un objeto UsuarioDTO.
     */
    public UsuarioDTO leerPorUsername(String username) throws Exception {
        usuarioDTO = usuarioDAO.leerPorUsername(username);
        return usuarioDTO;
    }

    /**
     * Esta función Java lee una lista de objetos UsuarioDTO excluyendo al usuario actual según la ID
     * de usuario proporcionada.
     * 
     * @param idUsuarioLogeado El parámetro `idUsuarioLogeado` representa el ID del usuario actualmente
     * conectado. Este método `leerSinUsuarioActual` se utiliza para recuperar una lista de objetos
     * `UsuarioDTO` excluyendo al usuario con el ID especificado.
     * @return Se devuelve una ArrayList de objetos UsuarioDTO.
     */
    public ArrayList<UsuarioDTO> leerSinUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return usuarioDAO.leerSinUsuarioActual(idUsuarioLogeado);
    }

    /**
     * La función `actualizar` en Java actualiza la información de un usuario utilizando un Objeto de
     * Transferencia de Datos (DTO) y devuelve un booleano que indica el éxito de la operación.
     * 
     * @param usuarioDTO El parámetro `usuarioDTO` es un objeto de tipo `UsuarioDTO` que contiene los
     * datos de un usuario que necesita ser actualizado en la base de datos.
     * @return El método devuelve un valor booleano, que es el resultado de llamar al método
     * `actualizar` sobre el objeto `usuarioDAO` con el parámetro `usuarioDTO`.
     */
    public boolean actualizar(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.actualizar(usuarioDTO);
    }

    /**
     * Esta función de Java elimina un usuario con la ID especificada utilizando un método DAO.
     * 
     * @param idUsuario El parámetro `idUsuario` es un valor Entero que representa el identificador
     * único de un usuario que desea eliminar del sistema. Este método intenta eliminar al usuario con
     * el `idUsuario` especificado llamando al método `eliminar` del objeto `usuarioDAO`.
     * @return Se devuelve un valor booleano.
     */
    public boolean eliminar(Integer idUsuario) throws Exception {
        return usuarioDAO.eliminar(idUsuario);
    }

    /**
     * La función `logear` intenta iniciar sesión en un usuario con el nombre de usuario y contraseña
     * proporcionados llamando al método `loginUsuario` del objeto `usuarioDAO`.
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

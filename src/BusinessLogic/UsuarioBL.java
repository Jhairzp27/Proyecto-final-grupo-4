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

    /**
     * Esta función de Java lee un usuario por su ID utilizando un objeto de transferencia de datos
     * (DTO) y una clase DAO.
     * 
     * @param idUsuario El parámetro idUsuario es un valor Entero que representa el identificador
     * único de un usuario en el sistema. Este método leerPorId está diseñado para leer y recuperar
     * un objeto UsuarioDTO de la base de datos en función de este ID de usuario.
     * @return El método está devolviendo un objeto UsuarioDTO con los datos del usuario identificado
     * por el idUsuario proporcionado.
     */
    public UsuarioDTO leerPorId(Integer idUsuario) throws Exception {
        usuarioDTO = usuarioDAO.leerPor(idUsuario);
        return usuarioDTO;
    }

    /**
     * Esta función Java lee un usuario por su nombre de usuario desde un objeto de acceso a datos y
     * devuelve un objeto UserDTO.
     * 
     * @param username El método leerPorUsername toma un parámetro String llamado username. Este
     * método se utiliza para recuperar un objeto UsuarioDTO de la base de datos según el `nombre de
     * usuario` proporcionado.
     * @return El método devuelve un objeto UsuarioDTO.
     */
    public UsuarioDTO leerPorUsername(String username) throws Exception {
        usuarioDTO = usuarioDAO.leerPorUsername(username);
        return usuarioDTO;
    }

    /**
     * Esta función Java lee una lista de objetos UsuarioDTO excluyendo el que tiene el
     * idUsuarioLogeado especificado.
     * 
     * @param idUsuarioLogeado El parámetro idUsuarioLogeado representa el ID del usuario actualmente
     * conectado. Este método leerSinUsuarioActual se utiliza para recuperar una lista de objetos
     * UsuarioDTO excluyendo al usuario con el ID especificado.
     * @return Se devuelve una ArrayList de objetos UsuarioDTO.
     */
    public ArrayList<UsuarioDTO> leerSinUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return usuarioDAO.leerSinUsuarioActual(idUsuarioLogeado);
    }

    /**
     * Esta función de Java actualiza la información de un usuario utilizando un objeto de
     * transferencia de datos (DTO) y devuelve un valor booleano que indica el éxito de la operación.
     * 
     * @param usuarioDTO El parámetro usuarioDTO es un objeto de tipo UsuarioDTO que contiene los
     * datos necesarios para actualizar un usuario en la base de datos. Es probable que este objeto
     * incluya información como la identificación del usuario, el nombre, el correo electrónico y
     * cualquier otro detalle relevante que deba actualizarse. El actualizar
     * @return El método devuelve un valor booleano, que es el resultado de llamar al método
     * actualizar sobre el objeto usuarioDAO con el parámetro usuarioDTO.
     */
    public boolean actualizar(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.actualizar(usuarioDTO);
    }

    /**
     * La función eliminar en Java intenta eliminar un usuario con el ID especificado usando el
     * objeto usuarioDAO y devuelve un booleano que indica el éxito de la operación.
     * 
     * @param idUsuario El parámetro idUsuario es un valor Entero que representa el identificador
     * único de un usuario que desea eliminar del sistema.
     * @return Se devuelve un valor booleano.
     */
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

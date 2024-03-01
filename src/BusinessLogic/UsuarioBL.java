package BusinessLogic;

import java.util.ArrayList;
import java.util.List;

import DataAccess.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

/**
 * La clase `UsuarioBL` en Java contiene métodos para el registro de usuarios, lectura de datos de
 * usuarios, actualización de información de usuarios, eliminación de usuarios e inicio de sesión de
 * usuarios utilizando un Objeto de transferencia de datos (DTO) y un Objeto de acceso a datos (DAO).
 */
public class UsuarioBL {
    private UsuarioDTO usuarioDTO;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public UsuarioBL() {}
    
    /**
     * La función `registrador` en Java intenta crear un nuevo usuario utilizando el objeto
     * `UsuarioDTO` proporcionado y devuelve un valor booleano que indica el éxito de la operación.
     * 
     * @param usuarioDTO El parámetro `usuarioDTO` es un objeto de tipo `UsuarioDTO`, que probablemente
     * contiene información sobre un usuario como su nombre, correo electrónico, contraseña, etc. Este
     * objeto se utiliza como entrada al método `registrar` para crear un nuevo usuario en el sistema
     * pasándolo al `
     * @return El método "registrador" devuelve un valor booleano.
     */
    public boolean registrar(UsuarioDTO usuarioDTO) throws Exception {
        return usuarioDAO.crear(usuarioDTO);
    }

    /**
     * Esta función Java lee todos los datos del usuario de la base de datos utilizando la clase
     * UsuarioDAO y los devuelve como una lista de objetos UsuarioDTO.
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
    * @param idUsuario El parámetro `idUsuario` es un valor Entero que representa el identificador
    * único de un usuario en el sistema. El método `leerPorId` está diseñado para recuperar un objeto
    * `UsuarioDTO` de la base de datos en función de este ID de usuario.
    * @return El método devuelve un objeto UsuarioDTO.
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
     * se utiliza para buscar un usuario en la base de datos según su nombre de usuario.
     * @return El método devuelve un objeto UsuarioDTO.
     */
    public UsuarioDTO leerPorUsername(String username) throws Exception {
        usuarioDTO = usuarioDAO.leerPorUsername(username);
        return usuarioDTO;
    }

    /**
     * Esta función Java lee una lista de objetos UsuarioDTO excluyendo el correspondiente al
     * idUsuarioLogeado proporcionado.
     * 
     * @param idUsuarioLogeado El parámetro `idUsuarioLogeado` representa el ID del usuario que
     * actualmente está conectado. Este método `leerSinUsuarioActual` está diseñado para leer y
     * devolver una lista de objetos `UsuarioDTO` excluyendo al usuario que está actualmente conectado.
     * @return Se devuelve una ArrayList de objetos UsuarioDTO.
     */
    public ArrayList<UsuarioDTO> leerSinUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return usuarioDAO.leerSinUsuarioActual(idUsuarioLogeado);
    }

    /**
     * Esta función de Java actualiza la información de un usuario utilizando un objeto de
     * transferencia de datos (DTO) y devuelve un valor booleano que indica el éxito de la operación.
     * 
     * @param usuarioDTO El parámetro `usuarioDTO` es un objeto de tipo `UsuarioDTO` que contiene los
     * datos necesarios para actualizar un usuario en la base de datos.
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
     * único de un usuario que desea eliminar del sistema.
     * @return Se devuelve un valor booleano.
     */
    public boolean eliminar(Integer idUsuario) throws Exception {
        return usuarioDAO.eliminar(idUsuario);
    }

    /**
     * Esta función de Java intenta iniciar sesión como usuario llamando a un método en la clase
     * usuarioDAO con el nombre de usuario y la contraseña proporcionados.
     * 
     * @param username El parámetro "nombre de usuario" es una cadena que representa el nombre de
     * usuario del usuario que intenta iniciar sesión.
     * @param password El parámetro `contraseña` en el método `logear` es de tipo String. Se utiliza
     * para almacenar la entrada de contraseña proporcionada por el usuario con fines de autenticación.
     * @return El método devuelve un valor booleano, que indica si el intento de inicio de sesión fue
     * exitoso o no.
     */
    public boolean logear(String username, String password) throws Exception {
        return usuarioDAO.loginUsuario(username, password);
    }
}

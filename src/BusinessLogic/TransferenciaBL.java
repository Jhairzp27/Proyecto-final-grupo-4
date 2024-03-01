package BusinessLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import DataAccess.TransferenciaDAO;
import DataAccess.DTO.TransferenciaDTO;
import DataAccess.DTO.UsuarioDTO;
import Framework.NewException;

/**
 * La clase `TransferenciaBL` en Java proporciona métodos para crear, leer, actualizar y eliminar
 * transacciones de transferencia,  así como funcionalidades para recargar saldos de usuarios y
 * transferir dinero entre usuarios.
 */
public class TransferenciaBL {
    private TransferenciaDTO transferenciaDTO;
    private TransferenciaDAO transferenciaDAO = new TransferenciaDAO();
    private UsuarioBL usuarioBL = new UsuarioBL();

    public TransferenciaBL() {}

    /**
     * La función `crear` en Java crea una transferencia utilizando el objeto TransferenciaDTO
     * proporcionado llamando al método crear de la transferenciaDAO.
     * 
     * @param transferenciaDTO El parámetro `transferenciaDTO` es un objeto de tipo `TransferenciaDTO`,
     * que probablemente contiene datos relacionados con una transacción de transferencia, como el
     * monto, el remitente, el destinatario y cualquier otra información relevante necesaria para crear
     * una transferencia. Este método `crear` se encarga de crear una transferencia utilizando los
     * datos
     * @return El método devuelve un valor booleano, que es el resultado de llamar al método `crear`
     * sobre el objeto `transferenciaDAO` con el parámetro `transferenciaDTO`.
     */
    public boolean crear(TransferenciaDTO transferenciaDTO) throws Exception {
        return transferenciaDAO.crear(transferenciaDTO);
    }

    /**
     * Esta función Java lee todos los objetos transferenciaDTO del transferenciaDAO.
     * 
     * @return Se está devolviendo una Lista de objetos de TransferenciaDTO.
     */
    public List<TransferenciaDTO> leerTodo() throws Exception {
        return transferenciaDAO.leerTodo();
    }

    /**
     * Esta función Java lee un objeto TransferenciaDTO por su ID usando un TransferenciaDAO.
     * 
     * @param idTransferencia El parámetro `idTransferencia` es un valor entero que representa el
     * identificador único de una entidad de transferencia. Este método `leerPor` está diseñado para
     * leer y recuperar una TransferenciaDTO (Objeto de Transferencia de Datos) basado en el
     * `idTransferencia` proporcionado.
     * @return El método devuelve un objeto TransferenciaDTO.
     */
    public TransferenciaDTO leerPor(Integer idTransferencia) throws Exception {
        transferenciaDTO = transferenciaDAO.leerPor(idTransferencia);
        return transferenciaDTO;
    }

    /**
     * La función `recargar` en Java actualiza el saldo de un usuario, crea un registro de
     * transferencia y devuelve verdadero si ambas operaciones tienen éxito.
     * 
     * @param usuarioDTO El parámetro `usuarioDTO` en el método `recargar` es de tipo `UsuarioDTO`, que
     * probablemente representa un objeto de transferencia de datos para un usuario. Contiene
     * información como el ID del usuario, el nombre y el saldo actual.
     * @param montoRecarga El parámetro `montoRecarga` representa la cantidad de dinero que se está
     * recargando a la cuenta del usuario.
     * @return El método `recargar` devuelve un valor booleano - `true` si los métodos
     * `usuarioBL.actualizar(usuarioDTO)` y `crear(transferenciaDTO)` devuelven true, indicando que el
     * saldo del usuario se actualizó exitosamente y se creó un registro de transferencia. De lo
     * contrario, devuelve "falso".
     */
    public boolean recargar(UsuarioDTO usuarioDTO, float montoRecarga) throws Exception {
        if (montoRecarga < 0)
            return false;

        float saldoActual = usuarioDTO.getSaldo(),
                saldoNuevo = saldoActual + montoRecarga;
        usuarioDTO.setSaldo(saldoNuevo);

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actual = LocalDateTime.now();
        transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setIdUsuarioEnvia(-1);
        transferenciaDTO.setIdUsuarioRecibe(usuarioDTO.getIdUsuario());
        transferenciaDTO.setMonto(montoRecarga);
        transferenciaDTO.setFecha(formatoFecha.format(actual).toString());

        if (usuarioBL.actualizar(usuarioDTO) && crear(transferenciaDTO))
            return true;
        else
            return false;
    }

    /**
     * La función `transferirDinero` transfiere una cantidad específica de dinero de un usuario a otro,
     * actualizando sus saldos y creando un registro de transferencia si tiene éxito.
     * 
     * @param usuarioDTOLogeado El parámetro `usuarioDTOLogeado` es de tipo `UsuarioDTO` y representa
     * al usuario que está iniciando la transferencia de dinero. Contiene información sobre este
     * usuario, incluido su ID y saldo actual.
     * @param idUsuarioRecibe El parámetro `idUsuarioRecibe` en el método `transferirDinero` representa
     * el ID del usuario que recibirá la transferencia de dinero. Esta identificación se utiliza para
     * identificar al usuario destinatario en el sistema para que la transferencia pueda procesarse con
     * precisión.
     * @param monto El parámetro "monto" en el método "transferirDinero" representa la cantidad de
     * dinero que se está transfiriendo de un usuario a otro. Es un valor flotante que indica el valor
     * monetario de la transferencia. En el contexto de este método, es importante garantizar que el
     * "monto
     * @return El método `transferirDinero` devuelve un valor booleano. Devuelve "verdadero" si la
     * operación de transferencia de dinero es exitosa y "falso" si no se cumple alguna de las
     * condiciones para una transferencia exitosa o si hay un error durante el proceso.
     */
    public boolean transferirDinero(UsuarioDTO usuarioDTOLogeado, int idUsuarioRecibe, float monto) throws Exception {
        if (monto <= 0)
            return false;

        UsuarioDTO usuarioRecibe = usuarioBL.leerPorId(idUsuarioRecibe);

        float saldoActualUsuarioLogeado = usuarioDTOLogeado.getSaldo();

        if (saldoActualUsuarioLogeado < monto)
            return false;

        float nuevoSaldoUsuarioLogeado = saldoActualUsuarioLogeado - monto,
              saldoActualUsuarioRecibe = usuarioRecibe.getSaldo(),
              nuevoSaldoUsuarioRecibe = saldoActualUsuarioRecibe + monto;

        usuarioDTOLogeado.setSaldo(nuevoSaldoUsuarioLogeado);
        usuarioRecibe.setSaldo(nuevoSaldoUsuarioRecibe);

        if (usuarioBL.actualizar(usuarioDTOLogeado) && usuarioBL.actualizar(usuarioRecibe)) {
            TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime actual = LocalDateTime.now();
            transferenciaDTO.setIdUsuarioEnvia(usuarioDTOLogeado.getIdUsuario());
            transferenciaDTO.setIdUsuarioRecibe(idUsuarioRecibe);
            transferenciaDTO.setMonto(monto);
            transferenciaDTO.setFecha(formatoFecha.format(actual).toString());

            if (crear(transferenciaDTO))
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * La función `esNumeroFloatPositivo` comprueba si una cadena determinada se puede analizar como un
     * número flotante positivo en Java.
     * 
     * @param str El parámetro `str` en el método `esNumeroFloatPositivo` es una cadena que representa
     * un número. El método intenta analizar esta cadena en un valor flotante y luego verifica si el
     * valor flotante analizado es mayor o igual a 0. Si el valor flotante analizado es positivo o
     * @return El método `esNumeroFloatPositivo` devuelve un valor booleano que indica si la cadena de
     * entrada representa un número flotante positivo.
     */
    public boolean esNumeroFloatPositivo(String str) throws Exception {
        try {
            if (Float.parseFloat(str) >= 0)
                return true;
            return false;
        } catch (NumberFormatException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "esNumeroFloatPositivo()");

        }
    }

    /**
     * La función "esNumeroEntero" en Java comprueba si una cadena determinada se puede analizar en un
     * número entero y genera una excepción personalizada si no es así.
     * 
     * @param str El método `esNumeroEntero` toma un parámetro `String` llamado `str`, que representa
     * la cadena de entrada que debe verificarse si es un número entero válido. El método intenta
     * analizar la cadena de entrada como un número entero usando `Integer.parseInt(str)`. Si el
     * análisis tiene éxito,
     * @return El método `esNumeroEntero` devuelve un valor booleano, ya sea `verdadero` si la cadena
     * de entrada se puede analizar como un número entero, o arroja una excepción si la cadena de
     * entrada no se puede analizar como un número entero.
     */
    public boolean esNumeroEntero(String str) throws Exception {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "esNumeroEntero()");
        }
    }

}
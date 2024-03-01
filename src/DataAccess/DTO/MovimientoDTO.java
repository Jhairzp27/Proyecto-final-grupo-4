package DataAccess.DTO;

/**
 * La clase `MovimientoDTO` representa un objeto de transferencia de datos para una transacción
 * financiera con atributos como ID de transferencia, usuario emisor, usuario receptor, monto y fecha.
 */
public class MovimientoDTO {
    private Integer IdTransferencia;
    private String  UsuarioEnvia;
    private String  UsuarioRecibe;
    private Float   Monto;
    private String  Fecha;

    public MovimientoDTO() {}
    
    public MovimientoDTO(Integer idTransferencia,
                         String usuarioEnvia, 
                         String usuarioRecibe, 
                         Float monto,
                         String fecha) {
        IdTransferencia = idTransferencia;
        UsuarioEnvia    = usuarioEnvia;
        UsuarioRecibe   = usuarioRecibe;
        Monto           = monto;
        Fecha           = fecha;
    }

    /**
     * Esta función Java devuelve el IdTransferencia como un Número Entero.
     * 
     * @return El método `getIdTransferencia` devuelve un valor entero que representa la
     * IdTransferencia.
     */
    public Integer getIdTransferencia() {
        return IdTransferencia;
    }

    /**
     * Esta función Java establece el valor de la variable IdTransferencia.
     * 
     * @param idTransferencia El parámetro `idTransferencia` es un valor Entero que representa el ID de
     * una transferencia.
     */
    public void setIdTransferencia(Integer idTransferencia) {
        IdTransferencia = idTransferencia;
    }

    /**
     * La función `getUserSubmission` en Java devuelve el valor de la variable `UserSubmission`.
     * 
     * @return El método `getUsuarioEnvia` está devolviendo el valor de la variable `UsuarioEnvia`.
     */
    public String getUsuarioEnvia() {
        return UsuarioEnvia;
    }

    /**
     * La función establece el valor de la variable "UsuarioEnvia" al parámetro "usuarioEnvia"
     * proporcionado.
     * 
     * @param usuarioEnvia El parámetro `usuarioEnvia` es un String que representa al usuario que está
     * enviando un mensaje o realizando una acción. En el fragmento de código proporcionado, el método
     * `setUsuarioEnvia` se utiliza para establecer el valor de la variable `UsuarioEnvia` en la cadena
     * `usuarioEnvia` especificada.
     */
    public void setUsuarioEnvia(String usuarioEnvia) {
        UsuarioEnvia = usuarioEnvia;
    }

    /**
     * La función `getUsuarioRecibe` en Java devuelve el valor de la variable `UsuarioRecibe`.
     * 
     * @return El método `getUsuarioRecibe` está devolviendo el valor de la variable `UsuarioRecibe`.
     */
    public String getUsuarioRecibe() {
        return UsuarioRecibe;
    }

    /**
     * La función establece el valor de la variable "UsuarioRecibe" en Java.
     * 
     * @param usuarioRecibe El parámetro "usuarioRecibe" es un String que representa el nombre de
     * usuario del usuario que recibirá algo.
     */
    public void setUsuarioRecibe(String usuarioRecibe) {
        UsuarioRecibe = usuarioRecibe;
    }

    // El método `getMonto()` de la clase `MovimientoDTO` es un método getter en Java que se utiliza
    // para recuperar el valor del atributo `Monto`, que representa el monto de una transacción
    // financiera.
    public Float getMonto() {
        return Monto;
    }

    // El método `setMonto(Float monto)` en la clase `MovimientoDTO` es un método setter en Java que se
    // utiliza para establecer el valor del atributo `Monto`, que representa el monto de una
    // transacción financiera.
    public void setMonto(Float monto) {
        Monto = monto;
    }

    // El método `getFecha()` de la clase `MovimientoDTO` es un método getter en Java que se utiliza
    // para recuperar el valor del atributo `Fecha`, que representa la fecha de una transacción
    // financiera. Este método devuelve el valor del atributo `Fecha` cuando se llama.
    public String getFecha() {
        return Fecha;
    }

    // El método `setFecha(String fecha)` en la clase `MovimientoDTO` es un método setter en Java que
    // se utiliza para establecer el valor del atributo `Fecha`, que representa la fecha de una
    // transacción financiera.
    public void setFecha(String fecha) {
        Fecha = fecha;
    }


    @Override
    // El método `public String toString()` en Java es un método anulado de la clase `Object` que se
    // utiliza para devolver una representación de cadena de un objeto. Cuando llamas a `toString()` en
    // un objeto, devuelve una cadena que normalmente incluye información sobre el estado del objeto.
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n IdTransferencia: " + getIdTransferencia()
             + "\n UsuarioEnvia:  " + getUsuarioEnvia()
             + "\n UsuarioRecibe: " + getUsuarioRecibe()
             + "\n Monto:           " + getMonto()
             + "\n Fecha:           " + getFecha();
    }
}

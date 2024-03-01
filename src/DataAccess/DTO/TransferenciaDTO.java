package DataAccess.DTO;

/**
 * La clase `TransferenciaDTO` representa un objeto de transferencia de datos para una transacción
 * financiera con varios atributos como ID de transferencia, ID del remitente, ID del receptor, monto,
 * fecha y estado.
 */
public class TransferenciaDTO {
    private Integer IdTransferencia;
    private Integer IdUsuarioEnvia;
    private Integer IdUsuarioRecibe;
    private Float   Monto;
    private String  Fecha;
    private String  Estado;
    private String  FechaCrea;
    private String  FechaModifica;

    public TransferenciaDTO() {}

    public TransferenciaDTO(Integer idTransferencia, 
                            Integer idUsuarioEnvia, 
                            Integer idUsuarioRecibe,
                            Float   monto,
                            String  fecha,
                            String  estado,
                            String  fechaCrea,
                            String  fechaModifica) {
        IdTransferencia = idTransferencia;
        IdUsuarioEnvia  = idUsuarioEnvia;
        IdUsuarioRecibe = idUsuarioRecibe;
        Monto           = monto;
        Fecha           = fecha;
        Estado          = estado;
        FechaCrea       = fechaCrea;
        FechaModifica   = fechaModifica;
    }

    public TransferenciaDTO(Integer idTransferencia, 
                            Integer idUsuarioEnvia, 
                            Integer idUsuarioRecibe,
                            Float   monto,
                            String  fecha) {
        IdTransferencia = idTransferencia;
        IdUsuarioEnvia  = idUsuarioEnvia;
        IdUsuarioRecibe = idUsuarioRecibe;
        Monto           = monto;
        Fecha           = fecha;
    }

    /**
     * Esta función Java devuelve el IdTransferencia como un Número Entero.
     * 
     * @return El método `getIdTransferencia` devuelve un valor `Entero` que representa la
     * `IdTransferencia`.
     */
    public Integer getIdTransferencia() {
        return IdTransferencia;
    }
    
    /**
     * La función setIdTransferencia establece el valor de la variable IdTransferencia.
     * 
     * @param idTransferencia El parámetro `idTransferencia` es un valor Entero que representa el ID de
     * una transferencia.
     */
    public void setIdTransferencia(Integer idTransferencia) {
        IdTransferencia = idTransferencia;
    }
    
    /**
     * La función devuelve el IdUsuarioEnvia como un Entero.
     * 
     * @return El método devuelve un valor entero que representa la identificación del usuario que
     * envió el mensaje.
     */
    public Integer getIdUsuarioEnvia() {
        return IdUsuarioEnvia;
    }
    
    /**
     * La función setIdUsuarioEnvia establece el valor de la variable IdUsuarioEnvia en Java.
     * 
     * @param idUsuarioEnvia El parámetro `idUsuarioEnvia` es un Entero que representa el ID del
     * usuario que envía un mensaje o realiza una acción.
     */
    public void setIdUsuarioEnvia(Integer idUsuarioEnvia) {
        IdUsuarioEnvia = idUsuarioEnvia;
    }
    
    /**
     * La función devuelve el Id del usuario que recibe un mensaje.
     * 
     * @return El método devuelve un valor entero que representa la identificación del usuario que
     * recibe algo.
     */
    public Integer getIdUsuarioRecibe() {
        return IdUsuarioRecibe;
    }
    
    /**
     * Esta función Java establece el valor de la variable "IdUsuarioRecibe" al valor entero
     * proporcionado.
     * 
     * @param idUsuarioRecibe El parámetro `idUsuarioRecibe` es un valor Entero que representa el ID
     * del usuario que está recibiendo algo.
     */
    public void setIdUsuarioRecibe(Integer idUsuarioRecibe) {
        IdUsuarioRecibe = idUsuarioRecibe;
    }
    
    // El método `getMonto()` en la clase `TransferenciaDTO` es un método getter para recuperar el
    // valor del atributo `Monto`, que representa el monto de la transacción financiera. Este método
    // devuelve un valor `Float` correspondiente al monto de la transacción almacenado en el atributo
    // `Monto` del objeto `TransferenciaDTO`.
    public Float getMonto() {
        return Monto;
    }
    
    // El método `public void setMonto(Float monto)` en la clase `TransferenciaDTO` es un método de
    // establecimiento utilizado para establecer el valor del atributo `Monto`, que representa el monto
    // de una transacción financiera.
    public void setMonto(Float monto) {
        Monto = monto;
    }
    
    // El método `getFecha()` en la clase `TransferenciaDTO` es un método getter que se utiliza para
    // recuperar el valor del atributo `Fecha`, que representa la fecha de una transacción financiera.
    // Este método devuelve un valor `String` correspondiente a la fecha almacenada en el atributo
    // `Fecha` del objeto `TransferenciaDTO`.
    public String getFecha() {
        return Fecha;
    }
    
    // El método `public void setFecha(String fecha)` en la clase `TransferenciaDTO` es un método
    // setter utilizado para establecer el valor del atributo `Fecha`, que representa la fecha de una
    // transacción financiera.
    public void setFecha(String fecha) {
        Fecha = fecha;
    }
    
    // El método `public String getEstado() {` en la clase `TransferenciaDTO` es un método getter que
    // se utiliza para recuperar el valor del atributo `Estado`, que representa el estado de una
    // transacción financiera. Este método devuelve un valor `String` correspondiente al estado
    // almacenado en el atributo `Estado` del objeto `TransferenciaDTO`.
    public String getEstado() {
        return Estado;
    }
    
    // El método `public void setEstado(String estado)` en la clase `TransferenciaDTO` es un método de
    // establecimiento utilizado para establecer el valor del atributo `Estado`, que representa el
    // estado de una transacción financiera. Este método toma un parámetro `String` `estado` y lo
    // asigna al atributo `Estado` del objeto `TransferenciaDTO`, actualizando efectivamente el estado
    // de la transacción.
    public void setEstado(String estado) {
        Estado = estado;
    }
    
    // El método `public String getFechaCrea() {` en la clase `TransferenciaDTO` es un método getter
    // utilizado para recuperar el valor del atributo `FechaCrea`, que representa la fecha de creación
    // de una transacción financiera. Este método devuelve un valor `String` correspondiente a la fecha
    // de creación almacenada en el atributo `FechaCrea` del objeto `TransferenciaDTO`.
    public String getFechaCrea() {
        return FechaCrea;
    }
    
    // El método `public void setFechaCrea(String fechaCrea)` en la clase `TransferenciaDTO` es un
    // método setter utilizado para establecer el valor del atributo `FechaCrea`, que representa la
    // fecha de creación de una transacción financiera. Este método toma un parámetro `String
    // fechaCrea` y lo asigna al atributo `FechaCrea` del objeto `TransferenciaDTO`, actualizando
    // efectivamente la fecha de creación de la transacción.
    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }
    
    // El método `public String getFechaModifica() {` en la clase `TransferenciaDTO` es un método
    // getter utilizado para recuperar el valor del atributo `FechaModifica`, que representa la fecha
    // de modificación de una transacción financiera. Este método devuelve un valor `String`
    // correspondiente a la fecha de modificación almacenada en el atributo `FechaModifica` del objeto
    // `TransferenciaDTO`.
    public String getFechaModifica() {
        return FechaModifica;
    }
    
    // El método `public void setFechaModifica(String fechaModifica)` en la clase `TransferenciaDTO` es
    // un método setter utilizado para establecer el valor del atributo `FechaModifica`, que representa
    // la fecha de modificación de una transacción financiera. Este método toma un parámetro `String
    // fechaModifica` y lo asigna al atributo `FechaModifica` del objeto `TransferenciaDTO`,
    // actualizando efectivamente la fecha de modificación de la transacción.
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }
    // El método `public String toString()` en la clase `TransferenciaDTO` anula el método `toString()`
    // predeterminado proporcionado por la clase `Object` en Java.
    @Override
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n IdTransferencia: " + getIdTransferencia()
             + "\n IdUsuarioEnvia:  " + getIdUsuarioEnvia()
             + "\n IdUsuarioRecibe: " + getIdUsuarioRecibe()
             + "\n Monto:           " + getMonto()
             + "\n Fecha:           " + getFecha()
             + "\n Estado:          " + getEstado()
             + "\n FechaCrea:       " + getFechaCrea()
             + "\n FechaModifica:   " + getFechaModifica();
    }
}

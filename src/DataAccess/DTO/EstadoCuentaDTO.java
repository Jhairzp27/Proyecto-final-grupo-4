package DataAccess.DTO;

/**
 * La clase EstadoCuentaDTO representa un objeto de transferencia de datos para estados de cuenta con
 * propiedades de fecha, ID de transacción, descripción, monto y saldo.
 */
public class EstadoCuentaDTO {
    private String  Fecha;
    private Integer IdMovimiento;
    private String  Descripcion;
    private String  Monto;
    private Float   Saldo;
    
    public EstadoCuentaDTO(String  fecha, 
                           Integer idMovimiento,
                           String  descripcion,
                           String  monto,
                           Float   saldo) {
        Fecha        = fecha;
        IdMovimiento = idMovimiento;
        Descripcion  = descripcion;
        Monto        = monto;
        Saldo        = saldo;
    }

    /**
     * La función `getFecha()` en Java devuelve el valor de la variable `Fecha`.
     * 
     * @return El método `getFecha` devuelve el valor de la variable `Fecha`.
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * Esta función Java devuelve la parte de fecha corta de una cadena de fecha completa determinada.
     * 
     * @return El método `getFechaCorta` devuelve una subcadena de la cadena `Fecha` desde el índice 0
     * hasta (pero sin incluir) el primer carácter de espacio. Esta subcadena representa la parte de
     * fecha de la cadena `Fecha`.
     */
    public String getFechaCorta() {
        return Fecha.substring(0, Fecha.indexOf(' '));
    }

    /**
     * La función `setFecha` establece el valor de la variable `Fecha` en una clase Java.
     * 
     * @param fecha El parámetro "fecha" es un String que representa una fecha.
     */
    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    /**
     * Esta función Java devuelve el IdMovimiento como un Entero.
     * 
     * @return El método devuelve un valor entero que representa el IdMovimiento.
     */
    public Integer getIdMovimiento() {
        return IdMovimiento;
    }

    /**
     * Esta función Java establece el valor de la variable IdMovimiento.
     * 
     * @param idMovimiento El parámetro `idMovimiento` es un valor Entero que representa el
     * identificador único de un movimiento.
     */
    public void setIdMovimiento(Integer idMovimiento) {
        IdMovimiento = idMovimiento;
    }

    /**
     * La función `getDescripcion` en Java devuelve el valor de la variable `Descripcion`.
     * 
     * @return El método `getDescripcion` devuelve el valor de la variable `Descripcion`.
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * La función `setDescripcion` establece el valor de la variable `Descripcion` en una clase Java.
     * 
     * @param descripcion El método `setDescripcion` es un método de establecimiento utilizado para
     * establecer el valor de la propiedad `Descripcion` en una clase. El parámetro `descripcion` es el
     * valor que se asignará a la propiedad `Descripcion` cuando se llame a este método.
     */
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String // El método `getMonto` de la clase `EstadoCuentaDTO` es un método getter que
    // devuelve el valor de la propiedad `Monto`, que representa el monto asociado a una
    // transacción en el estado de cuenta. Simplemente recupera y devuelve el valor
    // almacenado en la variable `Monto` del objeto.
    getMonto() {
        return Monto;
    }

    // El método `setMonto(String monto)` en la clase `EstadoCuentaDTO` es un método de establecimiento
    // en Java. Se utiliza para establecer el valor de la propiedad `Monto`, que representa el monto
    // asociado con una transacción en el estado de cuenta.
    public void setMonto(String monto) {
        Monto = monto;
    }

    // El método `public Float getSaldo()` en la clase `EstadoCuentaDTO` es un método getter en Java
    // que devuelve el valor de la propiedad `Saldo`. Este método se utiliza para recuperar y devolver
    // el valor almacenado en la variable `Saldo` del objeto. La propiedad `Saldo` representa el saldo
    // asociado con una transacción de cuenta.
    public Float getSaldo() {
        return Saldo;
    }

    // El método `public void setSaldo(Float saldo)` en la clase `EstadoCuentaDTO` es un método de
    // establecimiento en Java. Se utiliza para establecer el valor de la propiedad `Saldo`, que
    // representa el saldo asociado a una transacción en una cuenta. Cuando se llama a este método con
    // un valor `Float` como parámetro `saldo`, asigna ese valor a la propiedad `Saldo` del objeto,
    // actualizando la información del saldo para esa transacción en particular.
    public void setSaldo(Float saldo) {
        Saldo = saldo;
    }

    @Override
    // El método `public String toString()` en Java anula el método `toString()` predeterminado
    // proporcionado por la clase `Object`. Cuando un objeto se imprime usando
    // `System.out.println(object)`, se llama al método `toString()` para obtener una representación de
    // cadena del objeto.
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n Fecha:        " + getFecha()
             + "\n IdMovimiento: " + getIdMovimiento()
             + "\n Descripcion:  " + getDescripcion()
             + "\n Monto:        " + getMonto()
             + "\n Saldo:        " + getSaldo();
    }
}

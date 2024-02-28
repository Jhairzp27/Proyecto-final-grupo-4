package DataAccess.DTO;

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

    public String getFecha() {
        return Fecha;
    }

    public String getFechaCorta() {
        return Fecha.substring(0, Fecha.indexOf(' '));
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public Integer getIdMovimiento() {
        return IdMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        IdMovimiento = idMovimiento;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String monto) {
        Monto = monto;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        Saldo = saldo;
    }

    @Override
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n Fecha:        " + getFecha()
             + "\n IdMovimiento: " + getIdMovimiento()
             + "\n Descripcion:  " + getDescripcion()
             + "\n Monto:        " + getMonto()
             + "\n Saldo:        " + getSaldo();
    }
}

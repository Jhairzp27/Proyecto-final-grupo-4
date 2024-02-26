package DataAccess.DTO;

public class MovimientoDTO {
    private Integer IdTransferencia;
    private String  UsuarioEnvia;
    private String  UsuarioRecibe;
    private Float   Monto;
    private String  Fecha;

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

    public Integer getIdTransferencia() {
        return IdTransferencia;
    }

    public void setIdTransferencia(Integer idTransferencia) {
        IdTransferencia = idTransferencia;
    }

    public String getUsuarioEnvia() {
        return UsuarioEnvia;
    }

    public void setUsuarioEnvia(String usuarioEnvia) {
        UsuarioEnvia = usuarioEnvia;
    }

    public String getUsuarioRecibe() {
        return UsuarioRecibe;
    }

    public void setUsuarioRecibe(String usuarioRecibe) {
        UsuarioRecibe = usuarioRecibe;
    }

    public Float getMonto() {
        return Monto;
    }

    public void setMonto(Float monto) {
        Monto = monto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public MovimientoDTO() {}

    @Override
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n IdTransferencia: " + getIdTransferencia()
             + "\n UsuarioEnvia:  " + getUsuarioEnvia()
             + "\n UsuarioRecibe: " + getUsuarioRecibe()
             + "\n Monto:           " + getMonto()
             + "\n Fecha:           " + getFecha();
    }
}

package DataAccess.DTO;

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

    public Integer getIdTransferencia() {
        return IdTransferencia;
    }
    
    public void setIdTransferencia(Integer idTransferencia) {
        IdTransferencia = idTransferencia;
    }
    
    public Integer getIdUsuarioEnvia() {
        return IdUsuarioEnvia;
    }
    
    public void setIdUsuarioEnvia(Integer idUsuarioEnvia) {
        IdUsuarioEnvia = idUsuarioEnvia;
    }
    
    public Integer getIdUsuarioRecibe() {
        return IdUsuarioRecibe;
    }
    
    public void setIdUsuarioRecibe(Integer idUsuarioRecibe) {
        IdUsuarioRecibe = idUsuarioRecibe;
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
    
    public String getEstado() {
        return Estado;
    }
    
    public void setEstado(String estado) {
        Estado = estado;
    }
    
    public String getFechaCrea() {
        return FechaCrea;
    }
    
    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }
    
    public String getFechaModifica() {
        return FechaModifica;
    }
    
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }
    
}

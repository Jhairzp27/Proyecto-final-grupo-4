package DataAccess.DTO;

public class UsuarioDTO {
    private Integer IdUsuario;
    private String  Nombre;
    private String  Cedula;
    private Integer IdSexo;
    private String  Clave;
    private Float   Saldo;
    private String  Estado;
    private String  FechaCrea;
    private String  FechaModifica;
    
    public UsuarioDTO() {}

    public UsuarioDTO(Integer idUsuario, 
                      String  nombre,
                      String  cedula,
                      Integer idSexo,
                      String  clave,
                      Float   saldo,
                      String  estado,
                      String  fechaCrea,
                      String  fechaModifica) {
        IdUsuario     = idUsuario;
        Nombre        = nombre;
        Cedula        = cedula;
        IdSexo        = idSexo;
        Clave         = clave;
        Saldo         = saldo;
        Estado        = estado;
        FechaCrea     = fechaCrea;
        FechaModifica = fechaModifica;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        Saldo = saldo;
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

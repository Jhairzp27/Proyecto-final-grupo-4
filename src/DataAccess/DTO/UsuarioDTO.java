package DataAccess.DTO;

public class UsuarioDTO {
    private Integer IdUsuario;
    private String  Nombre;
    private String  Cedula;
    private String  Username;
    private String  Clave;
    private String  Email;
    private Integer IdSexo;
    private Float   Saldo;
    private String  Estado;
    private String  FechaCrea;
    private String  FechaModifica;
    
    public UsuarioDTO() {}

    public UsuarioDTO(Integer idUsuario, 
                      String  nombre,
                      String  cedula,
                      String  username,
                      String  clave,
                      String  email,
                      Integer idSexo,
                      Float   saldo,
                      String  estado,
                      String  fechaCrea,
                      String  fechaModifica) {
        IdUsuario     = idUsuario;
        Nombre        = nombre;
        Cedula        = cedula;
        Username      = username;
        Clave         = clave;
        Email         = email;
        IdSexo        = idSexo;
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

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
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

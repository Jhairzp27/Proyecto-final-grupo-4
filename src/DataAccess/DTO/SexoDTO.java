package DataAccess.DTO;

public class SexoDTO {
    private Integer IdSexo;
    private String  Nombre;
    private String  Estado;
    private String  FechaCrea;
    private String  FechaModifica;

    public SexoDTO() {}

    public SexoDTO(Integer idSexo,
                   String  nombre,
                   String  estado, 
                   String  fechaCrea,
                   String  fechaModifica) {
        IdSexo        = idSexo;
        Nombre        = nombre;
        Estado        = estado;
        FechaCrea     = fechaCrea;
        FechaModifica = fechaModifica;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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

    @Override
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n IdSexo:        " + getIdSexo()
             + "\n Nombre:        " + getNombre()
             + "\n Estado:        " + getEstado()
             + "\n FechaCrea:     " + getFechaCrea()
             + "\n FechaModifica: " + getFechaModifica();
    }
}

package DataAcces.DTO;

public class SexoDTO {
    private int IdSexo;
    private String SexoNombre;
    private String FechaCrea;
    private String FechaModifica;

    public SexoDTO(int idSexo, String sexoNombre, String fechaCrea, String fechaModifica) {
        IdSexo = idSexo;
        SexoNombre = sexoNombre;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
    }

    public SexoDTO() {
    }

    public int getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(int idSexo) {
        IdSexo = idSexo;
    }

    public String getSexoNombre() {
        return SexoNombre;
    }

    public void setSexoNombre(String sexoNombre) {
        SexoNombre = sexoNombre;
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
        return "IdSexo:" + IdSexo +
                " SexoNombre:" + SexoNombre +
                " FechaCrea:" + FechaCrea +
                " FechaModifica:" + FechaModifica +
                "\n";
    }
}

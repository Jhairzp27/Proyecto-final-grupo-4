package DataAcces.DTO;

public class PersonaDTO {
    private int IdPersona;
    private int IdSexo;
    private int IdRol;
    private String PersonaNombre;
    private String PersonaApellido;
    private String PersonaCedula;
    private String PersonaFechaNacimiento;
    private String FechaCrea;
    private String FechaModifica;
    private String Estado;

    public PersonaDTO(Integer idPersona, int idSexo, int idRol,String personaNombre, String personaApellido, String personaCedula,
            String personaFechaNacimiento, String fechaCrea, String fechaModifica, String estado) {
        IdPersona = idPersona;
        IdSexo = idSexo;
        IdRol = idRol;
        PersonaNombre = personaNombre;
        PersonaApellido = personaApellido;
        PersonaCedula = personaCedula;
        PersonaFechaNacimiento = personaFechaNacimiento;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
        Estado = estado;
    }

    public PersonaDTO() {
    }

    public Integer getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(Integer idPersona) {
        IdPersona = idPersona;
    }

    public int getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(int idSexo) {
        IdSexo = idSexo;
    }

    public int getIdRol() {
        return IdRol;
    }

    public void setIdRol(int idRol) {
        IdRol = idRol;
    }

    public String getPersonaNombre() {
        return PersonaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        PersonaNombre = personaNombre;
    }

    public String getPersonaApellido() {
        return PersonaApellido;
    }

    public void setPersonaApellido(String personaApellido) {
        PersonaApellido = personaApellido;
    }

    public String getPersonaCedula() {
        return PersonaCedula;
    }

    public void setPersonaCedula(String personaCedula) {
        PersonaCedula = personaCedula;
    }

    public String getPersonaFechaNacimiento() {
        return PersonaFechaNacimiento;
    }

    public void setPersonaFechaNacimiento(String personaFechaNacimiento) {
        PersonaFechaNacimiento = personaFechaNacimiento;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return  "\n IdPersona: " + IdPersona +
                "\n IdSexo: " + IdSexo +
                "\n IdRol: " + IdRol +
                "\n PersonaNombre: " + PersonaNombre +
                "\n PersonaApellido: " + PersonaApellido +
                "\n PersonaCedula: " + PersonaCedula +
                "\n PersonaFechaNacimiento: " + PersonaFechaNacimiento +
                "\n FechaCrea: " + FechaCrea +
                "\n FechaModifica: " + FechaModifica +
                "\n Estado: " + Estado +
                "\n";
    }
}

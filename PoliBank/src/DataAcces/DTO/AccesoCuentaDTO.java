package DataAcces.DTO;

public class AccesoCuentaDTO {
    private int IdAccesoCuenta;
    private int IdCuenta;
    private String AccesoCuentaUsuario;
    private String AccesoCuentaClave;
    private String FechaCrea;
    private String FechaModifica;
    private String Estado;

    public AccesoCuentaDTO(int idAccesoCuenta, int idCuenta, String accesoCuentaUsuario, String accesoCuentaClave,
            String fechaCrea, String fechaModifica, String estado) {
        IdAccesoCuenta = idAccesoCuenta;
        IdCuenta = idCuenta;
        AccesoCuentaUsuario = accesoCuentaUsuario;
        AccesoCuentaClave = accesoCuentaClave;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
        Estado = estado;
    }

    public AccesoCuentaDTO() {
    }

    public int getIdAccesoCuenta() {
        return IdAccesoCuenta;
    }

    public void setIdAccesoCuenta(int idAccesoCuenta) {
        IdAccesoCuenta = idAccesoCuenta;
    }

    public int getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        IdCuenta = idCuenta;
    }

    public String getAccesoCuentaUsuario() {
        return AccesoCuentaUsuario;
    }

    public void setAccesoCuentaUsuario(String accesoCuentaUsuario) {
        AccesoCuentaUsuario = accesoCuentaUsuario;
    }

    public String getAccesoCuentaClave() {
        return AccesoCuentaClave;
    }

    public void setAccesoCuentaClave(String accesoCuentaClave) {
        AccesoCuentaClave = accesoCuentaClave;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "IdAccesoCuenta: " + IdAccesoCuenta +
                " IdCuenta: " + IdCuenta +
                " AccesoCuentaUsuario: " + AccesoCuentaUsuario +
                " AccesoCuentaClave: " + AccesoCuentaClave +
                " FechaCrea: " + FechaCrea +
                " FechaModifica: " + FechaModifica +
                " Estado: " + Estado +
                "\n";
    }
}

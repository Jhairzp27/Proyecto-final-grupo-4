package DataAcces.DTO;

public class CuentaDTO {
    private int IdCuenta;
    private int IdPersona;
    private double CuentaSaldo;
    private String FechaCrea;
    private String FechaModifica;
    private String Estado;

    public CuentaDTO(int idCuenta, int idPersona, double cuentaSaldo, String fechaCrea,
            String fechaModifica, String estado) {
        IdCuenta = idCuenta;
        IdPersona = idPersona;
        CuentaSaldo = cuentaSaldo;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
        Estado = estado;
    }

    public CuentaDTO() {
    }

    public int getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        IdCuenta = idCuenta;
    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
    }

    public double getCuentaSaldo() {
        return CuentaSaldo;
    }

    public void setCuentaSaldo(double cuentaSaldo) {
        CuentaSaldo = cuentaSaldo;
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
        return "IdCuenta: " + IdCuenta +
                " IdPersona: " + IdPersona +
                " CuentaSaldo: " + CuentaSaldo +
                " FechaCrea: " + FechaCrea +
                " FechaModifica: " + FechaModifica +
                " Estado: " + Estado +
                "\n";
    }
}

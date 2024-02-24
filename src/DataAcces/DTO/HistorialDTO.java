package DataAcces.DTO;

public class HistorialDTO {
    private int IdHistorial;
    private int IdCuenta;
    private String HistorialMovimiento;
    private String FechaCrea;
    private String FechaModifica;
    private String Estado;

    public HistorialDTO(int idHistorial, int idCuenta, String historialMovimiento, String fechaCrea,
            String fechaModifica, String estado) {
        IdHistorial = idHistorial;
        IdCuenta = idCuenta;
        HistorialMovimiento = historialMovimiento;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
        Estado = estado;
    }

    public HistorialDTO() {
    }

    public int getIdHistorial() {
        return IdHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        IdHistorial = idHistorial;
    }

    public int getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        IdCuenta = idCuenta;
    }

    public String getHistorialMovimiento() {
        return HistorialMovimiento;
    }

    public void setHistorialMovimiento(String historialMovimiento) {
        HistorialMovimiento = historialMovimiento;
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
        return "IdHistorial: " + IdHistorial +
                " IdCuenta: " + IdCuenta +
                " HistorialMovimiento: " + HistorialMovimiento +
                " FechaCrea: " + FechaCrea +
                " FechaModifica: " + FechaModifica +
                " Estado: " + Estado +
                "\n";
    }
}

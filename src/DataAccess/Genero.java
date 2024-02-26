package DataAccess;

public class Genero {
    private String nombre;
    private int idGenero;

    public Genero(String nombre, int idGenero) {
        this.nombre = nombre;
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdGenero() {
        return idGenero;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

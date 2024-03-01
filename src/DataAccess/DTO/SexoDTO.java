package DataAccess.DTO;

/**
 * La clase SexoDTO es un objeto de transferencia de datos Java que representa información sobre un
 * género con propiedades como IdSexo, Nombre, Estado, FechaCrea y FechaModifica.
 */
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

    /**
     * La función devuelve el valor de la variable IdSexo como un número entero.
     * 
     * @return El método `getIdSexo` devuelve un valor `Entero` que representa la propiedad `IdSexo`.
     */
    public Integer getIdSexo() {
        return IdSexo;
    }

    /**
     * Esta función Java establece el valor de la variable "IdSexo" en el valor entero proporcionado.
     * 
     * @param idSexo El parámetro `idSexo` es un Entero que representa el identificador del género
     * (sexo en español).
     */
    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
    }

    /**
     * La función `getNombre()` en Java devuelve el valor de la variable `Nombre`.
     * 
     * @return El método `getNombre()` devuelve el valor de la variable `Nombre`.
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * La función establece el valor de una variable llamada "Nombre" en la cadena de entrada "nombre".
     * 
     * @param nombre El parámetro "nombre" es un parámetro de tipo String que representa el nombre que
     * se desea establecer para un objeto o variable.
     */
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    /**
     * La función `getEstado` en Java devuelve el valor de la variable `Estado`.
     * 
     * @return El método devuelve el valor de la variable "Estado".
     */
    public String getEstado() {
        return Estado;
    }

    public void // El método `setEstado` en la clase `SexoDTO` es un método de establecimiento para la
    // propiedad `Estado`. Toma un parámetro de tipo String llamado `estado` y establece el
    // valor de la propiedad `Estado` al valor pasado como parámetro. Este método se
    // utiliza para actualizar o establecer el estado/estado asociado con el género
    // representado por el objeto `SexoDTO`.
    setEstado(String estado) {
        Estado = estado;
    }

    // El método `getFechaCrea()` en la clase `SexoDTO` es un método getter en Java que recupera el
    // valor de la propiedad `FechaCrea`, que representa la fecha de creación asociada con el objeto de
    // género. Este método devuelve un valor de "Cadena" que representa la fecha de creación del
    // género.
    public String getFechaCrea() {
        return FechaCrea;
    }

    // La línea `public void setFechaCrea(String fechaCrea) {` en la clase `SexoDTO` define un método
    // de establecimiento para la propiedad `FechaCrea`. Este método le permite establecer o actualizar
    // el valor de la propiedad `FechaCrea` de un objeto `SexoDTO` pasando un parámetro `String` que
    // representa la fecha de creación.
    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }

    // El método `getFechaModifica()` en la clase `SexoDTO` es un método getter en Java que recupera el
    // valor de la propiedad `FechaModifica`. Este método devuelve un valor de "Cadena" que representa
    // la fecha de modificación asociada con el objeto de género. Permite acceder a la propiedad
    // `FechaModifica`, proporcionando la fecha en la que se modificó por última vez la información de
    // género.
    public String getFechaModifica() {
        return FechaModifica;
    }

    // La línea `public void setFechaModifica(String fechaModifica) {` en la clase `SexoDTO` define un
    // método de establecimiento para la propiedad `FechaModifica`. Este método le permite establecer o
    // actualizar el valor de la propiedad `FechaModifica` de un objeto `SexoDTO` pasando un parámetro
    // `String` que representa la fecha de modificación.
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    @Override
    // El método `public String toString()` en la clase `SexoDTO` anula el método `toString()`
    // predeterminado proporcionado por la clase `Object` en Java.
    public String toString() {
        return "\n" + getClass().getName() 
             + "\n IdSexo:        " + getIdSexo()
             + "\n Nombre:        " + getNombre()
             + "\n Estado:        " + getEstado()
             + "\n FechaCrea:     " + getFechaCrea()
             + "\n FechaModifica: " + getFechaModifica();
    }
}

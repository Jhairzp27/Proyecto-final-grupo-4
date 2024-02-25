package Framework;

public class NewException extends Exception {

    public NewException(String e, String clase, String metodo) {
        System.out.println("[ERROR EN PoliBank para el log] " + clase + "." + metodo + " : " + e);
    }
    
}
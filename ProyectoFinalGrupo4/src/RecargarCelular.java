import java.util.Scanner;

public class RecargarCelular {
    private String nroCelular;
    private Float cantidad;
    private String[] operadoras = {"Claro","CNT", "Movistar"};
    private Scanner scanner;


    public RecargarCelular() {
        // Inicialización del Scanner en el constructor
        this.scanner = new Scanner(System.in);
    }

    public void validarNumeroCelular() {
        System.out.println("Ingresa un número de celular: ");
        String numeroCelular = scanner.nextLine();

        try {
            validarNumeroCelular(numeroCelular);
            System.out.println("El número de celular es válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("El número de celular ingresado no es válido.");
        }
    }

    public void validarNumeroCelular(String numero) {
        if (numero.length() != 10 || numero.charAt(0) != '0' || !numero.matches("[0-9]+")) {
            throw new IllegalArgumentException("El número de celular no es válido");
        }
    }
}

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1. Transferir dinero \n2. Pagar servicios\n3. Consultar Saldos  ");
        System.out.println("4. Invertir \n5. Recargar celular\n6. Cambiar contraseña  ");
        System.out.println("7. Estado de Cuenta \n8. Imprimir Estado de Cuenta ");
        int opcionMenu = Integer.parseInt(sc.nextLine());
        BancaVirtual oBancaVirtual = new BancaVirtual();
        switch (opcionMenu) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
                oBancaVirtual.recargarCelular();
                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;

            default:
                break;
        }
    }

}

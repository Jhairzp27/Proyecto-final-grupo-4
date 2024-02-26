import UserInterface.Form.MainForm;
import UserInterface.GUI.PnlLogin;

public class App {
    public static void main(String[] args) {
        PnlLogin login = new PnlLogin();
        login.setVisible(true); // Mostrar la ventana de Login al inicio
        login.addLoginSuccessListener(() -> {
            login.dispose(); // Cerrar la ventana de Login una vez que se haya iniciado sesión correctamente
            MainForm mainForm = new MainForm("PoliBank"); // Crear y mostrar el MainForm
            mainForm.setVisible(true);
        });
    }
}

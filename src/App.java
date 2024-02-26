import UserInterface.Form.MainForm;
import UserInterface.GUI.PnlLogin;

public class App {
    public static void main(String[] args) throws Exception {
        PnlLogin login = new PnlLogin();
        login.setVisible(true);
        login.addLoginSuccessListener(() -> {
            login.dispose();
            MainForm mainForm = new MainForm("PoliBank", login.getUsuarioLogeado(), login);
            mainForm.setVisible(true);
        });
    }
}

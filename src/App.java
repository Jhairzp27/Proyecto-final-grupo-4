import DataAccess.DatabaseInitializer;
import UserInterface.Form.MainForm;
import UserInterface.Form.SplashScreenForm;
import UserInterface.GUI.PnlLogin;

public class App {
    public static void main(String[] args) throws Exception {
        SplashScreenForm.show();
        DatabaseInitializer.initializeDatabase();
        PnlLogin login = new PnlLogin();
        login.setVisible(true);
        login.addLoginSuccessListener(() -> {
            login.dispose();
            MainForm mainForm = new MainForm("PoliBank", login.getUsuarioLogeado(), login);
            mainForm.setVisible(true);
        });
    }
}

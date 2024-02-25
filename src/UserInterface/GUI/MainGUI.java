package UserInterface.GUI;

import DataAccess.UserDAO;
import UserInterface.Form.Login;
import UserInterface.Form.RegistrationForm;

public class MainGUI {
    Login login = new Login();
    RegistrationForm registrarse = new RegistrationForm(login);
    UserDAO user = new UserDAO();

    public static void main(String[] args) {
        UserDAO.crearTabla();
        new MainGUI();
    }
}

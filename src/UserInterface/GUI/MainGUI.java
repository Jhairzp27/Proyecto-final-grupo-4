package UserInterface.GUI;

import DataAccess.UserDAO;

public class MainGUI {
    PnlLogin login = new PnlLogin();
    PnlRegistrationForm registrarse = new PnlRegistrationForm(login);
    UserDAO user = new UserDAO();
}

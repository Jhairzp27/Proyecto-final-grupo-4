package DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private static final String DBUser_URL = "jdbc:sqlite:database//DataBasePoliBank.sqlite";

    public static void crearTabla(){
        try (Connection connection = DriverManager.getConnection(DBUser_URL)){
            String crearTablaQuery = "CREATE TABLE IF NOT EXISTS Usuarios (IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Username TEXT UNIQUE, Password TEXT, Email TEXT UNIQUE)";
            connection.createStatement().executeUpdate(crearTablaQuery);
        } catch (Exception e) {
            System.out.println("Error al crear tabla" + e.getMessage());
        }
    }

    public static void registrarUsuario(String Nombre, String Username, String Password, String Email){
        try (Connection connection = DriverManager.getConnection(DBUser_URL)){
            String insertarQuery = "INSERT INTO Usuarios (Nombre, Username, Password, Email) VALUES(?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(insertarQuery);
            pst.setString(1, Nombre);
            pst.setString(2, Username);
            pst.setString(3, Password);
            pst.setString(4, Email);

            pst.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar el usuario."+e.getMessage());
        }
    }

    public static boolean loginUsuario(String Username, String Password){
        try (Connection connection = DriverManager.getConnection(DBUser_URL)){
            String selectQuery = "SELECT * FROM Usuarios WHERE Username = ? AND Password = ?";
            PreparedStatement pst = connection.prepareStatement(selectQuery);
            pst.setString(1,Username);
            pst.setString(2,Password);
            ResultSet  result = pst.executeQuery();
            return result.next();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            return false;
        }
    }
}

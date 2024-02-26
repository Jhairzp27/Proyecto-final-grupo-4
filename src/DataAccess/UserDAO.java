package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private static final String DBUser_URL = "jdbc:sqlite:database//DataBasePoliBank.sqlite";

    public static void crearTabla() {
        try (Connection connection = DriverManager.getConnection(DBUser_URL)) {
            String crearTablaQuery = "CREATE TABLE IF NOT EXISTS Usuario (IdUsuario INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT, Cedula TEXT UNIQUE, Username TEXT UNIQUE, Password TEXT, Email TEXT UNIQUE, IdSexo INTEGER REFERENCES Sexo(IdSexo), Saldo REAL NOT NULL DEFAULT (0.0), Estado VARCHAR(1) NOT NULL DEFAULT 'A', FechaCreacion DATETIME NOT NULL DEFAULT (DATETIME('NOW', 'LOCALTIME')), FechaModificacion DATE)";
            connection.createStatement().executeUpdate(crearTablaQuery);
        } catch (Exception e) {
            System.out.println("Error al crear tabla" + e.getMessage());
        }
    }

    public static void registrarUsuario(String Nombre, String Cedula,String Username, String Password, String Email, int IdSexo) {
        try (Connection connection = DriverManager.getConnection(DBUser_URL)) {
            String insertarQuery = "INSERT INTO Usuario(Nombre, Cedula, Username, Password, Email, IdSexo) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(insertarQuery);
            pst.setString(1, Nombre);
            pst.setString(2, Cedula);
            pst.setString(3, Username);
            pst.setString(4, Password);
            pst.setString(5, Email);
            pst.setInt(6, IdSexo);

            pst.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al registrar el usuario." + e.getMessage());
        }
    }

    public static boolean loginUsuario(String Username, String Password) {
        try (Connection connection = DriverManager.getConnection(DBUser_URL)) {
            String selectQuery = "SELECT * FROM Usuario WHERE Username = ? AND Password = ?";
            PreparedStatement pst = connection.prepareStatement(selectQuery);
            pst.setString(1, Username);
            pst.setString(2, Password);
            ResultSet result = pst.executeQuery();
            return result.next();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            return false;
        }
    }
}

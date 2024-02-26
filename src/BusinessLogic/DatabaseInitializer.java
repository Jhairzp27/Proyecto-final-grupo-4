package BusinessLogic;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String DB_URSexo = "jdbc:sqlite:database/DataBasePoliBank.sqlite";

    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URSexo)) {
            executeScript("Script/DDL_PoliBank.sql", connection);
            executeScript("Script//DML_PoliBank.sql", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeScript(String scriptPath, Connection connection) {
        try {
            String scriptContent = Files.readString(Paths.get(scriptPath));
            Statement statement = connection.createStatement();
            statement.executeUpdate(scriptContent);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}

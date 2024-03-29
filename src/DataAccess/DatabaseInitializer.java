/*
|----------------------------------------|
| (©) 2K24 EPN-FIS, All rights reserved. |
| jhair.zambrano@epn.edu.ec   Jhairzp27  |
|----------------------------------------|
Autor : Jhairzp27
Fecha : 26.feb.2k24
*/
package DataAccess;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Framework.NewException;

public class DatabaseInitializer {
    private static final String DB_URSexo = "jdbc:sqlite:database/DataBasePoliBank.sqlite";

    public static void initializeDatabase() throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URSexo)) {
            executeScript("Script/DDL_PoliBank.sql", connection);
            executeScript("Script//DML_PoliBank.sql", connection);
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), DatabaseInitializer.class.getName(), "initializeDatabase()");
        }
    }

    private static void executeScript(String scriptPath, Connection connection) throws Exception {
        try {
            String scriptContent = Files.readString(Paths.get(scriptPath));
            Statement statement = connection.createStatement();
            statement.executeUpdate(scriptContent);
        } catch (IOException | SQLException e) {
            throw new NewException(e.getMessage(), DatabaseInitializer.class.getName(), "executeScript()");
        }
    }

}

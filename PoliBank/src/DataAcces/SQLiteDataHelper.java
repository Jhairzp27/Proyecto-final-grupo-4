package DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SQLiteDataHelper {
    private static String direccionConeccionBaseDatos = "jdbc:sqlite:database//DataBasePoliBank.sqlite";
    private static Connection coneccion = null;

    protected SQLiteDataHelper() {

    }

    protected static synchronized Connection abrirConexion() throws Exception {
        try {
            if (coneccion == null)
                coneccion = DriverManager.getConnection(direccionConeccionBaseDatos);

        } catch (SQLException e) {
            throw e;
        }
        return coneccion;
    }

    protected static void cerrarConeccion() throws Exception {
        try {
            if (coneccion != null)
                coneccion.close();
        } catch (SQLException e) {
            throw e;
        }
    }
}

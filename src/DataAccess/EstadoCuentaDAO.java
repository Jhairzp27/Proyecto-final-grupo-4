package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DTO.EstadoCuentaDTO;
import Framework.NewException;

public class EstadoCuentaDAO extends SQLiteDataHelper {
    public ArrayList<EstadoCuentaDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        ArrayList<EstadoCuentaDTO> movimientos = new ArrayList<>();
        String consulta = "SELECT t.Fecha Fecha,                                               "
                        + "       t.IdTransferencia IdMovimiento,                              "
                        + "       CASE                                                         "
                        + "           WHEN t.IdUsuarioEnvia = -1 THEN 'Recarga'                "
                        + "           ELSE 'Transferencia'                                     "
                        + "       END Descripcion,                                             "
                        + "       CASE                                                         "
                        + "           WHEN t.IdUsuarioEnvia = ? THEN '-' || t.Monto            " 
                        + "           ELSE '+' || t.Monto                                      "
                        + "       END Monto,                                                   "
                        + "       (SELECT SUM(CASE                                             "
                        + "                        WHEN t1.IdUsuarioEnvia = ? THEN -t1.Monto   "
                        + "                        ELSE t1.Monto                               "
                        + "                    END)                                            "
                        + "        FROM Transferencia t1                                       "
                        + "        WHERE t1.Fecha <= t.Fecha                                   "
                        + "              AND (t1.IdUsuarioEnvia = ? OR t1.IdUsuarioRecibe = ?) "
                        + "              AND t1.Estado = 'A') Saldo                            "
                        + "FROM Transferencia t                                                "
                        + "LEFT JOIN Usuario uenvia ON t.IdUsuarioEnvia = uenvia.IdUsuario     "
                        + "JOIN Usuario urecibe ON t.IdUsuarioRecibe = urecibe.IdUsuario       "
                        + "WHERE t.Estado = 'A'                                                "
                        + "AND (t.IdUsuarioEnvia = ? OR t.IdUsuarioRecibe = ?)                 ";

        try {
            Connection conexion            = abrirConexion();
            PreparedStatement  declaracion = conexion.prepareStatement(consulta);
            for (int i = 1; i <= 6; i++)
                declaracion.setInt(i, idUsuarioLogeado);
            ResultSet  conjuntoResultante = declaracion.executeQuery();
            while (conjuntoResultante.next()) {
                EstadoCuentaDTO EstadoCuentaDTO = new EstadoCuentaDTO(conjuntoResultante.getString(1),
                                                                      conjuntoResultante.getInt(2),
                                                                      conjuntoResultante.getString(3),
                                                                      conjuntoResultante.getString(4),
                                                                      conjuntoResultante.getFloat(5));
                movimientos.add(EstadoCuentaDTO);
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPorUsuarioActual()");
        }
        return movimientos;
    }
}

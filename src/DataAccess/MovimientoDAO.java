package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataAccess.DTO.MovimientoDTO;
import Framework.NewException;

public class MovimientoDAO extends SQLiteDataHelper {
    public ArrayList<MovimientoDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        ArrayList<MovimientoDTO> movimientos = new ArrayList<>();
        String consulta = "SELECT t.IdTransferencia IdTransferencia,                     "
                        + "uenvia.Nombre UsuarioEnvia,                                   "
                        + "urecibe.Nombre UsuarioRecibe,                                 "
                        + "t.Monto Monto,                                                "
                        + "t.Fecha Fecha                                                 "
                        + "FROM Transferencia t                                          "
                        + "JOIN Usuario uenvia ON t.IdUsuarioEnvia = uenvia.IdUsuario    "
                        + "JOIN Usuario urecibe ON t.IdUsuarioRecibe = urecibe.IdUsuario "
                        + "WHERE t.Estado = 'A'                                          "
                        + "AND (t.IdUsuarioEnvia = ? OR t.IdUsuarioRecibe = ?)           ";
        try {
            Connection conexion            = abrirConexion();
            PreparedStatement  declaracion = conexion.prepareStatement(consulta);
            declaracion.setInt(1, idUsuarioLogeado);
            declaracion.setInt(2, idUsuarioLogeado);
            ResultSet  conjuntoResultante = declaracion.executeQuery();
            while (conjuntoResultante.next()) {
                MovimientoDTO MovimientoDTO = new MovimientoDTO(conjuntoResultante.getInt(1),
                                                                         conjuntoResultante.getString(2), 
                                                                         conjuntoResultante.getString(3),
                                                                         conjuntoResultante.getFloat(4),
                                                                         conjuntoResultante.getString(5));
                movimientos.add(MovimientoDTO);
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPorUsuarioActual()");
        }
        return movimientos;
    }
}

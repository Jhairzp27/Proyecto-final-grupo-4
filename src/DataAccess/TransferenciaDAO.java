package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.TransferenciaDTO;
import Framework.NewException;

public class TransferenciaDAO extends SQLiteDataHelper implements IDAO<TransferenciaDTO> {
    @Override
    public boolean crear(TransferenciaDTO entidad) throws Exception {
        String consulta = "INSERT INTO Transferencia (IdUsuarioEnvia, IdUsuarioRecibe, Monto, Fecha) VALUES (?, ?, ?, ?)";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setInt(1, entidad.getIdUsuarioEnvia());
            declaracion.setInt(2, entidad.getIdUsuarioRecibe());
            declaracion.setFloat(3, entidad.getMonto());
            declaracion.setString(4, entidad.getFecha());
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "crear()");
        }

    }

    @Override
    public List<TransferenciaDTO> leerTodo() throws Exception {
        String consulta = "SELECT * FROM Transferencia WHERE Estado = 'A'";
        List<TransferenciaDTO> lista = new ArrayList<>();
        try {
            Connection conexion           = abrirConexion();
            Statement  declaracion        = conexion.createStatement();
            ResultSet  conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                TransferenciaDTO transferenciaDTO = new TransferenciaDTO(conjuntoResultante.getInt(1),
                                                                         conjuntoResultante.getInt(2), 
                                                                         conjuntoResultante.getInt(3),
                                                                         conjuntoResultante.getFloat(4),
                                                                         conjuntoResultante.getString(5),
                                                                         conjuntoResultante.getString(6),
                                                                         conjuntoResultante.getString(7),
                                                                         conjuntoResultante.getString(8));
                lista.add(transferenciaDTO);
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerTodo()");
        }
        return lista;
    }

    @Override
    public TransferenciaDTO leerPor(Integer id) throws Exception {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        String consulta = "SELECT * FROM Transferencia WHERE Estado = 'A' AND IdTransferencia = " + id.toString();
        try {
            Connection conexion          = abrirConexion();
            Statement declaracion        = conexion.createStatement();
            ResultSet conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                transferenciaDTO = new TransferenciaDTO(conjuntoResultante.getInt(1),
                                                        conjuntoResultante.getInt(2), 
                                                        conjuntoResultante.getInt(3),
                                                        conjuntoResultante.getFloat(4),
                                                        conjuntoResultante.getString(5),
                                                        conjuntoResultante.getString(6),
                                                        conjuntoResultante.getString(7),
                                                        conjuntoResultante.getString(8));
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPor()");
        }
        return transferenciaDTO;
    }

    @Override
    public boolean actualizar(TransferenciaDTO entidad) throws Exception {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actual = LocalDateTime.now();
        String consulta = "UPDATE Transferencia SET IdUsuarioEnvia = ?, "
                        + "IdUsuarioRecibe = ?,                         "
                        + "Monto = ?,                                   "
                        + "Fecha = ?,                                   "
                        + "FechaModifica = ?                            "
                        + "WHERE IdTransferencia = ?                    ";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setInt(1, entidad.getIdUsuarioEnvia());
            declaracion.setInt(2, entidad.getIdUsuarioRecibe());
            declaracion.setFloat(3, entidad.getMonto());
            declaracion.setString(4, entidad.getFecha());
            declaracion.setString(5, formatoFecha.format(actual).toString());
            declaracion.setInt(6, entidad.getIdTransferencia());
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "actualizar()");
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String consulta = "UPDATE Transferencia SET Estado = ? WHERE IdTransferencia = " + id.toString();
        try {
            Connection conexion                = abrirConexion();
            PreparedStatement declaracion      = conexion.prepareStatement(consulta);
            declaracion.setString(1, "X");
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "eliminar()");
        }
    }
}

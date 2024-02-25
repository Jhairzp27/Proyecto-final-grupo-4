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

import DataAccess.DTO.SexoDTO;
import Framework.NewException;

public class SexoDAO extends SQLiteDataHelper implements IDAO<SexoDTO> {
    @Override
    public boolean crear(SexoDTO entidad) throws Exception {
        String consulta = "INSERT INTO Sexo (Nombre) VALUES (?)";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1, entidad.getNombre());
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "crear()");
        }

    }

    @Override
    public List<SexoDTO> leerTodo() throws Exception {
        String consulta = "SELECT * FROM Sexo WHERE Estado = 'A'";
        List<SexoDTO> lista = new ArrayList<>();
        try {
            Connection conexion           = abrirConexion();
            Statement  declaracion        = conexion.createStatement();
            ResultSet  conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                SexoDTO sexoDTO = new SexoDTO(conjuntoResultante.getInt(1),
                                              conjuntoResultante.getString(2), 
                                              conjuntoResultante.getString(3),
                                              conjuntoResultante.getString(4),
                                              conjuntoResultante.getString(5));
                lista.add(sexoDTO);
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerTodo()");
        }
        return lista;
    }

    @Override
    public SexoDTO leerPor(Integer id) throws Exception {
        SexoDTO sexoDTO = new SexoDTO();
        String consulta = "SELECT * FROM Sexo WHERE Estado = 'A' AND IdSexo = " + id.toString();
        try {
            Connection conexion          = abrirConexion();
            Statement declaracion        = conexion.createStatement();
            ResultSet conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                sexoDTO = new SexoDTO(conjuntoResultante.getInt(1),
                                      conjuntoResultante.getString(2), 
                                      conjuntoResultante.getString(3),
                                      conjuntoResultante.getString(4),
                                      conjuntoResultante.getString(5));
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPor()");
        }
        return sexoDTO;
    }

    @Override
    public boolean actualizar(SexoDTO entidad) throws Exception {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actual = LocalDateTime.now();
        String consulta = "UPDATE Sexo SET Nombre = ?, "
                        + "FechaModifica = ?           "
                        + "WHERE IdSexo = ?            ";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1, entidad.getNombre());
            declaracion.setString(2, formatoFecha.format(actual).toString());
            declaracion.setInt(3, entidad.getIdSexo());
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "actualizar()");
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String consulta = "UPDATE Sexo SET Estado = ? WHERE IdSexo = " + id.toString();
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

    @Override
    public Integer getMaxId() throws Exception {
        int maxId = 0;
        String consulta = "SELECT MAX(IdSexo) FROM Sexo WHERE Estado = 'A'";
        try {
            Connection conexion           = abrirConexion();
            Statement  declaracion        = conexion.createStatement();
            ResultSet  conjuntoResultante = declaracion.executeQuery(consulta);
            if (conjuntoResultante.next())
                maxId = conjuntoResultante.getInt(1);
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "getMaxId()");
        }
        return maxId;
    }
}

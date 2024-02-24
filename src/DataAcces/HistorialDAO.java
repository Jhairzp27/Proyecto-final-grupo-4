package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAcces.DTO.HistorialDTO;

public class HistorialDAO extends SQLiteDataHelper implements IDAO<HistorialDTO> {

    @Override
    public boolean crear(HistorialDTO entidad) throws Exception {
        String query = "INSERT INTO Historial (IdCuenta, HistorialMovimiento) VALUES (?, ?)";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidad.getIdCuenta());
            preparedStatement.setString(2, entidad.getHistorialMovimiento());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HistorialDTO> leerTodo() throws Exception {
        List<HistorialDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Historial WHERE Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                HistorialDTO historialDTO = new HistorialDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                lista.add(historialDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public HistorialDTO leer(Integer id) throws Exception {
        String query = "SELECT * FROM Historial WHERE IdHistorial=" + id.toString() + " AND Estado = 'A'";
        HistorialDTO historialDTO = new HistorialDTO();
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                historialDTO = new HistorialDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historialDTO;
    }

    @Override
    public boolean actualizar(HistorialDTO entidad) throws Exception {
        String query = "UPDATE Historial SET IdCuenta=?, HistorialMovimiento=? WHERE IdHistorial = ? AND Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidad.getIdCuenta());
            preparedStatement.setString(2, entidad.getHistorialMovimiento());
            preparedStatement.setInt(3, entidad.getIdHistorial());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String query = "UPDATE HISTORIAL SET Estado=? WHERE  IdHistorial = " + id.toString();
        try {
            Connection connection = abrirConexion();
            PreparedStatement prearedStatement = connection.prepareStatement(query);
            prearedStatement.setString(1, "X");
            prearedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
}
package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAcces.DTO.AccesoCuentaDTO;

public class AccesoCuentaDAO extends SQLiteDataHelper implements IDAO<AccesoCuentaDTO> {

    @Override
    public boolean crear(AccesoCuentaDTO entidad) throws Exception {
        String query = "INSERT INTO AccesoCuenta (AccesoCuentaClave) VALUES (?)";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entidad.getAccesoCuentaClave());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AccesoCuentaDTO> leerTodo() throws Exception {
        List<AccesoCuentaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM AccesoCuenta WHERE Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AccesoCuentaDTO accesoCuentaDTO = new AccesoCuentaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                lista.add(accesoCuentaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public AccesoCuentaDTO leer(Integer id) throws Exception {
        String query = "SELECT * FROM AccesoCuenta WHERE IdAccesoCuenta=" + id.toString() + " AND Estado = 'A'";
        AccesoCuentaDTO accesoCuentaDTO = new AccesoCuentaDTO();
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                accesoCuentaDTO = new AccesoCuentaDTO(
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
        return accesoCuentaDTO;
    }

    @Override
    public boolean actualizar(AccesoCuentaDTO entidad) throws Exception {
        String query = "UPDATE AccesoCuenta SET IdCuenta=?, AccesoCuentaClave=? WHERE IdAccesoCuenta = ? AND Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidad.getIdCuenta());
            preparedStatement.setString(2, entidad.getAccesoCuentaClave());
            preparedStatement.setInt(4, entidad.getIdAccesoCuenta());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String query = "UPDATE CUENTA SET Estado=? WHERE  IdCuenta = " + id.toString();
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

package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAcces.DTO.CuentaDTO;

public class CuentaDAO extends SQLiteDataHelper implements IDAO<CuentaDTO> {

    @Override
    public boolean crear(CuentaDTO entidad) throws Exception {
        String query = "INSERT INTO Cuenta (IdPersona, CuentaSaldo) VALUES (?, ?)";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entidad.getIdPersona());
            preparedStatement.setDouble(2, entidad.getCuentaSaldo());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CuentaDTO> leerTodo() throws Exception {
        List<CuentaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Cuenta WHERE Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CuentaDTO cuentaDTO = new CuentaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                lista.add(cuentaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public CuentaDTO leer(Integer id) throws Exception {
        String query = "SELECT * FROM Cuenta WHERE IdCuenta=" + id.toString() + " AND Estado = 'A'";
        CuentaDTO cuentaDTO = new CuentaDTO();
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                cuentaDTO = new CuentaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDouble(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuentaDTO;
    }

    @Override
    public boolean actualizar(CuentaDTO entidad) throws Exception {
        String query = "UPDATE Cuenta SET IdPersona=?, CuentaSaldo=? WHERE IdCuenta = ? AND Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidad.getIdPersona());
            preparedStatement.setDouble(2, entidad.getCuentaSaldo());
            preparedStatement.setInt(4, entidad.getIdCuenta());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String query = "UPDATE Cuenta SET Estado=? WHERE  IdCuenta = " + id.toString();
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

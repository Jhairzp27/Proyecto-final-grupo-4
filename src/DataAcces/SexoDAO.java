package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAcces.DTO.SexoDTO;

public class SexoDAO extends SQLiteDataHelper implements IDAO<SexoDTO> {

    @Override
    public boolean crear(SexoDTO entidad) throws Exception {
        String query = "INSERT INTO SEXO (SexoNombre) VALUES(?)";
        try {
            Connection connection = abrirConexion();
            PreparedStatement prearedStatement = connection.prepareStatement(query);
            prearedStatement.setString(1, entidad.getSexoNombre());
            prearedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }

    }

    @Override
    public List<SexoDTO> leerTodo() throws Exception {
        String query = "SELECT * FROM SEXO WHERE Estado = 'A'";
        List<SexoDTO> lista = new ArrayList<>();
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                SexoDTO sexoDTO = new SexoDTO(resultSet.getInt(1),
                                              resultSet.getString(2), 
                                              resultSet.getString(3),
                                              resultSet.getString(4),
                                              resultSet.getString(5));
                lista.add(sexoDTO);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    @Override
    public SexoDTO leer(Integer id) throws Exception {
        String query = "SELECT * FROM SEXO WHERE IdSexo=" + id.toString() + " AND Estado = 'A'";
        SexoDTO oSexoDTO = new SexoDTO();
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                oSexoDTO = new SexoDTO(resultSet.getInt(1),
                                       resultSet.getString(2), 
                                       resultSet.getString(3),
                                       resultSet.getString(4),
                                       resultSet.getString(5));
            }
        } catch (SQLException e) {
        }
        return oSexoDTO;
    }

    @Override
    public boolean actualizar(SexoDTO entidad) throws Exception {
        String query = "UPDATE SEXO SET SexoNombre=? WHERE IdSexo = ? ";
        try {
            Connection connection = abrirConexion();
            PreparedStatement prearedStatement = connection.prepareStatement(query);
            prearedStatement.setString(1, entidad.getSexoNombre());
            prearedStatement.setInt(2, entidad.getIdSexo());
            prearedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String query = "UPDATE SEXO SET Estado=? WHERE  IdSexo = " + id.toString();
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

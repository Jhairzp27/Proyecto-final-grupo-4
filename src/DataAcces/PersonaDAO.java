package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAcces.DTO.PersonaDTO;

public class PersonaDAO extends SQLiteDataHelper implements IDAO<PersonaDTO> {

    @Override
    public boolean crear(PersonaDTO entidad) throws Exception {
        String query = "INSERT INTO Persona (IdSexo, IdRol, PersonaNombre, PersonaApellido, PersonaCedula, PersonaFechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entidad.getIdSexo());
            preparedStatement.setInt(2, entidad.getIdRol());
            preparedStatement.setString(3, entidad.getPersonaNombre());
            preparedStatement.setString(4, entidad.getPersonaApellido());
            preparedStatement.setString(5, entidad.getPersonaCedula());
            preparedStatement.setString(6, entidad.getPersonaFechaNacimiento());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PersonaDTO> leerTodo() throws Exception {
        List<PersonaDTO> lista = new ArrayList<>();
        String query = "SELECT * FROM Persona WHERE Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PersonaDTO personaDTO = new PersonaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
                lista.add(personaDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public PersonaDTO leer(Integer id) throws Exception {
        String query = "SELECT * FROM Persona WHERE IdPersona=" + id.toString() + " AND Estado = 'A'";
        PersonaDTO personaDTO = new PersonaDTO();
        try {
            Connection connection = abrirConexion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                personaDTO = new PersonaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personaDTO;
    }

    @Override
    public boolean actualizar(PersonaDTO entidad) throws Exception {
        String query = "UPDATE PERSONA SET IdSexo=?, IdRol=?, PersonaNombre=?, PersonaApellido=?, PersonaCedula=?, PersonaFechaNacimiento=? WHERE IdPersona = ? AND Estado = 'A'";
        try {
            Connection connection = abrirConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidad.getIdSexo());
            preparedStatement.setInt(2, entidad.getIdRol());
            preparedStatement.setString(3, entidad.getPersonaNombre());
            preparedStatement.setString(4, entidad.getPersonaApellido());
            preparedStatement.setString(5, entidad.getPersonaCedula());
            preparedStatement.setString(6, entidad.getPersonaFechaNacimiento());
            preparedStatement.setInt(7, entidad.getIdPersona());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String query = "UPDATE PERSONA SET Estado=? WHERE  IdPersona = " + id.toString();
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

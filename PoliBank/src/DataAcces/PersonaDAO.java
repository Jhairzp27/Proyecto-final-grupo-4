package DataAcces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DataAcces.DTO.PersonaDTO;

public class PersonaDAO extends SQLiteDataHelper implements IDAO<PersonaDTO> {

    @Override
    public boolean crear(PersonaDTO entidad) throws Exception {
        String query = "INSERT INTO Persona (IdSexo, PersonaNombre, PersonaApellido, PersonaCedula, PersonaFechaNacimiento) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = abrirConeccion();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, entidad.getIdSexo());
            preparedStatement.setString(2, entidad.getPersonaNombre());
            preparedStatement.setString(3, entidad.getPersonaApellido());
            preparedStatement.setString(4, entidad.getPersonaCedula());
            preparedStatement.setDate(5, java.sql.Date.valueOf(entidad.getPersonaFechaNacimiento()));
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
            Connection connection = abrirConeccion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PersonaDTO personaDTO = new PersonaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9));
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
            Connection connection = abrirConeccion();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                personaDTO = new PersonaDTO(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personaDTO;
    }

    @Override
    public boolean actualizar(PersonaDTO entidad) throws Exception {
        String query = "UPDATE Persona SET IdSexo=?, PersonaNombre=?, PersonaApellido=?, PersonaCedula=?, PersonaFechaNacimiento=?, FechaModifica=? WHERE IdPersona = ? AND Estado = 'A'";
        try {
            Connection connection = abrirConeccion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entidad.getIdSexo());
            preparedStatement.setString(2, entidad.getPersonaNombre());
            preparedStatement.setString(3, entidad.getPersonaApellido());
            preparedStatement.setString(4, entidad.getPersonaCedula());
            preparedStatement.setDate(5, java.sql.Date.valueOf(entidad.getPersonaFechaNacimiento()));
            preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
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
            Connection connection = abrirConeccion();
            PreparedStatement prearedStatement = connection.prepareStatement(query);
            prearedStatement.setString(1, "X");
            prearedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
}

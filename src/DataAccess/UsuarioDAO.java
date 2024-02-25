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

import DataAccess.DTO.UsuarioDTO;
import Framework.NewException;

public class UsuarioDAO extends SQLiteDataHelper implements IDAO<UsuarioDTO> {
    @Override
    public boolean crear(UsuarioDTO entidad) throws Exception {
        String consulta = "INSERT INTO Usuario (Nombre, Cedula, IdSexo, Clave) VALUES (?, ?, ?, ?)";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1, entidad.getNombre());
            declaracion.setString(2, entidad.getCedula());
            declaracion.setInt(3, entidad.getIdSexo());
            declaracion.setString(4, entidad.getClave());
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "crear()");
        }

    }

    @Override
    public List<UsuarioDTO> leerTodo() throws Exception {
        String consulta = "SELECT * FROM Usuario WHERE Estado = 'A'";
        List<UsuarioDTO> lista = new ArrayList<>();
        try {
            Connection conexion           = abrirConexion();
            Statement  declaracion        = conexion.createStatement();
            ResultSet  conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                UsuarioDTO usuarioDTO = new UsuarioDTO(conjuntoResultante.getInt(1),
                                                       conjuntoResultante.getString(2), 
                                                       conjuntoResultante.getString(3),
                                                       conjuntoResultante.getInt(4),
                                                       conjuntoResultante.getString(5),
                                                       conjuntoResultante.getFloat(6),
                                                       conjuntoResultante.getString(7),
                                                       conjuntoResultante.getString(8),
                                                       conjuntoResultante.getString(9));
                lista.add(usuarioDTO);
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerTodo()");
        }
        return lista;
    }

    @Override
    public UsuarioDTO leerPor(Integer id) throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        String consulta = "SELECT * FROM Usuario WHERE Estado = 'A' AND IdUsuario = " + id.toString();
        try {
            Connection conexion          = abrirConexion();
            Statement declaracion        = conexion.createStatement();
            ResultSet conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                usuarioDTO = new UsuarioDTO(conjuntoResultante.getInt(1),
                                            conjuntoResultante.getString(2), 
                                            conjuntoResultante.getString(3),
                                            conjuntoResultante.getInt(4),
                                            conjuntoResultante.getString(5),
                                            conjuntoResultante.getFloat(6),
                                            conjuntoResultante.getString(7),
                                            conjuntoResultante.getString(8),
                                            conjuntoResultante.getString(9));
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPor()");
        }
        return usuarioDTO;
    }

    @Override
    public boolean actualizar(UsuarioDTO entidad) throws Exception {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actual = LocalDateTime.now();
        String consulta = "UPDATE Usuario SET Nombre = ?, "
                        + "Cedula = ?,                    "
                        + "IdSexo = ?,                    "
                        + "Clave = ?,                     "
                        + "FechaModifica = ?              "
                        + "WHERE IdUsuario = ?            ";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1, entidad.getNombre());
            declaracion.setString(2, entidad.getCedula());
            declaracion.setInt(3, entidad.getIdSexo());
            declaracion.setString(4, entidad.getClave());
            declaracion.setString(5, formatoFecha.format(actual).toString());
            declaracion.setInt(6, entidad.getIdUsuario());
            declaracion.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "actualizar()");
        }
    }

    @Override
    public boolean eliminar(Integer id) throws Exception {
        String consulta = "UPDATE Usuario SET Estado = ? WHERE IdUsuario = " + id.toString();
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
        String consulta = "SELECT MAX(IdUsuario) FROM Usuario WHERE Estado = 'A'";
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

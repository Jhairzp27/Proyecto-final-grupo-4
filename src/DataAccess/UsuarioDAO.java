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
        String consulta = "INSERT INTO Usuario (Nombre, Cedula, Username, Clave, Email, IdSexo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1, entidad.getNombre());
            declaracion.setString(2, entidad.getCedula());
            declaracion.setString(3, entidad.getUsername());
            declaracion.setString(4, entidad.getClave());
            declaracion.setString(5, entidad.getEmail());
            declaracion.setInt(6, entidad.getIdSexo());
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
                                                       conjuntoResultante.getString(4),
                                                       conjuntoResultante.getString(5),
                                                       conjuntoResultante.getString(6),
                                                       conjuntoResultante.getInt(7),
                                                       conjuntoResultante.getFloat(8),
                                                       conjuntoResultante.getString(9),
                                                       conjuntoResultante.getString(10),
                                                       conjuntoResultante.getString(11));
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
                                            conjuntoResultante.getString(4),
                                            conjuntoResultante.getString(5),
                                            conjuntoResultante.getString(6),
                                            conjuntoResultante.getInt(7),
                                            conjuntoResultante.getFloat(8),
                                            conjuntoResultante.getString(9),
                                            conjuntoResultante.getString(10),
                                            conjuntoResultante.getString(11));
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPor()");
        }
        return usuarioDTO;
    }

    public UsuarioDTO leerPorUsername(String username) throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        String consulta = "SELECT * FROM Usuario WHERE Estado = 'A' AND Username = '" + username + "'";
        try {
            Connection conexion          = abrirConexion();
            Statement declaracion        = conexion.createStatement();
            ResultSet conjuntoResultante = declaracion.executeQuery(consulta);
            while (conjuntoResultante.next()) {
                usuarioDTO = new UsuarioDTO(conjuntoResultante.getInt(1),
                                            conjuntoResultante.getString(2), 
                                            conjuntoResultante.getString(3),
                                            conjuntoResultante.getString(4),
                                            conjuntoResultante.getString(5),
                                            conjuntoResultante.getString(6),
                                            conjuntoResultante.getInt(7),
                                            conjuntoResultante.getFloat(8),
                                            conjuntoResultante.getString(9),
                                            conjuntoResultante.getString(10),
                                            conjuntoResultante.getString(11));
            }
        } catch (SQLException e) {
            throw new NewException(e.getMessage(), getClass().getName(), "leerPorUsername()");
        }
        return usuarioDTO;
    }

    @Override
    public boolean actualizar(UsuarioDTO entidad) throws Exception {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actual = LocalDateTime.now();
        String consulta = "UPDATE Usuario SET Nombre = ?, "
                        + "Cedula = ?,                    "
                        + "Username = ?,                  "
                        + "Clave = ?,                     "
                        + "Email = ?,                     "
                        + "IdSexo = ?,                    "
                        + "Saldo = ?,                     "
                        + "Estado = ?,                    "
                        + "FechaModifica = ?              "
                        + "WHERE IdUsuario = ?            ";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1, entidad.getNombre());
            declaracion.setString(2, entidad.getCedula());
            declaracion.setString(3, entidad.getUsername());
            declaracion.setString(4, entidad.getClave());
            declaracion.setString(5, entidad.getEmail());
            declaracion.setInt(6, entidad.getIdSexo());
            declaracion.setFloat(7, entidad.getSaldo());
            declaracion.setString(8, entidad.getEstado());
            declaracion.setString(9, formatoFecha.format(actual).toString());
            declaracion.setInt(10, entidad.getIdUsuario());
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

    public boolean loginUsuario(String username, String password){
        String consulta = "SELECT * FROM Usuario WHERE Estado = 'A' AND Username = ? AND Clave = ?";
        try {
            Connection conexion           = abrirConexion();
            PreparedStatement declaracion = conexion.prepareStatement(consulta);
            declaracion.setString(1,username);
            declaracion.setString(2,password);
            ResultSet  conjuntoResultante = declaracion.executeQuery();
            return conjuntoResultante.next();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesion: " + e.getMessage());
            return false;
        }
    }
}

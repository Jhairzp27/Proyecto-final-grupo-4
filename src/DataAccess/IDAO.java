package DataAccess;

import java.util.List;

public interface IDAO<T> {
    public boolean crear(T entidad)      throws Exception;
    public List<T> leerTodo()            throws Exception;
    public T leerPor(Integer id)         throws Exception;
    public boolean actualizar(T entidad) throws Exception;
    public boolean eliminar(Integer id)  throws Exception;
}

package BusinessLogic;

import java.util.List;

import DataAccess.SexoDAO;
import DataAccess.DTO.SexoDTO;

/**
 * La clase `SexoBL` en Java sirve como una capa de lógica de negocios que interactúa con un objeto
 * `SexoDAO` para realizar operaciones CRUD en objetos `SexoDTO` que representan datos de género o
 * sexo.
 */
public class SexoBL {
    private SexoDTO sexoDTO;
    private SexoDAO sexoDAO = new SexoDAO();

    public SexoBL() {}
    
    /**
     * La función crea un nuevo registro en la base de datos utilizando el objeto SexoDTO
     * proporcionado.
     * 
     * @param SexoDTO El parámetro `SexoDTO` es probablemente una clase de Objeto de transferencia de
     * datos (DTO) que representa datos relacionados con un género o sexo. Puede contener atributos
     * como `id`, `nombre`, `descripcion`, etc., dependiendo de los requisitos específicos de su
     * aplicación. Este parámetro se utiliza para pasar el género.
     * @return El método devuelve un valor booleano, que es el resultado de llamar al método `crear` en
     * el objeto `sexoDAO` con el parámetro `SexoDTO`.
     */
    public boolean crear(SexoDTO SexoDTO) throws Exception {
        return sexoDAO.crear(SexoDTO);
    }

    /**
     * Esta función de Java lee todos los registros de una fuente de datos y los devuelve como una
     * lista de objetos SexoDTO.
     * 
     * @return Se devuelve una lista de objetos SexoDTO.
     */
    public List<SexoDTO> leerTodo() throws Exception {
        return sexoDAO.leerTodo();
    }

    /**
     * Esta función Java lee un objeto SexoDTO por su ID usando un SexoDAO y lo devuelve.
     * 
     * @param idSexo El parámetro `idSexo` es un número entero que representa el identificador único de
     * un género o sexo específico en el sistema. El método `leerPor` se utiliza para recuperar los
     * detalles del género o sexo correspondiente al `idSexo` proporcionado de la base de datos.
     * @return El método devuelve un objeto SexoDTO.
     */
    public SexoDTO leerPor(Integer idSexo) throws Exception {
        sexoDTO = sexoDAO.leerPor(idSexo);
        return sexoDTO;
    }

    /**
     * La función `actualizar` actualiza un objeto SexoDTO usando sexoDAO.
     * 
     * @param SexoDTO El parámetro `SexoDTO` en el método `actualizar` es probablemente un objeto de
     * transferencia de datos (DTO) que representa información relacionada con un género o sexo. Se
     * utiliza para pasar datos entre las capas de una aplicación, como entre la capa de presentación y
     * la capa de acceso a datos. El "real"
     * @return El método devuelve un valor booleano, que es el resultado de llamar al método
     * `actualizar` en el objeto `sexoDAO` con el parámetro `SexoDTO`.
     */
    public boolean actualizar(SexoDTO SexoDTO) throws Exception {
        return sexoDAO.actualizar(SexoDTO);
    }

    /**
     * Esta función de Java llama al método eliminar en sexoDAO para eliminar un registro según el
     * idSexo proporcionado.
     * 
     * @param idSexo El parámetro `idSexo` es un valor Entero que representa el identificador único de
     * un registro en la base de datos que corresponde a un género específico (sexo). El método
     * `eliminar` es responsable de eliminar el registro con el `idSexo` dado de la base de datos
     * usando el `sexoDAO`
     * @return Se devuelve un valor booleano.
     */
    public boolean eliminar(Integer idSexo) throws Exception {
        return sexoDAO.eliminar(idSexo);
    }
}

package BusinessLogic;
import java.util.List;

import DataAcces.PersonaDAO;
import DataAcces.DTO.PersonaDTO;

public class PersonaBL {
    private PersonaDTO persona;
    private PersonaDAO pDAO = new PersonaDAO();

    public PersonaBL (){}


    public List<PersonaDTO> leerTodo() throws Exception {
        return pDAO.leerTodo();
    }
    public PersonaDTO leer(int idPersona) throws Exception {
        persona = pDAO.leer(idPersona);
        return persona;
    }
    public boolean crear (int idSexo,int idRol, String PersonaNombre, String PersonaApellido, String PersonaCedula, String PersonaFechaNacimiento) throws Exception {
        persona = new PersonaDTO();
        persona.setIdSexo(idSexo);
        persona.setIdRol(idRol);
        persona.setPersonaNombre(PersonaNombre);
        persona.setPersonaApellido(PersonaApellido);
        persona.setPersonaCedula(PersonaCedula);
        persona.setPersonaFechaNacimiento(PersonaFechaNacimiento);
        return pDAO.crear(persona);
    }
    public boolean actualizar(int idPersona, int idSexo, int idRol, String PersonaNombre, String PersonaApellido, String PersonaCedula, String PersonaFechaNacimiento) throws Exception{
        persona = new PersonaDTO();
        persona.setIdPersona(idPersona);
        persona.setIdSexo(idSexo);
        persona.setIdRol(idRol);
        persona.setPersonaNombre(PersonaNombre);
        persona.setPersonaApellido(PersonaApellido);
        persona.setPersonaCedula(PersonaCedula);
        persona.setPersonaFechaNacimiento(PersonaFechaNacimiento);
        return pDAO.actualizar(persona);
    }
    public boolean eliminar(int idPersona) throws Exception{
        return pDAO.eliminar(idPersona);
    }
}

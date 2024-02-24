import java.util.Scanner;

import javax.swing.JFrame;

import DataAcces.AccesoCuentaDAO;
import DataAcces.CuentaDAO;
import DataAcces.PersonaDAO;
import DataAcces.SexoDAO;
import DataAcces.DTO.AccesoCuentaDTO;
import DataAcces.DTO.CuentaDTO;
import DataAcces.DTO.PersonaDTO;
import DataAcces.DTO.SexoDTO;
import UserInterface.Form.MainMenuPanel;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("My primera DAC");

        SexoDTO Screar = new SexoDTO();
        SexoDAO s = new SexoDAO();
        Screar.setSexoNombre("PachaLOver");

        // Crear sexo
        // s.crear(Screar);
        // Leer todo de sexo
        for (SexoDTO filaSexoDTO : s.leerTodo()) {
            System.out.println(filaSexoDTO);

        }
        SexoDTO oS = new SexoDTO();
        // Editar una fila de datos de sexo
        System.out.println("Ingrese el nombre a editar");
        String nuevoSexo = sc.nextLine();
        oS.setSexoNombre(nuevoSexo);
        oS.setIdSexo(2);
        s.actualizar(oS);
        // Leer una fila de sexo
        System.out.println(s.leer(2));
        // Borrado Logico Sexo
        s.eliminar(1);

        //Tabla persona
        //Mostrar todo
        PersonaDAO oP = new PersonaDAO();
        for (PersonaDTO string : oP.leerTodo()) {
            System.out.println(string);
        }
        // Editar una fila
        PersonaDTO P = new PersonaDTO();
        P.setPersonaNombre("Pedro");
        P.setIdPersona(1);
        P.setIdSexo(1);
        P.setPersonaApellido("Pascal");
        P.setPersonaFechaNacimiento("2003-09-30");
        P.setPersonaCedula("123455677");
        oP.actualizar(P);
        // Mostrar una fila
        System.out.println(oP.leer(1));
        // Eliminar una fila
        oP.eliminar(1);

        // cuenta
        CuentaDAO c = new CuentaDAO();
        // mostrar todo
        for (CuentaDTO string : c.leerTodo()) {
            System.out.println(string);
        }
        // Editar fila
        CuentaDTO oC = new CuentaDTO();
        oC.setCuentaSaldo(1644.77);
        oC.setIdCuenta(1);
        oC.setIdPersona(1);
        // Mostrar una fila
        c.actualizar(oC);
        System.out.println(c.leer(1));
        // Eliminar
        c.eliminar(1);

        // acceso a cuenta
        AccesoCuentaDAO acceso = new AccesoCuentaDAO();
        AccesoCuentaDTO acceso2 = new AccesoCuentaDTO();
        acceso2.setAccesoCuentaClave("1234");
        acceso.crear(acceso2);

        //--------Interface principal-----------
        // Crear el JFrame principal
        JFrame frame = new JFrame("PoliBank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de MainMenuPanel
        MainMenuPanel mainMenuPanel = new MainMenuPanel();

        // Agregar MainMenuPanel al JFrame
        frame.getContentPane().add(mainMenuPanel);

        // Establecer el JFrame en pantalla completa
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Configurar el tamaño y hacer visible el JFrame
        frame.setSize(800, 600); // Puedes ajustar el tamaño según tus necesidades
        frame.setLocationRelativeTo(null); // Centrar en la pantalla
        frame.setVisible(true);
        
        
    }
}

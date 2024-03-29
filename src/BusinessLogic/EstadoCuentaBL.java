package BusinessLogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import DataAccess.EstadoCuentaDAO;
import DataAccess.DTO.EstadoCuentaDTO;
import DataAccess.DTO.UsuarioDTO;
import Framework.NewException;
import br.com.adilson.util.PrinterMatrix;

/**
 * La clase `EstadoCuentaBL` en Java contiene métodos para leer e imprimir estados de cuenta usando una
 * impresora matricial
 */
public class EstadoCuentaBL {
    private EstadoCuentaDAO estadoCuentaDAO = new EstadoCuentaDAO();

    public EstadoCuentaBL() {}

    /**
     * Esta función Java lee una lista de objetos EstadoCuentaDTO según el ID del usuario actualmente
     * conectado.
     * 
     * @param idUsuarioLogeado El parámetro `idUsuarioLogeado` representa el ID del usuario actualmente
     * conectado. Este método `leerPorUsuarioActual` se utiliza para recuperar una lista de objetos
     * `EstadoCuentaDTO` asociados al usuario identificado por el `idUsuarioLogeado` proporcionado.
     * @return Se está devolviendo un ArrayList de objetos EstadoCuentaDTO.
     */
    public ArrayList<EstadoCuentaDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return estadoCuentaDAO.leerPorUsuarioActual(idUsuarioLogeado);
    }

    /**
     * La función `imprimir` en Java imprime un estado de cuenta usando una impresora matricial y luego
     * lo envía a la impresora predeterminada para su salida física.
     * 
     * @param listaEstadoCuentaDTO El método `imprimir` toma dos parámetros:
     * @param usuarioDTOLogeado El parámetro `usuarioDTOLogeado` es de tipo `UsuarioDTO` y representa
     * al usuario que ha iniciado sesión. Contiene información como el nombre del usuario, DNI y número
     * de DNI (C.I.).
     */
    public void imprimir(ArrayList<EstadoCuentaDTO> listaEstadoCuentaDTO, UsuarioDTO usuarioDTOLogeado) throws Exception {
        PrinterMatrix printer = new PrinterMatrix();
        String rutaArchivo = "data\\impresion.txt";

        printer.setOutSize((listaEstadoCuentaDTO.size() * 2) + 15, 90);

        // printCharAtCol(a, b, c, "texto")
        // a = linea, b = columna incia, c = columna termina

        // printTextWrap(a, b, c, d, "texto")
        // a = linea empieza, b = linea termina, c = columna empieza, d = columna
        // termina

        printer.printTextWrap(1, 1, 7, 90, "POLIBANK");
        printer.printTextWrap(1, 1, 55, 90, "ESTADO DE CUENTA");

        printer.printTextWrap(3, 3, 7, 90, usuarioDTOLogeado.getNombre().toUpperCase());
        printer.printTextWrap(3, 3, 55, 90, "C.I.: " + usuarioDTOLogeado.getCedula());

        printer.printTextWrap(4, 4, 7, 90, "ID CUENTA: " + usuarioDTOLogeado.getIdUsuario().toString());

        printer.printCharAtCol(7, 5, 74, "-");
        printer.printCharAtCol(8, 5, 5, "|");
        printer.printTextWrap(7, 7, 29, 90, "DETALLE DE MOVIMIENTOS");
        printer.printCharAtCol(8, 74, 74, "|");

        printer.printCharAtCol(9, 5, 74, "-");
        printer.printCharAtCol(10, 5, 5, "|");
        printer.printTextWrap(9, 9, 8, 90, "Fecha");
        printer.printCharAtCol(10, 18, 18, "|");
        printer.printTextWrap(9, 9, 19, 90, "Id Movimiento");
        printer.printCharAtCol(10, 34, 34, "|");
        printer.printTextWrap(9, 9, 36, 90, "Descripcion");
        printer.printCharAtCol(10, 50, 50, "|");
        printer.printTextWrap(9, 9, 51, 90, "Monto ($)");
        printer.printCharAtCol(10, 62, 62, "|");
        printer.printTextWrap(9, 9, 63, 90, "Saldo ($)");
        printer.printCharAtCol(10, 74, 74, "|");
        printer.printCharAtCol(11, 5, 74, "-");

        int i, j, lineaEmpieza = 0;
        for (i = 1, j = 0; i <= listaEstadoCuentaDTO.size(); i++, j++) {
            lineaEmpieza = i + 10 + j;
            EstadoCuentaDTO ec = listaEstadoCuentaDTO.get(i - 1);
            printer.printCharAtCol(lineaEmpieza + 1, 5, 5, "|");
            printer.printTextWrap(lineaEmpieza, lineaEmpieza, 6, 90, ec.getFechaCorta());
            printer.printCharAtCol(lineaEmpieza + 1, 18, 18, "|");
            printer.printTextWrap(lineaEmpieza, lineaEmpieza, 25, 90, ec.getIdMovimiento().toString());
            printer.printCharAtCol(lineaEmpieza + 1, 34, 34, "|");
            printer.printTextWrap(lineaEmpieza, lineaEmpieza, 35, 90, ec.getDescripcion());
            printer.printCharAtCol(lineaEmpieza + 1, 50, 50, "|");
            printer.printTextWrap(lineaEmpieza, lineaEmpieza, 51, 90, ec.getMonto());
            printer.printCharAtCol(lineaEmpieza + 1, 62, 62, "|");
            printer.printTextWrap(lineaEmpieza, lineaEmpieza, 63, 90, ec.getSaldo().toString());
            printer.printCharAtCol(lineaEmpieza + 1, 74, 74, "|");
            printer.printCharAtCol(lineaEmpieza + 2, 5, 74, "-");
        }

        lineaEmpieza += 4;
        printer.printCharAtCol(lineaEmpieza, 5, 74, "-");
        printer.printCharAtCol(lineaEmpieza + 1, 5, 5, "|");
        printer.printTextWrap(lineaEmpieza, lineaEmpieza, 6, 90, "@2024 PoliBank");
        printer.printTextWrap(lineaEmpieza, lineaEmpieza, 25, 90, "ESCUELA POLITECNICA NACIONAL");
        printer.printTextWrap(lineaEmpieza, lineaEmpieza, 57, 90, "Quito - Ecuador");
        printer.printCharAtCol(lineaEmpieza + 1, 74, 74, "|");
        printer.printCharAtCol(lineaEmpieza + 2, 5, 74, "-");

        printer.toFile(rutaArchivo);

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(rutaArchivo);
        } catch (FileNotFoundException ex) {
            throw new NewException(ex.getMessage(), getClass().getName(), "imprimir()");
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        try {
            if (defaultPrintService != null) {
                DocPrintJob printJob = defaultPrintService.createPrintJob();
                printJob.print(document, attributeSet);
            }
        } catch (Exception ex) {
            throw new NewException(ex.getMessage(), getClass().getName(), "imprimir()");
        }
    }
}

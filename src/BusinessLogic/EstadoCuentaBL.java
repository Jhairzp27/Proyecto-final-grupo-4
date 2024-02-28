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
import javax.swing.JOptionPane;

import DataAccess.EstadoCuentaDAO;
import DataAccess.DTO.EstadoCuentaDTO;
import DataAccess.DTO.UsuarioDTO;
import br.com.adilson.util.PrinterMatrix;

public class EstadoCuentaBL {
    private EstadoCuentaDAO estadoCuentaDAO = new EstadoCuentaDAO();

    public EstadoCuentaBL() {}

    public ArrayList<EstadoCuentaDTO> leerPorUsuarioActual(Integer idUsuarioLogeado) throws Exception {
        return estadoCuentaDAO.leerPorUsuarioActual(idUsuarioLogeado);
    }

    public void imprimir(ArrayList<EstadoCuentaDTO> listaEstadoCuentaDTO, UsuarioDTO usuarioDTOLogeado) {
        PrinterMatrix printer = new PrinterMatrix();
        String rutaArchivo = "src\\UserInterface\\Resource\\data\\impresion.txt";

        // Pixeles de hoja A4
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
            try {
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
            } catch (Exception e) {
                break;
            }
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

        // Se prepara el archivo para imprimirlo
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(rutaArchivo);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        if (inputStream == null)
            return;

        // Formato del documento
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // Creación del documento
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        // Conjunto de atributos para la impresión
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        // Se busca la impresora predeterminada
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        // Si hay una impresora disponible, se envía el trabajo de impresión
        if (defaultPrintService != null) {
            // Se crea un trabajo de impresión
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                // Se envía el documento a imprimir
                printJob.print(document, attributeSet);
            } catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay una impresora instalada");
        }
    }
}

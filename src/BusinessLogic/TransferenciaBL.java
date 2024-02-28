package BusinessLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import DataAccess.TransferenciaDAO;
import DataAccess.DTO.TransferenciaDTO;
import DataAccess.DTO.UsuarioDTO;

public class TransferenciaBL {
    private TransferenciaDTO transferenciaDTO;
    private TransferenciaDAO transferenciaDAO = new TransferenciaDAO();
    private UsuarioBL usuarioBL = new UsuarioBL();

    public TransferenciaBL() {}
    
    public boolean crear(TransferenciaDTO transferenciaDTO) throws Exception {
        return transferenciaDAO.crear(transferenciaDTO);
    }

    public List<TransferenciaDTO> leerTodo() throws Exception {
        return transferenciaDAO.leerTodo();
    }

    public TransferenciaDTO leerPor(Integer idTransferencia) throws Exception {
        transferenciaDTO = transferenciaDAO.leerPor(idTransferencia);
        return transferenciaDTO;
    }

    public boolean actualizar(TransferenciaDTO transferenciaDTO) throws Exception {
        return transferenciaDAO.actualizar(transferenciaDTO);
    }

    public boolean eliminar(Integer idTransferencia) throws Exception {
        return transferenciaDAO.eliminar(idTransferencia);
    }

    public boolean recargar(UsuarioDTO usuarioDTO, float montoRecarga) throws Exception {
        if (montoRecarga < 0)
            return false;

        float saldoActual = usuarioDTO.getSaldo(),
              saldoNuevo  = saldoActual + montoRecarga;
        usuarioDTO.setSaldo(saldoNuevo);

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime actual = LocalDateTime.now();
        transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setIdUsuarioEnvia(-1);
        transferenciaDTO.setIdUsuarioRecibe(usuarioDTO.getIdUsuario());
        transferenciaDTO.setMonto(montoRecarga);
        transferenciaDTO.setFecha(formatoFecha.format(actual).toString());

        if (usuarioBL.actualizar(usuarioDTO) && crear(transferenciaDTO))
            return true;
        else
            return false;
    }

    public boolean transferirDinero(UsuarioDTO usuarioDTOLogeado, int idUsuarioRecibe, float monto) throws Exception {
        if (monto <= 0)
            return false;
    
        UsuarioDTO usuarioRecibe = usuarioBL.leerPorId(idUsuarioRecibe);
    
        float saldoActualUsuarioLogeado = usuarioDTOLogeado.getSaldo();
    
        if (saldoActualUsuarioLogeado < monto)
            return false;
    
        float nuevoSaldoUsuarioLogeado = saldoActualUsuarioLogeado - monto;
        float saldoActualUsuarioRecibe = usuarioRecibe.getSaldo();
        float nuevoSaldoUsuarioRecibe = saldoActualUsuarioRecibe + monto;
    
        usuarioDTOLogeado.setSaldo(nuevoSaldoUsuarioLogeado);
        usuarioRecibe.setSaldo(nuevoSaldoUsuarioRecibe);
    
        if (usuarioBL.actualizar(usuarioDTOLogeado) && usuarioBL.actualizar(usuarioRecibe)) {
            TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime actual = LocalDateTime.now();
            transferenciaDTO.setIdUsuarioEnvia(usuarioDTOLogeado.getIdUsuario());
            transferenciaDTO.setIdUsuarioRecibe(idUsuarioRecibe);
            transferenciaDTO.setMonto(monto);
            transferenciaDTO.setFecha(formatoFecha.format(actual).toString());
    
            if (crear(transferenciaDTO))
                return true;
            else
                return false;
        } else
            return false;
    }
    
    public boolean esNumeroFloatPositivo(String str) {
        try {
            if(Float.parseFloat(str) >= 0)
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean esNumeroEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
}
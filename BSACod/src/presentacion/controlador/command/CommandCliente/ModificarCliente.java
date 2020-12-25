package presentacion.controlador.command.CommandCliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosCliente;

/**
 * The Class ModificarCliente.
 */
public class ModificarCliente implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {

    final TransferCliente transferCliente = (TransferCliente) objeto;
    final SACliente sACliente = FactorySA.getInstance().createSACliente();
    String mensaje;
    Contexto contexto;

    try {
      final int id = sACliente.modificarCliente(transferCliente);
      mensaje = " Cliente modificado correctamente. Su ID es: " + id + ". ";
      contexto = new Contexto(EventosCliente.MODIFICAR_CLIENTE_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosCliente.MODIFICAR_CLIENTE_KO, mensaje);
    }

    return contexto;
  }
}
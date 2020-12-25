package presentacion.controlador.command.CommandCliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosCliente;

/**
 * The Class MostrarCliente.
 */
public class MostrarCliente implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final int id = (int) objeto;
    TransferCliente transferCliente;
    final SACliente sACliente = FactorySA.getInstance().createSACliente();
    String mensaje;
    Contexto contexto;

    try {
      transferCliente = sACliente.mostrarCliente(id);
      contexto = new Contexto(EventosCliente.MOSTRAR_CLIENTE_OK, transferCliente);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosCliente.MOSTRAR_CLIENTE_KO, mensaje);
    }

    return contexto;
  }
}
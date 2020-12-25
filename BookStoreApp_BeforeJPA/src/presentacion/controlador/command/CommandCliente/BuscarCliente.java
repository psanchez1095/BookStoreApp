package presentacion.controlador.command.CommandCliente;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosCliente;

/**
 * The Class BuscarCliente.
 */
public class BuscarCliente implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {

    final Integer id = (Integer) objeto;
    final SACliente sACliente = FactorySA.getInstance().createSACliente();
    String mensaje;
    Contexto contexto;
    TransferCliente transferCliente;
    try {
      transferCliente = sACliente.mostrarCliente(id);

      contexto = new Contexto(EventosCliente.BUSCAR_CLIENTE_OK, transferCliente);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosCliente.BUSCAR_CLIENTE_KO, mensaje);
    }
    return contexto;
  }
}

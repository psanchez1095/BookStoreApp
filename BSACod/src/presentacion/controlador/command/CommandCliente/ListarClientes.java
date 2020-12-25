package presentacion.controlador.command.CommandCliente;

import java.util.Collection;

import negocio.cliente.SACliente;
import negocio.cliente.TransferCliente;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosCliente;

/**
 * The Class ListarClientes.
 */
public class ListarClientes implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final SACliente sACliente = FactorySA.getInstance().createSACliente();
    String mensaje;
    Contexto contexto;

    try {
      final Collection<TransferCliente> lista = sACliente.listarClientes();
      contexto = new Contexto(EventosCliente.LISTAR_CLIENTES_OK, lista);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosCliente.LISTAR_CLIENTES_KO, mensaje);
    }

    return contexto;
  }

}
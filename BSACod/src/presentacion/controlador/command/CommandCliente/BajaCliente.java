package presentacion.controlador.command.CommandCliente;

import negocio.cliente.SACliente;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosCliente;

/**
 * The Class BajaCliente.
 */
public class BajaCliente implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    int id = (int) objeto;
    final SACliente sACliente = FactorySA.getInstance().createSACliente();
    String mensaje;
    Contexto contexto;

    try {
      id = sACliente.bajaCliente(id);
      mensaje = " Se ha dado de baja el cliente correctamente. ";
      contexto = new Contexto(EventosCliente.BAJA_CLIENTE_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosCliente.BAJA_CLIENTE_KO, mensaje);
    }

    return contexto;
  }
}
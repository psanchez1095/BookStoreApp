package presentacion.controlador.command.CommandMenu;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMenu;

/**
 * The Class CommandMostrarVistaCliente.
 */
public class CommandMostrarVistaCliente implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    return new Contexto(EventosMenu.MOSTRAR_CLIENTE_VISTA, null);
  }

}

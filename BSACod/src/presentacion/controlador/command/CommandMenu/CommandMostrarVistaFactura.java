package presentacion.controlador.command.CommandMenu;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMenu;

/**
 * The Class CommandMostrarVistaFactura.
 */
public class CommandMostrarVistaFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    return new Contexto(EventosMenu.MOSTRAR_FACTURA_VISTA, null);
  }

}

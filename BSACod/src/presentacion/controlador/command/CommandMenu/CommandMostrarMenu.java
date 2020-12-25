package presentacion.controlador.command.CommandMenu;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMenu;

/**
 * The Class CommandMostrarMenu.
 */
public class CommandMostrarMenu implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    return new Contexto(EventosMenu.MOSTRAR_MENU_VISTA, null);
  }
}
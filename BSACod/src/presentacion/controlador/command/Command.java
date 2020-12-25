package presentacion.controlador.command;

import presentacion.contexto.Contexto;

/**
 * The Interface Command.
 */
public interface Command {

  /**
   * Execute.
   *
   * @param objeto
   *          the objeto
   * @return the contexto
   */
  public Contexto execute(Object objeto);
}
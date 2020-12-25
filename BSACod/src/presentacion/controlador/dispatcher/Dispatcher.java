package presentacion.controlador.dispatcher;

import presentacion.contexto.Contexto;

/**
 * The Class Dispatcher.
 */
public abstract class Dispatcher {

  /** The instance. */
  private static Dispatcher instance;

  /**
   * Gets the single instance of Dispatcher.
   *
   * @return single instance of Dispatcher
   */
  public static Dispatcher getInstance() {
    if (instance == null) {
      instance = new DispatcherImp();
    }
    return instance;
  }

  /**
   * Crear vista.
   *
   * @param contexto
   *          the contexto
   */
  public abstract void crearVista(Contexto contexto);
}
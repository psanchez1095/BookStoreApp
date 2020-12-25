package presentacion.controlador.appController;

import presentacion.contexto.Contexto;

/**
 * The Class Controller.
 */
public abstract class Controller {

  /** The instance. */
  private static Controller instance;

  /**
   * Gets the single instance of Controller.
   *
   * @return single instance of Controller
   */
  public static Controller getInstance() {
    if (instance == null) {
      instance = new ControllerImp();
    }
    return instance;
  }

  /**
   * Handle request.
   *
   * @param contexto
   *          the contexto
   */
  public abstract void handleRequest(Contexto contexto);
}
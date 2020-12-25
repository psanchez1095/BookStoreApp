package presentacion.controlador.appController;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.controlador.command.FactoriaComando;
import presentacion.controlador.dispatcher.Dispatcher;

/**
 * The Class ControllerImp.
 */
public class ControllerImp extends Controller {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.appController.Controller#handleRequest(
   * presentacion.contexto.Contexto)
   */
  @Override
  public void handleRequest(final Contexto contexto) {
    final Command command = FactoriaComando.getInstance().nuevoComando(contexto.getEvento());
    final Contexto resultado = command.execute(contexto.getDatos());
    Dispatcher.getInstance().crearVista(resultado);
  }
}

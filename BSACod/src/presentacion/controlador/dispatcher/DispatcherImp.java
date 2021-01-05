package presentacion.controlador.dispatcher;

import presentacion.contexto.Contexto;
import presentacion.eventos.EventosEmpleado;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.FactoriaVistaPrincipal;
import presentacion.vistas.gui.VistaPrincipal;

/**
 * The Class DispatcherImp.
 */
public class DispatcherImp extends Dispatcher {

  /** The vista principal. */
  private VistaPrincipal vistaPrincipal;

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.dispatcher.Dispatcher#crearVista(presentacion.
   * contexto.Contexto)
   */
  @Override
  public void crearVista(final Contexto contexto) {
    switch (contexto.getEvento()) {
      case EventosMenu.MOSTRAR_MENU_VISTA:
      case EventosMenu.MOSTRAR_CLIENTE_VISTA:
      case EventosMenu.MOSTRAR_EDITORIAL_VISTA:
      case EventosMenu.MOSTRAR_LIBRO_VISTA:
      case EventosMenu.MOSTRAR_FACTURA_VISTA:
      case EventosMenu.MOSTRAR_DEPARTAMENTO_VISTA:
      case EventosMenu.MOSTRAR_MATERIAL_VISTA:
      case EventosMenu.MOSTRAR_EMPLEADO_VISTA:
      case EventosMenu.MOSTRAR_LIBRERIA_VISTA:
    	  vistaPrincipal = FactoriaVistaPrincipal.getInstance().generarVista(contexto.getEvento());
    	  break;
      default:
        vistaPrincipal.actualizar(contexto);
    }
  }
}
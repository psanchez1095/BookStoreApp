package presentacion.vistas.gui;

import presentacion.eventos.EventosMenu;
import presentacion.vistas.Empleado.VistaEmpleado;
import presentacion.vistas.Material.VistaMaterial;
import presentacion.vistas.cliente.VistaCliente;
import presentacion.vistas.departamento.VistaDepartamento;
import presentacion.vistas.editorial.VistaEditorial;
import presentacion.vistas.factura.VistaFactura;
import presentacion.vistas.libreria.VistaLibreria;
import presentacion.vistas.libro.VistaLibro;
import presentacion.vistas.menu.VistaMenu;

/**
 * The Class FactoriaVistaPrincipalImp.
 */
public class FactoriaVistaPrincipalImp extends FactoriaVistaPrincipal {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.vistas.gui.FactoriaVistaPrincipal#generarVista(int)
   */
  @Override
  public VistaPrincipal generarVista(final int evento) {

    VistaPrincipal vistaPrincipal = null;

    switch (evento) {
      case EventosMenu.MOSTRAR_MENU_VISTA:
        vistaPrincipal = new VistaMenu();
        break;
      case EventosMenu.MOSTRAR_CLIENTE_VISTA:
        vistaPrincipal = new VistaCliente();
        break;
      case EventosMenu.MOSTRAR_EDITORIAL_VISTA:
        vistaPrincipal = new VistaEditorial();
        break;
      case EventosMenu.MOSTRAR_LIBRO_VISTA:
          vistaPrincipal = new VistaLibro();
          break;
      case EventosMenu.MOSTRAR_FACTURA_VISTA:
          vistaPrincipal = new VistaFactura();
          break;
      case EventosMenu.MOSTRAR_EMPLEADO_VISTA:
          vistaPrincipal = new VistaEmpleado();
          break;
      case EventosMenu.MOSTRAR_DEPARTAMENTO_VISTA:
        vistaPrincipal = new VistaDepartamento();
        break;
      case EventosMenu.MOSTRAR_MATERIAL_VISTA:
        vistaPrincipal = new VistaMaterial();
        break;
      case EventosMenu.MOSTRAR_LIBRERIA_VISTA:
            vistaPrincipal = new VistaLibreria();
            break;
     
    }
    return vistaPrincipal;
  }
}

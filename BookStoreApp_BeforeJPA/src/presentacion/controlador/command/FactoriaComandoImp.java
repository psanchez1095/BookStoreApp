
package presentacion.controlador.command;

import presentacion.controlador.command.CommandCliente.AltaCliente;
import presentacion.controlador.command.CommandCliente.BajaCliente;
import presentacion.controlador.command.CommandCliente.BuscarCliente;
import presentacion.controlador.command.CommandCliente.ListarClientes;
import presentacion.controlador.command.CommandCliente.ModificarCliente;
import presentacion.controlador.command.CommandCliente.MostrarCliente;
import presentacion.controlador.command.CommandEditorial.AltaEditorial;
import presentacion.controlador.command.CommandEditorial.BajaEditorial;
import presentacion.controlador.command.CommandEditorial.BuscarEditorial;
import presentacion.controlador.command.CommandEditorial.ListarEditoriales;
import presentacion.controlador.command.CommandEditorial.ModificarEditorial;
import presentacion.controlador.command.CommandEditorial.MostrarEditorial;
import presentacion.controlador.command.CommandFactura.AltaFactura;
import presentacion.controlador.command.CommandFactura.AnadirLibroFactura;
import presentacion.controlador.command.CommandFactura.BajaFactura;
import presentacion.controlador.command.CommandFactura.CerrarFactura;
import presentacion.controlador.command.CommandFactura.EliminarLibroFactura;
import presentacion.controlador.command.CommandFactura.FacturasMasDeMil;
import presentacion.controlador.command.CommandFactura.ListarFacturas;
import presentacion.controlador.command.CommandFactura.ModificarFactura;
import presentacion.controlador.command.CommandFactura.MostrarFactura;
import presentacion.controlador.command.CommandFactura.MostrarLibrosDeFactura;
import presentacion.controlador.command.CommandLibro.AltaLibro;
import presentacion.controlador.command.CommandLibro.BajaLibro;
import presentacion.controlador.command.CommandLibro.BuscarLibro;
import presentacion.controlador.command.CommandLibro.ListarLibro;
import presentacion.controlador.command.CommandLibro.ModificarLibro;
import presentacion.controlador.command.CommandLibro.MostrarLibro;
import presentacion.controlador.command.CommandMenu.CommandMostrarMenu;
import presentacion.controlador.command.CommandMenu.CommandMostrarVistaCliente;
import presentacion.controlador.command.CommandMenu.CommandMostrarVistaEditorial;
import presentacion.controlador.command.CommandMenu.CommandMostrarVistaFactura;
import presentacion.controlador.command.CommandMenu.CommandMostrarVistaLibro;
import presentacion.eventos.EventosCliente;
import presentacion.eventos.EventosEditorial;
import presentacion.eventos.EventosFactura;
import presentacion.eventos.EventosLibro;
import presentacion.eventos.EventosMenu;

/**
 * The Class FactoriaComandoImp.
 */
public class FactoriaComandoImp extends FactoriaComando {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.FactoriaComando#nuevoComando(int)
   */
  @Override
  public Command nuevoComando(final int idEvento) {
    Command command = null;

    switch (idEvento) {

      case EventosMenu.MOSTRAR_MENU_VISTA:
        command = new CommandMostrarMenu();
        break;
      case EventosMenu.MOSTRAR_CLIENTE_VISTA:
        command = new CommandMostrarVistaCliente();
        break;
      case EventosMenu.MOSTRAR_LIBRO_VISTA:
        command = new CommandMostrarVistaLibro();
        break;
      case EventosMenu.MOSTRAR_EDITORIAL_VISTA:
        command = new CommandMostrarVistaEditorial();
        break;
      case EventosMenu.MOSTRAR_FACTURA_VISTA:
        command = new CommandMostrarVistaFactura();
        break;

      // Editorial
      case EventosEditorial.ALTA_EDITORIAL:
        command = new AltaEditorial();
        break;
      case EventosEditorial.BAJA_EDITORIAL:
        command = new BajaEditorial();
        break;
      case EventosEditorial.MODIFICAR_EDITORIAL:
        command = new ModificarEditorial();
        break;
      case EventosEditorial.MOSTRAR_EDITORIAL:
        command = new MostrarEditorial();
        break;
      case EventosEditorial.LISTAR_EDITORIALES:
        command = new ListarEditoriales();
        break;
      case EventosEditorial.BUSCAR_EDITORIAL:
        command = new BuscarEditorial();
        break;

      // Cliente
      case EventosCliente.ALTA_CLIENTE:
        command = new AltaCliente();
        break;
      case EventosCliente.BAJA_CLIENTE:
        command = new BajaCliente();
        break;
      case EventosCliente.MODIFICAR_CLIENTE:
        command = new ModificarCliente();
        break;
      case EventosCliente.MOSTRAR_CLIENTE:
        command = new MostrarCliente();
        break;
      case EventosCliente.LISTAR_CLIENTES:
        command = new ListarClientes();
        break;
      case EventosCliente.BUSCAR_CLIENTE:
        command = new BuscarCliente();
        break;

      // Factura
      case EventosFactura.ALTA_FACTURA:
        command = new AltaFactura();
        break;
      case EventosFactura.BAJA_FACTURA:
        command = new BajaFactura();
        break;
      case EventosFactura.MODIFICAR_FACTURA:
        command = new ModificarFactura();
        break;
      case EventosFactura.MOSTRAR_FACTURA:
        command = new MostrarFactura();
        break;
      case EventosFactura.LISTAR_FACTURAS:
        command = new ListarFacturas();
        break;
      case EventosFactura.FACTURAS_MAS_DE_MIL:
        command = new FacturasMasDeMil();
        break;
      case EventosFactura.ANIADIR_LIBRO_FACTURA:
        command = new AnadirLibroFactura();
        break;
      case EventosFactura.ELIMINAR_LIBRO_FACTURA:
        command = new EliminarLibroFactura();
        break;
      case EventosFactura.MOSTRAR_LIBROS_FACTURA:
        command = new MostrarLibrosDeFactura();
        break;
      case EventosFactura.CERRAR_FACTURA:
        command = new CerrarFactura();
        break;

      // Libro
      case EventosLibro.ALTA_LIBRO:
        command = new AltaLibro();
        break;
      case EventosLibro.BAJA_LIBRO:
        command = new BajaLibro();
        break;
      case EventosLibro.MODIFICAR_LIBRO:
        command = new ModificarLibro();
        break;
      case EventosLibro.MOSTRAR_LIBRO:
        command = new MostrarLibro();
        break;
      case EventosLibro.LISTAR_LIBROS:
        command = new ListarLibro();
        break;
      case EventosLibro.BUSCAR_LIBRO:
        command = new BuscarLibro();
        break;
    }
    return command;
  }
}

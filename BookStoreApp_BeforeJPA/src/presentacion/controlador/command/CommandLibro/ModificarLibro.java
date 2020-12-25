package presentacion.controlador.command.CommandLibro;

import negocio.factorias.FactorySA;
import negocio.libro.SALibro;
import negocio.libro.TFLibro;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibro;

/**
 * The Class ModificarLibro.
 */
public class ModificarLibro implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final TFLibro libro = (TFLibro) objeto;
    final SALibro sa = FactorySA.getInstance().createLibro();
    String mensaje;
    Contexto contexto;

    try {
      sa.modificarLibro(libro);
      mensaje = " Libro modificado corretamente. Su ID es: " + libro.getID() + ". ";
      contexto = new Contexto(EventosLibro.MODIFICAR_LIBRO_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosLibro.MODIFICAR_LIBRO_KO, mensaje);
    }

    return contexto;
  }
}
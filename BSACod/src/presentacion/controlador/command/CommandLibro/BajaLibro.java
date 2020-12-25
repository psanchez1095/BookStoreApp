/**
 *
 */
package presentacion.controlador.command.CommandLibro;

import negocio.factorias.FactorySA;
import negocio.libro.SALibro;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibro;

/**
 * The Class BajaLibro.
 */
public class BajaLibro implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final Integer idLibro = (Integer) objeto;
    final SALibro sa = FactorySA.getInstance().createLibro();
    String mensaje;
    Contexto contexto;

    try {
      sa.bajaLibro(idLibro);
      mensaje = "Libro dado de baja corretamente. Su ID es: " + idLibro + ". ";
      contexto = new Contexto(EventosLibro.BAJA_LIBRO_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosLibro.BAJA_LIBRO_KO, mensaje);
    }

    return contexto;
  }
}
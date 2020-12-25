package presentacion.controlador.command.CommandLibro;

import negocio.factorias.FactorySA;
import negocio.libro.SALibro;
import negocio.libro.TFLibro;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibro;

/**
 * The Class AltaLibro.
 */
public class AltaLibro implements Command {

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
      final int id = sa.altaLibro(libro);
      mensaje = "Libro dado de alta correctamente. Su ID es: " + id + ". ";
      contexto = new Contexto(EventosLibro.ALTA_LIBRO_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosLibro.ALTA_LIBRO_KO, mensaje);

    }

    return contexto;
  }
}
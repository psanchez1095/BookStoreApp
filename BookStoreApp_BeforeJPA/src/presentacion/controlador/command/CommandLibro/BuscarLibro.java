package presentacion.controlador.command.CommandLibro;

import negocio.factorias.FactorySA;
import negocio.libro.SALibro;
import negocio.libro.TFLibro;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibro;

/**
 * The Class BuscarLibro.
 */
public class BuscarLibro implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final int id = (int) objeto;
    TFLibro libro;
    final SALibro sa = FactorySA.getInstance().createLibro();
    String mensaje;
    Contexto contexto;

    try {
      libro = sa.mostrarLibro(id); // Reutilizo la accion de mostrar pues siguen
                                   // la misma logica.
      contexto = new Contexto(EventosLibro.BUSCAR_LIBRO_OK, libro);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosLibro.BUSCAR_LIBRO_KO, mensaje);
    }

    return contexto;
  }

}

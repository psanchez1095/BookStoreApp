package presentacion.controlador.command.CommandLibro;

import java.util.Collection;

import negocio.factorias.FactorySA;
import negocio.libro.SALibro;
import negocio.libro.TFLibro;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibro;

/**
 * The Class ListarLibro.
 */
public class ListarLibro implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final SALibro libroSA = FactorySA.getInstance().createLibro();
    String mensaje;
    Contexto contexto;
    try {
      final Collection<TFLibro> listaLibros = libroSA.listarLibros();
      contexto = new Contexto(EventosLibro.LISTAR_LIBROS_OK, listaLibros);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosLibro.LISTAR_LIBROS_KO, mensaje);
    }
    return contexto;
  }
}
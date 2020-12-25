package presentacion.controlador.command.CommandEditorial;

import negocio.editorial.SAEditorial;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEditorial;

/**
 * The Class BajaEditorial.
 */
public class BajaEditorial implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final Integer idEdit = (Integer) objeto;
    final SAEditorial sa = FactorySA.getInstance().createSAEditorial();
    String mensaje;
    Contexto contexto;

    try {
      sa.bajaEditorial(idEdit);
      mensaje = " Editorial dado de baja corretamente. Su ID es: " + idEdit + ". ";
      contexto = new Contexto(EventosEditorial.BAJA_EDITORIAL_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosEditorial.BAJA_EDITORIAL_KO, mensaje);
    }

    return contexto;

  }
}
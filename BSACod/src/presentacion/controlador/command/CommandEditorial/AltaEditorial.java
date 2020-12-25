package presentacion.controlador.command.CommandEditorial;

import negocio.editorial.SAEditorial;
import negocio.editorial.TransferEditorial;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEditorial;

/**
 * The Class AltaEditorial.
 */
public class AltaEditorial implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final TransferEditorial editorial = (TransferEditorial) objeto;
    final SAEditorial sa = FactorySA.getInstance().createSAEditorial();
    String mensaje;
    Contexto contexto;

    try {
      final int id = sa.altaEditorial(editorial);
      mensaje = " Editorial dado de alta corretamente. Su ID es: " + id + ". ";
      contexto = new Contexto(EventosEditorial.ALTA_EDITORIAL_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosEditorial.ALTA_EDITORIAL_KO, mensaje);
    }

    return contexto;

  }
}
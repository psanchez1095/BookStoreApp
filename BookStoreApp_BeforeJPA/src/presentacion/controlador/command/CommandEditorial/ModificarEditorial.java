package presentacion.controlador.command.CommandEditorial;

import negocio.editorial.SAEditorial;
import negocio.editorial.TransferEditorial;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEditorial;

/**
 * The Class ModificarEditorial.
 */
public class ModificarEditorial implements Command {

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
      sa.modificarEditorial(editorial);
      mensaje = " Editorial modificado correctamente. Su ID es: " + editorial.getID() + ". ";
      contexto = new Contexto(EventosEditorial.MODIFICAR_EDITORIAL_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosEditorial.MODIFICAR_EDITORIAL_KO, mensaje);
    }

    return contexto;

  }
}
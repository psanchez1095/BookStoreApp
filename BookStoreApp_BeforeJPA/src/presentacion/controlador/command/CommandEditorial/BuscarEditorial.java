package presentacion.controlador.command.CommandEditorial;

import negocio.editorial.SAEditorial;
import negocio.editorial.TransferEditorial;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEditorial;

/**
 * The Class BuscarEditorial.
 */
public class BuscarEditorial implements Command {

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
    TransferEditorial edit;
    try {
      edit = sa.mostrarEditorial(idEdit);

      contexto = new Contexto(EventosEditorial.BUSCAR_EDITORIAL_OK, edit);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosEditorial.BUSCAR_EDITORIAL_KO, mensaje);
    }

    return contexto;

  }
}

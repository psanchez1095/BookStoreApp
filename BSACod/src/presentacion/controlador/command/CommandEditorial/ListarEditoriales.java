package presentacion.controlador.command.CommandEditorial;

import java.util.Collection;

import negocio.editorial.SAEditorial;
import negocio.editorial.TransferEditorial;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEditorial;

/**
 * The Class ListarEditoriales.
 */
public class ListarEditoriales implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final SAEditorial editSA = FactorySA.getInstance().createSAEditorial();
    String mensaje;
    Contexto contexto;
    try {
      final Collection<TransferEditorial> listaEdit = editSA.listarEditoriales();
      contexto = new Contexto(EventosEditorial.LISTAR_EDITORIALES_OK, listaEdit);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosEditorial.LISTAR_EDITORIALES_KO, mensaje);
    }
    return contexto;
  }
}
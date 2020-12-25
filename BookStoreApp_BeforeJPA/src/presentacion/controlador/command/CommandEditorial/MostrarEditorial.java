package presentacion.controlador.command.CommandEditorial;

import negocio.editorial.SAEditorial;
import negocio.editorial.TransferEditorial;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEditorial;

/**
 * The Class MostrarEditorial.
 */
public class MostrarEditorial implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final Integer id = (Integer) objeto;
    TransferEditorial editorial;
    final SAEditorial sa = FactorySA.getInstance().createSAEditorial();
    String mensaje;
    Contexto contexto;

    try {
      editorial = sa.mostrarEditorial(id);
      contexto = new Contexto(EventosEditorial.MOSTRAR_EDITORIAL_OK, editorial);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosEditorial.MOSTRAR_EDITORIAL_KO, mensaje);
    }

    return contexto;
  }
}
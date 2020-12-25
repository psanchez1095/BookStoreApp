package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class MostrarFactura.
 */
public class MostrarFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object obj) {
    final int id = (int) obj;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;
    try {
      final TFactura tFactura = sa.mostrarFactura(id);
      if (tFactura == null) {
        throw new Exception("No se encuentra una factura con el ID " + id);
      }
      contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_OK, tFactura);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA_KO, mensaje);
    }
    return contexto;
  }
}
package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class ModificarFactura.
 */
public class ModificarFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final TFactura tFactura = (TFactura) objeto;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;

    try {
      sa.modificarFactura(tFactura);
      mensaje = "Factura modificada correctamente. Su ID es: " + tFactura.getID() + ". ";
      contexto = new Contexto(EventosFactura.MODIFICAR_FACTURA_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.MODIFICAR_FACTURA_KO, mensaje);
    }
    return contexto;
  }
}
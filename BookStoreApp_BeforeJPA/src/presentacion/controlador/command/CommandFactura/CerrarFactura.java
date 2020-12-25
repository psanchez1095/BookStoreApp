package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class CerrarFactura.
 */
public class CerrarFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final int id = (int) objeto;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;
    try {
      sa.cerrarFactura(id);
      mensaje = "Factura cerrada correctamente.";
      contexto = new Contexto(EventosFactura.CERRAR_FACTURA_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.CERRAR_FACTURA_KO, mensaje);
    }

    return contexto;
  }

}

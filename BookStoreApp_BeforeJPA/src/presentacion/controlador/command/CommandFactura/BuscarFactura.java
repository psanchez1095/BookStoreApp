package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class BuscarFactura.
 */
public class BuscarFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final int id = (int) objeto;
    TFactura factura;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;

    try {
      factura = sa.mostrarFactura(id);
      contexto = new Contexto(EventosFactura.BUSCAR_FACTURA_OK, factura);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.BUSCAR_FACTURA_KO, mensaje);
    }

    return contexto;
  }
}
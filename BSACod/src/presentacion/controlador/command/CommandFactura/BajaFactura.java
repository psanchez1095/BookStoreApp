package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class BajaFactura.
 */
public class BajaFactura implements Command {

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
      sa.bajaFactura(id);
      mensaje = "Factura dada de baja correctamente.";
      contexto = new Contexto(EventosFactura.BAJA_FACTURA_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.BAJA_FACTURA_KO, mensaje);
    }
    return contexto;
  }
}
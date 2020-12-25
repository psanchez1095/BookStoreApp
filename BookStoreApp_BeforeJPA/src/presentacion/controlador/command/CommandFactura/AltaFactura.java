package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class AltaFactura.
 */
public class AltaFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final int idCliente = (int) objeto;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;
    try {
      final int id = sa.altaFactura(idCliente);
      mensaje = "Factura dada de alta correctamente. Su ID es: " + id + ".";
      contexto = new Contexto(EventosFactura.ALTA_FACTURA_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.ALTA_FACTURA_KO, mensaje);
    }

    return contexto;
  }
}
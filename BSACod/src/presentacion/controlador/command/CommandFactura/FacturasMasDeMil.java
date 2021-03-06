package presentacion.controlador.command.CommandFactura;

import java.util.Collection;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class FacturasMasDeMil.
 */
public class FacturasMasDeMil implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object obj) {
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;

    try {
      final Collection<TFactura> listaMasMil = sa.listarFacturasMasMilEuros();
      contexto = new Contexto(EventosFactura.FACTURAS_MAS_DE_MIL_OK, listaMasMil);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.FACTURAS_MAS_DE_MIL_KO, mensaje);
    }
    return contexto;
  }
}
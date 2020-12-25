package presentacion.controlador.command.CommandFactura;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import negocio.lineadefactura.TLineaDeFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class AnadirLibroFactura.
 */
public class AnadirLibroFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final TLineaDeFactura tLineaDeFactura = (TLineaDeFactura) objeto;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;

    try {
      sa.anadirLibroAFactura(tLineaDeFactura);
      mensaje = "Factura modificada correctamente. Su ID es: " + tLineaDeFactura.getId() + ". ";
      contexto = new Contexto(EventosFactura.ANIADIR_LIBRO_FACTURA_OK, mensaje);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.ANIADIR_LIBRO_FACTURA_KO, mensaje);
    }
    return contexto;
  }
}
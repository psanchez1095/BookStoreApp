package presentacion.controlador.command.CommandFactura;

import java.util.Collection;

import negocio.factorias.FactorySA;
import negocio.factura.SAFactura;
import negocio.lineadefactura.TLineaDeFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosFactura;

/**
 * The Class MostrarLibrosDeFactura.
 */
public class MostrarLibrosDeFactura implements Command {

  /*
   * (non-Javadoc)
   *
   * @see presentacion.controlador.command.Command#execute(java.lang.Object)
   */
  @Override
  public Contexto execute(final Object objeto) {
    final int idFactura = (int) objeto;
    final SAFactura sa = FactorySA.getInstance().createSAFactura();
    String mensaje;
    Contexto contexto;
    try {
      final Collection<TLineaDeFactura> list = sa.listarLineasDeFactura(idFactura);
      contexto = new Contexto(EventosFactura.MOSTRAR_LIBROS_FACTURA_OK, list);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosFactura.MOSTRAR_LIBROS_FACTURA_KO, mensaje);
    }

    return contexto;
  }

}

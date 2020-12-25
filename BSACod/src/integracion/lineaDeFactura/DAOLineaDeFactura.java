package integracion.lineaDeFactura;

import java.util.Collection;

import negocio.lineadefactura.TLineaDeFactura;

/**
 * The Interface DAOLineaDeFactura.
 */
public interface DAOLineaDeFactura {

  /**
   * Aniadir libro A factura.
   *
   * @param tLineaDeFactura
   *          the t linea de factura
   * @return true, if successful
   */
  public boolean aniadirLibroAFactura(TLineaDeFactura tLineaDeFactura);

  /**
   * Eliminar libro de factura.
   *
   * @param tLineaDeFactura
   *          the t linea de factura
   * @return true, if successful
   */
  public boolean eliminarLibroDeFactura(TLineaDeFactura tLineaDeFactura);

  /**
   * Leer linea de factura.
   *
   * @param idLibro
   *          the id libro
   * @param idFactura
   *          the id factura
   * @return the t linea de factura
   */
  public TLineaDeFactura leerLineaDeFactura(int idLibro, int idFactura);

  /**
   * Listar lineas de factura.
   *
   * @param idFactura
   *          the id factura
   * @return the collection
   */
  public Collection<TLineaDeFactura> listarLineasDeFactura(int idFactura);
}

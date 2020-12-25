package negocio.factura;

import java.util.Collection;

import negocio.lineadefactura.TLineaDeFactura;

/**
 * The Interface SAFactura.
 */
public interface SAFactura {

  /**
   * Alta factura.
   *
   * @param idCliente
   *          the id cliente
   * @return the int
   * @throws Exception
   *           the exception
   */
  public int altaFactura(int idCliente) throws Exception;

  /**
   * Anadir libro A factura.
   *
   * @param tLineaDeFactura
   *          the t linea de factura
   * @throws Exception
   *           the exception
   */
  public void anadirLibroAFactura(TLineaDeFactura tLineaDeFactura) throws Exception;

  /**
   * Baja factura.
   *
   * @param id
   *          the id
   * @throws Exception
   *           the exception
   */
  public void bajaFactura(int id) throws Exception;

  /**
   * Cerrar factura.
   *
   * @param id
   *          the id
   * @throws Exception
   *           the exception
   */
  public void cerrarFactura(int id) throws Exception;

  /**
   * Eliminar libro de factura.
   *
   * @param tLineaDeFactura
   *          the t linea de factura
   * @throws Exception
   *           the exception
   */
  public void eliminarLibroDeFactura(TLineaDeFactura tLineaDeFactura) throws Exception;

  /**
   * Listar facturas.
   *
   * @return the collection
   * @throws Exception
   *           the exception
   */
  public Collection<TFactura> listarFacturas() throws Exception;

  /**
   * Listar facturas mas mil euros.
   *
   * @return the collection
   * @throws Exception
   *           the exception
   */
  public Collection<TFactura> listarFacturasMasMilEuros() throws Exception;

  /**
   * Listar lineas de factura.
   *
   * @param idFactura
   *          the id factura
   * @return the collection
   * @throws Exception
   *           the exception
   */
  public Collection<TLineaDeFactura> listarLineasDeFactura(int idFactura) throws Exception;

  /**
   * Modificar factura.
   *
   * @param tFactura
   *          the t factura
   * @throws Exception
   *           the exception
   */
  public void modificarFactura(TFactura tFactura) throws Exception;

  /**
   * Mostrar factura.
   *
   * @param id
   *          the id
   * @return the t factura
   * @throws Exception
   *           the exception
   */
  public TFactura mostrarFactura(int id) throws Exception;
}
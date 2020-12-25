package integracion.factura;

import java.util.Collection;

import negocio.factura.TFactura;

/**
 * The Interface DAOFactura.
 */
public interface DAOFactura {

  /**
   * Alta factura.
   *
   * @param idCliente
   *          the id cliente
   * @return the integer
   */
  public Integer altaFactura(int idCliente);

  /**
   * Baja factura.
   *
   * @param id
   *          the id
   * @return true, if successful
   */
  public boolean bajaFactura(int id);

  /**
   * Cerrar factura.
   *
   * @param id
   *          the id
   * @return true, if successful
   */
  public boolean cerrarFactura(int id);

  /**
   * Leer factura.
   *
   * @param id
   *          the id
   * @return the t factura
   */
  public TFactura leerFactura(int id);

  /**
   * Listar facturas.
   *
   * @return the collection
   */
  public Collection<TFactura> listarFacturas();

  /**
   * Listar facturas mas mil euros.
   *
   * @return the collection
   */
  public Collection<TFactura> listarFacturasMasMilEuros();

  /**
   * Modificar factura.
   *
   * @param tFactura
   *          the t factura
   * @return true, if successful
   */
  public boolean modificarFactura(TFactura tFactura);
}
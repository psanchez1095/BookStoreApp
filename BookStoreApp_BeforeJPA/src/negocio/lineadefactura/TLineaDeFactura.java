package negocio.lineadefactura;

/**
 * The Class TLineaDeFactura.
 */
public class TLineaDeFactura {

  /** The cantidad. */
  private int cantidad;

  /** The id. */
  private int id;

  /** The id factura. */
  private int idFactura;

  /** The id libro. */
  private int idLibro;

  /** The precio total. */
  private double precioTotal;

  /**
   * Gets the cantidad.
   *
   * @return the cantidad
   */
  public int getCantidad() {
    return cantidad;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the id factura.
   *
   * @return the id factura
   */
  public int getIdFactura() {
    return idFactura;
  }

  /**
   * Gets the id libro.
   *
   * @return the id libro
   */
  public int getIdLibro() {
    return idLibro;
  }

  /**
   * Gets the precio total.
   *
   * @return the precio total
   */
  public double getPrecioTotal() {
    return precioTotal;
  }

  /**
   * Sets the cantidad.
   *
   * @param cantidad
   *          the new cantidad
   */
  public void setCantidad(final int cantidad) {
    this.cantidad = cantidad;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setId(final int id) {
    this.id = id;
  }

  /**
   * Sets the id factura.
   *
   * @param idFactura
   *          the new id factura
   */
  public void setIdFactura(final int idFactura) {
    this.idFactura = idFactura;
  }

  /**
   * Sets the id libro.
   *
   * @param idLibro
   *          the new id libro
   */
  public void setIdLibro(final int idLibro) {
    this.idLibro = idLibro;
  }

  /**
   * Sets the precio total.
   *
   * @param precioTotal
   *          the new precio total
   */
  public void setPrecioTotal(final double precioTotal) {
    this.precioTotal = precioTotal;
  }

}

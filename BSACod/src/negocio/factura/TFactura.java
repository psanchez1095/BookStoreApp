package negocio.factura;

import java.util.Date;

/**
 * The Class TFactura.
 */
public class TFactura {

  /** The activo. */
  private boolean activo;

  /** The cerrada. */
  private boolean cerrada;

  /** The fecha. */
  private Date fecha;

  /** The id. */
  private int id;

  /** The id cliente. */
  private int idCliente;

  /** The importe. */
  private double importe;

  /**
   * Gets the fecha.
   *
   * @return the fecha
   */
  public Date getFecha() {
    return fecha;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getID() {
    return id;
  }

  /**
   * Gets the id cliente.
   *
   * @return the id cliente
   */
  public int getIdCliente() {
    return idCliente;
  }

  /**
   * Gets the importe.
   *
   * @return the importe
   */
  public double getImporte() {
    return importe;
  }

  /**
   * Checks if is activo.
   *
   * @return true, if is activo
   */
  public boolean isActivo() {
    return activo;
  }

  /**
   * Checks if is cerrada.
   *
   * @return true, if is cerrada
   */
  public boolean isCerrada() {
    return cerrada;
  }

  /**
   * Sets the activo.
   *
   * @param activo
   *          the new activo
   */
  public void setActivo(final Boolean activo) {
    this.activo = activo;
  }

  /**
   * Sets the cerrada.
   *
   * @param cerrada
   *          the new cerrada
   */
  public void setCerrada(final boolean cerrada) {
    this.cerrada = cerrada;
  }

  /**
   * Sets the fecha.
   *
   * @param fecha
   *          the new fecha
   */
  public void setFecha(final Date fecha) {
    this.fecha = fecha;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setID(final Integer id) {
    this.id = id;
  }

  /**
   * Sets the id cliente.
   *
   * @param idCliente
   *          the new id cliente
   */
  public void setIdCliente(final int idCliente) {
    this.idCliente = idCliente;
  }

  /**
   * Sets the importe.
   *
   * @param importe
   *          the new importe
   */
  public void setImporte(final Double importe) {
    this.importe = importe;
  }
}
package negocio.cliente;

/**
 * The Class TransferClienteEmpresa.
 */
public class TransferClienteEmpresa extends TransferCliente {

  /** The descuento por empresa. */
  private Double descuentoPorEmpresa;

  /**
   * Gets the descuento por empresa.
   *
   * @return the descuento por empresa
   */
  public Double getDescuentoPorEmpresa() {
    return descuentoPorEmpresa;
  }

  /**
   * Sets the descuento por empresa.
   *
   * @param descuentoPorEmpresa
   *          the new descuento por empresa
   */
  public void setDescuentoPorEmpresa(final Double descuentoPorEmpresa) {
    this.descuentoPorEmpresa = descuentoPorEmpresa;
  }

}
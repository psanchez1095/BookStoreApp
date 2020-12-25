package negocio.cliente;

/**
 * The Class TransferClienteNormal.
 */
public class TransferClienteNormal extends TransferCliente {

  /** The gastos de envio. */
  private Double gastosDeEnvio;

  /**
   * Gets the gastos de envio.
   *
   * @return the gastos de envio
   */
  public Double getGastosDeEnvio() {
    return gastosDeEnvio;
  }

  /**
   * Sets the gastos de envio.
   *
   * @param gastosDeEnvio
   *          the new gastos de envio
   */
  public void setGastosDeEnvio(final Double gastosDeEnvio) {
    this.gastosDeEnvio = gastosDeEnvio;
  }

}

package negocio.queries;

import negocio.factura.TFactura;

/**
 * The Class QueriesFactoryIMP.
 */
public class QueriesFactoryIMP extends QueriesFactory {

  /*
   * (non-Javadoc)
   *
   * @see integracion.queries.QueriesFactory#facturasSuperioresMilEuros()
   */
  @Override
  public Query<TFactura> facturasSuperioresMilEuros() {
    return new FacturasSuperioresMilEuros();
  }

}
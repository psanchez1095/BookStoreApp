
package negocio.queries;

import negocio.factura.TFactura;

/**
 * A factory for creating Queries objects.
 */
public abstract class QueriesFactory {

  /** The instance queries factory. */
  private static QueriesFactory instanceQueriesFactory;

  /**
   * Gets the single instance of QueriesFactory.
   *
   * @return single instance of QueriesFactory
   */
  public synchronized static QueriesFactory getInstance() {
    if (instanceQueriesFactory == null) {
      instanceQueriesFactory = new QueriesFactoryIMP();
    }
    return instanceQueriesFactory;
  }

  /**
   * Facturas superiores mil euros.
   *
   * @return the query
   */
  public abstract Query<TFactura> facturasSuperioresMilEuros();
}
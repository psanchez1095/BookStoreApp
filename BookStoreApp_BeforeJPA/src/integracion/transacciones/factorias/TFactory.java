package integracion.transacciones.factorias;

import integracion.transacciones.Transaction;

/**
 * A factory for creating T objects.
 */
public abstract class TFactory {

  /**
   * The Class FactorySingletonHelper.
   */
  private static class FactorySingletonHelper {

    /** The Constant INSTANCE. */
    private static final TFactory INSTANCE = new TFactoriaImp();
  }

  /**
   * Gets the single instance of TFactory.
   *
   * @return single instance of TFactory
   */
  public static TFactory getInstance() {
    return FactorySingletonHelper.INSTANCE;
  }

  /**
   * New transaccion.
   *
   * @return the transaction
   */
  public abstract Transaction newTransaccion();

}
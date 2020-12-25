package integracion.transacciones.manager;

import integracion.transacciones.Transaction;

/**
 * The Class TManager.
 */
public abstract class TManager {

  /**
   * The Class TManagerSingletonHelper.
   */
  private static class TManagerSingletonHelper {

    /** The Constant INSTANCE. */
    private static final TManager INSTANCE = new TManagerImp();
  }

  /**
   * Gets the single instance of TManager.
   *
   * @return single instance of TManager
   */
  public synchronized static TManager getInstance() {
    return TManagerSingletonHelper.INSTANCE;
  }

  /**
   * Gets the transaction.
   *
   * @return the transaction
   */
  public abstract Transaction getTransaction();

  /**
   * New transaction.
   *
   * @return the transaction
   */
  public abstract Transaction newTransaction();

  /**
   * Removes the transaction.
   */
  public abstract void removeTransaction();
}
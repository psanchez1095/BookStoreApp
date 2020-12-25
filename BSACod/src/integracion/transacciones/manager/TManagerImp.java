package integracion.transacciones.manager;

import java.util.concurrent.ConcurrentHashMap;

import integracion.transacciones.Transaction;
import integracion.transacciones.factorias.TFactory;

/**
 * The Class TManagerImp.
 */
public class TManagerImp extends TManager {

  /** The transactions. */
  private final ConcurrentHashMap<Thread, Transaction> transactions;

  /**
   * Instantiates a new t manager imp.
   */
  public TManagerImp() {
    transactions = new ConcurrentHashMap<>();
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.manager.TManager#getTransaction()
   */
  @Override
  public Transaction getTransaction() {
    return transactions.get(Thread.currentThread());
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.manager.TManager#newTransaction()
   */
  @Override
  public Transaction newTransaction() {
    if (transactions.containsKey(Thread.currentThread())) {
      return transactions.get(Thread.currentThread());
    }

    final Transaction transaccion = TFactory.getInstance().newTransaccion();
    transactions.put(Thread.currentThread(), transaccion);

    return transaccion;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.manager.TManager#removeTransaction()
   */
  @Override
  public void removeTransaction() {
    transactions.remove(Thread.currentThread());
  }
}
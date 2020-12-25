package integracion.transacciones.factorias;

import integracion.transacciones.Transaction;
import integracion.transacciones.TransactionMySQL;

/**
 * The Class TFactoriaImp.
 */
public class TFactoriaImp extends TFactory {

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.factorias.TFactory#newTransaccion()
   */
  @Override
  public Transaction newTransaccion() {
    final Transaction transaccion = new TransactionMySQL();
    return transaccion;
  }
}
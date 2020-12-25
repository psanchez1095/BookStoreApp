package integracion.transacciones;

import java.sql.SQLException;

/**
 * The Interface Transaction.
 */
public interface Transaction {

  /**
   * Commit.
   *
   * @throws SQLException
   *           the SQL exception
   */
  public void commit() throws SQLException;

  /**
   * Gets the resource.
   *
   * @return the resource
   */
  public Object getResource();

  /**
   * Rollback.
   *
   * @throws SQLException
   *           the SQL exception
   */
  public void rollback() throws SQLException;

  /**
   * Start.
   *
   * @throws SQLException
   *           the SQL exception
   * @throws ClassNotFoundException
   *           the class not found exception
   */
  public void start() throws SQLException, ClassNotFoundException;
}
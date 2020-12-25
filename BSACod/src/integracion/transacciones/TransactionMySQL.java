package integracion.transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Class TransactionMySQL.
 */
public class TransactionMySQL implements Transaction {

  /** The Constant PWD. */
  private static final String PWD = "";

  /** The Constant URL. */
  private static final String URL =
    "jdbc:mysql://127.0.0.1:3306/bookstoreapp?useUnicode=true&serverTimezone=UTC";

  /** The Constant USER. */
  private static final String USER = "root";

  /** The conexion. */
  private Connection conexion;

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.Transaction#commit()
   */
  @Override
  public void commit() throws SQLException {
    conexion.commit();
    conexion.close();
    conexion = null;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.Transaction#getResource()
   */
  @Override
  public Object getResource() {
    return conexion;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.Transaction#rollback()
   */
  @Override
  public void rollback() throws SQLException {
    conexion.rollback();
    conexion.close();
    conexion = null;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.transacciones.Transaction#start()
   */
  @Override
  public void start() throws SQLException, ClassNotFoundException {
    conexion = DriverManager.getConnection(URL, USER, PWD);
    conexion.setAutoCommit(false);
    /*
     * preguntar sobre esto Statement stmt = conexion.createStatement();
     * stmt.execute("PRAGMA foreign_keys = ON"); stmt.close();
     */
  }
}
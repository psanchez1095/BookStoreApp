package negocio.cliente;

import java.sql.SQLException;
import java.util.Collection;

/**
 * The Interface SACliente.
 */
public interface SACliente {

  /**
   * Alta cliente.
   *
   * @param transferCliente
   *          the transfer cliente
   * @return the integer
   * @throws SQLException
   *           the SQL exception
   * @throws ClassNotFoundException
   *           the class not found exception
   * @throws Exception
   *           the exception
   */
  public Integer altaCliente(TransferCliente transferCliente)
    throws SQLException, ClassNotFoundException, Exception;

  /**
   * Baja cliente.
   *
   * @param id
   *          the id
   * @return the integer
   * @throws SQLException
   *           the SQL exception
   * @throws ClassNotFoundException
   *           the class not found exception
   * @throws Exception
   *           the exception
   */
  public Integer bajaCliente(Integer id) throws SQLException, ClassNotFoundException, Exception;

  /**
   * Listar clientes.
   *
   * @return the collection
   * @throws SQLException
   *           the SQL exception
   * @throws ClassNotFoundException
   *           the class not found exception
   */
  public Collection<TransferCliente> listarClientes() throws SQLException, ClassNotFoundException;

  /**
   * Modificar cliente.
   *
   * @param transferCliente
   *          the transfer cliente
   * @return the integer
   * @throws SQLException
   *           the SQL exception
   * @throws ClassNotFoundException
   *           the class not found exception
   * @throws Exception
   *           the exception
   */
  public Integer modificarCliente(TransferCliente transferCliente)
    throws SQLException, ClassNotFoundException, Exception;

  /**
   * Mostrar cliente.
   *
   * @param id
   *          the id
   * @return the transfer cliente
   * @throws SQLException
   *           the SQL exception
   * @throws ClassNotFoundException
   *           the class not found exception
   * @throws Exception
   *           the exception
   */
  public TransferCliente mostrarCliente(Integer id)
    throws SQLException, ClassNotFoundException, Exception;
}
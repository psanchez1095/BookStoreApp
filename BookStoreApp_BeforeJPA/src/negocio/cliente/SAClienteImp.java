package negocio.cliente;

import java.sql.SQLException;
import java.util.Collection;

import integracion.cliente.DAOCliente;
import integracion.factorias.FactoriaDAO;
import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;

/**
 * The Class SAClienteImp.
 */
public class SAClienteImp implements SACliente {

  /*
   * (non-Javadoc)
   *
   * @see negocio.cliente.SACliente#altaCliente(negocio.cliente.TransferCliente)
   */
  @Override
  public Integer altaCliente(final TransferCliente transferCliente) throws Exception {

    Integer idResultado = -1;

    if (transferCliente != null) {

      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();

      if (transaction != null) {

        transaction.start();
        final TransferCliente cliente =
          daoCliente.leerClientePorIdentificacionFiscal(transferCliente.getIdentificacionFiscal()); // hacer
                                                                                                    // esta
                                                                                                    // operacion
                                                                                                    // en
                                                                                                    // DAO
        if (cliente == null) {
          idResultado = daoCliente.altaCliente(transferCliente);
          if (idResultado > 0) {
            transaction.commit();
          } else {
            transaction.rollback();
          }
        } else {
          final boolean activo = cliente.getActivo();
          if (activo) {
            transaction.rollback();
            throw new Exception("El cliente ya existe.");
          } else {
            cliente.setActivo(true);
            final boolean modificado = daoCliente.modificarCliente(transferCliente);
            if (modificado) {
              transaction.commit();
            } else {
              transaction.rollback();
            }
          }
        }
      }
      transactionManager.removeTransaction();
    }
    return idResultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.cliente.SACliente#bajaCliente(java.lang.Integer)
   */
  @Override
  public Integer bajaCliente(final Integer id)
    throws SQLException, ClassNotFoundException, Exception {
    Integer resultado = -1;
    if (id > 0) {
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();

      if (transaction != null) {

        transaction.start();
        final TransferCliente cliente = daoCliente.getClienteId(id);

        if (cliente != null) {
          final boolean borrado = daoCliente.bajaCliente(id);
          if (borrado) {
            resultado = 1;
            transaction.commit();
          } else {
            transaction.rollback();

          }
        } else {
          transaction.rollback();
          throw new Exception("Cliente inexistente, revisa el ID.");
        }
      }
      transactionManager.removeTransaction();
    }

    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.cliente.SACliente#listarClientes()
   */
  @Override
  public Collection<TransferCliente> listarClientes() throws ClassNotFoundException, SQLException {

    Collection<TransferCliente> listaClientes = null;

    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.newTransaction();

    if (transaction != null) {
      transaction.start();
      final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();
      listaClientes = daoCliente.listarClientes();
      transaction.commit();
    }
    transactionManager.removeTransaction();

    return listaClientes;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * negocio.cliente.SACliente#modificarCliente(negocio.cliente.TransferCliente)
   */
  @Override
  public Integer modificarCliente(final TransferCliente transferCliente)
    throws SQLException, ClassNotFoundException, Exception {

    Integer resultado = -1;

    if (transferCliente != null) {

      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();

      if (transaction != null) {

        transaction.start();
        final Integer idCliente = transferCliente.getID();
        final TransferCliente cliente = daoCliente.getClienteId(idCliente);
        if (cliente != null) {
          final boolean modificado = daoCliente.modificarCliente(transferCliente);

          if (modificado) {
            resultado = 1;
            transaction.commit();
          } else {
            transaction.rollback();
          }
        } else {

          transaction.rollback();

        }
      }
      transactionManager.removeTransaction();
    }
    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.cliente.SACliente#mostrarCliente(java.lang.Integer)
   */
  @Override
  public TransferCliente mostrarCliente(final Integer id)
    throws SQLException, ClassNotFoundException, Exception {

    TransferCliente cliente = null;

    if (id > 0) {

      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();

      if (transaction != null) {

        transaction.start();

        cliente = daoCliente.getClienteId(id);

        if (cliente == null) {
          transaction.rollback();
          throw new Exception("Cliente inexistente, revisa el ID.");
        } else {
          transaction.commit();
        }

      }
      transactionManager.removeTransaction();
    }
    return cliente;
  }
}
package negocio.editorial;

import java.sql.SQLException;
import java.util.Collection;

import integracion.editorial.DAOEditorial;
import integracion.factorias.FactoriaDAO;
import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;

/**
 * The Class SAEditorialImp.
 */
public class SAEditorialImp implements SAEditorial {

  /*
   * (non-Javadoc)
   *
   * @see negocio.editorial.SAEditorial#altaEditorial(negocio.editorial.
   * TransferEditorial)
   */
  @Override
  public Integer altaEditorial(final TransferEditorial tEditorial) throws Exception {
    Integer idResultado = -1;
    if (tEditorial != null) {
      final String nombre = tEditorial.getNombre();
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
      if (transaction != null) {
        transaction.start();
        final TransferEditorial editorial = daoEditorial.leerEditorialporNombre(nombre);

        if (editorial == null) {
          idResultado = daoEditorial.altaEditorial(tEditorial);
          if (idResultado > 0) {
            transaction.commit();
          } else {
            transaction.rollback();
          }
        } else {
          final boolean activo = editorial.getActivo();
          if (activo) {
            transaction.rollback();
            throw new Exception("La editorial ya existe");
          } else {
            editorial.setActivo(true);
            final boolean modificado = daoEditorial.modificarEditorial(editorial);
            if (modificado) {
              idResultado = editorial.getID();
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
   * @see negocio.editorial.SAEditorial#bajaEditorial(java.lang.Integer)
   */
  @Override
  public Integer bajaEditorial(final Integer id) throws Exception {
    Integer resultado = -1;
    if (id > 0) {
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
      if (transaction != null) {
        transaction.start();
        final TransferEditorial editorial = daoEditorial.leerEditorialporID(id);
        if (editorial != null) {
          final boolean borrado = daoEditorial.bajaEditorial(id);
          if (borrado) {
            resultado = 1;
            transaction.commit();
          } else {
            transaction.rollback();
          }
        } else {
          transaction.rollback();
          throw new Exception("La editorial que se quiere dar de baja no existe");
        }
      }
      transactionManager.removeTransaction();
    }
    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.editorial.SAEditorial#listarEditoriales()
   */
  @Override
  public Collection<TransferEditorial> listarEditoriales()
    throws SQLException, ClassNotFoundException {
    Collection<TransferEditorial> listaEditoriales = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.newTransaction();
    if (transaction != null) {
      transaction.start();
      final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
      listaEditoriales = daoEditorial.listarEditoriales();
      transaction.commit();
    }
    transactionManager.removeTransaction();

    return listaEditoriales;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.editorial.SAEditorial#modificarEditorial(negocio.editorial.
   * TransferEditorial)
   */
  @Override
  public Integer modificarEditorial(final TransferEditorial tEditorial) throws Exception {
    Integer resultado = -1;
    if (tEditorial != null) {
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
      if (transaction != null) {
        transaction.start();
        final Integer idEditorial = tEditorial.getID();
        final TransferEditorial editorial = daoEditorial.leerEditorialporID(idEditorial);
        if (editorial != null) {
          final boolean modificado = daoEditorial.modificarEditorial(tEditorial);
          if (modificado) {
            resultado = 1;
            transaction.commit();
          } else {
            transaction.rollback();
          }
        } else {
          transaction.rollback();
          throw new Exception("La editorial que se quiere modificar existe");

        }
      }
      transactionManager.removeTransaction();
    }
    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.editorial.SAEditorial#mostrarEditorial(java.lang.Integer)
   */
  @Override
  public TransferEditorial mostrarEditorial(final Integer id) throws Exception {
    TransferEditorial editorial = null;
    if (id != null & id > 0) {
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      if (transaction != null) {
        transaction.start();
        final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
        editorial = daoEditorial.mostrarEditorial(id);
        if (editorial == null) {
          transaction.rollback();
          throw new Exception("La editorial no existe");

        } else {
          transaction.commit();
        }
      }
      transactionManager.removeTransaction();
    }
    return editorial;
  }
}
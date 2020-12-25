package negocio.libro;

import java.sql.SQLException;
import java.util.Collection;

import integracion.editorial.DAOEditorial;
import integracion.factorias.FactoriaDAO;
import integracion.libro.DAOLibro;
import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.editorial.TransferEditorial;

/**
 * The Class SALibroImp.
 */
public class SALibroImp implements SALibro {

  /*
   * (non-Javadoc)
   *
   * @see negocio.libro.SALibro#altaLibro(negocio.libro.TFLibro)
   */
  @Override
  public Integer altaLibro(final TFLibro tLibro) throws Exception {
    Integer idResultado = -2;
    if (tLibro != null) {

      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();

      if (transaction != null) {
        transaction.start();
        final TFLibro libro = daoLibro.leeLibroPorNombre(tLibro.getTitulo());
        if (libro == null) {
          final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
          final TransferEditorial editorial =
            daoEditorial.leerEditorialporID(tLibro.getEditorialID());

          if (editorial != null) {
            if (editorial.getActivo() != true) {
              transaction.rollback();
              throw new Exception("La editorial no está activa.");
            }

            idResultado = daoLibro.altaLibro(tLibro);
          }
          if (idResultado > 0) {
            transaction.commit();
          } else {
            transaction.rollback();
            throw new Exception("La editorial no existe.");
          }
        } else {
          final boolean activo = libro.getActivo();
          if (activo) {
            transaction.rollback();
            throw new Exception("El libro ya existe.");
          } else {
            libro.setActivo(true);
            final boolean modificado = daoLibro.modificarLibro(libro);
            if (modificado) {
              idResultado = libro.getID();
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
   * @see negocio.libro.SALibro#bajaLibro(java.lang.Integer)
   */
  @Override
  public Integer bajaLibro(final Integer id) throws Exception {
    Integer resultado = -1;
    if (id > 0) {
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();
      if (transaction != null) {
        transaction.start();
        final TFLibro libro = daoLibro.leerLibroPorID(id);
        if (libro != null) {
          final boolean borrado = daoLibro.bajaLibro(id);
          if (borrado) {
            resultado = 1;
            transaction.commit();
          } else {
            transaction.rollback();

          }
        } else {
          transaction.rollback();
          throw new Exception("El libro que se quiere dar de baja no existe.");

        }
      }
      transactionManager.removeTransaction();
    }
    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.libro.SALibro#listarLibros()
   */
  @Override
  public Collection<TFLibro> listarLibros() throws SQLException, ClassNotFoundException {
    Collection<TFLibro> listaLibros = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.newTransaction();
    if (transaction != null) {
      transaction.start();
      final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();
      listaLibros = daoLibro.listarLibro();
      transaction.commit();
    }
    transactionManager.removeTransaction();

    return listaLibros;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.libro.SALibro#modificarLibro(negocio.libro.TFLibro)
   */
  @Override
  public Integer modificarLibro(final TFLibro tLibro) throws Exception {
    Integer resultado = -1;
    if (tLibro != null) {

      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();

      if (transaction != null) {
        transaction.start();
        final DAOEditorial daoEditorial = FactoriaDAO.getInstance().generarDAOEditorial();
        final TransferEditorial editorial =
          daoEditorial.leerEditorialporID(tLibro.getEditorialID());
        if (editorial != null) {
          if (editorial.getActivo() != true) {
            transaction.rollback();
            throw new Exception("La editorial no está activa.");
          }
          final boolean modificado = daoLibro.modificarLibro(tLibro);
          if (modificado) {
            resultado = 1;
            transaction.commit();
          } else {
            transaction.rollback();
          }
        } else {
          transaction.rollback();
          throw new Exception("La editorial no existe.");
        }
      }

      transactionManager.removeTransaction();

    }
    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.libro.SALibro#mostrarLibro(java.lang.Integer)
   */
  @Override
  public TFLibro mostrarLibro(final Integer id) throws Exception {
    TFLibro libro = null;
    if (id != null & id > 0) {
      final TManager transactionManager = TManager.getInstance();
      final Transaction transaction = transactionManager.newTransaction();
      if (transaction != null) {
        transaction.start();
        final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();
        libro = daoLibro.mostrarLibros(id);
        if (libro == null) {
          transaction.rollback();
          throw new Exception("El libro no existe.");
        } else {
          transaction.commit();
        }
      }
      transactionManager.removeTransaction();
    }
    return libro;
  }
}
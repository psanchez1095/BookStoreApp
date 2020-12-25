package integracion.libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.libro.TFLibro;

/**
 * The Class DAOLibroImp.
 */
public class DAOLibroImp implements DAOLibro {

  // no
  // estoy
  /** The Constant FOR_UPDATE. */
  // seguro
  public static final String FOR_UPDATE = " FOR UPDATE";

  /** The Constant SELECT_ALL_QUERY. */
  public static final String SELECT_ALL_QUERY = "SELECT * FROM libro WHERE activo=1"; // activo=1

  /** The Constant SELECT_QUERY_ID. */
  public static final String SELECT_QUERY_ID = "SELECT * FROM libro WHERE id =";

  /** The Constant SELECT_QUERY_NOMBRE. */
  public static final String SELECT_QUERY_NOMBRE = "SELECT * FROM libro WHERE TITULO =";

  /** The Constant UPDATE_QUERY. */
  public static final String UPDATE_QUERY =
    "UPDATE libro SET TITULO = ?, GENERO = ?, CANTIDAD = ?, ACTIVO = ?, EDITORIAL_FK = ?  WHERE ID =";

  /** The Constant UPDATE_QUERY_BAJA. */
  public static final String UPDATE_QUERY_BAJA = "UPDATE libro SET ACTIVO=0 WHERE ID = ";

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#altaLibro(negocio.libro.TFLibro)
   */
  @Override
  public Integer altaLibro(final TFLibro libro) {
    int id = 0;
    final TManager tm = TManager.getInstance();
    final Transaction transaction = tm.getTransaction();

    if (transaction != null) {

      try {
        final Connection con = (Connection) transaction.getResource();
        final PreparedStatement pstm =
          con.prepareStatement("INSERT INTO libro (TITULO, GENERO, CANTIDAD, ACTIVO, EDITORIAL_FK)"
            + "values ( ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, libro.getTitulo()); // set de los atributos del
                                              // transfer
        pstm.setString(2, libro.getGenero());
        pstm.setInt(3, libro.getCantidad());
        pstm.setBoolean(4, libro.getActivo());
        pstm.setInt(5, libro.getEditorialID()); // por defecto el activo
                                                // debería ser true?

        pstm.execute();

        final ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
          id = rs.getInt(1);
        }
        rs.close();
        pstm.close();

      } catch (final Exception e) {
        id = -1;
      }
    }

    return id;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#bajaLibro(java.lang.Integer)
   */
  @Override
  public boolean bajaLibro(final Integer IDlibro) {

    final TManager tm = TManager.getInstance();
    final Transaction transaction = tm.getTransaction();

    if (transaction != null) {
      Connection con = null;
      PreparedStatement pstm = null;
      try {
        con = (Connection) transaction.getResource();
        pstm = con.prepareStatement(SELECT_QUERY_ID + IDlibro + FOR_UPDATE);
        pstm.execute();

        pstm = con.prepareStatement(UPDATE_QUERY_BAJA + IDlibro);
        pstm.execute();

        pstm.close();
      } catch (final Exception e) {
        return false;
      }
    }

    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#leeLibroPorNombre(java.lang.String)
   */
  @Override
  public TFLibro leeLibroPorNombre(final String nombre) {
    TFLibro libro = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(
          DAOLibroImp.SELECT_QUERY_NOMBRE + '"' + nombre + '"' + DAOLibroImp.FOR_UPDATE);
        if (resultSet.next()) {
          libro = new TFLibro();
          libro.setID(resultSet.getInt("ID"));
          libro.setTitulo(resultSet.getString("TITULO"));
          libro.setGenero(resultSet.getString("GENERO"));
          libro.setCantidad(resultSet.getInt("CANTIDAD"));
          libro.setActivo(resultSet.getBoolean("ACTIVO"));
          libro.setEditorial(resultSet.getInt("EDITORIAL_FK"));
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        libro = null;
      }
    }
    return libro;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#leerLibroPorID(java.lang.Integer)
   */
  @Override
  public TFLibro leerLibroPorID(final Integer id) {
    TFLibro libro = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet =
          statement.executeQuery(DAOLibroImp.SELECT_QUERY_ID + id + DAOLibroImp.FOR_UPDATE);
        if (resultSet.next()) {
          libro = new TFLibro();
          libro.setID(id);
          libro.setTitulo(resultSet.getString("TITULO"));
          libro.setGenero(resultSet.getString("GENERO"));
          libro.setCantidad(resultSet.getInt("CANTIDAD"));
          libro.setActivo(resultSet.getBoolean("ACTIVO"));
          libro.setEditorial(resultSet.getInt("EDITORIAL_FK"));
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        libro = null;
      }
    }
    return libro;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#listarLibro()
   */
  @Override
  public Collection<TFLibro> listarLibro() {
    Collection<TFLibro> listaLibros = new ArrayList<>();
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(DAOLibroImp.SELECT_ALL_QUERY);
        while (resultSet.next()) {
          final TFLibro libro = new TFLibro();
          libro.setID(resultSet.getInt("ID"));
          libro.setTitulo(resultSet.getString("TITULO"));
          libro.setGenero(resultSet.getString("GENERO"));
          libro.setCantidad(resultSet.getInt("CANTIDAD"));
          libro.setActivo(resultSet.getBoolean("ACTIVO"));
          libro.setEditorial(resultSet.getInt("EDITORIAL_FK"));
          listaLibros.add(libro);
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        listaLibros = null;
      }
    }
    return listaLibros;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#modificarLibro(negocio.libro.TFLibro)
   */
  @Override
  public boolean modificarLibro(final TFLibro libro) {
    boolean resultado = false;
    final TManager tm = TManager.getInstance();
    final Transaction transaction = tm.getTransaction();
    if (transaction != null) {
      final Connection con = (Connection) transaction.getResource();
      try {
        final PreparedStatement statement =
          con.prepareStatement(DAOLibroImp.UPDATE_QUERY + libro.getID());
        statement.setString(1, libro.getTitulo());
        statement.setString(2, libro.getGenero());
        statement.setInt(3, libro.getCantidad());
        statement.setBoolean(4, libro.getActivo());
        statement.setInt(5, libro.getEditorialID());

        final Integer rs = statement.executeUpdate();
        resultado = rs > 0;
        statement.close();
      } catch (final Exception e) {
        resultado = false;
      }
    }
    return resultado;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.libro.DAOLibro#mostrarLibros(java.lang.Integer)
   */
  @Override
  public TFLibro mostrarLibros(final Integer IDlibro) {
    TFLibro libro = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet =
          statement.executeQuery(DAOLibroImp.SELECT_QUERY_ID + IDlibro + DAOLibroImp.FOR_UPDATE);
        if (resultSet.next()) {
          libro = new TFLibro();
          libro.setID(IDlibro);
          libro.setTitulo(resultSet.getString("TITULO"));
          libro.setGenero(resultSet.getString("GENERO"));
          libro.setCantidad(resultSet.getInt("CANTIDAD"));
          libro.setActivo(resultSet.getBoolean("ACTIVO"));
          libro.setEditorial(resultSet.getInt("EDITORIAL_FK"));
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        libro = null;
      }
    }
    return libro;
  }
}

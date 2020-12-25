package integracion.editorial;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.editorial.TransferEditorial;

/**
 * The Class DAOEditorialImp.
 */
public class DAOEditorialImp implements DAOEditorial {

  /** The Constant FOR_UPDATE. */
  public static final String FOR_UPDATE = " FOR UPDATE";

  /** The Constant INSERT_QUERY. */
  public static final String INSERT_QUERY =
    "INSERT INTO editorial (NOMBRE, EMAIL, TIPO, ANIOFUNDACION, NUMEROFACTURAS, ACTIVO) values (?, ?, ?, ?, ?, ?)";

  /** The Constant SELECT_ALL_QUERY. */
  public static final String SELECT_ALL_QUERY = "SELECT * FROM editorial";

  /** The Constant SELECT_QUERY_ID. */
  public static final String SELECT_QUERY_ID = "SELECT * FROM editorial WHERE ID = ";

  /** The Constant SELECT_QUERY_NOBRE. */
  public static final String SELECT_QUERY_NOBRE = "SELECT * FROM editorial WHERE NOMBRE = ";

  /** The Constant UPDATE_QUERY. */
  public static final String UPDATE_QUERY =
    "UPDATE editorial SET NOMBRE = ?, EMAIL = ?, TIPO = ?, ANIOFUNDACION = ?, NUMEROFACTURAS = ?, ACTIVO = ? WHERE ID =";

  /** The Constant UPDATE_QUERY_BAJA. */
  public static final String UPDATE_QUERY_BAJA = "UPDATE editorial SET ACTIVO=0 WHERE ID = ";

  /*
   * (non-Javadoc)
   *
   * @see integracion.editorial.DAOEditorial#altaEditorial(negocio.editorial.
   * TransferEditorial)
   */
  @Override
  public Integer altaEditorial(final TransferEditorial editorial) {
    int id = 0;
    final TManager tm = TManager.getInstance();
    final Transaction transaction = tm.getTransaction();

    if (transaction != null) {

      try {
        final Connection con = (Connection) transaction.getResource();
        final PreparedStatement pstm =
          con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

        pstm.setString(1, editorial.getNombre());
        pstm.setString(2, editorial.getEmail());
        pstm.setString(3, editorial.getTipo());
        pstm.setDate(4, new Date(editorial.getAnioFundacion().getTime()));
        pstm.setInt(5, editorial.getNumeroFacturas());
        pstm.setBoolean(6, editorial.getActivo());

        pstm.execute();

        final ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
          id = rs.getInt(1);
        }
        pstm.close();

      } catch (final SQLException e) {
        id = -1;
      }
    }
    return id;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.editorial.DAOEditorial#bajaEditorial(java.lang.Integer)
   */
  @Override
  public boolean bajaEditorial(final Integer id) {

    final TManager tm = TManager.getInstance();
    final Transaction transaction = tm.getTransaction();

    if (transaction != null) {
      Connection con = null;
      PreparedStatement pstm = null;
      try {
        con = (Connection) transaction.getResource();
        pstm = con.prepareStatement(SELECT_QUERY_ID + id + FOR_UPDATE);
        pstm.execute();

        pstm = con.prepareStatement(UPDATE_QUERY_BAJA + id);
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
   * @see
   * integracion.editorial.DAOEditorial#leerEditorialporID(java.lang.Integer)
   */
  @Override
  public TransferEditorial leerEditorialporID(final Integer id) {
    TransferEditorial editorial = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      try {
        final Connection connection = (Connection) transaction.getResource();
        final PreparedStatement statement = connection.prepareStatement(SELECT_QUERY_ID + id);
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
          editorial = new TransferEditorial();
          editorial.setID(resultSet.getInt("ID"));
          editorial.setNumeroFacturas(resultSet.getInt("NUMEROFACTURAS"));
          editorial.setTipo(resultSet.getString("TIPO"));
          editorial.setNombre(resultSet.getString("NOMBRE"));
          editorial.setEmail(resultSet.getString("EMAIL"));
          editorial.setAnioFundacion(resultSet.getDate("ANIOFUNDACION"));
          editorial.setActivo(resultSet.getBoolean("ACTIVO"));
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        editorial = null;
      }
    }
    return editorial;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * integracion.editorial.DAOEditorial#leerEditorialporNombre(java.lang.String)
   */
  @Override
  public TransferEditorial leerEditorialporNombre(final String nombre) {
    TransferEditorial editorial = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      try {
        final Connection connection = (Connection) transaction.getResource();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet =
          statement.executeQuery(SELECT_QUERY_NOBRE + '"' + nombre + '"' + FOR_UPDATE);
        if (resultSet.next()) {
          editorial = new TransferEditorial();
          editorial.setID(resultSet.getInt("ID"));
          editorial.setNumeroFacturas(resultSet.getInt("NUMEROFACTURAS"));
          editorial.setTipo(resultSet.getString("TIPO"));
          editorial.setNombre(nombre);
          editorial.setEmail(resultSet.getString("EMAIL"));
          editorial.setAnioFundacion(resultSet.getDate("ANIOFUNDACION"));
          editorial.setActivo(resultSet.getBoolean("ACTIVO"));
        }
        resultSet.close();
        statement.close();

      } catch (final Exception e) {
        editorial = null;
      }
    }
    return editorial;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.editorial.DAOEditorial#listarEditoriales()
   */
  @Override
  public Collection<TransferEditorial> listarEditoriales() {
    Collection<TransferEditorial> listaEditoriales = new ArrayList<>();
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      try {
        final Connection connection = (Connection) transaction.getResource();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
        while (resultSet.next()) {
          final TransferEditorial editorial = new TransferEditorial();
          editorial.setID(resultSet.getInt("ID"));
          editorial.setNumeroFacturas(resultSet.getInt("NUMEROFACTURAS"));
          editorial.setTipo(resultSet.getString("TIPO"));
          editorial.setNombre(resultSet.getString("NOMBRE"));
          editorial.setEmail(resultSet.getString("EMAIL"));
          editorial.setAnioFundacion(resultSet.getDate("ANIOFUNDACION"));
          editorial.setActivo(resultSet.getBoolean("ACTIVO"));
          listaEditoriales.add(editorial);
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        listaEditoriales = null;
      }
    }
    return listaEditoriales;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * integracion.editorial.DAOEditorial#modificarEditorial(negocio.editorial.
   * TransferEditorial)
   */
  @Override
  public boolean modificarEditorial(final TransferEditorial editorial) {
    boolean resultado = false;
    final TManager tm = TManager.getInstance();
    final Transaction transaction = tm.getTransaction();
    if (transaction != null) {
      try {
        final Connection connection = (Connection) transaction.getResource();
        final PreparedStatement statement =
          connection.prepareStatement(UPDATE_QUERY + editorial.getID());
        statement.setString(1, editorial.getNombre());
        statement.setString(2, editorial.getEmail());
        statement.setString(3, editorial.getTipo());
        statement.setDate(4, new Date(editorial.getAnioFundacion().getTime()));
        statement.setInt(5, editorial.getNumeroFacturas());
        statement.setBoolean(6, editorial.getActivo());

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
   * @see integracion.editorial.DAOEditorial#mostrarEditorial(java.lang.Integer)
   */
  @Override
  public TransferEditorial mostrarEditorial(final Integer id) {
    TransferEditorial editorial = null;
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      try {
        final Connection connection = (Connection) transaction.getResource();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(SELECT_QUERY_ID + id + FOR_UPDATE);
        if (resultSet.next()) {
          editorial = new TransferEditorial();
          editorial.setID(id);
          editorial.setNumeroFacturas(resultSet.getInt("NUMEROFACTURAS"));
          editorial.setTipo(resultSet.getString("TIPO"));
          editorial.setNombre(resultSet.getString("NOMBRE"));
          editorial.setEmail(resultSet.getString("EMAIL"));
          editorial.setAnioFundacion(resultSet.getDate("ANIOFUNDACION"));
          editorial.setActivo(resultSet.getBoolean("ACTIVO"));
        }
        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        editorial = null;
      }
    }
    return editorial;
  }
}

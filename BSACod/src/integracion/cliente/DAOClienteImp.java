package integracion.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteEmpresa;
import negocio.cliente.TransferClienteNormal;

/**
 * The Class DAOClienteImp.
 */
public class DAOClienteImp implements DAOCliente {

  /** The Constant FOR_UPDATE. */
  public static final String FOR_UPDATE = " FOR UPDATE";

  /** The Constant INSERT_QUERY_CLIENTE. */
  public static final String INSERT_QUERY_CLIENTE =
    "INSERT INTO  cliente (NOMBRE, IDENTIFICACIONFISCAL, DIRECCION, EMAIL, ACTIVO,GASTOSENVIO) "
      + "values (?, ?, ?, ?, ?,?)";

  /** The Constant INSERT_QUERY_EMPRESA. */
  public static final String INSERT_QUERY_EMPRESA =
    "INSERT INTO  cliente (NOMBRE, IDENTIFICACIONFISCAL, DIRECCION, EMAIL, ACTIVO,DESCUENTOEMPRESA) "
      + "values (?, ?, ?, ?, ?,?)";

  /** The Constant SELECT_ALL_QUERY. */
  public static final String SELECT_ALL_QUERY = "SELECT * FROM cliente";

  /** The Constant SELECT_QUERY_ID. */
  public static final String SELECT_QUERY_ID = "SELECT * FROM cliente WHERE ID =";

  /** The Constant SELECT_QUERY_IDENTIFICACIONFISCAL. */
  public static final String SELECT_QUERY_IDENTIFICACIONFISCAL =
    "SELECT * FROM cliente WHERE IDENTIFICACIONFISCAL =";

  /** The Constant UPDATE_QUERY_BAJA. */
  public static final String UPDATE_QUERY_BAJA = "UPDATE cliente SET ACTIVO=0 WHERE ID =";

  /** The Constant UPDATE_QUERY_EMPRESA. */
  public static final String UPDATE_QUERY_EMPRESA =
    "UPDATE cliente SET NOMBRE = ?, IDENTIFICACIONFISCAL = ?, DIRECCION = ?, EMAIL = ?, "
      + "ACTIVO = ?, DESCUENTOEMPRESA = ? WHERE ID =";

  /** The Constant UPDATE_QUERY_NORMAL. */
  public static final String UPDATE_QUERY_NORMAL =
    "UPDATE cliente SET NOMBRE = ?, IDENTIFICACIONFISCAL = ?, DIRECCION = ?, EMAIL = ?, "
      + "ACTIVO = ?, GASTOSENVIO = ? WHERE ID =";

  /*
   * (non-Javadoc)
   *
   * @see
   * integracion.cliente.DAOCliente#altaCliente(negocio.cliente.TransferCliente)
   */
  @Override
  public Integer altaCliente(final TransferCliente transferCliente) {
    int id = 0;
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();

    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        PreparedStatement preparedStatement;
        if (transferCliente instanceof TransferClienteEmpresa) {
          preparedStatement =
            connection.prepareStatement(INSERT_QUERY_EMPRESA, Statement.RETURN_GENERATED_KEYS);
        } else {
          preparedStatement =
            connection.prepareStatement(INSERT_QUERY_CLIENTE, Statement.RETURN_GENERATED_KEYS);
        }

        preparedStatement.setString(1, transferCliente.getNombre());
        preparedStatement.setString(2, transferCliente.getIdentificacionFiscal());
        preparedStatement.setString(3, transferCliente.getDireccion());
        preparedStatement.setString(4, transferCliente.getEmail());
        preparedStatement.setBoolean(5, transferCliente.getActivo());
        if (transferCliente instanceof TransferClienteEmpresa) {
          preparedStatement.setDouble(6,
            ((TransferClienteEmpresa) transferCliente).getDescuentoPorEmpresa());
        } else {
          preparedStatement.setDouble(6,
            ((TransferClienteNormal) transferCliente).getGastosDeEnvio());
        }
        preparedStatement.execute();

        final ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
          id = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
      } catch (final SQLException e) {
        id = -1;
      }
    }
    return id;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.cliente.DAOCliente#bajaCliente(java.lang.Integer)
   */
  @Override
  public boolean bajaCliente(final Integer id) {

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
   * @see integracion.cliente.DAOCliente#existeCliente(int)
   */
  @Override
  public boolean existeCliente(final int id) {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();
    boolean exists = false;
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM cliente WHERE ACTIVO = 1 AND ID = ? FOR UPDATE");
        statement.setInt(1, id);
        final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
          exists = true;
        }
        resultSet.close();
        statement.close();
      } catch (final SQLException e) {
        exists = false;
      }
    }
    return exists;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.cliente.DAOCliente#getClienteEmpresaId(java.lang.Integer)
   */
  @Override
  public TransferClienteEmpresa getClienteEmpresaId(final Integer id) {
    TransferClienteEmpresa transferClienteEmpresa = null;
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(SELECT_QUERY_ID + id + FOR_UPDATE);
        if (resultSet.next()) {
          transferClienteEmpresa = new TransferClienteEmpresa();
          transferClienteEmpresa.setID(id);
          transferClienteEmpresa.setNombre(resultSet.getString("NOMBRE"));
          transferClienteEmpresa
            .setIdentificacionFiscal(resultSet.getString("IDENTIFICACIONFISCAL"));
          transferClienteEmpresa.setDireccion(resultSet.getString("DIRECCION"));
          transferClienteEmpresa.setEmail(resultSet.getString("EMAIL"));
          transferClienteEmpresa.setActivo(resultSet.getBoolean("ACTIVO"));
          transferClienteEmpresa.setDescuentoPorEmpresa(resultSet.getDouble("DESCUENTOEMPRESA"));
        }

        resultSet.close();
        statement.close();
      } catch (final SQLException e) {
        transferClienteEmpresa = null;
      }
    }
    return transferClienteEmpresa;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.cliente.DAOCliente#getClienteId(java.lang.Integer)
   */
  @Override
  public TransferCliente getClienteId(final Integer id) {
    TransferCliente transferCliente = null;
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(SELECT_QUERY_ID + id + FOR_UPDATE);
        if (resultSet.next()) {

          final String nombre = resultSet.getString("NOMBRE");
          final String idFisc = resultSet.getString("IDENTIFICACIONFISCAL");
          final String direccion = resultSet.getString("DIRECCION");
          final String email = resultSet.getString("EMAIL");
          final boolean activo = resultSet.getBoolean("ACTIVO");
          Double temp = null;
          temp = resultSet.getDouble("DESCUENTOEMPRESA");
          if (temp != 0.0) {
            transferCliente = new TransferClienteEmpresa();
            ((TransferClienteEmpresa) transferCliente).setDescuentoPorEmpresa(temp.doubleValue());
          }

          else {
            temp = resultSet.getDouble("GASTOSENVIO");
            transferCliente = new TransferClienteNormal();
            ((TransferClienteNormal) transferCliente).setGastosDeEnvio(temp.doubleValue());
          }
          transferCliente.setID(id);
          transferCliente.setNombre(nombre);
          transferCliente.setIdentificacionFiscal(idFisc);
          transferCliente.setDireccion(direccion);
          transferCliente.setEmail(email);
          transferCliente.setActivo(activo);
        }

        resultSet.close();
        statement.close();
      } catch (final SQLException e) {
        transferCliente = null;
      }
    }
    return transferCliente;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.cliente.DAOCliente#getClienteNormalId(java.lang.Integer)
   */
  @Override
  public TransferClienteNormal getClienteNormalId(final Integer id) {
    TransferClienteNormal transferClienteNormal = null;
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(SELECT_QUERY_ID + id + FOR_UPDATE);
        if (resultSet.next()) {
          transferClienteNormal = new TransferClienteNormal();
          transferClienteNormal.setID(id);
          transferClienteNormal.setNombre(resultSet.getString("NOMBRE"));
          transferClienteNormal
            .setIdentificacionFiscal(resultSet.getString("IDENTIFICACIONFISCAL"));
          transferClienteNormal.setDireccion(resultSet.getString("DIRECCION"));
          transferClienteNormal.setEmail(resultSet.getString("EMAIL"));
          transferClienteNormal.setActivo(resultSet.getBoolean("ACTIVO"));
          transferClienteNormal.setGastosDeEnvio(resultSet.getDouble("GASTOSENVIO"));
        }
        resultSet.close();
        statement.close();
      } catch (final SQLException e) {
        transferClienteNormal = null;
      }
    }
    return transferClienteNormal;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * integracion.cliente.DAOCliente#leerClientePorIdentificacionFiscal(java.lang
   * .String)
   */
  @Override
  public TransferCliente leerClientePorIdentificacionFiscal(final String identificacionFiscal) {
    TransferCliente transferCliente = null;
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(
          SELECT_QUERY_IDENTIFICACIONFISCAL + '"' + identificacionFiscal + '"' + FOR_UPDATE);
        if (resultSet.next()) {
          transferCliente = new TransferCliente();
          transferCliente.setID(resultSet.getInt("ID"));
          transferCliente.setNombre(resultSet.getString("NOMBRE"));
          transferCliente.setIdentificacionFiscal(identificacionFiscal);
          transferCliente.setDireccion(resultSet.getString("DIRECCION"));
          transferCliente.setEmail(resultSet.getString("EMAIL"));
          transferCliente.setActivo(resultSet.getBoolean("ACTIVO"));
        }
      } catch (final SQLException e) {
        transferCliente = null;
      }
    }
    return transferCliente;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.cliente.DAOCliente#listarClientes()
   */
  @Override
  public Collection<TransferCliente> listarClientes() {
    Collection<TransferCliente> listaClientes = new ArrayList<>();
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
        while (resultSet.next()) {
          TransferCliente transferCliente = new TransferCliente();
          final int id = resultSet.getInt("ID");
          final String nombre = resultSet.getString("NOMBRE");
          final String idFisc = resultSet.getString("IDENTIFICACIONFISCAL");
          final String direccion = resultSet.getString("DIRECCION");
          final String email = resultSet.getString("EMAIL");
          final boolean activo = resultSet.getBoolean("ACTIVO");
          Double temp = null;
          temp = resultSet.getDouble("DESCUENTOEMPRESA");
          if (temp != 0.0) {
            transferCliente = new TransferClienteEmpresa();
            ((TransferClienteEmpresa) transferCliente).setDescuentoPorEmpresa(temp.doubleValue());
          }

          else {
            temp = resultSet.getDouble("GASTOSENVIO");
            transferCliente = new TransferClienteNormal();
            ((TransferClienteNormal) transferCliente).setGastosDeEnvio(temp.doubleValue());
          }
          transferCliente.setID(id);
          transferCliente.setNombre(nombre);
          transferCliente.setIdentificacionFiscal(idFisc);
          transferCliente.setDireccion(direccion);
          transferCliente.setEmail(email);
          transferCliente.setActivo(activo);

          listaClientes.add(transferCliente);
        }
        resultSet.close();
        statement.close();
      } catch (final SQLException e) {
        listaClientes = null;
      }
    }
    return listaClientes;
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.cliente.DAOCliente#modificarCliente(negocio.cliente.
   * TransferCliente)
   */
  @Override
  public boolean modificarCliente(final TransferCliente transferCliente) {
    boolean result = false;
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.getTransaction();

    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        PreparedStatement preparedStatement = null;

        if (transferCliente instanceof TransferClienteEmpresa) {
          preparedStatement =
            connection.prepareStatement(UPDATE_QUERY_EMPRESA + transferCliente.getID());
        } else {
          preparedStatement =
            connection.prepareStatement(UPDATE_QUERY_NORMAL + transferCliente.getID());
        }

        preparedStatement.setString(1, transferCliente.getNombre());
        preparedStatement.setString(2, transferCliente.getIdentificacionFiscal());
        preparedStatement.setString(3, transferCliente.getDireccion());
        preparedStatement.setString(4, transferCliente.getEmail());
        preparedStatement.setBoolean(5, transferCliente.getActivo());
        if (transferCliente instanceof TransferClienteEmpresa) {
          preparedStatement.setDouble(6,
            ((TransferClienteEmpresa) transferCliente).getDescuentoPorEmpresa());
        } else {
          preparedStatement.setDouble(6,
            ((TransferClienteNormal) transferCliente).getGastosDeEnvio());
        }
        final Integer rs = preparedStatement.executeUpdate();
        result = rs > 0;

        preparedStatement.close();
      } catch (final SQLException e) {
        result = false;
      }
    }
    return result;
  }
}
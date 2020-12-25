package integracion.factura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.factura.TFactura;
import negocio.queries.QueriesFactory;

/**
 * The Class DAOFacturaImp.
 */
public class DAOFacturaImp implements DAOFactura {

  /** The Constant SELECT_MASMIL_QUERY. */
  public static final String SELECT_MASMIL_QUERY =
    "SELECT * FROM factura WHERE IMPORTE > 1000 FOR UPDATE";

  /** The Constant INSERT_QUERY. */
  private static final String INSERT_QUERY =
    "INSERT INTO factura (CLIENTE_FK, FECHA, IMPORTE) VALUES (?, CURDATE(), 0)";

  /** The Constant SELECT_ALL_QUERY. */
  private static final String SELECT_ALL_QUERY =
    "SELECT * FROM factura WHERE ACTIVO = 1 FOR UPDATE";

  /*
   * (non-Javadoc)
   * 
   * @see integracion.factura.DAOFactura#altaFactura(int)
   */
  @Override
  public Integer altaFactura(final int idCliente) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    Integer idFactura = null;
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
        connection = (Connection) transaction.getResource();
        stmt = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, idCliente);
        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        if (rs.next()) {
          idFactura = rs.getInt(1);
        }
        rs.close();
        stmt.close();
      } catch (final Exception e) {
        return null;
      }
      return idFactura;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see integracion.factura.DAOFactura#bajaFactura(int)
   */
  @Override
  public boolean bajaFactura(final int id) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      try {
        connection = (Connection) transaction.getResource();
        stmt = connection.prepareStatement("UPDATE factura SET ACTIVO = 0 WHERE ID = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
      } catch (final Exception e) {
        return false;
      }
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see integracion.factura.DAOFactura#cerrarFactura(int)
   */
  @Override
  public boolean cerrarFactura(final int id) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      try {
        connection = (Connection) transaction.getResource();
        stmt = connection.prepareStatement("UPDATE factura SET CERRADO = 1 WHERE ID = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
      } catch (final Exception e) {
        return false;
      }
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see integracion.factura.DAOFactura#leerFactura(int)
   */
  @Override
  public TFactura leerFactura(final int id) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    TFactura tFactura = null;
    if (transaction != null) {
      try {
        final Connection connection = (Connection) transaction.getResource();
        final PreparedStatement stmt = connection
          .prepareStatement("SELECT * FROM factura WHERE ID = ? AND ACTIVO = 1 FOR UPDATE");
        stmt.setInt(1, id);
        final ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
          tFactura = new TFactura();
          tFactura.setID(id);
          tFactura.setFecha(rs.getDate("FECHA"));
          tFactura.setImporte(rs.getDouble("IMPORTE"));
          tFactura.setIdCliente(rs.getInt("CLIENTE_FK"));
          tFactura.setActivo(rs.getBoolean("ACTIVO"));
          tFactura.setCerrada(rs.getBoolean("CERRADO"));
        }
        rs.close();
        stmt.close();
      } catch (final Exception e) {
        return null;
      }
      return tFactura;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see integracion.factura.DAOFactura#listarFacturas()
   */
  @Override
  public Collection<TFactura> listarFacturas() {
    final List<TFactura> listaFacturas = new ArrayList<>();
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      final Connection connection = (Connection) transaction.getResource();
      try {
        final PreparedStatement statement =
          connection.prepareStatement(DAOFacturaImp.SELECT_ALL_QUERY);
        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
          final TFactura factura = new TFactura();
          factura.setID(resultSet.getInt("ID"));
          factura.setFecha(resultSet.getDate("FECHA"));
          factura.setIdCliente(resultSet.getInt("CLIENTE_FK"));
          factura.setImporte(resultSet.getDouble("IMPORTE"));
          factura.setActivo(resultSet.getBoolean("ACTIVO"));
          factura.setCerrada(resultSet.getBoolean("CERRADO"));
          listaFacturas.add(factura);
        }

        resultSet.close();
        statement.close();
      } catch (final Exception e) {
        return null;
      }
      return listaFacturas;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see integracion.factura.DAOFactura#listarFacturasMasMilEuros()
   */
  @Override
  public Collection<TFactura> listarFacturasMasMilEuros() {
    return QueriesFactory.getInstance().facturasSuperioresMilEuros().execute();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * integracion.factura.DAOFactura#modificarFactura(negocio.factura.TFactura)
   */
  @Override
  public boolean modificarFactura(final TFactura tFactura) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      try {
        connection = (Connection) transaction.getResource();
        stmt = connection.prepareStatement(
          "UPDATE factura SET FECHA = CURDATE(), IMPORTE = ?, CLIENTE_FK = ? WHERE ID = ?");
        stmt.setDouble(1, tFactura.getImporte());
        stmt.setInt(2, tFactura.getIdCliente());
        stmt.setInt(3, tFactura.getID());
        stmt.executeUpdate();
        stmt.close();
      } catch (final Exception e) {
        return false;
      }
      return true;
    }
    return false;
  }
}
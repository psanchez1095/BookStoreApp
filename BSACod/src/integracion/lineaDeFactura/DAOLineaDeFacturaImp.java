package integracion.lineaDeFactura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.lineadefactura.TLineaDeFactura;

/**
 * The Class DAOLineaDeFacturaImp.
 */
public class DAOLineaDeFacturaImp implements DAOLineaDeFactura {

  /*
   * (non-Javadoc)
   *
   * @see
   * integracion.lineaDeFactura.DAOLineaDeFactura#aniadirLibroAFactura(negocio.
   * lineadefactura.TLineaDeFactura)
   */
  @Override
  public boolean aniadirLibroAFactura(final TLineaDeFactura tLineaDeFactura) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
        connection = (Connection) transaction.getResource();
        stmt = connection.prepareStatement(
          "SELECT * FROM lineadefactura WHERE LIBRO_PK = ? AND FACTURA_PK = ? FOR UPDATE");
        stmt.setInt(1, tLineaDeFactura.getIdLibro());
        stmt.setInt(2, tLineaDeFactura.getIdFactura());
        rs = stmt.executeQuery();
        if (rs.next()) {
          final int cantidad = rs.getInt("CANTIDAD") + tLineaDeFactura.getCantidad();
          final double precio = rs.getDouble("PRECIOTOTAL") + tLineaDeFactura.getPrecioTotal();
          final int id = rs.getInt("ID");
          rs.close();
          stmt.close();
          stmt = connection.prepareStatement(
            "UPDATE lineadefactura SET PRECIOTOTAL = ?, CANTIDAD = ? WHERE ID = ?");
          stmt.setDouble(1, precio);
          stmt.setInt(2, cantidad);
          stmt.setInt(3, id);
        } else {
          rs.close();
          stmt.close();
          stmt = connection.prepareStatement(
            "INSERT INTO lineadefactura (LIBRO_PK,FACTURA_PK,PRECIOTOTAL,CANTIDAD) VALUES (?,?,?,?)");
          stmt.setInt(1, tLineaDeFactura.getIdLibro());
          stmt.setInt(2, tLineaDeFactura.getIdFactura());
          stmt.setDouble(3, tLineaDeFactura.getPrecioTotal());
          stmt.setInt(4, tLineaDeFactura.getCantidad());
        }
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
   * @see
   * integracion.lineaDeFactura.DAOLineaDeFactura#eliminarLibroDeFactura(negocio
   * .lineadefactura.TLineaDeFactura)
   */
  @Override
  public boolean eliminarLibroDeFactura(final TLineaDeFactura tLineaDeFactura) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
        connection = (Connection) transaction.getResource();

        stmt = connection.prepareStatement(
          "SELECT * FROM lineadefactura WHERE LIBRO_PK = ? AND FACTURA_PK = ? FOR UPDATE");
        stmt.setInt(1, tLineaDeFactura.getIdLibro());
        stmt.setInt(2, tLineaDeFactura.getIdFactura());
        rs = stmt.executeQuery();
        if (!rs.next()) {
          return true;
        }
        int cantidad = rs.getInt("CANTIDAD");
        final int id = rs.getInt("ID");
        double precioEliminado = rs.getDouble("PRECIOTOTAL");
        if (cantidad <= tLineaDeFactura.getCantidad()) {
          rs.close();
          stmt.close();
          stmt = connection.prepareStatement("DELETE FROM lineadefactura WHERE ID = ?");
          stmt.setInt(1, id);
        } else {
          double precio = precioEliminado / cantidad;
          precioEliminado = precio * tLineaDeFactura.getCantidad();
          cantidad -= tLineaDeFactura.getCantidad();
          precio *= cantidad;
          rs.close();
          stmt.close();
          stmt = connection.prepareStatement(
            "UPDATE lineadefactura SET PRECIOTOTAL = ?, CANTIDAD = ? WHERE ID = ?");
          stmt.setDouble(1, precio);
          stmt.setInt(2, cantidad);
          stmt.setInt(3, id);
        }
        stmt.executeUpdate();
        stmt.close();
        tLineaDeFactura.setPrecioTotal(precioEliminado);
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
   * @see integracion.lineaDeFactura.DAOLineaDeFactura#leerLineaDeFactura(int,
   * int)
   */
  @Override
  public TLineaDeFactura leerLineaDeFactura(final int idLibro, final int idFactura) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();
    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      TLineaDeFactura tLineaDeFactura = null;
      try {
        connection = (Connection) transaction.getResource();

        stmt = connection.prepareStatement(
          "SELECT * FROM lineadefactura WHERE LIBRO_PK = ? AND FACTURA_PK = ? FOR UPDATE");
        stmt.setInt(1, idLibro);
        stmt.setInt(2, idFactura);
        rs = stmt.executeQuery();
        if (rs.next()) {
          tLineaDeFactura = new TLineaDeFactura();
          tLineaDeFactura.setId(rs.getInt("ID"));
          tLineaDeFactura.setIdFactura(idFactura);
          tLineaDeFactura.setIdLibro(idLibro);
          tLineaDeFactura.setCantidad(rs.getInt("CANTIDAD"));
          tLineaDeFactura.setPrecioTotal(rs.getDouble("PRECIOTOTAL"));
        }
        rs.close();
        stmt.close();
      } catch (final Exception e) {
        return null;
      }
      return tLineaDeFactura;
    }
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * integracion.lineaDeFactura.DAOLineaDeFactura#listarLineasDeFactura(int)
   */
  @Override
  public Collection<TLineaDeFactura> listarLineasDeFactura(final int idFactura) {
    final TManager transferManager = TManager.getInstance();
    final Transaction transaction = transferManager.getTransaction();

    if (transaction != null) {
      Connection connection;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      final Collection<TLineaDeFactura> list = new ArrayList<>();
      try {
        connection = (Connection) transaction.getResource();

        stmt = connection
          .prepareStatement("SELECT * FROM lineadefactura WHERE FACTURA_PK = ? FOR UPDATE");
        stmt.setInt(1, idFactura);
        rs = stmt.executeQuery();
        TLineaDeFactura tLineaDeFactura;
        while (rs.next()) {
          tLineaDeFactura = new TLineaDeFactura();
          tLineaDeFactura.setId(rs.getInt("ID"));
          tLineaDeFactura.setIdFactura(idFactura);
          tLineaDeFactura.setIdLibro(rs.getInt("LIBRO_PK"));
          tLineaDeFactura.setCantidad(rs.getInt("CANTIDAD"));
          tLineaDeFactura.setPrecioTotal(rs.getDouble("PRECIOTOTAL"));
          list.add(tLineaDeFactura);
        }
        rs.close();
        stmt.close();
      } catch (final Exception e) {
        return null;
      }
      return list;
    }
    return null;
  }

}

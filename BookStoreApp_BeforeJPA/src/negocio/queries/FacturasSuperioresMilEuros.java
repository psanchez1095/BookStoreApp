
package negocio.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.factura.DAOFacturaImp;
import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.factura.TFactura;

/**
 * The Class FacturasSuperioresMilEuros.
 */
public class FacturasSuperioresMilEuros implements Query<TFactura> {

  /*
   * (non-Javadoc)
   *
   * @see integracion.queries.Query#execute()
   */
  @Override
  public List<TFactura> execute() {
    final TManager transactionManager = TManager.getInstance();
    final Transaction transaction = transactionManager.getTransaction();
    if (transaction != null) {
      final List<TFactura> listaFacturas = new ArrayList<>();
      try {
        final Connection connection = (Connection) transaction.getResource();
        final Statement stmt = connection.createStatement();
        final ResultSet rs = stmt.executeQuery(DAOFacturaImp.SELECT_MASMIL_QUERY);
        TFactura tFactura;
        while (rs.next()) {
          tFactura = new TFactura();
          tFactura.setID(rs.getInt("ID"));
          tFactura.setFecha(rs.getDate("FECHA"));
          tFactura.setImporte(rs.getDouble("IMPORTE"));
          tFactura.setActivo(rs.getBoolean("ACTIVO"));
          tFactura.setIdCliente(rs.getInt("CLIENTE_FK"));
          listaFacturas.add(tFactura);
        }
        rs.close();
        stmt.close();
      } catch (final Exception e) {
        return null;
      }
      return listaFacturas;
    }
    return null;
  }
}
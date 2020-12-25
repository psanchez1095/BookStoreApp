package negocio.factura;

import java.util.Collection;

import integracion.cliente.DAOCliente;
import integracion.factorias.FactoriaDAO;
import integracion.factura.DAOFactura;
import integracion.libro.DAOLibro;
import integracion.lineaDeFactura.DAOLineaDeFactura;
import integracion.transacciones.Transaction;
import integracion.transacciones.manager.TManager;
import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteEmpresa;
import negocio.libro.TFLibro;
import negocio.lineadefactura.TLineaDeFactura;

/**
 * The Class SAFacturaImp.
 */
public class SAFacturaImp implements SAFactura {

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#altaFactura(int)
   */
  @Override
  public int altaFactura(final int idCliente) throws Exception {

    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();
    if (!daoCliente.existeCliente(idCliente)) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("El cliente no existe");
    }
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final Integer result = dao.altaFactura(idCliente);
    if (result == null) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("Error al dar de alta la factura");
    }
    transaction.commit();
    tManager.removeTransaction();
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#anadirLibroAFactura(negocio.lineadefactura.
   * TLineaDeFactura)
   */
  @Override
  public void anadirLibroAFactura(final TLineaDeFactura tLineaDeFactura) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura daoFactura = FactoriaDAO.getInstance().generarDAOFactura();
    final TFactura tFactura = daoFactura.leerFactura(tLineaDeFactura.getIdFactura());
    if (tFactura == null || !tFactura.isActivo()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe la factura");
    }
    if (tFactura.isCerrada()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("La factura está cerrada");
    }
    final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();
    TFLibro tLibro = null;
    try {
      tLibro = daoLibro.leerLibroPorID(tLineaDeFactura.getIdLibro());
    } catch (final Exception ex) {
    }
    if (tLibro == null || !tLibro.getActivo()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe el libro");
    }
    final DAOCliente daoCliente = FactoriaDAO.getInstance().generarDAOCliente();
    final TransferCliente tCliente = daoCliente.getClienteId(tFactura.getIdCliente());
    double descuento = 0;
    if (tCliente instanceof TransferClienteEmpresa) {
      descuento = ((TransferClienteEmpresa) tCliente).getDescuentoPorEmpresa();
    }
    if (descuento > 0) {
      tLineaDeFactura.setPrecioTotal(
        tLineaDeFactura.getPrecioTotal() - tLineaDeFactura.getPrecioTotal() * descuento / 100);
    }
    final DAOLineaDeFactura daoLineaDeFactura =
      FactoriaDAO.getInstance().generarDAOLineaDeFactura();
    if (!daoLineaDeFactura.aniadirLibroAFactura(tLineaDeFactura)) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("Error al añadir el libro a la factura");
    }
    tFactura.setImporte(tFactura.getImporte() + tLineaDeFactura.getPrecioTotal());
    if (!daoFactura.modificarFactura(tFactura)) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("Error al modificar el importe de la factura");
    }
    transaction.commit();
    tManager.removeTransaction();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#bajaFactura(int)
   */
  @Override
  public void bajaFactura(final int id) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final TFactura tFactura = dao.leerFactura(id);
    if (tFactura == null) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe la factura a eliminar");
    }
    if (tFactura.isActivo()) {
      if (!dao.bajaFactura(id)) {
        transaction.rollback();
        tManager.removeTransaction();
        throw new Exception("Error al dar de baja la factura");
      }
    }
    transaction.commit();
    tManager.removeTransaction();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#cerrarFactura(int)
   */
  @Override
  public void cerrarFactura(final int id) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final TFactura tFactura = dao.leerFactura(id);
    if (tFactura == null) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe la factura a cerrar");
    }
    if (!tFactura.isCerrada()) {
      if (!dao.cerrarFactura(id)) {
        transaction.rollback();
        tManager.removeTransaction();
        throw new Exception("Error al cerrar la factura");
      }
    }
    transaction.commit();
    tManager.removeTransaction();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * negocio.factura.SAFactura#eliminarLibroDeFactura(negocio.lineadefactura.
   * TLineaDeFactura)
   */
  @Override
  public void eliminarLibroDeFactura(final TLineaDeFactura tLineaDeFactura) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura daoFactura = FactoriaDAO.getInstance().generarDAOFactura();
    final TFactura tFactura = daoFactura.leerFactura(tLineaDeFactura.getIdFactura());
    if (tFactura == null || !tFactura.isActivo()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe la factura");
    }
    if (tFactura.isCerrada()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("La factura está cerrada");
    }
    final DAOLibro daoLibro = FactoriaDAO.getInstance().generarDAOLibro();
    TFLibro tLibro = null;
    try {
      tLibro = daoLibro.leerLibroPorID(tLineaDeFactura.getIdLibro());
    } catch (final Exception ex) {
    }
    if (tLibro == null || !tLibro.getActivo()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe el libro");
    }
    final DAOLineaDeFactura daoLineaDeFactura =
      FactoriaDAO.getInstance().generarDAOLineaDeFactura();
    final TLineaDeFactura tLineaDeFactura2 = daoLineaDeFactura
      .leerLineaDeFactura(tLineaDeFactura.getIdLibro(), tLineaDeFactura.getIdFactura());
    if (tLineaDeFactura2 == null) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe el libro en la factura");
    }
    if (!daoLineaDeFactura.eliminarLibroDeFactura(tLineaDeFactura)) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("Error al eliminar el libro de la factura");
    }
    tFactura.setImporte(tFactura.getImporte() - tLineaDeFactura.getPrecioTotal());
    if (!daoFactura.modificarFactura(tFactura)) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("Error al modificar el importe de la factura");
    }
    transaction.commit();
    tManager.removeTransaction();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#listarFacturas()
   */
  @Override
  public Collection<TFactura> listarFacturas() throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final Collection<TFactura> list = dao.listarFacturas();
    transaction.commit();
    tManager.removeTransaction();
    return list;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#listarFacturasMasMilEuros()
   */
  @Override
  public Collection<TFactura> listarFacturasMasMilEuros() throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final Collection<TFactura> list = dao.listarFacturasMasMilEuros();
    transaction.commit();
    tManager.removeTransaction();
    return list;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#listarLineasDeFactura(int)
   */
  @Override
  public Collection<TLineaDeFactura> listarLineasDeFactura(final int idFactura) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOLineaDeFactura daoLineaDeFactura =
      FactoriaDAO.getInstance().generarDAOLineaDeFactura();
    final Collection<TLineaDeFactura> list = daoLineaDeFactura.listarLineasDeFactura(idFactura);
    transaction.commit();
    tManager.removeTransaction();
    return list;
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#modificarFactura(negocio.factura.TFactura)
   */
  @Override
  public void modificarFactura(final TFactura tFactura) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final TFactura tFacturaAux = dao.leerFactura(tFactura.getID());
    if (tFacturaAux == null) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("No existe la factura a modificar");
    }
    if (tFacturaAux.isCerrada()) {
      transaction.rollback();
      tManager.removeTransaction();
      throw new Exception("La factura está cerrada");
    }
    if (tFacturaAux.isActivo()) {
      tFactura.setImporte(tFacturaAux.getImporte());
      if (!dao.modificarFactura(tFactura)) {
        transaction.rollback();
        tManager.removeTransaction();
        throw new Exception("Error al modificar la factura");
      }
    }
    transaction.commit();
    tManager.removeTransaction();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factura.SAFactura#mostrarFactura(int)
   */
  @Override
  public TFactura mostrarFactura(final int id) throws Exception {
    final TManager tManager = TManager.getInstance();
    final Transaction transaction = tManager.newTransaction();
    if (transaction == null) {
      throw new Exception("Error de base de datos");
    }
    transaction.start();
    final DAOFactura dao = FactoriaDAO.getInstance().generarDAOFactura();
    final TFactura tFactura = dao.leerFactura(id);
    transaction.commit();
    tManager.removeTransaction();
    return tFactura;
  }
}
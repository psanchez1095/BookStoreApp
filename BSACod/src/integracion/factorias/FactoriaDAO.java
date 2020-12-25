package integracion.factorias;

import integracion.cliente.DAOCliente;
import integracion.editorial.DAOEditorial;
import integracion.factura.DAOFactura;
import integracion.libro.DAOLibro;
import integracion.lineaDeFactura.DAOLineaDeFactura;

/**
 * The Class FactoriaDAO.
 */
public abstract class FactoriaDAO {

  /** The instance factoria DAO. */
  private static FactoriaDAO instanceFactoriaDAO;

  /**
   * Gets the single instance of FactoriaDAO.
   *
   * @return single instance of FactoriaDAO
   */
  public synchronized static FactoriaDAO getInstance() {
    if (instanceFactoriaDAO == null) {
      instanceFactoriaDAO = new FactoriaDAOImp();
    }
    return instanceFactoriaDAO;
  }

  /**
   * Generar DAO cliente.
   *
   * @return the DAO cliente
   */
  public abstract DAOCliente generarDAOCliente();

  /**
   * Generar DAO editorial.
   *
   * @return the DAO editorial
   */
  public abstract DAOEditorial generarDAOEditorial();

  /**
   * Generar DAO factura.
   *
   * @return the DAO factura
   */
  public abstract DAOFactura generarDAOFactura();

  /**
   * Generar DAO libro.
   *
   * @return the DAO libro
   */
  public abstract DAOLibro generarDAOLibro();

  /**
   * Generar DAO linea de factura.
   *
   * @return the DAO linea de factura
   */
  public abstract DAOLineaDeFactura generarDAOLineaDeFactura();
}
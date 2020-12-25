package integracion.factorias;

import integracion.cliente.DAOCliente;
import integracion.cliente.DAOClienteImp;
import integracion.editorial.DAOEditorial;
import integracion.editorial.DAOEditorialImp;
import integracion.factura.DAOFactura;
import integracion.factura.DAOFacturaImp;
import integracion.libro.DAOLibro;
import integracion.libro.DAOLibroImp;
import integracion.lineaDeFactura.DAOLineaDeFactura;
import integracion.lineaDeFactura.DAOLineaDeFacturaImp;

/**
 * The Class FactoriaDAOImp.
 */
public class FactoriaDAOImp extends FactoriaDAO {

  /*
   * (non-Javadoc)
   *
   * @see integracion.factorias.FactoriaDAO#generarDAOCliente()
   */
  @Override
  public DAOCliente generarDAOCliente() {
    return new DAOClienteImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.factorias.FactoriaDAO#generarDAOEditorial()
   */
  @Override
  public DAOEditorial generarDAOEditorial() {
    return new DAOEditorialImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.factorias.FactoriaDAO#generarDAOFactura()
   */
  @Override
  public DAOFactura generarDAOFactura() {
    return new DAOFacturaImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.factorias.FactoriaDAO#generarDAOLibro()
   */
  @Override
  public DAOLibro generarDAOLibro() {
    return new DAOLibroImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see integracion.factorias.FactoriaDAO#generarDAOLineaDeFactura()
   */
  @Override
  public DAOLineaDeFactura generarDAOLineaDeFactura() {
    return new DAOLineaDeFacturaImp();
  }
}
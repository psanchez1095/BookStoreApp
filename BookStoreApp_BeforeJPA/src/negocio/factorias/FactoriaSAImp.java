package negocio.factorias;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.editorial.SAEditorial;
import negocio.editorial.SAEditorialImp;
import negocio.factura.SAFactura;
import negocio.factura.SAFacturaImp;
import negocio.libro.SALibro;
import negocio.libro.SALibroImp;

/**
 * The Class FactoriaSAImp.
 */
public class FactoriaSAImp extends FactorySA {

  /*
   * (non-Javadoc)
   *
   * @see negocio.factorias.FactorySA#createLibro()
   */
  @Override
  public SALibro createLibro() {
    return new SALibroImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factorias.FactorySA#createSACliente()
   */
  @Override
  public SACliente createSACliente() {
    return new SAClienteImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factorias.FactorySA#createSAEditorial()
   */
  @Override
  public SAEditorial createSAEditorial() {
    return new SAEditorialImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factorias.FactorySA#createSAFactura()
   */
  @Override
  public SAFactura createSAFactura() {
    return new SAFacturaImp();
  }
}

package negocio.factorias;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.departamento.SADepartamento;
import negocio.departamento.SADepartamentoImp;
import negocio.editorial.SAEditorial;
import negocio.editorial.SAEditorialImp;
import negocio.empleado.SAEmpleado;
import negocio.empleado.SAEmpleadoImp;
import negocio.factura.SAFactura;
import negocio.factura.SAFacturaImp;
import negocio.libreria.SALibreria;
import negocio.libreria.SALibreriaImp;
import negocio.libro.SALibro;
import negocio.libro.SALibroImp;
import negocio.material.SAMaterial;
import negocio.material.SAMaterialImp;

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

  /*
   * (non-Javadoc)
   *
   * @see negocio.factorias.FactorySA#createSALibreria()
   */
  @Override
  public SALibreria createSALibreria() {
    return new SALibreriaImp();
  }

  @Override
  public SAMaterial createSAMaterial() {
    return new SAMaterialImp();
  }

  /*
   * (non-Javadoc)
   *
   * @see negocio.factorias.FactorySA#createSADepartamento()
   */
  @Override
  public SADepartamento createSADepartamento() {
    return new SADepartamentoImp();
  }

@Override
public SAEmpleado createSAEmpleado() {
	// TODO Auto-generated method stub
	 return new SAEmpleadoImp();
}
}

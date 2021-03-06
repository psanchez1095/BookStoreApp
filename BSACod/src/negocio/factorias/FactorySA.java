package negocio.factorias;

import negocio.cliente.SACliente;
import negocio.departamento.SADepartamento;
import negocio.editorial.SAEditorial;
import negocio.empleado.SAEmpleado;
import negocio.factura.SAFactura;
import negocio.libreria.SALibreria;
import negocio.libro.SALibro;
import negocio.material.SAMaterial;

/**
 * The Class FactorySA.
 */
public abstract class FactorySA {

  /** The instancia. */
  private static FactorySA instancia;

  /**
   * Gets the single instance of FactorySA.
   *
   * @return single instance of FactorySA
   */
  public synchronized static FactorySA getInstance() {
    if (instancia == null) {
      instancia = new FactoriaSAImp();
    }
    return instancia;
  }

  /**
   * Creates the libro.
   *
   * @return the SA libro
   */
  public abstract SALibro createLibro();

  /**
   * Creates the SA cliente.
   *
   * @return the SA cliente
   */
  public abstract SACliente createSACliente();

  /**
   * Creates the SA editorial.
   *
   * @return the SA editorial
   */
  public abstract SAEditorial createSAEditorial();

  /**
   * Creates the SA factura.
   *
   * @return the SA factura
   */
  public abstract SAFactura createSAFactura();

  /**
   * Creates the SA libreria.
   *
   * @return the SA libreria
   */
  public abstract SALibreria createSALibreria();

  /**
   * Creates the SA departamento.
   *
   * @return the SA departamento
   */
  public abstract SADepartamento createSADepartamento();

  public abstract SAMaterial createSAMaterial();
  public abstract SAEmpleado createSAEmpleado();
}
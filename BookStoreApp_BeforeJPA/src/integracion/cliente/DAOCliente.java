package integracion.cliente;

import java.util.Collection;

import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteEmpresa;
import negocio.cliente.TransferClienteNormal;

/**
 * The Interface DAOCliente.
 */
public interface DAOCliente {

  /**
   * Alta cliente.
   *
   * @param transferCliente
   *          the transfer cliente
   * @return the integer
   */
  public Integer altaCliente(TransferCliente transferCliente);

  /**
   * Baja cliente.
   *
   * @param id
   *          the id
   * @return true, if successful
   */
  public boolean bajaCliente(Integer id);

  /**
   * Existe cliente.
   *
   * @param id
   *          the id
   * @return true, if successful
   */
  public boolean existeCliente(int id);

  /**
   * Gets the cliente empresa id.
   *
   * @param id
   *          the id
   * @return the cliente empresa id
   */
  public TransferClienteEmpresa getClienteEmpresaId(Integer id);

  /**
   * Gets the cliente id.
   *
   * @param id
   *          the id
   * @return the cliente id
   */
  public TransferCliente getClienteId(Integer id);

  /**
   * Gets the cliente normal id.
   *
   * @param id
   *          the id
   * @return the cliente normal id
   */
  public TransferClienteNormal getClienteNormalId(Integer id);

  /**
   * Leer cliente por identificacion fiscal.
   *
   * @param identificacionFiscal
   *          the identificacion fiscal
   * @return the transfer cliente
   */
  public TransferCliente leerClientePorIdentificacionFiscal(String identificacionFiscal);

  /**
   * Listar clientes.
   *
   * @return the collection
   */
  public Collection<TransferCliente> listarClientes();

  /**
   * Modificar cliente.
   *
   * @param transferCliente
   *          the transfer cliente
   * @return true, if successful
   */
  public boolean modificarCliente(TransferCliente transferCliente);
}
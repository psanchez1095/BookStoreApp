package test.cliente.integracion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import integracion.cliente.DAOCliente;
import integracion.cliente.DAOClienteImp;
import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteNormal;

/**
 * The Class DAOClienteAltaCLienteTest.
 */
public class DAOClienteAltaCLienteTest {
  /**
   * Test execute KO.
   */
  public void testAltaClienteKO() {

    final TransferCliente cliente = new TransferClienteNormal();
    cliente.setDireccion("pep");
    cliente.setActivo(true);
    cliente.setNombre("pepp");
    cliente.setEmail("dasdsadas");
    ((TransferClienteNormal) cliente).setGastosDeEnvio(1.1);

    final DAOCliente tester = new DAOClienteImp();
    final int i = tester.altaCliente(cliente);
    assertTrue(i == -1);
  }

  /**
   * Test execute.
   */
  @Test
  public void testAltaClienteOK() {

    final TransferCliente cliente = new TransferClienteNormal();
    cliente.setDireccion("pep");
    cliente.setActivo(true);
    cliente.setNombre("pepp");
    cliente.setIdentificacionFiscal("423a12Y3");
    cliente.setEmail("dasdsadas");
    ((TransferClienteNormal) cliente).setGastosDeEnvio(1.1);
    final DAOCliente tester = new DAOClienteImp();
    final Integer i = tester.altaCliente(cliente);
    assertTrue(i > -1);
  }

}

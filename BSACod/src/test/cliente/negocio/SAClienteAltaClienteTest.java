package test.cliente.negocio;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteNormal;

/**
 * The Class SAClienteAltaClienteTest.
 */
public class SAClienteAltaClienteTest {

  /**
   * Test execute KO.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testAltaClienteKO() throws Exception {

    final TransferCliente cliente = new TransferClienteNormal();
    cliente.setDireccion("pep");
    cliente.setActivo(true);
    cliente.setNombre("pepp");
    cliente.setEmail("dasdsadas");
    ((TransferClienteNormal) cliente).setGastosDeEnvio(1.1);
    final SACliente tester = new SAClienteImp();
    final int i = tester.altaCliente(cliente);
    assertTrue(i < 0);

  }

  /**
   * Test execute.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testAltaClienteOK() throws Exception {

    final TransferCliente cliente = new TransferClienteNormal();
    cliente.setDireccion("pep");
    cliente.setActivo(true);
    cliente.setNombre("pepp");
    cliente.setIdentificacionFiscal("423a12Y");
    cliente.setEmail("dasdsadas");
    ((TransferClienteNormal) cliente).setGastosDeEnvio(1.1);
    final SACliente tester = new SAClienteImp();
    final int i = tester.altaCliente(cliente);
    assertTrue(i > 0);

  }

}

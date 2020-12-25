package test.cliente.presentacion.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteNormal;
import presentacion.controlador.command.CommandCliente.AltaCliente;
import presentacion.eventos.EventosCliente;

/**
 * The Class AltaClienteCommandTest.
 */
public class AltaClienteCommandTest {

  /**
   * Test execute.
   */
  @Test
  public void testExecute() {

    final TransferCliente cliente = new TransferClienteNormal();
    cliente.setDireccion("pep");
    cliente.setActivo(true);
    cliente.setNombre("pepp");
    cliente.setIdentificacionFiscal("42312Y");
    cliente.setEmail("dasdsadas");
    ((TransferClienteNormal) cliente).setGastosDeEnvio(1.1);
    final AltaCliente tester = new AltaCliente();
    assertEquals(EventosCliente.ALTA_CLIENTE_OK, tester.execute(cliente).getEvento());
  }

  /**
   * Test execute KO.
   */
  @Test
  public void testExecuteKO() {

    final TransferCliente cliente = new TransferClienteNormal();
    cliente.setDireccion("pep");
    cliente.setActivo(true);
    cliente.setNombre("pepp");
    cliente.setIdentificacionFiscal("42312Y");
    cliente.setEmail("dasdsadas");
    final AltaCliente tester = new AltaCliente();
    assertEquals(EventosCliente.ALTA_CLIENTE_KO, tester.execute(cliente).getEvento());
  }
}

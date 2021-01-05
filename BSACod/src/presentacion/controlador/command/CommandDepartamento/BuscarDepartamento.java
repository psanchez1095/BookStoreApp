package presentacion.controlador.command.CommandDepartamento;

import negocio.departamento.SADepartamento;
import negocio.departamento.TransferDepartamento;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class BuscarDepartamento implements Command {

  @Override
  public Contexto execute(Object objeto) {
    final Integer id = (Integer) objeto;
    final SADepartamento sADepartamento = FactorySA.getInstance().createSADepartamento();
    String mensaje;
    Contexto contexto;
    TransferDepartamento transferDepartamento;
    try {
      transferDepartamento = sADepartamento.mostrarDepartamento(id);

      contexto = new Contexto(EventosDepartamento.BUSCAR_DEPARTAMENTO_OK, transferDepartamento);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosDepartamento.BUSCAR_DEPARTAMENTO_KO, mensaje);
    }

    return contexto;

  }

}

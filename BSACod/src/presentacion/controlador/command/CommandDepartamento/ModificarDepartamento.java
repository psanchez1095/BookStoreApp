package presentacion.controlador.command.CommandDepartamento;

import negocio.departamento.SADepartamento;
import negocio.departamento.TransferDepartamento;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class ModificarDepartamento implements Command {

  @Override
  public Contexto execute(Object objeto) {
    final TransferDepartamento dpto = (TransferDepartamento) objeto;
    final SADepartamento saDpto = FactorySA.getInstance().createSADepartamento();
    String mensaje;
    Contexto contexto;

    try {
      if (saDpto.modificarDepartamento(dpto)) {
        mensaje = "Departamento modificado corretamente. Su ID es: " + dpto.getId() + ". ";
        contexto = new Contexto(EventosDepartamento.MODIFICAR_DEPARTAMENTO_OK, mensaje);
      } else {
        mensaje = "No se ha modificado el departamento corretamente. Su ID es: " + dpto.getId() + ". ";
        contexto = new Contexto(EventosDepartamento.MODIFICAR_DEPARTAMENTO_KO, mensaje);
      }
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosDepartamento.MODIFICAR_DEPARTAMENTO_KO, mensaje);
    }

    return contexto;
  }
}
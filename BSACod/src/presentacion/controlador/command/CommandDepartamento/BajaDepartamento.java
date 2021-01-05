package presentacion.controlador.command.CommandDepartamento;

import negocio.departamento.SADepartamento;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class BajaDepartamento implements Command {

  @Override
  public Contexto execute(Object objeto) {

    final Integer idDepartamento = (Integer) objeto;
    final SADepartamento saDpto = FactorySA.getInstance().createSADepartamento();
    String mensaje;
    Contexto contexto;

    try {
      if (saDpto.bajaDepartamento(idDepartamento)) {
        mensaje = "Departamento dado de baja corretamente. Su ID es: " + idDepartamento + ". ";
        contexto = new Contexto(EventosDepartamento.BAJA_DEPARTAMENTO_OK, mensaje);
      } else {
        mensaje = "No se ha podido dar de baja el departamento corretamente. Su ID es: "
          + idDepartamento + ". ";
        contexto = new Contexto(EventosDepartamento.BAJA_DEPARTAMENTO_KO, mensaje);
      }
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosDepartamento.BAJA_DEPARTAMENTO_KO, mensaje);
    }

    return contexto;
  }
}
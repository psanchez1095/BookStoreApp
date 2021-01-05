package presentacion.controlador.command.CommandDepartamento;

import java.util.Collection;

import negocio.departamento.SADepartamento;
import negocio.departamento.TransferDepartamento;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class ListarDepartamentos implements Command {

  @Override
  public Contexto execute(Object objeto) {

    final SADepartamento saDpto = FactorySA.getInstance().createSADepartamento();
    String mensaje;
    Contexto contexto;

    try {
      Collection<TransferDepartamento> listaDepartamentos = saDpto.listarDepartamentos();
      contexto = new Contexto(EventosDepartamento.LISTAR_DEPARTAMENTOS_OK, listaDepartamentos);
    } catch (final Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosDepartamento.LISTAR_DEPARTAMENTOS_KO, mensaje);
    }
    return contexto;
  }
}
package presentacion.controlador.command.CommandDepartamento;

import negocio.departamento.SADepartamento;
import negocio.departamento.TransferDepartamento;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class MostrarDepartamento implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final Integer idDepartamento  = (Integer) objeto;
		final SADepartamento  saDpto = FactorySA.getInstance().createSADepartamento();
		String mensaje;
		Contexto contexto;

		try {
      TransferDepartamento dpto = saDpto.mostrarDepartamento(idDepartamento);
			contexto = new Contexto(EventosDepartamento.MOSTRAR_DEPARTAMENTO_OK, dpto);
		} 
		catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosDepartamento.MOSTRAR_DEPARTAMENTO_KO, mensaje);
		}

		return contexto;
	}
}
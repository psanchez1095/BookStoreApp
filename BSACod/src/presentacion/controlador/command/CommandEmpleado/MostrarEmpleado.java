/**
 * 
 */
package presentacion.controlador.command.CommandEmpleado;

import negocio.factorias.FactorySA;
import negocio.empleado.EntityEmpleado;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TransferEmpleado;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEmpleado;

public class MostrarEmpleado implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final Integer id = (Integer) objeto;
		final SAEmpleado sa = FactorySA.getInstance().createSAEmpleado();
		String mensaje;
		Contexto contexto;

		try {
			EntityEmpleado Empleado = sa.mostrarEmpleado(id);
			contexto = new Contexto(EventosEmpleado.MOSTRAR_EMPLEADO_OK, Empleado);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.MOSTRAR_EMPLEADO_KO, mensaje);
		}

    	return contexto;
	}
}
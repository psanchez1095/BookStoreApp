/**
 * 
 */
package presentacion.controlador.command.CommandEmpleado;

import negocio.empleado.SAEmpleado;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEmpleado;

public class BajaEmpleado implements Command {
	
	@Override
	public Contexto execute(Object objeto) {
		// TODO Auto-generated method stub
		final Integer idEmpleado = (Integer) objeto;
		final SAEmpleado sa = FactorySA.getInstance().createSAEmpleado();
		String mensaje;
		Contexto contexto;

		try {
			sa.bajaEmpleado(idEmpleado);
			mensaje = "Empleado dado de baja corretamente.";
			contexto = new Contexto(EventosEmpleado.BAJA_EMPLEADO_OK, mensaje);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.BAJA_EMPLEADO_KO, mensaje);
		}

		return contexto;
	}
}
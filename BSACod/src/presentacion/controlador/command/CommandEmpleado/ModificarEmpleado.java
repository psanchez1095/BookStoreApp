/**
 * 
 */
package presentacion.controlador.command.CommandEmpleado;

import negocio.factorias.FactorySA;
import negocio.empleado.SAEmpleado;
import negocio.empleado.TransferEmpleado;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEmpleado;


public class ModificarEmpleado implements Command {
	
	public Contexto execute(Object objeto) {
		final TransferEmpleado Empleado = (TransferEmpleado) objeto;
		final SAEmpleado sa = FactorySA.getInstance().createSAEmpleado();
		String mensaje;
		Contexto contexto;

		try {
			sa.modificarEmpleado(Empleado);
			mensaje = " Empleado modificado correctamente. Su ID es: " + Empleado.getId() + ". ";
			contexto = new Contexto(EventosEmpleado.MODIFICAR_EMPLEADO_OK, mensaje);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.MODIFICAR_EMPLEADO_KO, mensaje);
		}

		return contexto;
	}
}
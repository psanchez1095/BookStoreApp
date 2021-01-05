/**
 * 
 */
package presentacion.controlador.command.CommandEmpleado;

import negocio.empleado.SAEmpleado;
import negocio.empleado.TransferEmpleado;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEmpleado;

public class AltaEmpleado implements Command {
	
	@Override
	public Contexto execute(Object objeto) {
		// TODO Auto-generated method stub
		TransferEmpleado empleado = (TransferEmpleado) objeto;
		SAEmpleado sa = FactorySA.getInstance().createSAEmpleado();
		String mensaje;
		Contexto contexto;

		try {
			int id = sa.altaEmpleado(empleado);
			mensaje = "Empleado dado de alta corretamente. Su ID es: " + id + ". ";
			contexto = new Contexto(EventosEmpleado.ALTA_EMPLEADO_OK, mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.ALTA_EMPLEADO_KO, mensaje);
		}

		return contexto;
	}
	}

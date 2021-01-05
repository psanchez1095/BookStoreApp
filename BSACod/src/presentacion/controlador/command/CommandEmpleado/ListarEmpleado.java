/**
 * 
 */
package presentacion.controlador.command.CommandEmpleado;

import java.util.Collection;

import negocio.empleado.EntityEmpleado;
import negocio.empleado.SAEmpleado;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEmpleado;


public class ListarEmpleado implements Command {
	
	@Override
	public Contexto execute(Object objeto) {
		// TODO Auto-generated method stub
		final SAEmpleado sa = FactorySA.getInstance().createSAEmpleado();
		String mensaje;
		Contexto contexto;
		try {
			Collection<EntityEmpleado> listaEmpleados = sa.listarEmpleado();
			contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS_OK, listaEmpleados);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS_KO, mensaje);
		}
		return contexto;
	}
}
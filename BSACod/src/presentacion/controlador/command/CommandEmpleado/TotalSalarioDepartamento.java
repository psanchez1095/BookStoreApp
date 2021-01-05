package presentacion.controlador.command.CommandEmpleado;



import negocio.empleado.SAEmpleado;
import negocio.empleado.TransferEmpleado;
import negocio.factorias.FactorySA;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosEmpleado;

public class TotalSalarioDepartamento implements Command {

	@Override
	public Contexto execute(Object objeto) {
			final TransferEmpleado Empleado = (TransferEmpleado) objeto;
			final SAEmpleado sa = FactorySA.getInstance().createSAEmpleado();
			String mensaje;
			Contexto contexto;

			try {
				contexto = new Contexto(EventosEmpleado.TOTAL_SALARIOS_DEP_OK, sa.totalSalariosDepartamento(Empleado.getDepartamento().getID()));
			} catch (final Exception e) {
				mensaje = e.getMessage();
				contexto = new Contexto(EventosEmpleado.TOTAL_SALARIOS_DEP_KO, mensaje);
			}

			return contexto;
		}
	}



package presentacion.controlador.command.CommandMenu;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMenu;

public class CommandMostrarVistaEmpleado implements Command {

	@Override
	public Contexto execute(Object objeto) {
		return new Contexto(EventosMenu.MOSTRAR_EMPLEADO_VISTA, null);
	}

}

package presentacion.controlador.command.CommandLibreria;

import negocio.factorias.FactorySA;
import negocio.libreria.SALibreria;
import negocio.libreria.TransferLibreria;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibreria;

public class BuscarLibreria implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final int idLibreria = (Integer) objeto;
		final SALibreria saLibreria = FactorySA.getInstance().createSALibreria();
		String mensaje;
		Contexto contexto;

		try {
			TransferLibreria libreria = saLibreria.mostrarLibreria(idLibreria);
			contexto = new Contexto(EventosLibreria.BUSCAR_LIBRERIA_OK, libreria);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosLibreria.BUSCAR_LIBRERIA_KO, mensaje);
		}

		return contexto;
	}
}
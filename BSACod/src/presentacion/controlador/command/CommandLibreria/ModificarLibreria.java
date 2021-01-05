package presentacion.controlador.command.CommandLibreria;

import negocio.factorias.FactorySA;
import negocio.libreria.SALibreria;
import negocio.libreria.TransferLibreria;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibreria;

public class ModificarLibreria implements Command {
	@Override
	public Contexto execute(Object objeto) {
		final TransferLibreria libreria = (TransferLibreria) objeto;
		final SALibreria saLibreria = FactorySA.getInstance().createSALibreria();
		String mensaje;
		Contexto contexto;
		
		try{
			saLibreria.modificarLibreria(libreria);
			mensaje = "Libreria modificada correctamente. Su ID es: " + libreria.getId() + ". ";
			contexto = new Contexto(EventosLibreria.MODIFICAR_LIBRERIA_OK, mensaje);
		} catch (final Exception e){
			mensaje = e.getMessage();
			contexto = new Contexto(EventosLibreria.MODIFICAR_LIBRERIA_KO, mensaje);
		}
		
		return contexto;
	}
}
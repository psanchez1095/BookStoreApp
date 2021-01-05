package presentacion.controlador.command.CommandLibreria;

import negocio.factorias.FactorySA;
import negocio.libreria.SALibreria;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibreria;

public class BajaLibreria implements Command {
	@Override
	public Contexto execute(Object objeto) {
		final int idLibreria = (Integer) objeto;
		final SALibreria saLibreria = FactorySA.getInstance().createSALibreria();
		String mensaje;
		Contexto contexto;
		
		try{
			saLibreria.bajaLibreria(idLibreria);
			mensaje = "Libreria dada de baja correctamente. Su ID es: " + idLibreria + ". ";
			contexto = new Contexto(EventosLibreria.BAJA_LIBRERIA_OK, mensaje);
		} catch(final Exception e){
			mensaje = e.getMessage();
			contexto = new Contexto(EventosLibreria.BAJA_LIBRERIA_KO, mensaje);
		}
		
		return contexto;
	}
}
package presentacion.controlador.command.CommandLibreria;

import java.util.Collection;

import negocio.factorias.FactorySA;
import negocio.libreria.SALibreria;
import negocio.libreria.TransferLibreria;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibreria;

public class ListarLibrerias implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final SALibreria saLibreria = FactorySA.getInstance().createSALibreria();
		String mensaje;
		Contexto contexto;
		
		try{
			Collection<TransferLibreria> listaLibrerias = saLibreria.listarLibrerias();
			contexto = new Contexto(EventosLibreria.LISTAR_LIBRERIAS_OK, listaLibrerias);
		} catch(final Exception e){
			mensaje = e.getMessage();
			contexto = new Contexto(EventosLibreria.LISTAR_LIBRERIAS_KO, mensaje);
		}
		
		return contexto;
	}
}
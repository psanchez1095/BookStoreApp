package presentacion.controlador.command.CommandLibreria;

import negocio.factorias.FactoriaSAImp;
import negocio.libreria.SALibreria;
import negocio.libreria.TransferLibreria;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosLibreria;

public class AltaLibreria implements Command {

  @Override
	public Contexto execute(final Object objeto) {
	  TransferLibreria libreria = (TransferLibreria) objeto;
	  SALibreria saLibreria = FactoriaSAImp.getInstance().createSALibreria();
	  String mensaje = "";
	  Contexto contexto;
	  
	  try{
		  int libreriaId = saLibreria.altaLibreria(libreria);
		  
		  if(libreriaId > 0){
			  mensaje = "Librería dada de alta correctamente. Su ID es: " + libreriaId + ". ";
			  contexto = new Contexto(EventosLibreria.ALTA_LIBRERIA_OK, mensaje);
		  }
		  else{
			  if(libreriaId == -10){
				  mensaje = "Libreria existente.";
			  }
			  else if(libreriaId == -5){
				  mensaje = "Libreria existente dada de baja.";
			  }
			  contexto = new Contexto(EventosLibreria.ALTA_LIBRERIA_KO, mensaje);
		  }
	  } catch (Exception e){
		  mensaje = e.getMessage();
		  contexto = new Contexto(EventosLibreria.ALTA_LIBRERIA_KO, mensaje);
	  }
	  return contexto;
  }
}
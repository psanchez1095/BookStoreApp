package presentacion.controlador.command.CommandDepartamento;

import negocio.departamento.SADepartamento;
import negocio.departamento.TransferDepartamento;
import negocio.factorias.FactoriaSAImp;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class AsignarMaterialOficina implements Command {

	@Override
	public Contexto execute(Object objeto) {
		TransferDepartamento departamento = (TransferDepartamento) objeto;
	    SADepartamento sADepartamento = FactoriaSAImp.getInstance().createSADepartamento();
	    String mensaje = "";
	    Contexto contexto;
	    
	    try { //TO DO
	      Integer departamentoId = sADepartamento.altaDepartamento(departamento);
	      
	      if(departamentoId > 0) {
	        mensaje = "Departamento dado de alta corretamente. Su ID es: " + departamentoId + ". ";
	        contexto = new Contexto(EventosDepartamento.ASIGNAR_MATERIAL_OFICINA_OK, mensaje);
	      } else {
	       
	          contexto=  new Contexto(EventosDepartamento.ASIGNAR_MATERIAL_OFICINA_KO, mensaje);
	      }
	    } catch (Exception e) {
	      mensaje = e.getMessage();
	      contexto = new Contexto(EventosDepartamento.ASIGNAR_MATERIAL_OFICINA_KO, mensaje);
	    }
	    return contexto;
	}

}

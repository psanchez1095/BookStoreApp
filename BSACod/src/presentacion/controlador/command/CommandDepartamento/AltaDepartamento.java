package presentacion.controlador.command.CommandDepartamento;

import negocio.departamento.SADepartamento;
import negocio.departamento.TransferDepartamento;
import negocio.factorias.FactoriaSAImp;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosDepartamento;

public class AltaDepartamento implements Command {

  public Contexto execute(final Object objeto) {
    TransferDepartamento departamento = (TransferDepartamento) objeto;
    SADepartamento sADepartamento = FactoriaSAImp.getInstance().createSADepartamento();
    String mensaje = "";
    Contexto contexto;
    
    try {
      Integer departamentoId = sADepartamento.altaDepartamento(departamento);
      
//      if(departamentoId > 0) {

      if(departamentoId != null) {
        mensaje = "Departamento dado de alta corretamente. Su ID es: " + departamentoId + ". ";
        contexto = new Contexto(EventosDepartamento.ALTA_DEPARTAMENTO_OK, mensaje);
      } else {
        mensaje = "Se ha producido un error al dar de alta el departamento.";
        contexto =  new Contexto(EventosDepartamento.ALTA_DEPARTAMENTO_KO, mensaje);
      }
//      else {
//        if (departamentoId == -3) {
//          mensaje = "Libreria no existente. El ID indicado es: " + departamento.getLibreria() + ". ";
//        } else if (departamentoId == -10) {
//          mensaje = "Departamento existente.";  //" Su ID es: " + departamentoId + ". ";
//        } else if (departamentoId == -5) {
//          mensaje = "Departamento existente dado de baja.";  //" Su ID es: " + departamentoId + ". ";
//        }
//          contexto=  new Contexto(EventosDepartamento.ALTA_DEPARTAMENTO_KO, mensaje);
//      }
    } catch (Exception e) {
      mensaje = e.getMessage();
      contexto = new Contexto(EventosDepartamento.ALTA_DEPARTAMENTO_KO, mensaje);
    }
    return contexto;
  }
}
package presentacion.controlador.command.CommandMaterial;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import negocio.factorias.FactorySA;
import negocio.material.SAMaterial;
import presentacion.eventos.EventosMaterial;
import negocio.material.TransferMaterial;

public class AltaMaterial implements Command {

	@Override
	public Contexto execute(Object objeto) {
		TransferMaterial material = (TransferMaterial) objeto;
		SAMaterial sa = FactorySA.getInstance().createSAMaterial();
		String mensaje;
		Contexto contexto;

		try {
			int id = sa.altaMaterial(material);
			mensaje = "Material dado de alta corretamente. Su ID es: " + id + ". ";
			contexto = new Contexto(EventosMaterial.ALTA_MATERIAL_OK, mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosMaterial.ALTA_MATERIAL_KO, mensaje);
		}

		return contexto;
	}
}
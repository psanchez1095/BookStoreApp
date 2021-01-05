package presentacion.controlador.command.CommandMaterial;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import negocio.factorias.FactorySA;
import negocio.material.SAMaterial;
import presentacion.eventos.EventosMaterial;
import negocio.material.TransferMaterial;

public class ModificarMaterial implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final TransferMaterial material = (TransferMaterial) objeto;
		final SAMaterial sa = FactorySA.getInstance().createSAMaterial();
		String mensaje;
		Contexto contexto;

		try {
			sa.modificarMaterial(material);
			mensaje = " Material modificado correctamente. Su ID es: " + material.getId() + ". ";
			contexto = new Contexto(EventosMaterial.MODIFICAR_MATERIAL_OK, mensaje);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosMaterial.MODIFICAR_MATERIAL_KO, mensaje);
		}

		return contexto;
	}
}
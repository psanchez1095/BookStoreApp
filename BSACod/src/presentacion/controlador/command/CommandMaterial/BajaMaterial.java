package presentacion.controlador.command.CommandMaterial;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import negocio.factorias.FactorySA;
import negocio.material.SAMaterial;
import presentacion.eventos.EventosMaterial;

public class BajaMaterial implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final Integer idMaterial = (Integer) objeto;
		final SAMaterial sa = FactorySA.getInstance().createSAMaterial();
		String mensaje;
		Contexto contexto;

		try {
			sa.bajaMaterial(idMaterial);
			mensaje = "Material dado de baja corretamente. Su ID es: " + idMaterial + ". ";
			contexto = new Contexto(EventosMaterial.BAJA_MATERIAL_OK, mensaje);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosMaterial.BAJA_MATERIAL_KO, mensaje);
		}

		return contexto;
	}
}
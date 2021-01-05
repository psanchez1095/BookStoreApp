package presentacion.controlador.command.CommandMaterial;

import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMaterial;
import negocio.factorias.FactorySA;
import negocio.material.TransferMaterial;
import negocio.material.SAMaterial;

public class MostrarMaterial implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final Integer id = (Integer) objeto;
		final SAMaterial sa = FactorySA.getInstance().createSAMaterial();
		String mensaje;
		Contexto contexto;

		try {
			TransferMaterial material = sa.mostrarMaterial(id);
			contexto = new Contexto(EventosMaterial.MOSTRAR_MATERIAL_OK, material);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosMaterial.MOSTRAR_MATERIAL_KO, mensaje);
		}

    	return contexto;
	}
}
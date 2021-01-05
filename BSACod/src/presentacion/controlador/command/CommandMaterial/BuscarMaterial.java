package presentacion.controlador.command.CommandMaterial;

import negocio.factorias.FactorySA;
import negocio.material.SAMaterial;
import negocio.material.TransferMaterial;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMaterial;

public class BuscarMaterial implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final Integer idMat = (Integer) objeto;
		final SAMaterial sa = FactorySA.getInstance().createSAMaterial();
		String mensaje;
		Contexto contexto;
		TransferMaterial mat;
		try {
			mat = sa.mostrarMaterial(idMat);

			contexto = new Contexto(EventosMaterial.BUSCAR_MATERIAL_OK, mat);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosMaterial.BUSCAR_MATERIAL_KO, mensaje);
		}

		return contexto;

	}

}

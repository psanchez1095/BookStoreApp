package presentacion.controlador.command.CommandMaterial;

import java.util.Collection;

import negocio.factorias.FactorySA;
import negocio.material.SAMaterial;
import presentacion.contexto.Contexto;
import presentacion.controlador.command.Command;
import presentacion.eventos.EventosMaterial;
import negocio.material.TransferMaterial;

public class ListarMaterial implements Command {

	@Override
	public Contexto execute(Object objeto) {
		final SAMaterial sa = FactorySA.getInstance().createSAMaterial();
		String mensaje;
		Contexto contexto;
		try {
			Collection<TransferMaterial> listaMateriales = sa.listarMaterial();
			contexto = new Contexto(EventosMaterial.LISTAR_MATERIALES_OK, listaMateriales);
		} catch (final Exception e) {
			mensaje = e.getMessage();
			contexto = new Contexto(EventosMaterial.LISTAR_MATERIALES_KO, mensaje);
		}
		return contexto;
	}
}

package negocio.material;

import java.util.Collection;

public interface SAMaterial {
	
	public Integer altaMaterial(TransferMaterial transferMaterial) throws ClassNotFoundException, Exception;
	public void bajaMaterial(Integer idMaterial) throws ClassNotFoundException, Exception;
	public Integer modificarMaterial(TransferMaterial transferMaterial) throws ClassNotFoundException, Exception;
	public Collection<TransferMaterial> listarMaterial() throws ClassNotFoundException, Exception;
	public TransferMaterial mostrarMaterial(Integer id) throws ClassNotFoundException, Exception;
}
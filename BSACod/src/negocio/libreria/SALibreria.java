package negocio.libreria;

import java.util.Collection;

public interface SALibreria {

	public Integer altaLibreria(TransferLibreria tLibreria) throws Exception;
	public void bajaLibreria(Integer idLibreria) throws ClassNotFoundException, Exception;
	public Integer modificarLibreria(TransferLibreria tLibreria) throws ClassNotFoundException, Exception;
	public TransferLibreria mostrarLibreria(Integer idLibreria) throws ClassNotFoundException, Exception;
	public Collection<TransferLibreria> listarLibrerias() throws ClassNotFoundException, Exception;
}
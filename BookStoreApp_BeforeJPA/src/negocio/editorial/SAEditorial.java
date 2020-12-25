package negocio.editorial;

import java.util.Collection;

/**
 * The Interface SAEditorial.
 */
public interface SAEditorial {

  /**
   * Alta editorial.
   *
   * @param tEditorial
   *          the t editorial
   * @return the integer
   * @throws Exception
   *           the exception
   */
  public Integer altaEditorial(TransferEditorial tEditorial) throws Exception;

  /**
   * Baja editorial.
   *
   * @param id
   *          the id
   * @return the integer
   * @throws Exception
   *           the exception
   */
  public Integer bajaEditorial(Integer id) throws Exception;

  /**
   * Listar editoriales.
   *
   * @return the collection
   * @throws Exception
   *           the exception
   */
  public Collection<TransferEditorial> listarEditoriales() throws Exception;

  /**
   * Modificar editorial.
   *
   * @param tEditorial
   *          the t editorial
   * @return the integer
   * @throws Exception
   *           the exception
   */
  public Integer modificarEditorial(TransferEditorial tEditorial) throws Exception;

  /**
   * Mostrar editorial.
   *
   * @param id
   *          the id
   * @return the transfer editorial
   * @throws Exception
   *           the exception
   */
  public TransferEditorial mostrarEditorial(Integer id) throws Exception;
}
package integracion.editorial;

import java.util.Collection;

import negocio.editorial.TransferEditorial;

/**
 * The Interface DAOEditorial.
 */
public interface DAOEditorial {

  /**
   * Alta editorial.
   *
   * @param editorial
   *          the editorial
   * @return the integer
   */
  public Integer altaEditorial(TransferEditorial editorial);

  /**
   * Baja editorial.
   *
   * @param id
   *          the id
   * @return true, if successful
   */
  public boolean bajaEditorial(Integer id);

  /**
   * Leer editorialpor ID.
   *
   * @param id
   *          the id
   * @return the transfer editorial
   */
  public TransferEditorial leerEditorialporID(Integer id);

  /**
   * Leer editorialpor nombre.
   *
   * @param nombre
   *          the nombre
   * @return the transfer editorial
   */
  public TransferEditorial leerEditorialporNombre(String nombre);

  /**
   * Listar editoriales.
   *
   * @return the collection
   */
  public Collection<TransferEditorial> listarEditoriales();

  /**
   * Modificar editorial.
   *
   * @param editorial
   *          the editorial
   * @return true, if successful
   */
  public boolean modificarEditorial(TransferEditorial editorial);

  /**
   * Mostrar editorial.
   *
   * @param id
   *          the id
   * @return the transfer editorial
   */
  public TransferEditorial mostrarEditorial(Integer id);
}
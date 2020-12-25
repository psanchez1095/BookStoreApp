package negocio.libro;

import java.util.Collection;

/**
 * The Interface SALibro.
 */
public interface SALibro {

  /**
   * Alta libro.
   *
   * @param tLibro
   *          the t libro
   * @return the integer
   * @throws Exception
   *           the exception
   */
  public Integer altaLibro(TFLibro tLibro) throws Exception;

  /**
   * Baja libro.
   *
   * @param id
   *          the id
   * @return the integer
   * @throws Exception
   *           the exception
   */
  public Integer bajaLibro(Integer id) throws Exception;

  /**
   * Listar libros.
   *
   * @return the collection
   * @throws Exception
   *           the exception
   */
  public Collection<TFLibro> listarLibros() throws Exception;

  /**
   * Modificar libro.
   *
   * @param tLibro
   *          the t libro
   * @return the integer
   * @throws Exception
   *           the exception
   */
  public Integer modificarLibro(TFLibro tLibro) throws Exception;

  /**
   * Mostrar libro.
   *
   * @param id
   *          the id
   * @return the TF libro
   * @throws Exception
   *           the exception
   */
  public TFLibro mostrarLibro(Integer id) throws Exception;

}
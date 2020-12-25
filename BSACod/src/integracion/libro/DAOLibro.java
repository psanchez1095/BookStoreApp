package integracion.libro;

import java.util.Collection;

import negocio.libro.TFLibro;

/**
 * The Interface DAOLibro.
 */
public interface DAOLibro {

  /**
   * Alta libro.
   *
   * @param libro
   *          the libro
   * @return the integer
   */
  public Integer altaLibro(TFLibro libro);

  /**
   * Baja libro.
   *
   * @param IDlibro
   *          the i dlibro
   * @return true, if successful
   */
  public boolean bajaLibro(Integer IDlibro);

  /**
   * Lee libro por nombre.
   *
   * @param nombre
   *          the nombre
   * @return the TF libro
   */
  public TFLibro leeLibroPorNombre(String nombre);

  /**
   * Leer libro por ID.
   *
   * @param id
   *          the id
   * @return the TF libro
   */
  public TFLibro leerLibroPorID(Integer id);

  /**
   * Listar libro.
   *
   * @return the collection
   */
  public Collection<TFLibro> listarLibro();

  /**
   * Modificar libro.
   *
   * @param libro
   *          the libro
   * @return true, if successful
   */
  public boolean modificarLibro(TFLibro libro);

  /**
   * Mostrar libros.
   *
   * @param IDlibro
   *          the i dlibro
   * @return the TF libro
   */
  public TFLibro mostrarLibros(Integer IDlibro);
}
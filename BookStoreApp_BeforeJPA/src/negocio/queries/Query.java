
package negocio.queries;

import java.util.List;

/**
 * The Interface Query.
 *
 * @param <T>
 *          the generic type
 */
public interface Query<T> {

  /**
   * Execute.
   *
   * @return the list
   */
  public List<T> execute();

}
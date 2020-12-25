package presentacion.contexto;

/**
 * The Class Contexto.
 */
public class Contexto {

  /** The datos. */
  private Object datos;

  /** The evento. */
  private int evento;

  /**
   * Instantiates a new contexto.
   *
   * @param evento
   *          the evento
   * @param datos
   *          the datos
   */
  public Contexto(final int evento, final Object datos) {
    this.datos = datos;
    this.evento = evento;
  }

  /**
   * Gets the datos.
   *
   * @return the datos
   */
  public Object getDatos() {
    return datos;
  }

  /**
   * Gets the evento.
   *
   * @return the evento
   */
  public int getEvento() {
    return evento;
  }

  /**
   * Sets the datos.
   *
   * @param datos
   *          the new datos
   */
  public void setDatos(final Object datos) {
    this.datos = datos;
  }

  /**
   * Sets the evento.
   *
   * @param evento
   *          the new evento
   */
  public void setEvento(final int evento) {
    this.evento = evento;
  }
}
package negocio.libro;

/**
 * The Class TFLibro.
 */
public class TFLibro {

  /** The activo. */
  private Boolean activo;

  /** The cantidad. */
  private Integer cantidad;

  /** The editorial. */
  private Integer editorial;

  /** The genero. */
  private String genero;

  /** The id. */
  private Integer id;

  /** The titulo. */
  private String titulo;

  /**
   * Gets the activo.
   *
   * @return the activo
   */
  public Boolean getActivo() {
    return activo;
  }

  /**
   * Gets the cantidad.
   *
   * @return the cantidad
   */
  public Integer getCantidad() {
    return cantidad;
  }

  /**
   * Gets the editorial ID.
   *
   * @return the editorial ID
   */
  public Integer getEditorialID() {
    return editorial;
  }

  /**
   * Gets the genero.
   *
   * @return the genero
   */
  public String getGenero() {
    return genero;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Integer getID() {
    return id;
  }

  /**
   * Gets the titulo.
   *
   * @return the titulo
   */
  public String getTitulo() {
    return titulo;
  }

  /**
   * Sets the activo.
   *
   * @param activo
   *          the new activo
   */
  public void setActivo(final Boolean activo) {
    this.activo = activo;
  }

  /**
   * Sets the cantidad.
   *
   * @param cantidad
   *          the new cantidad
   */
  public void setCantidad(final Integer cantidad) {
    this.cantidad = cantidad;
  }

  /**
   * Sets the editorial.
   *
   * @param editorial
   *          the new editorial
   */
  public void setEditorial(final Integer editorial) {
    this.editorial = editorial;
  }

  /**
   * Sets the genero.
   *
   * @param genero
   *          the new genero
   */
  public void setGenero(final String genero) {
    this.genero = genero;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setID(final Integer id) {
    this.id = id;
  }

  /**
   * Sets the titulo.
   *
   * @param titulo
   *          the new titulo
   */
  public void setTitulo(final String titulo) {
    this.titulo = titulo;
  }
}
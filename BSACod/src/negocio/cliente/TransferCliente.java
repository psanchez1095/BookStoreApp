package negocio.cliente;

/**
 * The Class TransferCliente.
 */
public class TransferCliente {

  /** The activo. */
  private Boolean activo;

  /** The direccion. */
  private String direccion;

  /** The email. */
  private String email;

  /** The id. */
  private Integer ID;

  /** The identificacion fiscal. */
  private String identificacionFiscal;

  /** The nombre. */
  private String nombre;

  /**
   * Gets the activo.
   *
   * @return the activo
   */
  public Boolean getActivo() {
    return activo;
  }

  /**
   * Gets the direccion.
   *
   * @return the direccion
   */
  public String getDireccion() {
    return direccion;
  }

  /**
   * Gets the email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public Integer getID() {
    return ID;
  }

  /**
   * Gets the identificacion fiscal.
   *
   * @return the identificacion fiscal
   */
  public String getIdentificacionFiscal() {
    return identificacionFiscal;
  }

  /**
   * Gets the nombre.
   *
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
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
   * Sets the direccion.
   *
   * @param direccion
   *          the new direccion
   */
  public void setDireccion(final String direccion) {
    this.direccion = direccion;
  }

  /**
   * Sets the email.
   *
   * @param email
   *          the new email
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Sets the id.
   *
   * @param id
   *          the new id
   */
  public void setID(final Integer id) {
    ID = id;
  }

  /**
   * Sets the identificacion fiscal.
   *
   * @param dni
   *          the new identificacion fiscal
   */
  public void setIdentificacionFiscal(final String dni) {
    identificacionFiscal = dni;
  }

  /**
   * Sets the nombre.
   *
   * @param nombre
   *          the new nombre
   */
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }
}
package negocio.editorial;

import java.util.Date;

/**
 * The Class TransferEditorial.
 */
public class TransferEditorial {

  /** The activo. */
  private Boolean activo;

  /** The anio fundacion. */
  private Date anioFundacion;

  /** The email. */
  private String email;

  /** The id. */
  private Integer id;

  /** The nombre. */
  private String nombre;

  /** The numero facturas. */
  private Integer numeroFacturas;

  /** The tipo. */
  private String tipo;

  /**
   * Gets the activo.
   *
   * @return the activo
   */
  public Boolean getActivo() {
    return activo;
  }

  /**
   * Gets the anio fundacion.
   *
   * @return the anio fundacion
   */
  public Date getAnioFundacion() {
    return anioFundacion;
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
    return id;
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
   * Gets the numero facturas.
   *
   * @return the numero facturas
   */
  public Integer getNumeroFacturas() {
    return numeroFacturas;
  }

  /**
   * Gets the tipo.
   *
   * @return the tipo
   */
  public String getTipo() {
    return tipo;
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
   * Sets the anio fundacion.
   *
   * @param anioFund
   *          the new anio fundacion
   */
  public void setAnioFundacion(final Date anioFund) {
    anioFundacion = anioFund;
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
    this.id = id;
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

  /**
   * Sets the numero facturas.
   *
   * @param numFact
   *          the new numero facturas
   */
  public void setNumeroFacturas(final Integer numFact) {
    numeroFacturas = numFact;
  }

  /**
   * Sets the tipo.
   *
   * @param tipo
   *          the new tipo
   */
  public void setTipo(final String tipo) {
    this.tipo = tipo;
  }
}
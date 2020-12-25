package presentacion.vistas.gui;

/**
 * The Class FactoriaVistaPrincipal.
 */
public abstract class FactoriaVistaPrincipal {

  /** The presentacion. */
  private static FactoriaVistaPrincipal presentacion;

  /**
   * Gets the single instance of FactoriaVistaPrincipal.
   *
   * @return single instance of FactoriaVistaPrincipal
   */
  public static FactoriaVistaPrincipal getInstance() {
    if (presentacion == null) {
      presentacion = new FactoriaVistaPrincipalImp();
    }
    return presentacion;
  }

  /**
   * Generar vista.
   *
   * @param evento
   *          the evento
   * @return the vista principal
   */
  public abstract VistaPrincipal generarVista(int evento);
}

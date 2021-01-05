package presentacion.controlador.command;

/**
 * The Class FactoriaComando.
 */
public abstract class FactoriaComando {

  /** The factoria comando instance. */
  private static FactoriaComando factoriaComandoInstance;

  /**
   * Gets the single instance of FactoriaComando.
   *
   * @return single instance of FactoriaComando
   */
  public synchronized static FactoriaComando getInstance() {
    if (factoriaComandoInstance == null) {
      factoriaComandoInstance = new FactoriaComandoImp();
    }
    return factoriaComandoInstance;
  }

  /**
   * Nuevo comando.
   *
   * @param idEvento
   *          the id evento
   * @return the command
   */
  public abstract Command nuevoComando(int idEvento);
  
}
package presentacion;

import javax.swing.SwingUtilities;

import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosMenu;

/**
 * The Class Main.
 */
public class Main {

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(final String[] args) {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (final Exception e) {
      System.out.println("Error loading JDBC MySQL driver");
      System.exit(1);
    }
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
  }
}
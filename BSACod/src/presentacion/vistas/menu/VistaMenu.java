package presentacion.vistas.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

/**
 * The Class VistaMenu.
 */
public class VistaMenu extends JFrame implements VistaPrincipal {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The factura boton. */
  private JButton menuBoton, clienteBoton, editorialBoton, libroBoton, facturaBoton, materialBoton, departamentoBoton, libreriaBoton, empleadoBoton;

  /**
   * Instantiates a new vista menu.
   */
  public VistaMenu() {
    vista();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * presentacion.vistas.gui.VistaPrincipal#actualizar(presentacion.contexto.
   * Contexto)
   */
  @Override
  public void actualizar(final Contexto contexto) {
  }

  /**
   * Botones.
   *
   * @param Path
   *          the path
   * @param color
   *          the color
   * @return the j button
   */
  public JButton botones(final String Path, final Color color) {
    final JButton boton = new JButton();
    boton.setPreferredSize(new Dimension(190, 120)); // 200, 125
    boton.setBackground(color);
    boton.setBorder(null);
    boton.setFocusPainted(false);
    boton.setIcon(new ImageIcon(Path));

    return boton;
  }

  /**
   * Botones menu.
   *
   * @param nombre
   *          the nombre
   * @return the j button
   */
  public JButton botonesMenu(final String nombre) {
    final JButton boton = new JButton(nombre);
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setFont(new Font("Arial", Font.PLAIN, 18));
    boton.setBackground(new Color(255, 255, 255));
    boton.setMaximumSize(new Dimension(170, 50));
    return boton;
  }

  /**
   * Boton exit.
   *
   * @return the j button
   */
  public JButton botonExit() {
    final JButton boton = new JButton("X");
    boton.setFocusPainted(false);
    boton.setBorder(null);
    boton.setContentAreaFilled(false);
    boton.setBorderPainted(false);
    boton.setPreferredSize(new Dimension(30, 30));
    boton.setFont(new Font("Corbel", Font.BOLD, 20));
    boton.setForeground(new Color(255, 255, 255));
    boton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el programa?",
          "Salir", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    return boton;
  }

  /**
   * Vista.
   */
  private void vista() {
    setSize(1080, 720);
    setLocationRelativeTo(null);
    setResizable(false);
    setUndecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final JPanel fondo = new JPanel();
    final JPanel barra = new JPanel();
    final JPanel panelMenuBoton = new JPanel();
    final JPanel panelSalir = new JPanel();

    barra.setBackground(new Color(66, 86, 98));
    panelMenuBoton.setBackground(new Color(66, 86, 98));
    panelSalir.setBackground(new Color(66, 86, 98));
    fondo.setBackground(new Color(225, 225, 225));

    barra.setLayout(new BorderLayout());
    fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 140));

    menuBoton = botonesMenu("MENÚ");
    menuBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
        ;
      }
    });

    final JButton salir = botonExit();

    panelMenuBoton.add(menuBoton);
    panelSalir.add(salir);
    barra.add(panelMenuBoton, BorderLayout.WEST);
    barra.add(panelSalir, BorderLayout.EAST);

    clienteBoton =
      botones(getClass().getClassLoader().getResource("iconos/menu/clientes.png").getPath(),
        new Color(255, 255, 255));
    clienteBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_CLIENTE_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    fondo.add(clienteBoton);

    editorialBoton =
      botones(getClass().getClassLoader().getResource("iconos/menu/editorial.png").getPath(),
        new Color(255, 255, 255));
    editorialBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_EDITORIAL_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    fondo.add(editorialBoton);

    libroBoton = botones(getClass().getClassLoader().getResource("iconos/menu/libro.png").getPath(),
      new Color(255, 255, 255));
    libroBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_LIBRO_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    fondo.add(libroBoton);

    facturaBoton =
      botones(getClass().getClassLoader().getResource("iconos/menu/factura.png").getPath(),
        new Color(255, 255, 255));
    facturaBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_FACTURA_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    fondo.add(facturaBoton);
    	    
    departamentoBoton =
    	    	    botones(getClass().getClassLoader().getResource("iconos/menu/Departamento.png").getPath(),
    	    	        new Color(255, 255, 255));
    departamentoBoton.addActionListener(new ActionListener() {
    	    	      @Override
    	    	      public void actionPerformed(final ActionEvent e) {
    	    	        dispose();
    	    	        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_DEPARTAMENTO_VISTA, null);
    	    	        Controller.getInstance().handleRequest(contexto);
    	    	      }
    	    	    });
    	    	    fondo.add(departamentoBoton);
   
    	    	    	    	    
    	    	    	    	    materialBoton =
    	    	    	    	    	      botones(getClass().getClassLoader().getResource("iconos/menu/Material.png").getPath(),
    	    	    	    	    	        new Color(255, 255, 255));
    	    	    	    	    materialBoton.addActionListener(new ActionListener() {
    	    	    	    	    	      @Override
    	    	    	    	    	      public void actionPerformed(final ActionEvent e) {
    	    	    	    	    	        dispose();
    	    	    	    	    	        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MATERIAL_VISTA, null);
    	    	    	    	    	        Controller.getInstance().handleRequest(contexto);
    	    	    	    	    	      }
    	    	    	    	    	    });
    	    	    	    	    	    fondo.add(materialBoton);
    	    	    	    	    	    
    	    	    	    	    	    empleadoBoton =
    	    	    	    	    	    	      botones(getClass().getClassLoader().getResource("iconos/menu/Empleado.png").getPath(),
    	    	    	    	    	    	        new Color(255, 255, 255));
    	    	    	    	    				empleadoBoton.addActionListener(new ActionListener() {
    	    	    	    	    	    	      @Override
    	    	    	    	    	    	      public void actionPerformed(final ActionEvent e) {
    	    	    	    	    	    	        dispose();
    	    	    	    	    	    	        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_EMPLEADO_VISTA, null);
    	    	    	    	    	    	        Controller.getInstance().handleRequest(contexto);
    	    	    	    	    	    	      }
    	    	    	    	    	    	    });
    	    	    	    	    	    	    fondo.add(empleadoBoton);   	    	    	    	    	    

    	    	    	    	    libreriaBoton =
    	    	    	    	    	      botones(getClass().getClassLoader().getResource("iconos/menu/Libreria.png").getPath(),
    	    	    	    	    	        new Color(255, 255, 255));
    	    	    	    	    libreriaBoton.addActionListener(new ActionListener() {
    	    	    	    	    	      @Override
    	    	    	    	    	      public void actionPerformed(final ActionEvent e) {
    	    	    	    	    	        dispose();
    	    	    	    	    	        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_LIBRERIA_VISTA, null);
    	    	    	    	    	        Controller.getInstance().handleRequest(contexto);
    	    	    	    	    	      }
    	    	    	    	    	    });
    	    	    	    	    	    fondo.add(libreriaBoton);	    	    	    	    
    	    	    	    

    add(barra, BorderLayout.NORTH);
    add(fondo, BorderLayout.CENTER);
    setVisible(true);
  }

}
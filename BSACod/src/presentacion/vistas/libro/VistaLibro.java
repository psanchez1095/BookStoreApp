/**
 *
 */
package presentacion.vistas.libro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocio.libro.TFLibro;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosLibro;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

/**
 * The Class VistaLibro.
 */
public class VistaLibro extends JFrame implements VistaPrincipal {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The alta libro. */
  protected JButton altaLibro;

  /** The baja libro. */
  protected JButton bajaLibro;

  /** The controller. */
  protected Controller controller;

  /** The listar libro. */
  protected JButton listarLibro;

  /** The modificar libro. */
  protected JButton modificarLibro;

  /** The mostrar libro. */
  protected JButton mostrarLibro;

  /** The volver. */
  protected JButton volver;

  /** The dtm. */
  private DefaultTableModel dtm;

  /** The mensaje. */
  private JLabel mensaje;

  /** The mod libro. */
  private TFLibro modLibro;

  /** The tabla. */
  private JTable tabla;

  /**
   * Instantiates a new vista libro.
   */
  public VistaLibro() {

    initGUI();

  }

  /*
   * (non-Javadoc)
   *
   * @see
   * presentacion.vistas.gui.VistaPrincipal#actualizar(presentacion.contexto.
   * Contexto)
   */
  @Override
  @SuppressWarnings("unchecked")
  public void actualizar(final Contexto contexto) {

    cleanJLabel();
    cleanTabla();

    switch (contexto.getEvento()) {
      case EventosLibro.ALTA_LIBRO_OK: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosLibro.ALTA_LIBRO_KO: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosLibro.BAJA_LIBRO_OK: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosLibro.BAJA_LIBRO_KO: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosLibro.MODIFICAR_LIBRO_OK: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosLibro.MODIFICAR_LIBRO_KO: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosLibro.MOSTRAR_LIBRO_OK: {
        final TFLibro libro = (TFLibro) contexto.getDatos();
        dtm.addRow(new Object[] { libro.getID(), libro.getTitulo(), libro.getGenero(),
          libro.getEditorialID(), libro.getCantidad(), libro.getActivo() });
      }
        ;
        break;
      case EventosLibro.MOSTRAR_LIBRO_KO: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosLibro.LISTAR_LIBROS_OK: {
        final List<TFLibro> lista = (List<TFLibro>) contexto.getDatos();
        for (final TFLibro libro : lista) {
          dtm.addRow(new Object[] { libro.getID(), libro.getTitulo(), libro.getGenero(),
            libro.getEditorialID(), libro.getCantidad(), libro.getActivo() });
        }
      }
        ;
        break;
      case EventosLibro.LISTAR_LIBROS_KO: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosLibro.BUSCAR_LIBRO_OK: {
        modLibro = (TFLibro) contexto.getDatos();
      }
        ;
        break;
      case EventosLibro.BUSCAR_LIBRO_KO: {
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
    }
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
    boton.setForeground(new Color(110, 120, 140));
    boton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?",
          "Salir", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    return boton;
  }

  /**
   * Boton principal.
   *
   * @param nombre
   *          the nombre
   * @return the j button
   */
  public JButton botonPrincipal(final String nombre) {
    final JButton boton = new JButton(nombre);
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setFont(new Font("Arial", Font.PLAIN, 18));
    boton.setBackground(new Color(255, 255, 255));
    boton.setMaximumSize(new Dimension(170, 50));
    return boton;
  }

  /**
   * Clean J label.
   */
  public void cleanJLabel() {
    mensaje.setText(" ");
    mensaje.setOpaque(false);
  }

  /**
   * Clean tabla.
   */
  public void cleanTabla() {
    for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
      dtm.removeRow(i);
    }
  }

  /**
   * Crear boton libro.
   *
   * @param Path
   *          the path
   * @param color
   *          the color
   * @return the j button
   */
  public JButton crearBotonLibro(final String Path, final Color color) {

    final JButton boton = new JButton();
    boton.setPreferredSize(new Dimension(135, 60));
    boton.setBackground(color);
    boton.setBorder(null);
    boton.setFocusPainted(false);
    boton.setIcon(new ImageIcon(Path));

    return boton;
  }

  /**
   * Inits the GUI.
   */
  public void initGUI() {
    setSize(1080, 720);
    setLocationRelativeTo(null);
    setResizable(false);
    setUndecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final JPanel fondo = new JPanel();
    final JPanel barra = new JPanel();
    final JPanel panelMenuBoton = new JPanel();
    final JPanel panelSalir = new JPanel();
    final JPanel panelBotones = new JPanel();
    final JPanel panelTabla = new JPanel();
    final JPanel panelMensaje = new JPanel();

    mensaje = new JLabel(" ");
    mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
    mensaje.setForeground(new Color(255, 255, 255));
    final JLabel modulo = new JLabel(" > LIBRO");
    modulo.setFont(new Font("Arial", Font.PLAIN, 18));
    modulo.setForeground(new Color(255, 255, 255));

    barra.setBackground(new Color(66, 86, 98));
    panelMenuBoton.setBackground(new Color(66, 86, 98));
    panelSalir.setBackground(new Color(66, 86, 98));
    fondo.setBackground(new Color(225, 225, 225));
    panelBotones.setBackground(new Color(125, 125, 125));

    barra.setLayout(new BorderLayout());
    fondo.setLayout(new BorderLayout());
    panelTabla.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    panelMensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 20));

    // Creacion del boton de menu
    final JButton menuBoton = botonPrincipal("MENÚ");
    menuBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });

    final JButton salir = botonExit();
    // --------------------------------

    panelMenuBoton.add(menuBoton);
    panelMenuBoton.add(modulo);
    panelSalir.add(salir);
    barra.add(panelMenuBoton, BorderLayout.WEST);
    barra.add(panelSalir, BorderLayout.EAST);

    final String[] tituloColummnas =
      { "ID", "Titulo", "Genero", "EditorialID", "Cantidad", "Activo" };
    dtm = new DefaultTableModel(null, tituloColummnas);
    tabla = new JTable(dtm);
    tabla.setPreferredSize(new Dimension(800, 400));
    final JScrollPane scroll = new JScrollPane(tabla);
    panelTabla.add(scroll);

    panelMensaje.add(mensaje);

    fondo.add(panelTabla, BorderLayout.CENTER);
    fondo.add(panelMensaje, BorderLayout.SOUTH);

    altaLibro = crearBotonLibro(
      getClass().getClassLoader().getResource("iconos/libro/ALTALIBRO.png").getPath(),
      new Color(243, 89, 63));
    altaLibro.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final JTextField tituloField = new JTextField();
        final JTextField generoField = new JTextField();
        final JTextField cantidadField = new JTextField();
        final JTextField editorialField = new JTextField();
        final Object[] mensaje = { "Titulo:", tituloField, "Genero:", generoField, "Cantidad:",
          cantidadField, "EditorialID", editorialField, };
        final int opcion =
          JOptionPane.showConfirmDialog(null, mensaje, "ALTA LIBRO", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
          try {
            final String titulo = tituloField.getText().trim();
            final String genero = generoField.getText().trim();
            final String cantidad = cantidadField.getText().trim();
            final String editorial = editorialField.getText().trim();
            boolean valid = true;
            String validationMessage = "";
            if (titulo.length() == 0) {
              validationMessage += "El campo \"titulo\" no puede estar vacío.";
              valid = false;
            }
            if (genero.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"genero\" no puede estar vacío.";
              valid = false;
            }
            if (editorial.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"editorial\" no puede estar vacío.";
              valid = false;
            }
            // TODO ¿EDITORIALID?
            if (cantidad.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"cantidad\" no puede estar vacío.";
              valid = false;
            }
            if (!valid) {
              JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error",
                JOptionPane.WARNING_MESSAGE);
            } else {

              final TFLibro libro = new TFLibro();
              libro.setTitulo(titulo);
              ;
              libro.setGenero(genero);
              libro.setCantidad(Integer.parseInt(cantidad));
              libro.setEditorial(Integer.parseInt(editorial));
              libro.setActivo(true);
              final Contexto contexto = new Contexto(EventosLibro.ALTA_LIBRO, libro);
              Controller.getInstance().handleRequest(contexto);
            }
          } catch (final NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo \"cantidad\" debe ser un número válido.",
              "Mensaje de error", JOptionPane.WARNING_MESSAGE);
          } catch (final Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
              JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    });
    panelBotones.add(altaLibro);

    bajaLibro = crearBotonLibro(
      getClass().getClassLoader().getResource("iconos/libro/BAJALIBRO.png").getPath(),
      new Color(0, 112, 192));
    bajaLibro.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA LIBRO",
          JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosLibro.BAJA_LIBRO, id);
            Controller.getInstance().handleRequest(contexto);
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(bajaLibro);

    modificarLibro = crearBotonLibro(
      getClass().getClassLoader().getResource("iconos/libro/MODLIBRO.png").getPath(),
      new Color(91, 155, 213));
    modificarLibro.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {

        try {
          String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MODIFICAR LIBRO",
            JOptionPane.QUESTION_MESSAGE);
          if (idString != null) {
            idString = idString.trim();
            if (idString.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int id = Integer.parseInt(idString);
              final Contexto contexto = new Contexto(EventosLibro.BUSCAR_LIBRO, id);
              Controller.getInstance().handleRequest(contexto);
            }
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null,
            "El campo \"ID\" debe contener un número entero positivo", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        } catch (final Exception ex) {
          JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }

        if (modLibro != null) {
          final JTextField tituloField = new JTextField(modLibro.getTitulo());
          final JTextField generoField = new JTextField(modLibro.getGenero());
          final JTextField cantidadField = new JTextField(String.valueOf(modLibro.getCantidad()));
          final JTextField editorialField =
            new JTextField(String.valueOf(modLibro.getEditorialID()));
          final JCheckBox activoField = new JCheckBox();
          final Object[] mensaje = { "titulo:", tituloField, "genero:", generoField, "cantidad",
            cantidadField, "editorialID", editorialField, "activo", activoField };
          final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR LIBRO",
            JOptionPane.OK_CANCEL_OPTION);
          if (opcion == JOptionPane.OK_OPTION) {
            try {
              final String titulo = tituloField.getText().trim();
              final String genero = generoField.getText().trim();
              final String cantidad = cantidadField.getText().trim();
              final String editorial = editorialField.getText().trim();
              boolean valid = true;
              String validationMessage = "";
              if (titulo.length() == 0) {
                validationMessage += "El campo \"titulo\" no puede estar vacío.";
                valid = false;
              }
              if (genero.length() == 0) {
                if (validationMessage.length() != 0) {
                  validationMessage += "\n";
                }
                validationMessage += "El campo \"genero\" no puede estar vacío.";
                valid = false;
              }
              if (cantidad.length() == 0) {
                if (validationMessage.length() != 0) {
                  validationMessage += "\n";
                }
                validationMessage += "El campo \"cantidad\" no puede estar vacío.";
                valid = false;
              }
              if (editorial.length() == 0) {
                if (validationMessage.length() != 0) {
                  validationMessage += "\n";
                }
                validationMessage += "El campo \"editorial\" no puede estar vacío.";
                valid = false;
              }
              /*
               * if (activo.length() == 0) { if (validationMessage.length() !=
               * 0) validationMessage += "\n"; validationMessage +=
               * "El campo \"activo\" no puede estar vacío."; valid = false; }
               */

              if (!valid) {
                JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error",
                  JOptionPane.WARNING_MESSAGE);
              } else {
                final TFLibro libro = new TFLibro();

                libro.setID(modLibro.getID());
                libro.setTitulo(tituloField.getText());
                libro.setGenero(generoField.getText());
                libro.setCantidad(Integer.parseInt(cantidadField.getText()));
                libro.setEditorial(Integer.parseInt(editorialField.getText()));
                libro.setActivo(activoField.isSelected());

                final Contexto contexto = new Contexto(EventosLibro.MODIFICAR_LIBRO, libro);
                Controller.getInstance().handleRequest(contexto);

              }
            } catch (final NumberFormatException ex) {
              JOptionPane.showMessageDialog(null, "El campo \"genero\" debe ser un número válido.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } catch (final Exception ex) {
              JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            }
          }
          modLibro = null;
        }
      }
    });
    panelBotones.add(modificarLibro);

    mostrarLibro = crearBotonLibro(
      getClass().getClassLoader().getResource("iconos/libro/MOSTRARLIBRO.png").getPath(),
      new Color(112, 173, 71));
    mostrarLibro.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MOSTRAR LIBRO",
            JOptionPane.QUESTION_MESSAGE);
          if (idString != null) {
            idString = idString.trim();
            if (idString.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int id = Integer.parseInt(idString);
              final Contexto contexto = new Contexto(EventosLibro.MOSTRAR_LIBRO, id);
              Controller.getInstance().handleRequest(contexto);
            }
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null,
            "El campo \"ID\" debe contener un número entero positivo", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        } catch (final Exception ex) {
          JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(mostrarLibro);

    listarLibro = crearBotonLibro(
      getClass().getClassLoader().getResource("iconos/libro/LISTARLIBRO.png").getPath(),
      new Color(255, 192, 0));
    listarLibro.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Contexto contexto = new Contexto(EventosLibro.LISTAR_LIBROS, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    panelBotones.add(listarLibro);

    add(barra, BorderLayout.NORTH);
    add(fondo, BorderLayout.CENTER);
    add(panelBotones, BorderLayout.SOUTH);
    setVisible(true);

  }
}
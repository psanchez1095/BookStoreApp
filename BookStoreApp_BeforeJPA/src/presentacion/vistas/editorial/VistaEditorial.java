package presentacion.vistas.editorial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import negocio.editorial.TransferEditorial;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosEditorial;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

/**
 * The Class VistaEditorial.
 */
public class VistaEditorial extends JFrame implements VistaPrincipal {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The alta editorial. */
  protected JButton altaEditorial;

  /** The baja editorial. */
  protected JButton bajaEditorial;

  /** The listar editoriales. */
  protected JButton listarEditoriales;

  /** The modificar editorial. */
  protected JButton modificarEditorial;

  /** The mostrar editorial. */
  protected JButton mostrarEditorial;

  /** The buscar editorial. */
  private TransferEditorial buscarEditorial;

  /** The default table model. */
  private DefaultTableModel defaultTableModel;

  /** The j label. */
  private JLabel jLabel; // mensaje

  /** The j table. */
  private JTable jTable;

  /**
   * Instantiates a new vista editorial.
   */
  public VistaEditorial() {
    vistaEditorial();
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
    // Antes de actualizar, limpiamos el contenido de mensaje (JLabel) y de
    // dtm (DefaultTableModel) para que los resultados de operaciones
    // anteriores
    // no se mezclen con el resultado de la operacion actual.
    limpiarJLabel();
    limpiarTabla();

    switch (contexto.getEvento()) {
      case EventosEditorial.ALTA_EDITORIAL_OK:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
        break;
      case EventosEditorial.ALTA_EDITORIAL_KO:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
        break;
      case EventosEditorial.BAJA_EDITORIAL_OK:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
        break;
      case EventosEditorial.BAJA_EDITORIAL_KO:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
        break;
      case EventosEditorial.MODIFICAR_EDITORIAL_OK: {
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosEditorial.MODIFICAR_EDITORIAL_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosEditorial.MOSTRAR_EDITORIAL_OK: {
        final TransferEditorial transferEditorial = (TransferEditorial) contexto.getDatos();
        defaultTableModel.addRow(new Object[] { transferEditorial.getID(),
          transferEditorial.getNombre(), transferEditorial.getEmail(), transferEditorial.getTipo(),
          transferEditorial.getAnioFundacion(), transferEditorial.getNumeroFacturas(),
          transferEditorial.getActivo() });
      }
        ;
        break;
      case EventosEditorial.MOSTRAR_EDITORIAL_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosEditorial.LISTAR_EDITORIALES_OK: {
        @SuppressWarnings("unchecked")
        final List<TransferEditorial> lista = (List<TransferEditorial>) contexto.getDatos();
        for (final TransferEditorial transferEditorial : lista) {
          defaultTableModel.addRow(new Object[] { transferEditorial.getID(),
            transferEditorial.getNombre(), transferEditorial.getEmail(),
            transferEditorial.getTipo(), transferEditorial.getAnioFundacion(),
            transferEditorial.getNumeroFacturas(), transferEditorial.getActivo() });
        }
      }
        ;
        break;
      case EventosEditorial.LISTAR_EDITORIALES_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosEditorial.BUSCAR_EDITORIAL_OK: {
        buscarEditorial = (TransferEditorial) contexto.getDatos();
      }
        ;
        break;
      case EventosEditorial.BUSCAR_EDITORIAL_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
    }
  }

  /**
   * Crear boton.
   *
   * @param Path
   *          the path
   * @param color
   *          the color
   * @return the j button
   */
  public JButton crearBoton(final String Path, final Color color) {
    final JButton boton = new JButton();
    boton.setPreferredSize(new Dimension(135, 60));
    boton.setBackground(color);
    boton.setBorder(null);
    boton.setFocusPainted(false);
    boton.setIcon(new ImageIcon(Path));

    return boton;
  }

  /**
   * Crear boton menu.
   *
   * @param nombre
   *          the nombre
   * @return the j button
   */
  public JButton crearBotonMenu(final String nombre) {
    final JButton boton = new JButton(nombre);
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setFont(new Font("Arial", Font.PLAIN, 18));
    boton.setBackground(new Color(255, 255, 255));
    boton.setMaximumSize(new Dimension(170, 50));
    return boton;
  }

  /**
   * Crear boton salir.
   *
   * @return the j button
   */
  public JButton crearBotonSalir() {
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
   * Limpiar J label.
   */
  public void limpiarJLabel() {
    jLabel.setText(" ");
    jLabel.setOpaque(false);
  }

  /**
   * Limpiar tabla.
   */
  public void limpiarTabla() {
    for (int i = defaultTableModel.getRowCount() - 1; i >= 0; i--) {
      defaultTableModel.removeRow(i);
    }
  }

  /**
   * Vista editorial.
   */
  private void vistaEditorial() {
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

    jLabel = new JLabel(" ");
    jLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    jLabel.setForeground(new Color(255, 255, 255));
    final JLabel modulo = new JLabel(" > EDITORIAL");
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
    final JButton menuBoton = crearBotonMenu("MENÚ");
    menuBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        dispose();
        final Contexto contexto = new Contexto(EventosMenu.MOSTRAR_MENU_VISTA, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });

    final JButton salir = crearBotonSalir();
    // --------------------------------

    panelMenuBoton.add(menuBoton);
    panelMenuBoton.add(modulo);
    panelSalir.add(salir);
    barra.add(panelMenuBoton, BorderLayout.WEST);
    barra.add(panelSalir, BorderLayout.EAST);

    // Creacion de la tabla.
    final String[] nombreColummnas =
      { "ID", "Nombre", "e-mail", "Tipo", "Fecha", "NumeroFacturas", "Activo" };
    defaultTableModel = new DefaultTableModel(null, nombreColummnas);
    jTable = new JTable(defaultTableModel);
    jTable.setPreferredSize(new Dimension(800, 400));
    final JScrollPane scroll = new JScrollPane(jTable);
    panelTabla.add(scroll);
    // -----------------------

    panelMensaje.add(jLabel);

    fondo.add(panelTabla, BorderLayout.CENTER);
    fondo.add(panelMensaje, BorderLayout.SOUTH);

    // Panel de botones.
    final JButton altaBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/editorial/ALTAEDITORIAL.png").getPath(),
      new Color(243, 89, 63));
    altaBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final JTextField nombreField = new JTextField();
        final JTextField tipoField = new JTextField();
        final JTextField aniofundacionField = new JTextField();
        final JTextField emailField = new JTextField();
        final JTextField numerofacturasField = new JTextField();
        final JCheckBox activoField = new JCheckBox();
        final Object[] mensaje = { "Nombre:", nombreField, "E-mail:", emailField, "Tipo:",
          tipoField, "Fecha:", aniofundacionField, "Numero de Facturas:", numerofacturasField,
          "Activo:", activoField, };
        final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA EDITORIAL",
          JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
          try {
            if (nombreField.getText() != null && emailField.getText() != null
              && tipoField.getText() != null && aniofundacionField.getText() != null
              && numerofacturasField != null && activoField.getText() != null
              && !nombreField.getText().equalsIgnoreCase("")
              && !emailField.getText().equalsIgnoreCase("")
              && !tipoField.getText().equalsIgnoreCase("")
              && !emailField.getText().equalsIgnoreCase("")
              && !numerofacturasField.getText().equalsIgnoreCase("")
            /* && !activoField.getText().equalsIgnoreCase("") */) {
              final TransferEditorial tEditorial = new TransferEditorial();
              tEditorial.setNombre(nombreField.getText());
              tEditorial.setEmail(emailField.getText());
              tEditorial.setTipo(tipoField.getText());
              tEditorial.setAnioFundacion(
                new SimpleDateFormat("dd/mm/yyyy").parse(aniofundacionField.getText()));
              tEditorial.setActivo(activoField.isSelected());
              tEditorial.setNumeroFacturas(Integer.parseInt(numerofacturasField.getText()));
              final Contexto contexto = new Contexto(EventosEditorial.ALTA_EDITORIAL, tEditorial);
              Controller.getInstance().handleRequest(contexto);
            } else {
              JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            }
          } catch (final NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
              "Mensaje de error", JOptionPane.WARNING_MESSAGE);
          } catch (final ParseException e1) {
            JOptionPane.showMessageDialog(null, "La fecha introducidad es incorrecta.",
              "Mensaje de error", JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    });
    panelBotones.add(altaBoton);

    final JButton bajasBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/editorial/BAJAEDITORIAL.png").getPath(),
      new Color(0, 112, 192));
    bajasBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA EDITORIAL",
          JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosEditorial.BAJA_EDITORIAL, id);
            Controller.getInstance().handleRequest(contexto);
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(bajasBoton);

    final JButton editarBoton = crearBoton(

      getClass().getClassLoader().getResource("iconos/editorial/MODEDITORIAL.png").getPath(),
      new Color(91, 155, 213));
    editarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:",
          "BUSCAR EDITORIAL", JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosEditorial.BUSCAR_EDITORIAL, id);

            Controller.getInstance().handleRequest(contexto);
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }

        if (buscarEditorial != null) {

          final JTextField nombreField =
            new JTextField(String.valueOf(buscarEditorial.getNombre()));
          final JTextField tipoField = new JTextField(String.valueOf(buscarEditorial.getTipo()));
          final JTextField aniofundacionField =
            new JTextField(String.valueOf(buscarEditorial.getAnioFundacion()));
          final JTextField emailField = new JTextField(String.valueOf(buscarEditorial.getEmail()));
          final JTextField numerofacturasField =
            new JTextField(String.valueOf(buscarEditorial.getNumeroFacturas()));
          final JCheckBox activoField = new JCheckBox();
          activoField.setSelected(buscarEditorial.getActivo());
          final Object[] mensaje = { "Nombre:", nombreField, "E-mail:", emailField, "Tipo:",
            tipoField, "Anio de Fundacion:", aniofundacionField, "Numero de Facturas:",
            numerofacturasField, "Activo:", activoField, };
          final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR EDITORIAL",
            JOptionPane.OK_CANCEL_OPTION);
          if (opcion == JOptionPane.OK_OPTION) {
            try {
              if (nombreField.getText() != null && tipoField.getText() != null
                && aniofundacionField.getText() != null && emailField.getText() != null
                && activoField.getText() != null && numerofacturasField.getText() != null
                && !nombreField.getText().equalsIgnoreCase("")
                && !emailField.getText().equalsIgnoreCase("")
                && !aniofundacionField.getText().equalsIgnoreCase("")
                && !emailField.getText().equalsIgnoreCase("")
              /* && !activoField.getText().equalsIgnoreCase("") */) {
                final TransferEditorial transferEditorial = new TransferEditorial();
                transferEditorial.setID(buscarEditorial.getID());
                transferEditorial.setNombre(nombreField.getText());
                transferEditorial.setAnioFundacion(
                  new SimpleDateFormat("dd/mm/yyyy").parse(aniofundacionField.getText()));
                transferEditorial.setTipo(tipoField.getText());
                transferEditorial.setEmail(emailField.getText());
                transferEditorial
                  .setNumeroFacturas(Integer.parseInt(numerofacturasField.getText()));
                transferEditorial.setActivo(activoField.isSelected());
                final Contexto contexto =
                  new Contexto(EventosEditorial.MODIFICAR_EDITORIAL, transferEditorial);
                Controller.getInstance().handleRequest(contexto);
              }
            } catch (final NumberFormatException ex) {
              JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } catch (final ParseException ex) {
              JOptionPane.showMessageDialog(null, "La fecha introducidad es incorrecta.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            }
          }
          buscarEditorial = null;
        }
      }
    });
    panelBotones.add(editarBoton);

    final JButton mostrarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/editorial/MOSTRAREDITORIAL.png").getPath(),
      new Color(112, 173, 71));
    mostrarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:",
          "MOSTRAR EDITORIAL", JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosEditorial.MOSTRAR_EDITORIAL, id);
            Controller.getInstance().handleRequest(contexto);
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(mostrarBoton);

    final JButton listarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/editorial/LISTAREDITORIAL.png").getPath(),
      new Color(255, 192, 0));
    listarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Contexto contexto = new Contexto(EventosEditorial.LISTAR_EDITORIALES, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    panelBotones.add(listarBoton);

    // -----------------------------------------

    // Aniadimos todos los paneles al JFrame.
    add(barra, BorderLayout.NORTH);
    add(fondo, BorderLayout.CENTER);
    add(panelBotones, BorderLayout.SOUTH);
    setVisible(true);
  }

}
package presentacion.vistas.factura;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocio.factura.TFactura;
import negocio.lineadefactura.TLineaDeFactura;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosFactura;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

/**
 * The Class VistaFactura.
 */
public class VistaFactura extends JFrame implements VistaPrincipal {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The dtm factura. */
  private DefaultTableModel dtmFactura;

  /** The dtm productos. */
  private DefaultTableModel dtmProductos;

  /** The factura buscar. */
  private TFactura facturaBuscar;

  /** The mensaje. */
  private JLabel mensaje;

  /** The tabla factura. */
  private JTable tablaFactura;

  /** The tabla productos. */
  private JTable tablaProductos;

  /**
   * Instantiates a new vista factura.
   */
  public VistaFactura() {
    vista();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * presentacion.vistas.gui.VistaPrincipal#actualizar(presentacion.contexto.
   * Contexto)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void actualizar(final Contexto contexto) {
    limpiarJLabel();

    switch (contexto.getEvento()) {
      case EventosFactura.ALTA_FACTURA_OK:
      case EventosFactura.BAJA_FACTURA_OK:
      case EventosFactura.ANIADIR_LIBRO_FACTURA_OK:
      case EventosFactura.ELIMINAR_LIBRO_FACTURA_OK:
      case EventosFactura.CERRAR_FACTURA_OK:
      case EventosFactura.MODIFICAR_FACTURA_OK: {
        limpiarTablaFacturas();
        limpiarTablaProductos();
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosFactura.ALTA_FACTURA_KO:
      case EventosFactura.BAJA_FACTURA_KO:
      case EventosFactura.ANIADIR_LIBRO_FACTURA_KO:
      case EventosFactura.ELIMINAR_LIBRO_FACTURA_KO:
      case EventosFactura.MOSTRAR_FACTURA_KO:
      case EventosFactura.MOSTRAR_LIBROS_FACTURA_KO:
      case EventosFactura.LISTAR_FACTURAS_KO:
      case EventosFactura.CERRAR_FACTURA_KO:
      case EventosFactura.MODIFICAR_FACTURA_KO: {
        limpiarTablaFacturas();
        limpiarTablaProductos();
        final String texto = (String) contexto.getDatos();
        mensaje.setText(texto);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosFactura.MOSTRAR_FACTURA_OK: {
        limpiarTablaFacturas();
        limpiarTablaProductos();
        final TFactura factura = (TFactura) contexto.getDatos();
        dtmFactura.addRow(new Object[] { factura.getID(), factura.getIdCliente(),
          new SimpleDateFormat("dd/MM/yyyy").format(factura.getFecha()), factura.getImporte(),
          factura.isCerrada() ? "Cerrada" : "Abierta" });
        facturaBuscar = factura;
      }
        ;
        break;
      case EventosFactura.MOSTRAR_LIBROS_FACTURA_OK: {
        limpiarTablaProductos();
        final Collection<TLineaDeFactura> productos =
          (Collection<TLineaDeFactura>) contexto.getDatos();
        for (final TLineaDeFactura tLineaDeFactura : productos) {
          dtmProductos.addRow(new Object[] { tLineaDeFactura.getIdLibro(),
            tLineaDeFactura.getCantidad(), tLineaDeFactura.getPrecioTotal() });
        }
      }
        ;
        break;
      case EventosFactura.LISTAR_FACTURAS_OK:
      case EventosFactura.FACTURAS_MAS_DE_MIL_OK: {
        limpiarTablaFacturas();
        limpiarTablaProductos();
        final Collection<TFactura> productos = (Collection<TFactura>) contexto.getDatos();
        for (final TFactura linea : productos) {
          dtmFactura.addRow(new Object[] { linea.getID(), linea.getIdCliente(),
            new SimpleDateFormat("dd/MM/yyyy").format(linea.getFecha()), linea.getImporte(),
            linea.isCerrada() ? "Cerrada" : "Abierta" });
        }
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
    boton.setPreferredSize(new Dimension(120, 60));
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
    mensaje.setText(" ");
    mensaje.setOpaque(false);
  }

  /**
   * Limpiar tabla facturas.
   */
  public void limpiarTablaFacturas() {
    for (int i = dtmFactura.getRowCount() - 1; i >= 0; i--) {
      dtmFactura.removeRow(i);
    }
  }

  /**
   * Limpiar tabla productos.
   */
  public void limpiarTablaProductos() {
    for (int i = dtmProductos.getRowCount() - 1; i >= 0; i--) {
      dtmProductos.removeRow(i);
    }
  }

  /**
   * Vista.
   */
  private void vista() {
    setSize(1280, 720);
    setLocationRelativeTo(null);
    setResizable(false);
    setUndecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final JPanel fondo = new JPanel();
    final JPanel barra = new JPanel();
    final JPanel panelMenuBoton = new JPanel();
    final JPanel panelSalir = new JPanel();
    final JPanel panelBotones = new JPanel();
    final JPanel panelTablaFactura = new JPanel();
    final JPanel panelTablaProductos = new JPanel();
    final JPanel panelMensaje = new JPanel();

    mensaje = new JLabel(" ");
    mensaje.setFont(new Font("Arial", Font.PLAIN, 18));
    mensaje.setForeground(new Color(255, 255, 255));
    final JLabel modulo = new JLabel(" > FACTURA");
    modulo.setFont(new Font("Arial", Font.PLAIN, 18));
    modulo.setForeground(new Color(255, 255, 255));

    barra.setBackground(new Color(66, 86, 98));
    panelMenuBoton.setBackground(new Color(66, 86, 98));
    panelSalir.setBackground(new Color(66, 86, 98));
    fondo.setBackground(new Color(225, 225, 225));
    panelBotones.setBackground(new Color(125, 125, 125));

    barra.setLayout(new BorderLayout());
    fondo.setLayout(new BorderLayout());
    panelTablaFactura.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
    panelTablaProductos.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 25));
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

    // Creacion de la tabla factura.
    final String[] nombreColummnasFactura = { "ID", "ID Cliente", "Fecha", "Importe", "Estado" };
    dtmFactura = new DefaultTableModel(null, nombreColummnasFactura);
    tablaFactura = new JTable(dtmFactura);
    tablaFactura.setPreferredSize(new Dimension(1000, 162));
    final JScrollPane scrollFactura = new JScrollPane(tablaFactura);
    scrollFactura.setPreferredSize(new Dimension(1000, 185));
    panelTablaFactura.add(scrollFactura);
    // -----------------------

    // Creacion de la tabla producto.
    final String[] nombreColummnasProductos = { "ID Libro", "Cantidad", "Importe" };
    dtmProductos = new DefaultTableModel(null, nombreColummnasProductos);
    tablaProductos = new JTable(dtmProductos);
    tablaProductos.setPreferredSize(new Dimension(1000, 162));
    final JScrollPane scrollProductos = new JScrollPane(tablaProductos);
    scrollProductos.setPreferredSize(new Dimension(1000, 185));
    panelTablaProductos.add(scrollProductos);
    // -----------------------

    panelMensaje.add(mensaje);

    fondo.add(panelTablaFactura, BorderLayout.NORTH);
    fondo.add(panelTablaProductos, BorderLayout.CENTER);
    fondo.add(panelMensaje, BorderLayout.SOUTH);

    // Panel de botones
    final JButton altaBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/ALTAFACTURA.png").getPath(),
      new Color(243, 89, 63));
    altaBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final JTextField idClienteField = new JTextField();
        final Object[] mensaje = { "ID Cliente:", idClienteField };
        final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA FACTURA",
          JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
          try {
            final String idCliente = idClienteField.getText().trim();
            if (idCliente.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID Cliente\" no puede estar vacío.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int idClienteInt = Integer.parseInt(idCliente);
              final Contexto contexto = new Contexto(EventosFactura.ALTA_FACTURA, idClienteInt);
              Controller.getInstance().handleRequest(contexto);
            }
          } catch (final NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo debe contener un número entero positivo.",
              "Mensaje de error", JOptionPane.WARNING_MESSAGE);
          } catch (final Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
              JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    });
    panelBotones.add(altaBoton);

    final JButton bajaBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/BAJAFACTURA.png").getPath(),
      new Color(0, 112, 192));
    bajaBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA FACTURA",
            JOptionPane.QUESTION_MESSAGE);
          if (idString != null) {
            idString = idString.trim();
            if (idString.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int id = Integer.parseInt(idString);
              final Contexto contexto = new Contexto(EventosFactura.BAJA_FACTURA, id);
              Controller.getInstance().handleRequest(contexto);
            }
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null,
            "El campo \"ID\" debe contener un número entero positivo.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        } catch (final Exception ex) {
          JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(bajaBoton);

    final JButton cerrarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/CERRARFACTURA.png").getPath(),
      new Color(0, 112, 192));
    cerrarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "CERRAR FACTURA",
            JOptionPane.QUESTION_MESSAGE);
          if (idString != null) {
            idString = idString.trim();
            if (idString.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int id = Integer.parseInt(idString);
              final Contexto contexto = new Contexto(EventosFactura.CERRAR_FACTURA, id);
              Controller.getInstance().handleRequest(contexto);
            }
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null,
            "El campo \"ID\" debe contener un número entero positivo.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        } catch (final Exception ex) {
          JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(cerrarBoton);

    final JButton anadirLibroBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/ANADIRLIBRO.png").getPath(),
      new Color(91, 155, 213));
    anadirLibroBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          final JTextField idFacturaField = new JTextField();
          final JTextField idProductoField = new JTextField();
          final JTextField cantidadField = new JTextField();
          final JTextField precioTotalField = new JTextField();
          final Object[] mensaje = { "ID Factura:", idFacturaField, "ID Libro:", idProductoField,
            "Cantidad:", cantidadField, "Precio total:", precioTotalField };
          final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "AÑADIR LIBRO A FACTURA",
            JOptionPane.OK_CANCEL_OPTION);
          if (opcion == JOptionPane.OK_OPTION) {
            final String idFacturaString = idFacturaField.getText().trim();
            final String idProductoString = idProductoField.getText().trim();
            final String cantidadString = cantidadField.getText().trim();
            final String precioString = precioTotalField.getText().trim().replace(',', '.');
            boolean valid = true;
            String validationMessage = "";
            if (idFacturaString.length() == 0) {
              validationMessage += "El campo \"ID Factura\" no puede estar vacío.";
              valid = false;
            }
            if (idProductoString.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"ID Libro\" no puede estar vacío.";
              valid = false;
            }
            if (cantidadString.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"Cantidad\" no puede estar vacío.";
              valid = false;
            }
            if (cantidadString.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"Precio total\" no puede estar vacío.";
              valid = false;
            }
            if (!valid) {
              JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error",
                JOptionPane.WARNING_MESSAGE);
            } else {
              final int idFactura = Integer.parseInt(idFacturaString);
              final int idLibro = Integer.parseInt(idProductoString);
              final int cantidad = Integer.parseInt(cantidadString);
              final double precio = Double.parseDouble(precioString);
              final TLineaDeFactura tLineaDeFactura = new TLineaDeFactura();
              tLineaDeFactura.setIdFactura(idFactura);
              tLineaDeFactura.setIdLibro(idLibro);
              tLineaDeFactura.setCantidad(cantidad);
              tLineaDeFactura.setPrecioTotal(precio);
              final Contexto contexto =
                new Contexto(EventosFactura.ANIADIR_LIBRO_FACTURA, tLineaDeFactura);
              Controller.getInstance().handleRequest(contexto);
            }
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Los campos deben contener números positivos",
            "Mensaje de error", JOptionPane.WARNING_MESSAGE);
        } catch (final Exception ex) {
          JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(anadirLibroBoton);

    final JButton eliminarProductoBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/ELIMINARLIBRO.png").getPath(),
      new Color(112, 173, 71));
    eliminarProductoBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          final JTextField idFacturaField = new JTextField();
          final JTextField idLibroField = new JTextField();
          final JTextField cantidadField = new JTextField();
          final Object[] mensaje = { "ID Factura:", idFacturaField, "ID Libro:", idLibroField,
            "Cantidad:", cantidadField };
          final int opcion = JOptionPane.showConfirmDialog(null, mensaje,
            "ELIMINAR LIBRO DE FACTURA", JOptionPane.OK_CANCEL_OPTION);
          if (opcion == JOptionPane.OK_OPTION) {
            final String idFacturaString = idFacturaField.getText().trim();
            final String idLibroString = idLibroField.getText().trim();
            final String cantidadString = cantidadField.getText().trim();
            boolean valid = true;
            String validationMessage = "";
            if (idFacturaString.length() == 0) {
              validationMessage += "El campo \"ID Factura\" no puede estar vacío.";
              valid = false;
            }
            if (idLibroString.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"ID Libro\" no puede estar vacío.";
              valid = false;
            }
            if (cantidadString.length() == 0) {
              if (validationMessage.length() != 0) {
                validationMessage += "\n";
              }
              validationMessage += "El campo \"Cantidad\" no puede estar vacío.";
              valid = false;
            }
            if (!valid) {
              JOptionPane.showMessageDialog(null, validationMessage, "Mensaje de error",
                JOptionPane.WARNING_MESSAGE);
            } else {
              final int idFactura = Integer.parseInt(idFacturaString);
              final int idProducto = Integer.parseInt(idLibroString);
              final int cantidad = Integer.parseInt(cantidadString);
              final TLineaDeFactura tLineaDeFactura = new TLineaDeFactura();
              tLineaDeFactura.setIdFactura(idFactura);
              tLineaDeFactura.setIdLibro(idProducto);
              tLineaDeFactura.setCantidad(cantidad);
              final Contexto contexto =
                new Contexto(EventosFactura.ELIMINAR_LIBRO_FACTURA, tLineaDeFactura);
              Controller.getInstance().handleRequest(contexto);
            }
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Los campos deben contener números enteros positivos",
            "Mensaje de error", JOptionPane.WARNING_MESSAGE);
        } catch (final Exception ex) {
          JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(eliminarProductoBoton);

    final JButton mostrarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/MOSTRARFACTURA.png").getPath(),
      new Color(255, 192, 0));
    mostrarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          String idString = JOptionPane.showInputDialog(null, "Introduce ID de factura:",
            "MOSTRAR FACTURA", JOptionPane.QUESTION_MESSAGE);
          if (idString != null) {
            idString = idString.trim();
            if (idString.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID\" no debe estar vacío",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int id = Integer.parseInt(idString);
              final Contexto contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA, id);
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
        if (facturaBuscar != null) {
          final Contexto contexto =
            new Contexto(EventosFactura.MOSTRAR_LIBROS_FACTURA, facturaBuscar.getID());
          Controller.getInstance().handleRequest(contexto);
        }
        facturaBuscar = null;
      }
    });
    panelBotones.add(mostrarBoton);
    final JButton modificarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/MODIFICARFACTURA.png").getPath(),
      new Color(255, 192, 0));
    modificarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        try {
          String idString = JOptionPane.showInputDialog(null, "Introduce ID de factura:",
            "MODIFICAR FACTURA", JOptionPane.QUESTION_MESSAGE);
          if (idString != null) {
            idString = idString.trim();
            if (idString.length() == 0) {
              JOptionPane.showMessageDialog(null, "El campo \"ID\" es obligatorio",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            } else {
              final int id = Integer.parseInt(idString);
              final Contexto contexto = new Contexto(EventosFactura.MOSTRAR_FACTURA, id);
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
        if (facturaBuscar != null) {
          if (facturaBuscar.isCerrada()) {
            JOptionPane.showMessageDialog(null, "La factura está cerrada", "Factura cerrada",
              JOptionPane.WARNING_MESSAGE);
          } else {
            final JTextField idClienteField = new JTextField();
            final Object[] mensaje = { "ID Cliente:", idClienteField };
            final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR FACTURA",
              JOptionPane.OK_CANCEL_OPTION);
            if (opcion == JOptionPane.OK_OPTION) {
              try {
                final String idCliente = idClienteField.getText().trim();
                if (idCliente.length() == 0) {
                  JOptionPane.showMessageDialog(null,
                    "El campo \"ID Cliente\" no puede estar vacío.", "Mensaje de error",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                  final int idClienteInt = Integer.parseInt(idCliente);
                  final TFactura tFactura = new TFactura();
                  tFactura.setID(facturaBuscar.getID());
                  tFactura.setIdCliente(idClienteInt);
                  final Contexto contexto =
                    new Contexto(EventosFactura.MODIFICAR_FACTURA, tFactura);
                  Controller.getInstance().handleRequest(contexto);
                }
              } catch (final NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                  "El campo debe contener un número entero positivo.", "Mensaje de error",
                  JOptionPane.WARNING_MESSAGE);
              } catch (final Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.",
                  "Mensaje de error", JOptionPane.WARNING_MESSAGE);
              }
            }
          }
          limpiarTablaFacturas();
          facturaBuscar = null;
        }
      }
    });
    panelBotones.add(modificarBoton);
    final JButton listarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/LISTARFACTURA.png").getPath(),
      new Color(255, 192, 0));
    listarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Contexto contexto = new Contexto(EventosFactura.LISTAR_FACTURAS, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    panelBotones.add(listarBoton);
    final JButton facturasMasDeMilBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/factura/FACTURASMASMILEUROS.png").getPath(),
      new Color(255, 255, 255));
    facturasMasDeMilBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Contexto contexto = new Contexto(EventosFactura.FACTURAS_MAS_DE_MIL, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    panelBotones.add(facturasMasDeMilBoton);
    // -----------------------------------------

    // Aniado todos los paneles al JFrame.
    add(barra, BorderLayout.NORTH);
    add(fondo, BorderLayout.CENTER);
    add(panelBotones, BorderLayout.SOUTH);
    setVisible(true);
  }
}
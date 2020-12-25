package presentacion.vistas.cliente;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteEmpresa;
import negocio.cliente.TransferClienteNormal;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosCliente;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

/**
 * The Class VistaCliente.
 */
public class VistaCliente extends JFrame implements VistaPrincipal {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1275127139413082187L;

  /** The cliente buscar. */
  private TransferCliente clienteBuscar;

  /** The default table model. */
  private DefaultTableModel defaultTableModel;

  /** The j label. */
  private JLabel jLabel; // mensaje

  /** The j table. */
  private JTable jTable;

  /**
   * Instantiates a new vista cliente.
   */
  public VistaCliente() {
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
    // Antes de actualizar, limpiamos el contenido de mensaje (JLabel) y de
    // dtm (DefaultTableModel) para que los resultados de operaciones
    // anteriores
    // no se mezclen con el resultado de la operacion actual.
    limpiarJLabel();
    limpiarTabla();

    switch (contexto.getEvento()) {
      case EventosCliente.ALTA_CLIENTE_OK:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
        break;
      case EventosCliente.ALTA_CLIENTE_KO:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
        break;
      case EventosCliente.BAJA_CLIENTE_OK:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
        break;
      case EventosCliente.BAJA_CLIENTE_KO:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
        break;
      case EventosCliente.MODIFICAR_CLIENTE_OK: {
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosCliente.MODIFICAR_CLIENTE_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosCliente.MOSTRAR_CLIENTE_OK: {
        final TransferCliente transferCliente = (TransferCliente) contexto.getDatos();
        if (transferCliente instanceof TransferClienteEmpresa) {

          defaultTableModel.addRow(new Object[] { transferCliente.getID(),
            transferCliente.getNombre(), transferCliente.getIdentificacionFiscal(),
            transferCliente.getDireccion(), transferCliente.getEmail(), transferCliente.getActivo(),
            ((TransferClienteEmpresa) transferCliente).getDescuentoPorEmpresa(), null });
        } else {
          defaultTableModel.addRow(new Object[] { transferCliente.getID(),
            transferCliente.getNombre(), transferCliente.getIdentificacionFiscal(),
            transferCliente.getDireccion(), transferCliente.getEmail(), transferCliente.getActivo(),
            null, ((TransferClienteNormal) transferCliente).getGastosDeEnvio() });
        }
      }
        ;
        break;
      case EventosCliente.MOSTRAR_CLIENTE_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosCliente.LISTAR_CLIENTES_OK: {
        @SuppressWarnings("unchecked")
        final List<TransferCliente> lista = (List<TransferCliente>) contexto.getDatos();
        for (final TransferCliente transferCliente : lista) {

          if (transferCliente instanceof TransferClienteEmpresa) {

            defaultTableModel
              .addRow(new Object[] { transferCliente.getID(), transferCliente.getNombre(),
                transferCliente.getIdentificacionFiscal(), transferCliente.getDireccion(),
                transferCliente.getEmail(), transferCliente.getActivo(),
                ((TransferClienteEmpresa) transferCliente).getDescuentoPorEmpresa(), null });
          } else {
            defaultTableModel
              .addRow(new Object[] { transferCliente.getID(), transferCliente.getNombre(),
                transferCliente.getIdentificacionFiscal(), transferCliente.getDireccion(),
                transferCliente.getEmail(), transferCliente.getActivo(), null,
                ((TransferClienteNormal) transferCliente).getGastosDeEnvio() });
          }

        }
      }
        ;
        break;
      case EventosCliente.LISTAR_CLIENTES_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;

      case EventosCliente.BUSCAR_CLIENTE_OK: {
        clienteBuscar = (TransferCliente) contexto.getDatos();
      }
        ;
        break;

      case EventosCliente.BUSCAR_CLIENTE_KO: {
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
    final JPanel panelBotones = new JPanel();
    final JPanel panelTabla = new JPanel();
    final JPanel panelMensaje = new JPanel();

    jLabel = new JLabel(" ");
    jLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    jLabel.setForeground(new Color(255, 255, 255));
    final JLabel modulo = new JLabel(" > CLIENTE");
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
    final String[] nombreColummnas = { "ID", "Nombre", "Identificación Fiscal", "Dirección",
      "Email", "Activo", "Descuento Por Empresa", "Gastos de Envio" };
    defaultTableModel = new DefaultTableModel(null, nombreColummnas);
    jTable = new JTable(defaultTableModel);
    jTable.setPreferredSize(new Dimension(1000, 400));
    final JScrollPane scroll = new JScrollPane(jTable);
    panelTabla.add(scroll);
    // -----------------------

    panelMensaje.add(jLabel);

    fondo.add(panelTabla, BorderLayout.CENTER);
    fondo.add(panelMensaje, BorderLayout.SOUTH);

    // Panel de botones.
    final JButton altaBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/cliente/altaCliente.png").getPath(),
      new Color(243, 89, 63));
    altaBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {

        final String[] lista = new String[2];
        lista[0] = "Empresa";
        lista[1] = "Normal";
        final JComboBox<String> comboTipo = new JComboBox<>(lista);

        final JTextField nombreField = new JTextField();
        final JTextField identificacionFiscalField = new JTextField();
        final JTextField direccionField = new JTextField();
        final JTextField emailField = new JTextField();
        final JTextField descuentoempField = new JTextField();
        final JTextField gastosEnvioField = new JTextField();
        final JCheckBox activoField = new JCheckBox();

        final Object[] mensaje = { "Tipo:", comboTipo, "Nombre:", nombreField,
          "Identificación Fiscal:", identificacionFiscalField, "Dirección:", direccionField,
          "Email:", emailField, "Activo:", activoField, "Descuento por empresa:", descuentoempField,
          "Gastos de envio a cliente:", gastosEnvioField };

        comboTipo.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(final ActionEvent e) {
            final String tipo = (String) comboTipo.getSelectedItem();
            if ("Empresa".equals(tipo)) {
              descuentoempField.setEnabled(true);
              gastosEnvioField.setEnabled(false);
            } else {
              descuentoempField.setEnabled(false);
              gastosEnvioField.setEnabled(true);
            }

          }
        });

        final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA CLIENTE",
          JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
          try {
            if (nombreField.getText() != null && identificacionFiscalField.getText() != null
              && direccionField.getText() != null && emailField.getText() != null
              && activoField.getText() != null && !nombreField.getText().equalsIgnoreCase("")
              && !identificacionFiscalField.getText().equalsIgnoreCase("")
              && !direccionField.getText().equalsIgnoreCase("")
              && !emailField.getText().equalsIgnoreCase("")) {
              // && !activoField.getText().equalsIgnoreCase("")) {

              TransferCliente transferCliente;

              if ("Empresa".equals(comboTipo.getSelectedItem())) {
                if (descuentoempField.getText() == null) {
                  throw new NumberFormatException();
                }
                transferCliente = new TransferClienteEmpresa();
                ((TransferClienteEmpresa) transferCliente)
                  .setDescuentoPorEmpresa(Double.valueOf(descuentoempField.getText()));
              } else {
                if (gastosEnvioField.getText() == null) {
                  throw new NumberFormatException();
                }
                transferCliente = new TransferClienteNormal();
                ((TransferClienteNormal) transferCliente)
                  .setGastosDeEnvio(Double.valueOf(gastosEnvioField.getText()));
              }

              transferCliente.setNombre(nombreField.getText());
              transferCliente.setIdentificacionFiscal(identificacionFiscalField.getText());
              transferCliente.setDireccion(direccionField.getText());
              transferCliente.setEmail(emailField.getText());
              transferCliente.setActivo(activoField.isSelected());
              final Contexto contexto = new Contexto(EventosCliente.ALTA_CLIENTE, transferCliente);
              Controller.getInstance().handleRequest(contexto);
            } else {
              JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            }
          } catch (final NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
              "Mensaje de error", JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    });
    panelBotones.add(altaBoton);

    final JButton bajasBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/cliente/bajaCliente.png").getPath(),
      new Color(0, 112, 192));
    bajasBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA CLIENTE",
          JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosCliente.BAJA_CLIENTE, id);
            Controller.getInstance().handleRequest(contexto);
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    panelBotones.add(bajasBoton);

    final JButton editarBoton =
      crearBoton(getClass().getClassLoader().getResource("iconos/cliente/MODCliente.png").getPath(),
        new Color(91, 155, 213));
    editarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        // La operacion de EDITAR se divide en dos partes:
        // 1. A partir del ID introducido, buscamos si existe o no el
        // Cliente. (Sigue la misma logica que MOSTRAR)
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BUSCAR CLIENTE",
          JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosCliente.BUSCAR_CLIENTE, id);
            Controller.getInstance().handleRequest(contexto);
          }
        } catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }

        // 2. Ya con la informacion cargada de Cliente (en caso de que
        // exista), permitimos al usuario modificar sus campos.
        if (clienteBuscar != null) {
          final JTextField nombreField = new JTextField(String.valueOf(clienteBuscar.getNombre()));
          final JTextField identificacionFiscalField =
            new JTextField(String.valueOf(clienteBuscar.getIdentificacionFiscal()));
          final JTextField direccionField =
            new JTextField(String.valueOf(clienteBuscar.getDireccion()));
          final JTextField emailField = new JTextField(String.valueOf(clienteBuscar.getEmail()));
          final JCheckBox activoField = new JCheckBox();
          activoField.setSelected(clienteBuscar.getActivo());
          final JTextField descuentoempField = new JTextField();
          final JTextField gastosEnvioField = new JTextField();
          if (clienteBuscar instanceof TransferClienteEmpresa) {
            gastosEnvioField.setEnabled(false);
            descuentoempField.setText(
              String.valueOf(((TransferClienteEmpresa) clienteBuscar).getDescuentoPorEmpresa()));
          } else {
            descuentoempField.setEnabled(false);
            gastosEnvioField
              .setText(String.valueOf(((TransferClienteNormal) clienteBuscar).getGastosDeEnvio()));
          }
          final Object[] mensaje =
            { "Nombre:", nombreField, "Identificación Fiscal:", identificacionFiscalField,
              "Dirección:", direccionField, "Email:", emailField, "Activo:", activoField,
              "Gastos de envio:", gastosEnvioField, "Descuento por empresa:", descuentoempField };
          final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR CLIENTE",
            JOptionPane.OK_CANCEL_OPTION);
          if (opcion == JOptionPane.OK_OPTION) {
            try {
              if (nombreField.getText() != null && identificacionFiscalField.getText() != null
                && direccionField.getText() != null && emailField.getText() != null
                && activoField.getText() != null && !nombreField.getText().equalsIgnoreCase("")
                && !identificacionFiscalField.getText().equalsIgnoreCase("")
                && !direccionField.getText().equalsIgnoreCase("")
                && !emailField.getText().equalsIgnoreCase("")) {

                TransferCliente transferCliente;

                if (clienteBuscar instanceof TransferClienteEmpresa) {
                  if (descuentoempField.getText() == null) {
                    throw new NumberFormatException();
                  }
                  transferCliente = new TransferClienteEmpresa();
                  ((TransferClienteEmpresa) transferCliente)
                    .setDescuentoPorEmpresa(Double.valueOf(descuentoempField.getText()));
                } else {
                  if (gastosEnvioField.getText() == null) {
                    throw new NumberFormatException();
                  }
                  transferCliente = new TransferClienteNormal();
                  ((TransferClienteNormal) transferCliente)
                    .setGastosDeEnvio(Double.valueOf(gastosEnvioField.getText()));
                }
                transferCliente.setID(clienteBuscar.getID());
                transferCliente.setNombre(nombreField.getText());
                transferCliente.setIdentificacionFiscal(identificacionFiscalField.getText());
                transferCliente.setDireccion(direccionField.getText());
                transferCliente.setEmail(emailField.getText());
                transferCliente.setActivo(activoField.isSelected());

                final Contexto contexto =
                  new Contexto(EventosCliente.MODIFICAR_CLIENTE, transferCliente);
                Controller.getInstance().handleRequest(contexto);
              }
            } catch (final NumberFormatException ex) {
              JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            }
          }
          clienteBuscar = null;
        }
      }
    });
    panelBotones.add(editarBoton);

    final JButton mostrarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/cliente/MOSTRARCliente.png").getPath(),
      new Color(112, 173, 71));
    mostrarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:",
          "MOSTRAR CLIENTE", JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosCliente.MOSTRAR_CLIENTE, id);
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
      getClass().getClassLoader().getResource("iconos/cliente/LISTARCliente.png").getPath(),
      new Color(255, 192, 0));
    listarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Contexto contexto = new Contexto(EventosCliente.LISTAR_CLIENTES, null);
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
package presentacion.vistas.departamento;

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

import negocio.departamento.TransferDepartamento;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosDepartamento;
import presentacion.eventos.EventosLibreria;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

public class VistaDepartamento extends JFrame implements VistaPrincipal {

  private static final long serialVersionUID = -4344608606248648803L;
  
  protected Object altaDepartamento;
  protected Object bajaDepartamento;
  protected Object modificarDepartamento;
  protected Object mostrarDepartamento;
  protected Object listarDepartamentos;
  protected Controller controller;
  private JTable jTable;
  private JLabel jLabel;
  private DefaultTableModel defaultTableModel;
  private TransferDepartamento buscarDepartamento;
  private boolean libreriaExistente;

  public VistaDepartamento() {
    vista();
  }

  @Override
  public void actualizar(final Contexto contexto) {
    limpiarJLabel();
    limpiarJTable();

    switch (contexto.getEvento()) {
    
      case EventosDepartamento.ALTA_DEPARTAMENTO_OK:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
        break;
      case EventosDepartamento.ALTA_DEPARTAMENTO_KO:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
        break;
      case EventosDepartamento.BAJA_DEPARTAMENTO_OK:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
        break;
      case EventosDepartamento.BAJA_DEPARTAMENTO_KO:
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
        break;
      case EventosDepartamento.MODIFICAR_DEPARTAMENTO_OK: {
        jLabel.setText((String) contexto.getDatos());
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(91, 186, 86));
      }
        ;
        break;
      case EventosDepartamento.MODIFICAR_DEPARTAMENTO_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosLibreria.BUSCAR_LIBRERIA_KO:{
      final String texto = (String) contexto.getDatos();
      jLabel.setText(texto);
      jLabel.setOpaque(true);
      jLabel.setBackground(new Color(218, 63, 54));
      this.libreriaExistente = false;
      
      } ;
      break;
      case EventosDepartamento.MOSTRAR_DEPARTAMENTO_OK: {
        final TransferDepartamento transferDepartamento =
          (TransferDepartamento) contexto.getDatos();
        defaultTableModel
          .addRow(new Object[] { transferDepartamento.getId(), transferDepartamento.getNombre(),
            transferDepartamento.getCantidadEmpleados(), transferDepartamento.getLibreria(), transferDepartamento.getActivo() });
      }
        ;
        break;
      case EventosDepartamento.MOSTRAR_DEPARTAMENTO_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosDepartamento.LISTAR_DEPARTAMENTOS_OK: {
        @SuppressWarnings("unchecked")
        final List<TransferDepartamento> lista = (List<TransferDepartamento>) contexto.getDatos();
        for (final TransferDepartamento transferDepartamento : lista) {
          defaultTableModel
            .addRow(new Object[] { transferDepartamento.getId(), transferDepartamento.getNombre(),
            transferDepartamento.getCantidadEmpleados(), transferDepartamento.getLibreria(), transferDepartamento.getActivo() });
        }
      }
        ;
        break;
      case EventosDepartamento.LISTAR_DEPARTAMENTOS_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
      case EventosDepartamento.BUSCAR_DEPARTAMENTO_OK: {
        buscarDepartamento = (TransferDepartamento) contexto.getDatos();
      }
        ;
        break;
      case EventosDepartamento.BUSCAR_DEPARTAMENTO_KO: {
        final String texto = (String) contexto.getDatos();
        jLabel.setText(texto);
        jLabel.setOpaque(true);
        jLabel.setBackground(new Color(218, 63, 54));
      }
        ;
        break;
    }
  }

  public JButton crearBoton(final String Path, final Color color) {
    final JButton boton = new JButton();
    boton.setPreferredSize(new Dimension(136, 61));
    boton.setBackground(color);
    boton.setBorder(null);
    boton.setFocusPainted(false);
    boton.setIcon(new ImageIcon(Path));

    return boton;
  }

  public JButton crearBotonMenu(final String nombre) {
    final JButton boton = new JButton(nombre);
    boton.setFocusPainted(false);
    boton.setBorderPainted(false);
    boton.setFont(new Font("Arial", Font.PLAIN, 18));
    boton.setBackground(new Color(255, 255, 255));
    boton.setMaximumSize(new Dimension(170, 50));
    return boton;
  }

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

  public void limpiarJLabel() {
    jLabel.setText(" ");
    jLabel.setOpaque(false);
  }

  public void limpiarJTable() {
    for (int i = defaultTableModel.getRowCount() - 1; i >= 0; i--) {
      defaultTableModel.removeRow(i);
    }
  }

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
    final JLabel modulo = new JLabel(" > DEPARTAMENTO");
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
    final String[] nombreColummnas = { "ID", "Nombre", "CantidadEmpleados", "Libreria", "Activo" };
    defaultTableModel = new DefaultTableModel(null, nombreColummnas);
    jTable = new JTable(defaultTableModel);
    jTable.setBackground(new Color(255,255,255));
    
    jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
    jTable.getTableHeader().setOpaque(false);
    jTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
    jTable.getTableHeader().setForeground(new Color(255, 255, 255));
    jTable.setPreferredSize(new Dimension(800, 400));
    
    final JScrollPane scroll = new JScrollPane(jTable);
    panelTabla.add(scroll);
    // -----------------------

    panelMensaje.add(jLabel);

    fondo.add(panelTabla, BorderLayout.CENTER);
    fondo.add(panelMensaje, BorderLayout.SOUTH);

    // Panel de botones.
    final JButton altaBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/departamento/ALTA_DEPARTAMENTO.png").getPath(),
      new Color(255, 255, 255));
    altaBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final JTextField nombre = new JTextField();
        final JTextField cantidadEmpleados = new JTextField();
        final JTextField libreria = new JTextField();
        final JCheckBox activoField = new JCheckBox();
        final Object[] mensaje = { "Nombre:", nombre, "CantidadEmpleados:", cantidadEmpleados,
          "Libreria:", libreria, "Activo:", activoField };
        final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA DEPARTAMENTO",
          JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
          try {
            if (nombre.getText() != null && cantidadEmpleados.getText() != null
              && libreria.getText() != null && activoField.getText() != null
              && !nombre.getText().equalsIgnoreCase("")
              && !cantidadEmpleados.getText().equalsIgnoreCase("")
              && !libreria.getText().equalsIgnoreCase("")) {

              TransferDepartamento transferDepartamento = new TransferDepartamento();

              transferDepartamento.setNombre(nombre.getText());
              transferDepartamento
                .setCantidadEmpleados(Integer.valueOf(cantidadEmpleados.getText()));
              transferDepartamento.setLibreria(Integer.valueOf(libreria.getText()));
              transferDepartamento.setActivo(activoField.isSelected());
      
              
              final Contexto contexto =
                new Contexto(EventosDepartamento.ALTA_DEPARTAMENTO, transferDepartamento);
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
      getClass().getClassLoader().getResource("iconos/departamento/BAJA_DEPARTAMENTO.png").getPath(),
      new Color(255, 255, 255));
    bajasBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:",
          "BAJA DEPARTAMENTO", JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosDepartamento.BAJA_DEPARTAMENTO, id);
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

      getClass().getClassLoader().getResource("iconos/departamento/MODIFICAR_DEPARTAMENTO.png").getPath(),new Color(255, 255, 255));
    
      editarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:",
          "BUSCAR DEPARTAMENTO", JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosDepartamento.BUSCAR_DEPARTAMENTO, id);

            Controller.getInstance().handleRequest(contexto);
          }
        } 
        catch (final NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
            JOptionPane.WARNING_MESSAGE);
        }
       

        
        if (buscarDepartamento != null) {

          final JTextField nombreField =
            new JTextField(String.valueOf(buscarDepartamento.getNombre()));
          final JTextField cantidadEmpleadosField =
            new JTextField(String.valueOf(buscarDepartamento.getCantidadEmpleados()));
           final JTextField libreriaField = new
           JTextField(String.valueOf(buscarDepartamento.getLibreria()));
          final JCheckBox activoField = new JCheckBox();
          activoField.setSelected(buscarDepartamento.getActivo());
          final Object[] mensaje = { "Nombre:", nombreField, "CantidadEmpleados:",
            cantidadEmpleadosField, "Libreria:", libreriaField, "Activo:", activoField };
          final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR DEPARTAMENTO",
            JOptionPane.OK_CANCEL_OPTION);
          if (opcion == JOptionPane.OK_OPTION) {
            try {
              if (nombreField.getText() != null && activoField.getText() != null) {

                final TransferDepartamento transferDepartamento = new TransferDepartamento();
                transferDepartamento.setId(buscarDepartamento.getId());
                transferDepartamento.setNombre(nombreField.getText());
                transferDepartamento
                  .setCantidadEmpleados(Integer.valueOf(cantidadEmpleadosField.getText()));
                 transferDepartamento.setLibreria(Integer.valueOf(libreriaField.getText()));
                transferDepartamento.setActivo(activoField.isSelected());
                
            libreriaExistente = true;
                    if (transferDepartamento.getLibreria() != null) {
                      final int id = transferDepartamento.getLibreria();
                      final Contexto contexte = new Contexto(EventosLibreria.BUSCAR_LIBRERIA, id);
                      Controller.getInstance().handleRequest(contexte);
                      if(contexte.getEvento() == EventosLibreria.BUSCAR_LIBRERIA_KO){
                    	  libreriaExistente = false;
                      }
                    }
               
                  
                  if( libreriaExistente ){ final Contexto contexto =
                  new Contexto(EventosDepartamento.MODIFICAR_DEPARTAMENTO, transferDepartamento);
                Controller.getInstance().handleRequest(contexto);
                  }
              }
              
            } catch (final NumberFormatException ex) {
              JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
            }
           
          }
          buscarDepartamento = null;
        }
      }
    });
    panelBotones.add(editarBoton);

    final JButton mostrarBoton = crearBoton(
      getClass().getClassLoader().getResource("iconos/departamento/MOSTRAR_DEPARTAMENTO.png").getPath(),
      new Color(255, 255, 255));
    mostrarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final String idString = JOptionPane.showInputDialog(null, "Introduce ID:",
          "MOSTRAR DEPARTAMENTO", JOptionPane.QUESTION_MESSAGE);
        try {
          if (idString != null) {
            final int id = Integer.parseInt(idString);
            final Contexto contexto = new Contexto(EventosDepartamento.MOSTRAR_DEPARTAMENTO, id);
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
      getClass().getClassLoader().getResource("iconos/departamento/LISTAR_DEPARTAMENTOS.png").getPath(),
      new Color(255, 255, 255));
    listarBoton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        final Contexto contexto = new Contexto(EventosDepartamento.LISTAR_DEPARTAMENTOS, null);
        Controller.getInstance().handleRequest(contexto);
      }
    });
    panelBotones.add(listarBoton);
    
    final JButton asignarMaterialDeOficina = crearBoton(
    	      getClass().getClassLoader().getResource("iconos/departamento/ASIGNAR_MATERIAL_OFICINA.png").getPath(),
    	      new Color(255, 255, 255));
    	    listarBoton.addActionListener(new ActionListener() {
    	      @Override
    	      public void actionPerformed(final ActionEvent e) {
    	        final Contexto contexto = new Contexto(EventosDepartamento.ASIGNAR_MATERIAL_OFICINA, null);
    	        Controller.getInstance().handleRequest(contexto);
    	      }
    	    });
    	    panelBotones.add(asignarMaterialDeOficina);


    // Aniadimos todos los paneles al JFrame.
    add(barra, BorderLayout.NORTH);
    add(fondo, BorderLayout.CENTER);
    add(panelBotones, BorderLayout.SOUTH);
    setVisible(true);
  }

}
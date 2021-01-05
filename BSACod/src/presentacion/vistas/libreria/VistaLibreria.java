package presentacion.vistas.libreria;

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

import negocio.libreria.EntityLibreria;
import negocio.libreria.TransferLibreria;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosLibreria;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

public class VistaLibreria extends JFrame implements VistaPrincipal {

	private static final long serialVersionUID = 1564831542157447L;
	protected JButton altaLibreria;
	protected JButton bajaLibreria;
	protected JButton modificarLibreria;
	protected JButton mostrarLibreria;
	protected JButton listarLibrerias;
	private TransferLibreria buscarLibreria;
	private DefaultTableModel defaultTableModel;
	private JLabel jLabel;
	private JTable jTable;

	public VistaLibreria() {
		initGUI();
	}

	public void actualizar(final Contexto contexto) {
		limpiarJLabel();
		limpiarTabla();

		switch (contexto.getEvento()) {
		case EventosLibreria.ALTA_LIBRERIA_OK:
			jLabel.setText((String) contexto.getDatos());
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(91, 186, 86));
			break;
		case EventosLibreria.ALTA_LIBRERIA_KO:
			jLabel.setText((String) contexto.getDatos());
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(218, 63, 54));
			break;
		case EventosLibreria.BAJA_LIBRERIA_OK:
			jLabel.setText((String) contexto.getDatos());
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(91, 186, 86));
			break;
		case EventosLibreria.BAJA_LIBRERIA_KO:
			jLabel.setText((String) contexto.getDatos());
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(218, 63, 54));
			break;
		case EventosLibreria.MODIFICAR_LIBRERIA_OK: {
			jLabel.setText((String) contexto.getDatos());
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(91, 186, 86));
		}
			;
			break;
		case EventosLibreria.MODIFICAR_LIBRERIA_KO: {
			final String texto = (String) contexto.getDatos();
			jLabel.setText(texto);
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(218, 63, 54));
		}
			;
			break;
		case EventosLibreria.MOSTRAR_LIBRERIA_OK: {
			final TransferLibreria libreria = (TransferLibreria) contexto.getDatos();
			defaultTableModel.addRow(new Object[] { libreria.getId(), libreria.getNombre(), libreria.getDireccion(),
					libreria.getActivo() });
		}
			;
			break;
		case EventosLibreria.MOSTRAR_LIBRERIA_KO: {
			final String texto = (String) contexto.getDatos();
			jLabel.setText(texto);
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(218, 63, 54));
		}
			;
			break;
		case EventosLibreria.LISTAR_LIBRERIAS_OK: {
			@SuppressWarnings("unchecked")
			final List<TransferLibreria> lista = (List<TransferLibreria>) contexto.getDatos();
			for (final TransferLibreria libreria : lista) {
				defaultTableModel.addRow(new Object[] { libreria.getId(), libreria.getNombre(), libreria.getDireccion(),
						libreria.getActivo() });
			}
		}
			;
			break;
		case EventosLibreria.LISTAR_LIBRERIAS_KO: {
			final String texto = (String) contexto.getDatos();
			jLabel.setText(texto);
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(218, 63, 54));
		}
			;
			break;
		case EventosLibreria.BUSCAR_LIBRERIA_OK: {
			buscarLibreria = (TransferLibreria) contexto.getDatos();
		}
			;
			break;
		case EventosLibreria.BUSCAR_LIBRERIA_KO: {
			final String texto = (String) contexto.getDatos();
			jLabel.setText(texto);
			jLabel.setOpaque(true);
			jLabel.setBackground(new Color(218, 63, 54));
		}
			;
			break;
		}
	}

	public JButton crearBoton(String path, Color color) {
		final JButton boton = new JButton();
		boton.setPreferredSize(new Dimension(135, 60));
		boton.setBackground(color);
		boton.setBorder(null);
		boton.setFocusPainted(false);
		boton.setIcon(new ImageIcon(path));

		return boton;
	}

	public JButton crearBotonMenu(String nombre) {
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
				final int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el programa?", "Salir",
						JOptionPane.YES_NO_OPTION);

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

	public void limpiarTabla() {
		for (int i = defaultTableModel.getRowCount() - 1; i >= 0; i--) {
			defaultTableModel.removeRow(i);
		}
	}

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

		jLabel = new JLabel(" ");
		jLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		jLabel.setForeground(new Color(255, 255, 255));
		final JLabel modulo = new JLabel(" > LIBRERIA");
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
		final String[] nombreColummnas = { "ID", "Nombre", "Direccion", "Activo" };
		defaultTableModel = new DefaultTableModel(null, nombreColummnas);
		jTable = new JTable(defaultTableModel);
		jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
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
				getClass().getClassLoader().getResource("iconos/libreria/ALTALIBRERIA.png").getPath(),
				new Color(243, 89, 63));
		altaBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JTextField NombreField = new JTextField();
				final JTextField DireccionField = new JTextField();
				final JCheckBox activoField = new JCheckBox();

				final Object[] mensaje = { "Nombre:", NombreField, "Direccion:", DireccionField, "Activo:",
						activoField };
				final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA LIBRERIA",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {
					try {
						if (NombreField.getText() != null && DireccionField.getText() != null
								&& !NombreField.getText().equalsIgnoreCase("")
								&& !DireccionField.getText().equalsIgnoreCase("")) {
							TransferLibreria tLibreria = new TransferLibreria();
							tLibreria.setNombre(NombreField.getText());
							tLibreria.setDireccion(DireccionField.getText());
							tLibreria.setActivo(activoField.isSelected());
							final Contexto contexto = new Contexto(EventosLibreria.ALTA_LIBRERIA, tLibreria);
							Controller.getInstance().handleRequest(contexto);
						} else {
							JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error",
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (final NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panelBotones.add(altaBoton);

		final JButton bajasBoton = crearBoton(
				getClass().getClassLoader().getResource("iconos/libreria/BAJALIBRERIA.png").getPath(),
				new Color(0, 112, 192));
		bajasBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA LIBRERIA",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (idString != null) {
						final int id = Integer.parseInt(idString);
						final Contexto contexto = new Contexto(EventosLibreria.BAJA_LIBRERIA, id);
						Controller.getInstance().handleRequest(contexto);
					}
				} catch (final NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotones.add(bajasBoton);

		final JButton modificarBoton = crearBoton(
				getClass().getClassLoader().getResource("iconos/libreria/MODIFICARLIBRERIA.png").getPath(),
				new Color(243, 89, 63));
		modificarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BUSCAR LIBRERIA",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (idString != null) {
						final int id = Integer.parseInt(idString);
						final Contexto contexto = new Contexto(EventosLibreria.BUSCAR_LIBRERIA, id);

						Controller.getInstance().handleRequest(contexto);
					}
				} catch (final NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
							JOptionPane.WARNING_MESSAGE);
				}
				if (buscarLibreria != null) {

					final JTextField NombreField = new JTextField(buscarLibreria.getNombre());
					final JTextField DireccionField = new JTextField(buscarLibreria.getDireccion());
					final JCheckBox activoField = new JCheckBox();
					activoField.setSelected(buscarLibreria.getActivo());

					final Object[] mensaje = { "Nombre:", NombreField, "Direccion:", DireccionField, "Activo:",
							activoField };
					final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR LIBRERIA",
							JOptionPane.OK_CANCEL_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
						try {
							if (NombreField.getText() != null && DireccionField.getText() != null
									&& !NombreField.getText().trim().equalsIgnoreCase("")
									&& !DireccionField.getText().trim().equalsIgnoreCase("")) {
								TransferLibreria tLibreria = new TransferLibreria();
								tLibreria.setId(buscarLibreria.getId());
								tLibreria.setNombre(NombreField.getText().trim());
								tLibreria.setDireccion(DireccionField.getText().trim());
								tLibreria.setActivo(activoField.isSelected());
								final Contexto contexto = new Contexto(EventosLibreria.MODIFICAR_LIBRERIA, tLibreria);
								Controller.getInstance().handleRequest(contexto);
							} else {
								JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
										"Mensaje de error", JOptionPane.WARNING_MESSAGE);
							}
						} catch (final NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					buscarLibreria = null;
				}
			}
		});
		panelBotones.add(modificarBoton);

		final JButton mostrarBoton = crearBoton(
				getClass().getClassLoader().getResource("iconos/libreria/MOSTRARLIBRERIA.png").getPath(),
				new Color(112, 173, 71));
		mostrarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MOSTRAR LIBRERIA",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (idString != null) {
						final int id = Integer.parseInt(idString);
						final Contexto contexto = new Contexto(EventosLibreria.MOSTRAR_LIBRERIA, id);
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
				getClass().getClassLoader().getResource("iconos/libreria/LISTARLIBRERIA.png").getPath(),
				new Color(255, 192, 0));
		listarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final Contexto contexto = new Contexto(EventosLibreria.LISTAR_LIBRERIAS, null);
				Controller.getInstance().handleRequest(contexto);
			}
		});
		panelBotones.add(listarBoton);

		// -----------------------------------------
		// Anadimos todos los paneles al JFrame.
		add(barra, BorderLayout.NORTH);
		add(fondo, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		setVisible(true);
	}
}
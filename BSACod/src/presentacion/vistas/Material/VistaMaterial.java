package presentacion.vistas.Material;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import negocio.material.TransferMaterial;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;
import presentacion.eventos.EventosMaterial;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

public class VistaMaterial extends JFrame implements VistaPrincipal {

	private static final long serialVersionUID = 4566249997952290538L;
	protected JButton altaMaterial;
	protected JButton bajaMaterial;
	protected JButton modificarMaterial;
	protected JButton mostrarMaterial;
	protected Controller controller;
	protected JButton listarMaterial;
	private TransferMaterial buscarMaterial;
	private DefaultTableModel defaultTableModel;
	private JLabel jLabel;
	private JTable jTable;

	public VistaMaterial() {
		initGui();
	}

	public void actualizar(final Contexto contexto) {
		limpiarJLabel();
		limpiarTabla();

		switch (contexto.getEvento()) {
			case EventosMaterial.ALTA_MATERIAL_OK:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(91, 186, 86));
				break;
			case EventosMaterial.ALTA_MATERIAL_KO:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
				break;
			case EventosMaterial.BAJA_MATERIAL_OK:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(91, 186, 86));
				break;
			case EventosMaterial.BAJA_MATERIAL_KO:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
				break;
			case EventosMaterial.MODIFICAR_MATERIAL_OK: {
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(91, 186, 86));
			}
				;
				break;
			case EventosMaterial.MODIFICAR_MATERIAL_KO: {
				final String texto = (String) contexto.getDatos();
				jLabel.setText(texto);
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
			}
				;
				break;
			case EventosMaterial.MOSTRAR_MATERIAL_OK: {
				final TransferMaterial material = (TransferMaterial) contexto.getDatos();
				defaultTableModel.addRow(new Object[] { material.getId(), material.getTipo(), material.getActivo() });
			}
				;
				break;
			case EventosMaterial.MOSTRAR_MATERIAL_KO: {
				final String texto = (String) contexto.getDatos();
				jLabel.setText(texto);
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
			}
				;
				break;
			case EventosMaterial.LISTAR_MATERIALES_OK: {
				@SuppressWarnings("unchecked")
				final List<TransferMaterial> lista = (List<TransferMaterial>) contexto.getDatos();
				for (final TransferMaterial material : lista) {
					defaultTableModel
							.addRow(new Object[] { material.getId(), material.getTipo(), material.getActivo() });
				}
			}
				;
				break;
			case EventosMaterial.LISTAR_MATERIALES_KO: {
				final String texto = (String) contexto.getDatos();
				jLabel.setText(texto);
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
			}
				;
				break;
			case EventosMaterial.BUSCAR_MATERIAL_OK: {
				buscarMaterial = (TransferMaterial) contexto.getDatos();
			}
				;
				break;
			case EventosMaterial.BUSCAR_MATERIAL_KO: {
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
		boton.setPreferredSize(new Dimension(135, 60));
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
				final int confirmacion = JOptionPane.showConfirmDialog(null, "Â¿Desea cerrar el programa?", "Salir",
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

	private void initGui() {
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
		final JLabel modulo = new JLabel(" > MATERIAL");
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
		final String[] nombreColummnas = { "ID", "Tipo", "Activo" };
		defaultTableModel = new DefaultTableModel(null, nombreColummnas);
		jTable = new JTable(defaultTableModel);
		jTable.setPreferredSize(new Dimension(800, 400));
		jTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,14));
		jTable.getTableHeader().setOpaque(false);
		jTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
		jTable.getTableHeader().setForeground(new Color(255, 255, 255));
		final JScrollPane scroll = new JScrollPane(jTable);
		panelTabla.add(scroll);
		// -----------------------

		panelMensaje.add(jLabel);

		fondo.add(panelTabla, BorderLayout.CENTER);
		fondo.add(panelMensaje, BorderLayout.SOUTH);

		// Panel de botones.
		final JButton altaBoton = crearBoton(
				getClass().getClassLoader().getResource("iconos/material/ALTAMATERIAL.png").getPath(),
				new Color(243, 89, 63));
		altaBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final JTextField tipoField = new JTextField();
				final JCheckBox activoField = new JCheckBox();
				final Object[] mensaje = { "Tipo:", tipoField, "Activo:", activoField, };
				final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA MATERIAL",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.OK_OPTION) {
					try {
						if (tipoField.getText() != null && activoField.getText() != null
								&& !tipoField.getText().equalsIgnoreCase("")) {
							final TransferMaterial material = new TransferMaterial();
							material.setTipo(tipoField.getText());
							material.setActivo(activoField.isSelected());
							final Contexto contexto = new Contexto(EventosMaterial.ALTA_MATERIAL, material);
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
				getClass().getClassLoader().getResource("iconos/material/BAJAMATERIAL.png").getPath(),
				new Color(0, 112, 192));
		bajasBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA MATERIAL",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (idString != null) {
						final int id = Integer.parseInt(idString);
						final Contexto contexto = new Contexto(EventosMaterial.BAJA_MATERIAL, id);
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

				getClass().getClassLoader().getResource("iconos/material/MODMATERIAL.png").getPath(),
				new Color(91, 155, 213));
		editarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BUSCAR MATERIAL",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (idString != null) {
						final int id = Integer.parseInt(idString);
						final Contexto contexto = new Contexto(EventosMaterial.BUSCAR_MATERIAL, id);

						Controller.getInstance().handleRequest(contexto);
					}
				} catch (final NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
							JOptionPane.WARNING_MESSAGE);
				}

				if (buscarMaterial != null) {

					final JTextField tipoField = new JTextField(String.valueOf(buscarMaterial.getTipo()));
					final JCheckBox activoField = new JCheckBox();
					activoField.setSelected(buscarMaterial.getActivo());
					final Object[] mensaje = { "Tipo:", tipoField, "Activo:", activoField, };
					final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR MATERIAL",
							JOptionPane.OK_CANCEL_OPTION);
					if (opcion == JOptionPane.OK_OPTION) {
						try {
							if (tipoField.getText() != null && activoField.getText() != null) {
								final TransferMaterial material = new TransferMaterial();
								material.setId(buscarMaterial.getId());
								material.setTipo(tipoField.getText());
								material.setActivo(activoField.isSelected());
								final Contexto contexto = new Contexto(EventosMaterial.MODIFICAR_MATERIAL, material);
								Controller.getInstance().handleRequest(contexto);
							}
						} catch (final NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.", "Mensaje de error",
									JOptionPane.WARNING_MESSAGE);
						}
					}
					buscarMaterial = null;
				}
			}
		});
		panelBotones.add(editarBoton);

		final JButton mostrarBoton = crearBoton(
				getClass().getClassLoader().getResource("iconos/material/MOSTRARMATERIAL.png").getPath(),
				new Color(112, 173, 71));
		mostrarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MOSTRAR MATERIAL",
						JOptionPane.QUESTION_MESSAGE);
				try {
					if (idString != null) {
						final int id = Integer.parseInt(idString);
						final Contexto contexto = new Contexto(EventosMaterial.MOSTRAR_MATERIAL, id);
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
				getClass().getClassLoader().getResource("iconos/material/LISTARMATERIAL.png").getPath(),
				new Color(255, 192, 0));
		listarBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final Contexto contexto = new Contexto(EventosMaterial.LISTAR_MATERIALES, null);
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
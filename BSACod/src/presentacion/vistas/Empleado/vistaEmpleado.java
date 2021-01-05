/**
 * 
 */
package presentacion.vistas.Empleado;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


import negocio.departamento.EntityDepartamento;
import negocio.empleado.EntityEmpleado;
import negocio.empleado.EntityEmpleadoTiempoCompleto;
import negocio.empleado.EntityEmpleadoTiempoParcial;
import negocio.empleado.TransferEmpleado;
import negocio.empleado.TransferEmpleadoCompleto;
import negocio.empleado.TransferEmpleadoParcial;
import presentacion.contexto.Contexto;
import presentacion.controlador.appController.Controller;

import presentacion.eventos.EventosEmpleado;
import presentacion.eventos.EventosMenu;
import presentacion.vistas.gui.VistaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VistaEmpleado extends JFrame implements VistaPrincipal {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JButton altaEmpleado;
	protected JButton bajaEmpleado;
	protected JButton modificarEmpleado;
	protected JButton mostrarEmpleado;
	protected JButton listarEmpleado;
	private TransferEmpleado buscarEmpleado;
	protected JButton totalSalariosDep;
	private DefaultTableModel defaultTableModel;
	protected Controller controller;
	private JLabel jLabel;
	private JTable jTable;

	
	public VistaEmpleado() {
		initGUI();
	}

	public void actualizar(final Contexto contexto) {
		limpiarJLabel();
		limpiarTabla();

		switch (contexto.getEvento()) {
			case EventosEmpleado.ALTA_EMPLEADO_OK:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(91, 186, 86));
				break;
			case EventosEmpleado.ALTA_EMPLEADO_KO:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
				break;
			case EventosEmpleado.BAJA_EMPLEADO_OK:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(91, 186, 86));
				break;
			case EventosEmpleado.BAJA_EMPLEADO_KO:
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_OK: {
				jLabel.setText((String) contexto.getDatos());
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(91, 186, 86));
			}
				;
				break;
			case EventosEmpleado.MODIFICAR_EMPLEADO_KO: {
				final String texto = (String) contexto.getDatos();
				jLabel.setText(texto);
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
			}
				;
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_OK: {
				final EntityEmpleado empleado = (EntityEmpleado) contexto.getDatos();
				if(empleado instanceof EntityEmpleadoTiempoCompleto){
					defaultTableModel
							.addRow(new Object[] { empleado.getId(), empleado.getDNI(), empleado.getNombre(), empleado.getCorreo(),
									empleado.getCuentaBancaria(), empleado.getDepartamento(), empleado.getActivo(),((EntityEmpleadoTiempoCompleto) empleado).getSalarioBase(),((EntityEmpleadoTiempoCompleto) empleado).getPagaExtra(),null,null,null});
				}
					else{
						
							defaultTableModel
									.addRow(new Object[] { empleado.getId(), empleado.getDNI(), empleado.getNombre(), empleado.getCorreo(),
											empleado.getCuentaBancaria(), empleado.getDepartamento(), empleado.getActivo(),null,null,((EntityEmpleadoTiempoParcial) empleado).getTiempoContrato(),((EntityEmpleadoTiempoParcial) empleado).getHorasMensuales(),((EntityEmpleadoTiempoParcial) empleado).getSalarioPorHoras()});
					
					
				}
			}
				;
				break;
			case EventosEmpleado.MOSTRAR_EMPLEADO_KO: {
				final String texto = (String) contexto.getDatos();
				jLabel.setText(texto);
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
			}
				;
				break;
			case EventosEmpleado.LISTAR_EMPLEADOS_OK: {
				@SuppressWarnings("unchecked")
				final List<EntityEmpleado> lista = (List<EntityEmpleado>) contexto.getDatos();
				
				for (final EntityEmpleado empleado : lista) {
					if(empleado instanceof EntityEmpleadoTiempoCompleto){
					defaultTableModel
							.addRow(new Object[] { empleado.getId(), empleado.getDNI(), empleado.getNombre(), empleado.getCorreo(),
									empleado.getCuentaBancaria(), empleado.getDepartamento(), empleado.getActivo(),((EntityEmpleadoTiempoCompleto) empleado).getSalarioBase(),((EntityEmpleadoTiempoCompleto) empleado).getPagaExtra(),null,null,null});
				}
					else{
						
							defaultTableModel
									.addRow(new Object[] { empleado.getId(), empleado.getDNI(), empleado.getNombre(), empleado.getCorreo(),
											empleado.getCuentaBancaria(), empleado.getDepartamento(), empleado.getActivo(),null,null,((EntityEmpleadoTiempoParcial) empleado).getTiempoContrato(),((EntityEmpleadoTiempoParcial) empleado).getHorasMensuales(),((EntityEmpleadoTiempoParcial) empleado).getSalarioPorHoras()});
					
					
				}
			}
			}
				;
				break;
			case EventosEmpleado.LISTAR_EMPLEADOS_KO: {
				final String texto = (String) contexto.getDatos();
				jLabel.setText(texto);
				jLabel.setOpaque(true);
				jLabel.setBackground(new Color(218, 63, 54));
			}
				;
				break;
			case EventosEmpleado.BUSCAR_EMPLEADO_OK: {
				buscarEmpleado = (TransferEmpleado) contexto.getDatos();
			}
				;
				break;
			case EventosEmpleado.BUSCAR_EMPLEADO_KO: {
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
		final JLabel modulo = new JLabel(" > EMPLEADO");
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
				final String[] nombreColummnas = { "ID", "Nombre", "DNI", "CuentaBancaria", "Correo", "Departamento", "Activo","Salario Base","Paga extra","Tiempo Contrato","Horas mensuales","Salario por horas"};
				defaultTableModel = new DefaultTableModel(null, nombreColummnas);
				jTable = new JTable(defaultTableModel);
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
						getClass().getClassLoader().getResource("iconos/empleado/ALTAEMPLEADO.png").getPath(),
						new Color(243, 89, 63));
				altaBoton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						 final String[] lista = new String[2];
					        lista[0] = "Tiempo Completo";
					        lista[1] = "Tiempo Parcial";
					        final JComboBox<String> comboTipo = new JComboBox<>(lista);
						
						//Atributos comunes empleado
						final JTextField DNIField = new JTextField();
						final JTextField NombreField = new JTextField();
						final JTextField CuentaField = new JTextField();
						final JTextField CorreoField = new JTextField();
						final JTextField DepField = new JTextField();
						final JCheckBox activoField = new JCheckBox();
						//Atributos tiempo parcial
						final JTextField TiempoContratoField = new JTextField();
						final JTextField HorasMensualesField = new JTextField();
						final JTextField SalarioPorHorasField = new JTextField();
						//Atributos tiempo completo
						final JTextField  SalarioBaseField = new JTextField();
						final JTextField PagaExtraField = new JTextField();
						
						final Object[] mensaje = { "Tipo:", comboTipo, "Nombre:", NombreField, "DNI:", DNIField, "Cuenta Bancaria:", CuentaField, 
								"Correo:", CorreoField, "Departamento:", DepField, "Activo:", activoField, "Tiempo Contrato:", TiempoContratoField, 
								"Horas Mensuales:", HorasMensualesField, "Salario Por Horas:", SalarioPorHorasField,
								"Salario Base:", SalarioBaseField, "Paga Extra:", PagaExtraField, };
						
						 comboTipo.addActionListener(new ActionListener() {
					          @Override
					          public void actionPerformed(final ActionEvent e) {
					            final String tipo = (String) comboTipo.getSelectedItem();
					            if ("Tiempo Completo".equals(tipo)) {
					            	SalarioBaseField.setEnabled(true);
					            	PagaExtraField.setEnabled(true);
					            	TiempoContratoField.setEnabled(false);
					            	HorasMensualesField.setEnabled(false);
					            	SalarioPorHorasField.setEnabled(false);
					            } else {
					            	SalarioBaseField.setEnabled(false);
					            	PagaExtraField.setEnabled(false);
					            	TiempoContratoField.setEnabled(true);
					            	HorasMensualesField.setEnabled(true);
					            	SalarioPorHorasField.setEnabled(true);
					            }

					          }
					        });
						
						final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "ALTA EMPLEADO",
								JOptionPane.OK_CANCEL_OPTION);
						if (opcion == JOptionPane.OK_OPTION) {
							try {
								if (DNIField.getText() != null && NombreField.getText() != null &&  activoField.getText() != null &&
										CuentaField.getText() != null && CorreoField.getText() != null && DepField.getText() != null &&
										!DNIField.getText().equalsIgnoreCase("") && !NombreField.getText().equalsIgnoreCase("") && 
										!CuentaField.getText().equalsIgnoreCase("") && !CorreoField.getText().equalsIgnoreCase("") && 
										!DepField.getText().equalsIgnoreCase("")) {
										
										TransferEmpleado tEmpleado;
						              if ("Tiempo Completo".equals(comboTipo.getSelectedItem())) {
						                if (SalarioBaseField.getText() == null || PagaExtraField.getText() == null) {
						                  throw new NumberFormatException();
						                }
						                else{
						                	tEmpleado = new TransferEmpleadoCompleto();
						                    ((TransferEmpleadoCompleto) tEmpleado)
						                      .setSalarioBase(Double.valueOf(SalarioBaseField.getText()));
						                    ((TransferEmpleadoCompleto) tEmpleado)
						                      .setPagaExtra(Double.valueOf(PagaExtraField.getText()));
						                }
						                
						              
						 
						              } else {
						                if (TiempoContratoField.getText() == null||HorasMensualesField.getText() == null||SalarioPorHorasField.getText() == null) {
						                  throw new NumberFormatException();
						                }
						                tEmpleado = new TransferEmpleadoParcial();
					                    ((TransferEmpleadoParcial) tEmpleado)
					                      .setTiempoContrato(Integer.valueOf(TiempoContratoField.getText()));
					                    ((TransferEmpleadoParcial) tEmpleado)
					                      .setHorasMensuales(Integer.valueOf(HorasMensualesField.getText()));
					                    ((TransferEmpleadoParcial) tEmpleado)
					                      .setSalarioPorHoras(Double.valueOf(SalarioPorHorasField.getText()));
						              }
									
								
									
									tEmpleado.setDNI(DNIField.getText());
									tEmpleado.setNombre(NombreField.getText());
									tEmpleado.setCuentaBancaria(CuentaField.getText());
									tEmpleado.setCorreo(CorreoField.getText());
									tEmpleado.setActivo(activoField.isSelected());
									
									Integer idDep = Integer.parseInt(DepField.getText());
									EntityDepartamento departamento = new EntityDepartamento(idDep);
									tEmpleado.setDepartamento(departamento);
								
									
									final Contexto contexto = new Contexto(EventosEmpleado.ALTA_EMPLEADO, tEmpleado);
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
						getClass().getClassLoader().getResource("iconos/empleado/BAJAEMPLEADO.png").getPath(),
						new Color(0, 112, 192));
				bajasBoton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BAJA EMPLEADO",
								JOptionPane.QUESTION_MESSAGE);
						try {
							if (idString != null) {
								final int id = Integer.parseInt(idString);
								final Contexto contexto = new Contexto(EventosEmpleado.BAJA_EMPLEADO, id);
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

						getClass().getClassLoader().getResource("iconos/empleado/MODIFICAREMPLEADO.png").getPath(),
						new Color(91, 155, 213));
				editarBoton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "BUSCAR EMPLEADO",
								JOptionPane.QUESTION_MESSAGE);
						try {
							if (idString != null) {
								final int id = Integer.parseInt(idString);
								final Contexto contexto = new Contexto(EventosEmpleado.BUSCAR_EMPLEADO, id);

								Controller.getInstance().handleRequest(contexto);
							}
						} catch (final NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
									JOptionPane.WARNING_MESSAGE);
						}

						if (buscarEmpleado != null) {

							 final JTextField NombreField = new JTextField(String.valueOf(buscarEmpleado.getNombre()));
					          final JTextField DNIField =
					            new JTextField(String.valueOf(buscarEmpleado.getDNI()));
					          final JTextField CorreoField =
					            new JTextField(String.valueOf(buscarEmpleado.getCorreo()));
					          final JTextField CuentaField =
							            new JTextField(String.valueOf(buscarEmpleado.getCuentaBancaria()));
					          final JTextField DepField =
							            new JTextField(String.valueOf(buscarEmpleado.getDepartamento()));
					        
					          final JCheckBox activoField = new JCheckBox();
					          activoField.setSelected(buscarEmpleado.getActivo());
					      
								final JTextField TiempoContratoField = new JTextField();
								final JTextField HorasMensualesField = new JTextField();
								final JTextField SalarioPorHorasField = new JTextField();
								
								final JTextField  SalarioBaseField = new JTextField();
								final JTextField PagaExtraField = new JTextField();
					          
					          if (buscarEmpleado instanceof TransferEmpleadoCompleto) {
					            	TiempoContratoField.setEnabled(false);
					            	HorasMensualesField.setEnabled(false);
					            	SalarioPorHorasField.setEnabled(false);
					            	SalarioBaseField.setText(
					            			String.valueOf(((TransferEmpleadoCompleto) buscarEmpleado).getSalarioBase()));
					            	PagaExtraField.setText(
								              String.valueOf(((TransferEmpleadoCompleto) buscarEmpleado).getPagaExtra()));
					            	
					          } else {
					        	  SalarioBaseField.setEnabled(false);
					              PagaExtraField.setEnabled(false);
					              TiempoContratoField
					              .setText(String.valueOf(((TransferEmpleadoParcial) buscarEmpleado).getTiempoContrato()));
					              HorasMensualesField
					              .setText(String.valueOf(((TransferEmpleadoParcial) buscarEmpleado).getHorasMensuales()));
					              SalarioPorHorasField
					              .setText(String.valueOf(((TransferEmpleadoParcial) buscarEmpleado).getSalarioPorHoras()));
					          }
					          final Object[] mensaje = {"Nombre:", NombreField, "DNI:", DNIField, "Cuenta Bancaria:", CuentaField, 
										"Correo:", CorreoField, "Departamento:", DepField, "Activo:", activoField, "Tiempo Contrato:", TiempoContratoField, 
										"Horas Mensuales:", HorasMensualesField, "Salario Por Horas:", SalarioPorHorasField,
										"Salario Base:", SalarioBaseField, "Paga Extra:", PagaExtraField, };
					          final int opcion = JOptionPane.showConfirmDialog(null, mensaje, "MODIFICAR EMPLEADO",
					            JOptionPane.OK_CANCEL_OPTION);
					          if (opcion == JOptionPane.OK_OPTION) {
					            try {
					              if (NombreField.getText() != null && DNIField.getText() != null
					                && CuentaField.getText() != null && CorreoField.getText() != null
					                && activoField.getText() != null && DepField.getText() != null&&!NombreField.getText().equalsIgnoreCase("")
					                && !DNIField.getText().equalsIgnoreCase("")
					                && !CuentaField.getText().equalsIgnoreCase("")
					                && !CorreoField.getText().equalsIgnoreCase("")&&!DepField.getText().equalsIgnoreCase("")) {

					                TransferEmpleado tEmpleado;

					                if (buscarEmpleado instanceof TransferEmpleadoCompleto) {
					                	if (SalarioBaseField.getText() == null || PagaExtraField.getText() == null) {
							                  throw new NumberFormatException();
							                }
							                else{
							                	tEmpleado = new TransferEmpleadoCompleto();
							                    ((TransferEmpleadoCompleto) tEmpleado)
							                      .setSalarioBase(Double.valueOf(SalarioBaseField.getText()));
							                    ((TransferEmpleadoCompleto) tEmpleado)
							                      .setPagaExtra(Double.valueOf(PagaExtraField.getText()));
							                }
					                } else {
					                	 if (TiempoContratoField.getText() == null||HorasMensualesField.getText() == null||SalarioPorHorasField.getText() == null) {
							                  throw new NumberFormatException();
							                }
							                tEmpleado = new TransferEmpleadoParcial();
						                    ((TransferEmpleadoParcial) tEmpleado)
						                      .setTiempoContrato(Integer.valueOf(TiempoContratoField.getText()));
						                    ((TransferEmpleadoParcial) tEmpleado)
						                      .setHorasMensuales(Integer.valueOf(HorasMensualesField.getText()));
						                    ((TransferEmpleadoParcial) tEmpleado)
						                      .setSalarioPorHoras(Double.valueOf(SalarioPorHorasField.getText()));
					                }
					                tEmpleado.setId(buscarEmpleado.getId());
					                tEmpleado.setNombre(NombreField.getText());
					                tEmpleado.setDNI(DNIField.getText());
					                tEmpleado.setCorreo(CorreoField.getText());
					                tEmpleado.setCuentaBancaria(CuentaField.getText());
					                Integer idDep = Integer.parseInt(DepField.getText());
									EntityDepartamento departamento = new EntityDepartamento(idDep);
									tEmpleado.setDepartamento(departamento);
					                tEmpleado.setActivo(activoField.isSelected());

					                final Contexto contexto =
					                  new Contexto(EventosEmpleado.MODIFICAR_EMPLEADO, tEmpleado);
					                Controller.getInstance().handleRequest(contexto);
					              }
					            } catch (final NumberFormatException ex) {
					              JOptionPane.showMessageDialog(null, "Datos introducidos incorrectos.",
					                "Mensaje de error", JOptionPane.WARNING_MESSAGE);
					            }
					          }
					          buscarEmpleado = null;
						}
					}
				});
				panelBotones.add(editarBoton);

				final JButton mostrarBoton = crearBoton(
						getClass().getClassLoader().getResource("iconos/empleado/MOSTRAREMPLEADO.png").getPath(),
						new Color(112, 173, 71));
				mostrarBoton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						final String idString = JOptionPane.showInputDialog(null, "Introduce ID:", "MOSTRAR EMPLEADO",
								JOptionPane.QUESTION_MESSAGE);
						try {
							if (idString != null) {
								final int id = Integer.parseInt(idString);
								final Contexto contexto = new Contexto(EventosEmpleado.MOSTRAR_EMPLEADO, id);
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
						getClass().getClassLoader().getResource("iconos/empleado/LISTAREMPLEADO.png").getPath(),
						new Color(255, 192, 0));
				listarBoton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(final ActionEvent e) {
						final Contexto contexto = new Contexto(EventosEmpleado.LISTAR_EMPLEADOS, null);
						Controller.getInstance().handleRequest(contexto);
					}
				});
				panelBotones.add(listarBoton);
				
				final JButton totalSalariosDep = crearBoton(
					      getClass().getClassLoader().getResource("iconos/empleado/TOTALSALARIOSDEPARTAMENTO.png").getPath(),
					      new Color(255, 255, 255));
							totalSalariosDep.addActionListener(new ActionListener() {
					      @Override
					      public void actionPerformed(final ActionEvent e) {
					    	  final String idString = JOptionPane.showInputDialog(null, "Introduce ID del Departamento:", "TOTAL SALARIO DEPARTAMENTO",
										JOptionPane.QUESTION_MESSAGE);
								try {
									if (idString != null) {
										final int id = Integer.parseInt(idString);
									    final Contexto contexto = new Contexto(EventosEmpleado.TOTAL_SALARIOS_DEP, id);
								        Controller.getInstance().handleRequest(contexto);
									}
								} catch (final NumberFormatException ex) {
									JOptionPane.showMessageDialog(null, "ID introducido incorrecto.", "Mensaje de error",
											JOptionPane.WARNING_MESSAGE);
								}
					      }
					    });
					    panelBotones.add(totalSalariosDep);

				// -----------------------------------------

				// Aniadimos todos los paneles al JFrame.
				add(barra, BorderLayout.NORTH);
				add(fondo, BorderLayout.CENTER);
				add(panelBotones, BorderLayout.SOUTH);
				setVisible(true);

	}
	
	//TODO falta boton de la query que no se del todo como hacerlo 


}
package presentacion;


import java.awt.Font;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenDepartamentosException;
import excepciones.NoExistenSalidasEnActividadException;
import excepciones.NoExistenUsuariosException;
import excepciones.NoHaySalidasDisponiblesException;

import logica.DTProveedor;
import logica.DTSalidasAT;
import logica.DTTurista;
import logica.DTUsuario;
import logica.IATYST;
import logica.IUsuario;



import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JTextArea;

public class ConsultaUsuario extends JInternalFrame {
	
	private JComboBox<String> comboBoxSeleccionarUsuario;
	private JComboBox<String> comboBoxSeleccionarSalidaTur;
	private JComboBox<String> comboBoxActividadesProveedor;
	private IUsuario controlUsr;
	private IATYST controlAt;
	private String nom;
	private DTUsuario usr;
	private JLabel labelSeleccionarUsuario;
	private JLabel lblDatosUsuario;
	private JTextArea textArea;
	private JLabel lblSeleccionarActividadProveedor;
	private JLabel lblSalidaTur;
	private JButton btnActividadProv;
	private JButton btnSalidaTur;
	private JLabel lblSeleccionarProv;
	private JComboBox<String> comboBoxSalidasProveedor;
	private JButton btnSalidaProv;
	private JButton btnCancelar;
	private ConsultaDeSalida consultaSalida;
	private ConsultaActividadTuristica consultaActividad;
	
	public ConsultaUsuario(IUsuario iUsr, IATYST iAt) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

        setTitle("Consulta de Usuario");
		
		controlUsr = iUsr;
		controlAt = iAt;
		
		setBounds(100, 100, 774, 293);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 266, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		comboBoxSeleccionarUsuario = new JComboBox<String>();
		comboBoxSeleccionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarDatos1();
				try {
					if(iUsr.existeProveedor(comboBoxSeleccionarUsuario.getSelectedItem().toString())) {
						lblSeleccionarActividadProveedor.setVisible(false);
						lblSeleccionarProv.setVisible(false);
						comboBoxActividadesProveedor.setVisible(false);
						comboBoxSalidasProveedor.setVisible(false);
						btnActividadProv.setVisible(false);
						btnSalidaProv.setVisible(false);
						comboBoxSeleccionarSalidaTur.setVisible(false);
						btnSalidaTur.setVisible(false);
						lblSalidaTur.setVisible(false);
						
						String[] actividades= controlUsr.listarActividadesTuristicasProveedorkevin(comboBoxSeleccionarUsuario.getSelectedItem().toString());
						try {
							if(actividades.length==0)
								throw new NoHaySalidasDisponiblesException("No Esta Inscripto A Salidas");
							comboBoxActividadesProveedor.setModel(new DefaultComboBoxModel<String>(actividades));
							comboBoxActividadesProveedor.setVisible(true);
							comboBoxActividadesProveedor.setSelectedItem(null);
								btnActividadProv.setVisible(true);
							lblSeleccionarActividadProveedor.setVisible(true);
						}catch(NoHaySalidasDisponiblesException e2) {
							JOptionPane.showMessageDialog(comboBoxSeleccionarUsuario, e2.getMessage(),"Consulta De Usuario", JOptionPane.ERROR_MESSAGE);
						}
						
					}else {
						lblSeleccionarActividadProveedor.setVisible(false);
						lblSeleccionarProv.setVisible(false);
						comboBoxActividadesProveedor.setVisible(false);
						comboBoxSalidasProveedor.setVisible(false);
						btnActividadProv.setVisible(false);
						btnSalidaProv.setVisible(false);
						comboBoxSeleccionarSalidaTur.setVisible(false);
						btnSalidaTur.setVisible(false);
						lblSalidaTur.setVisible(false);
						try {
							String [] salidas= controlUsr.listarSalidasTurista(comboBoxSeleccionarUsuario.getSelectedItem().toString());
							if(salidas.length==0)
								throw new NoHaySalidasDisponiblesException("No Esta Inscripto A Salidas");
							comboBoxSeleccionarSalidaTur.setModel(new DefaultComboBoxModel<String>(salidas));
							comboBoxSeleccionarSalidaTur.setVisible(true);
							btnSalidaTur.setVisible(true);
							lblSalidaTur.setVisible(true);
						}catch(NoHaySalidasDisponiblesException e2) {
							JOptionPane.showMessageDialog(comboBoxSeleccionarUsuario, e2.getMessage(),"Consulta De Usuario", JOptionPane.ERROR_MESSAGE);
						}
					}
				}catch(NullPointerException e1) {
					
				}
			}
		});
		
		labelSeleccionarUsuario = new JLabel("Seleccionar usuario:");
		labelSeleccionarUsuario.setFont(new Font("Tahoma", Font.ITALIC, 14));
		labelSeleccionarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_labelSeleccionarUsuario = new GridBagConstraints();
		gbc_labelSeleccionarUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_labelSeleccionarUsuario.gridx = 1;
		gbc_labelSeleccionarUsuario.gridy = 0;
		getContentPane().add(labelSeleccionarUsuario, gbc_labelSeleccionarUsuario);
		GridBagConstraints gbc_comboBoxSeleccionarUsuario = new GridBagConstraints();
		gbc_comboBoxSeleccionarUsuario.gridwidth = 5;
		gbc_comboBoxSeleccionarUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSeleccionarUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionarUsuario.gridx = 2;
		gbc_comboBoxSeleccionarUsuario.gridy = 0;
		getContentPane().add(comboBoxSeleccionarUsuario, gbc_comboBoxSeleccionarUsuario);
		
		lblDatosUsuario = new JLabel("Datos basicos del Usuario:");
		lblDatosUsuario.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblDatosUsuario = new GridBagConstraints();
		gbc_lblDatosUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatosUsuario.gridx = 1;
		gbc_lblDatosUsuario.gridy = 1;
		getContentPane().add(lblDatosUsuario, gbc_lblDatosUsuario);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 3;
		gbc_textArea.gridwidth = 5;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		getContentPane().add(textArea, gbc_textArea);
		
		lblSeleccionarActividadProveedor = new JLabel("Seleccionar actividad:");
		lblSeleccionarActividadProveedor.setVisible(false);
		lblSeleccionarActividadProveedor.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSeleccionarActividadProveedor = new GridBagConstraints();
		gbc_lblSeleccionarActividadProveedor.gridwidth = 2;
		gbc_lblSeleccionarActividadProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarActividadProveedor.gridx = 0;
		gbc_lblSeleccionarActividadProveedor.gridy = 5;
		getContentPane().add(lblSeleccionarActividadProveedor, gbc_lblSeleccionarActividadProveedor);
		
		lblSalidaTur = new JLabel("SeleccionarSalida");
		lblSalidaTur.setVisible(false);
		lblSalidaTur.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSalidaTur = new GridBagConstraints();
		gbc_lblSalidaTur.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalidaTur.gridx = 1;
		gbc_lblSalidaTur.gridy = 5;
		getContentPane().add(lblSalidaTur, gbc_lblSalidaTur);
		
		comboBoxSeleccionarSalidaTur = new JComboBox<String>();
		comboBoxSeleccionarSalidaTur.setVisible(false);
		comboBoxSeleccionarSalidaTur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		comboBoxActividadesProveedor = new JComboBox<String>();
		comboBoxActividadesProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] salidas=controlAt.listarSalidasActividad(comboBoxActividadesProveedor.getSelectedItem().toString());
					comboBoxSalidasProveedor.setModel(new DefaultComboBoxModel<String>(salidas));
					comboBoxSalidasProveedor.setVisible(true);
					lblSeleccionarProv.setVisible(true);
					btnSalidaProv.setVisible(true);
					
				}catch(NullPointerException e4) {
				} catch (NoExistenSalidasEnActividadException e1) {
					JOptionPane.showMessageDialog(comboBoxSalidasProveedor, e1.getMessage(), "Salidas Actividad", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		comboBoxActividadesProveedor.setVisible(false);
		GridBagConstraints gbc_comboBoxActividadesProveedor = new GridBagConstraints();
		gbc_comboBoxActividadesProveedor.gridwidth = 5;
		gbc_comboBoxActividadesProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividadesProveedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividadesProveedor.gridx = 2;
		gbc_comboBoxActividadesProveedor.gridy = 5;
		getContentPane().add(comboBoxActividadesProveedor, gbc_comboBoxActividadesProveedor);
		GridBagConstraints gbc_comboBoxSeleccionarSalidaTur = new GridBagConstraints();
		gbc_comboBoxSeleccionarSalidaTur.gridwidth = 4;
		gbc_comboBoxSeleccionarSalidaTur.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSeleccionarSalidaTur.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionarSalidaTur.gridx = 3;
		gbc_comboBoxSeleccionarSalidaTur.gridy = 5;
		getContentPane().add(comboBoxSeleccionarSalidaTur, gbc_comboBoxSeleccionarSalidaTur);
		
		btnActividadProv = new JButton("Mostrar Detalle");
		btnActividadProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String men= comboBoxActividadesProveedor.getSelectedItem().toString();
					if(men!=null && men!="")
						consultaActividad.llamadaExterna(men);				
				}catch(NullPointerException e5) {
				}
			}
		});
		btnActividadProv.setVisible(false);
		btnActividadProv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnActividadProv = new GridBagConstraints();
		gbc_btnActividadProv.gridwidth = 2;
		gbc_btnActividadProv.insets = new Insets(0, 0, 5, 0);
		gbc_btnActividadProv.gridx = 7;
		gbc_btnActividadProv.gridy = 5;
		getContentPane().add(btnActividadProv, gbc_btnActividadProv);
		
		btnSalidaTur = new JButton("Mostrar Detalle");
		btnSalidaTur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{					
					DTSalidasAT sal= controlAt.obtenerDatosSalidaTur(comboBoxSeleccionarUsuario.getSelectedItem().toString(), comboBoxSeleccionarSalidaTur.getSelectedItem().toString());
					consultaSalida.llamadaExternados(sal);
				}catch(NullPointerException e6) {
				}
			}
		});
		btnSalidaTur.setVisible(false);
		btnSalidaTur.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnSalidaTur = new GridBagConstraints();
		gbc_btnSalidaTur.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalidaTur.gridx = 8;
		gbc_btnSalidaTur.gridy = 5;
		getContentPane().add(btnSalidaTur, gbc_btnSalidaTur);
		
		lblSeleccionarProv = new JLabel("Seleccionar salida:");
		lblSeleccionarProv.setVisible(false);
		lblSeleccionarProv.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSeleccionarProv = new GridBagConstraints();
		gbc_lblSeleccionarProv.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarProv.gridx = 1;
		gbc_lblSeleccionarProv.gridy = 6;
		getContentPane().add(lblSeleccionarProv, gbc_lblSeleccionarProv);
		
		comboBoxSalidasProveedor = new JComboBox<String>();
		comboBoxSalidasProveedor.setVisible(false);
		GridBagConstraints gbc_comboBoxSalidasProveedor = new GridBagConstraints();
		gbc_comboBoxSalidasProveedor.gridwidth = 5;
		gbc_comboBoxSalidasProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSalidasProveedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSalidasProveedor.gridx = 2;
		gbc_comboBoxSalidasProveedor.gridy = 6;
		getContentPane().add(comboBoxSalidasProveedor, gbc_comboBoxSalidasProveedor);
		
		btnSalidaProv = new JButton("Mostrar Detalle");
		btnSalidaProv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTSalidasAT sal= controlAt.obtenerDatosSalida(comboBoxActividadesProveedor.getSelectedItem().toString(), comboBoxSalidasProveedor.getSelectedItem().toString());
				String salida= comboBoxSalidasProveedor.getSelectedItem().toString();
				String actividad=comboBoxActividadesProveedor.getSelectedItem().toString();
				try {
					consultaSalida.llamadaExterna(null, actividad, actividad, salida);
				} catch (NoExistenDepartamentosException | NoExistenActividadesEnDepartamentoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalidaProv.setVisible(false);
		btnSalidaProv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnSalidaProv = new GridBagConstraints();
		gbc_btnSalidaProv.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalidaProv.gridx = 8;
		gbc_btnSalidaProv.gridy = 6;
		getContentPane().add(btnSalidaProv, gbc_btnSalidaProv);
		
		btnCancelar = new JButton("Salir");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 7;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		//ACCION CANCELAR
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSeleccionarUsuario.removeAll();
				comboBoxSeleccionarSalidaTur.removeAll();
				comboBoxActividadesProveedor.setVisible(false);
				
				textArea.selectAll();
				textArea.replaceSelection("");
				btnSalidaProv.setVisible(false);
				comboBoxSalidasProveedor.setVisible(false);
				lblSeleccionarProv.setVisible(false);
				btnSalidaTur.setVisible(false);
				btnActividadProv.setVisible(false);
				lblSalidaTur.setVisible(false);
				comboBoxSeleccionarSalidaTur.setVisible(false);
				lblSeleccionarActividadProveedor.setVisible(false);
				setVisible(false);
			}
		});
	
	}
	
	public boolean cargarUsuarios() {
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlUsr.listarUsuarios());
			comboBoxSeleccionarUsuario.setModel(model);
			comboBoxSeleccionarUsuario.setSelectedItem(null);
			return true;
		} catch (NoExistenUsuariosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
			//EL ERROR ESTA MAL, INDICA QUE NO HAY USUARIOS PARA MODIFICAR
			return false;
			}
		}
	
	public void cargarDatos1() {
		if(comboBoxSeleccionarUsuario.getSelectedItem() != null) {
			textArea.selectAll();
			textArea.replaceSelection("");
			textArea.selectAll();
			textArea.replaceSelection("");
			comboBoxSeleccionarSalidaTur.removeAll();
			String id = comboBoxSeleccionarUsuario.getSelectedItem().toString();
			if(controlUsr.existeProveedor(id)) {
				DTProveedor dtProv = controlUsr.obtenerDatosProveedor(id);
				textArea.append(" Nickname = " + dtProv.getNickname());
				textArea.append("\n Nombre = " + dtProv.getNombre());
				textArea.append("\n Apellido = " + dtProv.getApellido());
				textArea.append("\n Correo electrónico = " + dtProv.getEmail());
				textArea.append("\n Fecha de Nacimiento = " + dtProv.getFechaNacimiento().toString());
				textArea.append("\n Descripcion = " + dtProv.getDescripcion());
				if(dtProv.getLink() == "") {
					textArea.append("\n Link Web = No tiene");
				} else {
					textArea.append("\n Link Web = " + dtProv.getLink());
				}
			} else {
				DTTurista dtTur = controlUsr.obtenerDatosTurista(id);
				textArea.append(" Nickname = " + dtTur.getNickname());
				textArea.append("\n Nombre = " + dtTur.getNombre());
				textArea.append("\n Apellido = " + dtTur.getApellido());
				textArea.append("\n Correo electrónico = " + dtTur.getEmail());
				textArea.append("\n Fecha de Nacimiento = " + dtTur.getFechaNacimiento().toString());
				textArea.append("\n Nacionalidad = " + dtTur.getNacionalidad());
				String[] salidas = controlUsr.listarSalidasTurista(dtTur.getNickname());
				DefaultComboBoxModel<String> model_2;
				model_2 = new DefaultComboBoxModel<String>(salidas);
				comboBoxSeleccionarSalidaTur.setModel(model_2);
				comboBoxSeleccionarSalidaTur.setSelectedItem(null);
				if(salidas.length == 0) {
					textArea.append("\n No se encuentra inscripto a ninguna salida.");
				} else {
					textArea.append("\n Salidas:");
					for (int i = 0; i < salidas.length; i++) {
						textArea.append("\n " + salidas[i]);
					}
				}
			}
		}
	}

	public void setActividadSalida(ConsultaActividadTuristica consultaATInternalFrame, ConsultaDeSalida consSalidaInternalFrame) {
		consultaActividad=consultaATInternalFrame;
		consultaSalida=consSalidaInternalFrame;
	}
}

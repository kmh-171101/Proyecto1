package presentacion;

import java.awt.Cursor;

import java.awt.Font;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import logica.IATYST;
import logica.IDepartamento;
import logica.IPaquete;

import excepciones.NoExistenPaquetesEnLaActividadException;
import excepciones.NoExistenSalidasEnActividadException;
import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenDepartamentosException;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class ConsultaActividadTuristica extends JInternalFrame {
	
	
	private IATYST controlAT;
	private IDepartamento controladorDepto;
	private IPaquete controlPaq;
	private JComboBox<String> comboBoxDepartamento;
	private JComboBox<String> comboBoxActividad;
	private JComboBox<String> comboBoxSalidas;
	private JComboBox<String> comboBoxPaquete;
	private String depto;
	private String act;
	private String sal;
	private String paq;
	private JTextArea nombreAct;
	private JButton consultaSalida;
	private JButton consultaPaquete;
	private JLabel tituloConsAT;
	private JLabel SeleccionarDepto;
	private JLabel SelecActT;
	private JLabel MostrarDatosActividad;
	private JLabel lblnombreact;
	private JLabel lblCostoPorTurista;
	private JTextArea costoAct;
	private JLabel lblNewLabel_3;
	private JTextArea descAct;
	private JLabel lblNewLabel_4 ;
	private JTextArea ciudadAct;
	private JLabel lblDuracionEnDias;
	private JTextArea duracionAct;
	private JLabel lblFechaDeAlta;
	private JTextArea fechaAct;
	private JLabel SelecSalida;
	private JLabel SelecPaquete;
	private JButton BotonSalir;
	private JLabel lblEstado;
	private JTextArea EstadoText;
	private JLabel lblCat;
	private JTextArea textAreaCat;
	
	public ConsultaActividadTuristica(IATYST iAT,IDepartamento iDep, IPaquete iPaq, ConsultaDePaqueteDeActividadTuristica ConsultaPaqATInternalFrame, ConsultaDeSalida ConsSalidaInternalFrame) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

		setTitle("Consulta De Actividad Turistica");
		
		
		setBounds(100, 100, 752, 419);
		
		
		controlAT = iAT;
		controladorDepto = iDep;
		controlPaq = iPaq;
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBounds(100, 100, 604, 419);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{126, 0, 250, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 30, 0, 29, 0, 0, 0, 0, 0, 0, 27, 24, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		tituloConsAT = new JLabel("Consulta de Actividad Turistica");
		tituloConsAT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_tituloConsAT = new GridBagConstraints();
		gbc_tituloConsAT.insets = new Insets(0, 0, 5, 5);
		gbc_tituloConsAT.gridx = 2;
		gbc_tituloConsAT.gridy = 0;
		getContentPane().add(tituloConsAT, gbc_tituloConsAT);

		SeleccionarDepto = new JLabel("Seleccionar Departamento");
		SeleccionarDepto.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_SeleccionarDepto = new GridBagConstraints();
		gbc_SeleccionarDepto.gridwidth = 2;
		gbc_SeleccionarDepto.fill = GridBagConstraints.BOTH;
		gbc_SeleccionarDepto.insets = new Insets(0, 0, 5, 5);
		gbc_SeleccionarDepto.gridx = 0;
		gbc_SeleccionarDepto.gridy = 1;
		getContentPane().add(SeleccionarDepto, gbc_SeleccionarDepto);
		
			comboBoxDepartamento = new JComboBox<String>();
			GridBagConstraints gbc_comboBoxDepartamento = new GridBagConstraints();
			gbc_comboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxDepartamento.gridx = 2;
			gbc_comboBoxDepartamento.gridy = 1;
			getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamento);
			//DatosAT.setVisible(true);
			

		SelecActT = new JLabel("Seleccionar Actividad");
		SelecActT.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_SelecActT = new GridBagConstraints();
		gbc_SelecActT.gridwidth = 2;
		gbc_SelecActT.fill = GridBagConstraints.BOTH;
		gbc_SelecActT.insets = new Insets(0, 0, 5, 5);
		gbc_SelecActT.gridx = 0;
		gbc_SelecActT.gridy = 2;
		getContentPane().add(SelecActT, gbc_SelecActT);
		
		comboBoxActividad = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxActividad = new GridBagConstraints();
		gbc_comboBoxActividad.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividad.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividad.gridx = 2;
		gbc_comboBoxActividad.gridy = 2;
		getContentPane().add(comboBoxActividad, gbc_comboBoxActividad);


		MostrarDatosActividad = new JLabel("Datos Actividad");
		MostrarDatosActividad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_MostrarDatosActividad = new GridBagConstraints();
		gbc_MostrarDatosActividad.insets = new Insets(0, 0, 5, 5);
		gbc_MostrarDatosActividad.gridx = 2;
		gbc_MostrarDatosActividad.gridy = 3;
		getContentPane().add(MostrarDatosActividad, gbc_MostrarDatosActividad);

		lblnombreact= new JLabel("Nombre:");
		lblnombreact.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblnombreact = new GridBagConstraints();
		gbc_lblnombreact.gridwidth = 2;
		gbc_lblnombreact.insets = new Insets(0, 0, 5, 5);
		gbc_lblnombreact.gridx = 0;
		gbc_lblnombreact.gridy = 4;
		getContentPane().add(lblnombreact, gbc_lblnombreact);
		
		nombreAct = new JTextArea();
		nombreAct.setEditable(false);
		GridBagConstraints gbc_nombreAct = new GridBagConstraints();
		gbc_nombreAct.insets = new Insets(0, 0, 5, 5);
		gbc_nombreAct.fill = GridBagConstraints.BOTH;
		gbc_nombreAct.gridx = 2;
		gbc_nombreAct.gridy = 4;
		getContentPane().add(nombreAct, gbc_nombreAct);
		

		lblCostoPorTurista= new JLabel("Costo por Turista:");
		lblCostoPorTurista.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblCostoPorTurista = new GridBagConstraints();
		gbc_lblCostoPorTurista.gridwidth = 2;
		gbc_lblCostoPorTurista.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoPorTurista.gridx = 0;
		gbc_lblCostoPorTurista.gridy = 5;
		getContentPane().add(lblCostoPorTurista, gbc_lblCostoPorTurista);
		

		costoAct= new JTextArea();
		costoAct.setEditable(false);
		GridBagConstraints gbc_costoAct = new GridBagConstraints();
		gbc_costoAct.insets = new Insets(0, 0, 5, 5);
		gbc_costoAct.fill = GridBagConstraints.BOTH;
		gbc_costoAct.gridx = 2;
		gbc_costoAct.gridy = 5;
		getContentPane().add(costoAct, gbc_costoAct);
		

		lblNewLabel_3= new JLabel("Descripcion:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 6;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		

		descAct= new JTextArea();
		descAct.setEditable(false);
		GridBagConstraints gbc_descAct = new GridBagConstraints();
		gbc_descAct.insets = new Insets(0, 0, 5, 5);
		gbc_descAct.fill = GridBagConstraints.BOTH;
		gbc_descAct.gridx = 2;
		gbc_descAct.gridy = 6;
		getContentPane().add(descAct, gbc_descAct);
		

		lblNewLabel_4 = new JLabel("Ciudad donde se realiza:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 7;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		

		ciudadAct= new JTextArea();
		ciudadAct.setEditable(false);
		GridBagConstraints gbc_ciudadAct = new GridBagConstraints();
		gbc_ciudadAct.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadAct.fill = GridBagConstraints.BOTH;
		gbc_ciudadAct.gridx = 2;
		gbc_ciudadAct.gridy = 7;
		getContentPane().add(ciudadAct, gbc_ciudadAct);
		

		lblDuracionEnDias= new JLabel("Duracion en dias:");
		lblDuracionEnDias.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblDuracionEnDias = new GridBagConstraints();
		gbc_lblDuracionEnDias.gridwidth = 2;
		gbc_lblDuracionEnDias.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracionEnDias.gridx = 0;
		gbc_lblDuracionEnDias.gridy = 8;
		getContentPane().add(lblDuracionEnDias, gbc_lblDuracionEnDias);
		

		duracionAct= new JTextArea();
		duracionAct.setEditable(false);
		GridBagConstraints gbc_duracionAct = new GridBagConstraints();
		gbc_duracionAct.insets = new Insets(0, 0, 5, 5);
		gbc_duracionAct.fill = GridBagConstraints.BOTH;
		gbc_duracionAct.gridx = 2;
		gbc_duracionAct.gridy = 8;
		getContentPane().add(duracionAct, gbc_duracionAct);
		

		lblFechaDeAlta= new JLabel("Fecha de alta:");
		lblFechaDeAlta.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblFechaDeAlta = new GridBagConstraints();
		gbc_lblFechaDeAlta.gridwidth = 2;
		gbc_lblFechaDeAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeAlta.gridx = 0;
		gbc_lblFechaDeAlta.gridy = 9;
		getContentPane().add(lblFechaDeAlta, gbc_lblFechaDeAlta);
		

		fechaAct= new JTextArea();
		fechaAct.setEditable(false);
		GridBagConstraints gbc_fechaAct = new GridBagConstraints();
		gbc_fechaAct.insets = new Insets(0, 0, 5, 5);
		gbc_fechaAct.fill = GridBagConstraints.BOTH;
		gbc_fechaAct.gridx = 2;
		gbc_fechaAct.gridy = 9;
		getContentPane().add(fechaAct, gbc_fechaAct);
		

		SelecSalida= new JLabel("Salidas asociadas");
		SelecSalida.setVisible(false);
		
		lblEstado = new JLabel("Estado Actual:");
		lblEstado.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.gridwidth = 2;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 10;
		getContentPane().add(lblEstado, gbc_lblEstado);
		
		EstadoText = new JTextArea();
		EstadoText.setEditable(false);
		GridBagConstraints gbc_EstadoText = new GridBagConstraints();
		gbc_EstadoText.insets = new Insets(0, 0, 5, 5);
		gbc_EstadoText.fill = GridBagConstraints.BOTH;
		gbc_EstadoText.gridx = 2;
		gbc_EstadoText.gridy = 10;
		getContentPane().add(EstadoText, gbc_EstadoText);
		
		lblCat = new JLabel("Categorias:");
		lblCat.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblCat = new GridBagConstraints();
		gbc_lblCat.insets = new Insets(0, 0, 5, 5);
		gbc_lblCat.gridx = 0;
		gbc_lblCat.gridy = 11;
		getContentPane().add(lblCat, gbc_lblCat);
		
		textAreaCat = new JTextArea();
		textAreaCat.setEditable(false);
		GridBagConstraints gbc_textAreaCat = new GridBagConstraints();
		gbc_textAreaCat.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaCat.fill = GridBagConstraints.BOTH;
		gbc_textAreaCat.gridx = 2;
		gbc_textAreaCat.gridy = 11;
		getContentPane().add(textAreaCat, gbc_textAreaCat);
		SelecSalida.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_SelecSalida = new GridBagConstraints();
		gbc_SelecSalida.gridwidth = 2;
		gbc_SelecSalida.insets = new Insets(0, 0, 5, 5);
		gbc_SelecSalida.gridx = 0;
		gbc_SelecSalida.gridy = 12;
		getContentPane().add(SelecSalida, gbc_SelecSalida);
		
		
		

		SelecPaquete= new JLabel("Paquetes asociados a la Actividad");
		SelecPaquete.setVisible(false);
		SelecPaquete.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_SelecPaquete = new GridBagConstraints();
		gbc_SelecPaquete.gridwidth = 2;
		gbc_SelecPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_SelecPaquete.gridx = 0;
		gbc_SelecPaquete.gridy = 13;
		getContentPane().add(SelecPaquete, gbc_SelecPaquete);

		
		
		//SELECCIONAR ACTIVIDAD
		comboBoxDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxDepartamento.getSelectedItem() != null && comboBoxDepartamento.getSelectedItem().toString()!="") {
					depto = comboBoxDepartamento.getSelectedItem().toString();
					try {
						comboBoxActividad.removeAllItems();
						comboBoxActividad.setModel(new DefaultComboBoxModel<String>(controladorDepto.listarActividadesDepartamento(depto)));
						comboBoxActividad.addItem("");
						comboBoxActividad.setSelectedItem("");
						nombreAct.setText("");
						descAct.setText("");
						duracionAct.setText("");
						costoAct.setText("");
						ciudadAct.setText("");
						fechaAct.setText("");
						EstadoText.setText("");
						textAreaCat.setText("");
						comboBoxSalidas.setVisible(false);
						SelecSalida.setVisible(false);
						consultaSalida.setVisible(false);
						comboBoxPaquete.setVisible(false);
						SelecPaquete.setVisible(false);
						consultaPaquete.setVisible(false);
					}catch(NoExistenActividadesEnDepartamentoException e1) {
						JOptionPane.showMessageDialog(comboBoxDepartamento, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
						nombreAct.setText("");
						descAct.setText("");
						duracionAct.setText("");
						costoAct.setText("");
						ciudadAct.setText("");
						fechaAct.setText("");
						EstadoText.setText("");
						textAreaCat.setText("");
						comboBoxSalidas.setVisible(false);
						SelecSalida.setVisible(false);
						consultaSalida.setVisible(false);
						comboBoxPaquete.setVisible(false);
						SelecPaquete.setVisible(false);
						consultaPaquete.setVisible(false);
						//comboBoxDepartamento.removeAllItems();
						//comboBoxActividad.removeAllItems();
						//comboBoxSalidas.removeAllItems();
						//setVisible(false);
					}
				}
				
			}
		});
		consultaPaquete = new JButton("Consultar Paquete");
		consultaPaquete.setVisible(false);
		consultaPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ConsultaPaqATInternalFrame.cargarPaquetes() && !comboBoxPaquete.getSelectedItem().toString().isEmpty()) {
					String nombrePaquete=comboBoxPaquete.getSelectedItem().toString();
					ConsultaPaqATInternalFrame.llamadaExterna(nombrePaquete);
				}
			}
		});
		
		consultaSalida = new JButton("Consultar Salida");
		consultaSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreDepartamento=(String) comboBoxDepartamento.getSelectedItem();
				String nombreActividad=act;
				String nombreSalida=(String) comboBoxSalidas.getSelectedItem();
				if(nombreActividad!=null && nombreActividad!="" && nombreSalida!=null && nombreSalida!="") {
					try {
						ConsSalidaInternalFrame.llamadaExterna(controladorDepto,nombreDepartamento,nombreActividad,nombreSalida);
					} catch (NoExistenDepartamentosException | NoExistenActividadesEnDepartamentoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		consultaSalida.setVisible(false);
		GridBagConstraints gbc_consultaSalida = new GridBagConstraints();
		gbc_consultaSalida.fill = GridBagConstraints.BOTH;
		gbc_consultaSalida.insets = new Insets(0, 0, 5, 0);
		gbc_consultaSalida.gridx = 3;
		gbc_consultaSalida.gridy = 12;
		getContentPane().add(consultaSalida, gbc_consultaSalida);
		
		//SELECCIONAR SALIDA
				comboBoxActividad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(comboBoxActividad.getSelectedItem()!=null && comboBoxActividad.getSelectedItem().toString()!="") {
							act = comboBoxActividad.getSelectedItem().toString();
							nombreAct.setText(controlAT.obtenerDatosActividad(act).getNombre());
							descAct.setText(controlAT.obtenerDatosActividad(act).getDescripcion());
							duracionAct.setText(""+controlAT.obtenerDatosActividad(act).getDuracion());
							costoAct.setText(""+controlAT.obtenerDatosActividad(act).getCostoPT());
							ciudadAct.setText(controlAT.obtenerDatosActividad(act).getCiudad());
							fechaAct.setText(""+controlAT.obtenerDatosActividad(act).getFechaAlta());
							EstadoText.setText(""+ controlAT.obtenerDatosActividad(act).getEstadoActual());
							String[] cats= controlAT.obtenerDatosActividad(act).getCategoria();
							String muestraCat="";
							if(cats[cats.length-1]==null) {
								int i=0;
								for(i=0;i<cats.length-2;i++) {
									muestraCat=muestraCat+cats[i].toString()+", ";
								}
								muestraCat=muestraCat+cats[cats.length-2]+".";
								textAreaCat.setText(muestraCat);								
							}else {
								int i=0;
								for(i=0;i<cats.length-1;i++) {
									muestraCat=muestraCat+cats[i].toString()+", ";
								}
								muestraCat=muestraCat+cats[cats.length-1]+".";
								textAreaCat.setText(muestraCat);
							}
							try {
								comboBoxSalidas.removeAllItems();
								comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(controlAT.listarSalidasActividad(act)));
								comboBoxSalidas.addItem("");
								comboBoxSalidas.setSelectedItem("");
								comboBoxSalidas.setVisible(true);
								SelecSalida.setVisible(true);
								consultaSalida.setVisible(true);
							}catch(NoExistenSalidasEnActividadException e1) {
								JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
								comboBoxSalidas.setVisible(false);
								SelecSalida.setVisible(false);
								consultaSalida.setVisible(false);
							}
							act = comboBoxActividad.getSelectedItem().toString();
							try {
								comboBoxPaquete.removeAllItems();
								comboBoxPaquete.setModel(new DefaultComboBoxModel<String>(controlAT.listarPaquetesActividad(act)));
								comboBoxPaquete.addItem("");
								comboBoxPaquete.setSelectedItem("");
								comboBoxPaquete.setVisible(true);
								SelecPaquete.setVisible(true);
								consultaPaquete.setVisible(true);
							}catch(NoExistenPaquetesEnLaActividadException e1) {
								JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
								comboBoxPaquete.setVisible(false);
								SelecPaquete.setVisible(false);
								consultaPaquete.setVisible(false);
							}
						}

					}
				});
		
		comboBoxSalidas = new JComboBox<String>();
		comboBoxSalidas.setVisible(false);
		GridBagConstraints gbc_comboBoxSalidas = new GridBagConstraints();
		gbc_comboBoxSalidas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSalidas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSalidas.gridx = 2;
		gbc_comboBoxSalidas.gridy = 12;
		getContentPane().add(comboBoxSalidas, gbc_comboBoxSalidas);
		

		BotonSalir = new JButton("Salir");
		BotonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxDepartamento.removeAllItems();
				comboBoxActividad.removeAllItems();
				comboBoxSalidas.removeAllItems();
				comboBoxPaquete.removeAllItems();
				nombreAct.setText("");
				descAct.setText("");
				duracionAct.setText("");
				costoAct.setText("");
				ciudadAct.setText("");
				fechaAct.setText("");
				setVisible(false);
				comboBoxPaquete.setVisible(false);
				comboBoxSalidas.setVisible(false);
				consultaSalida.setVisible(false);
				consultaPaquete.setVisible(false);
				SelecPaquete.setVisible(false);
				SelecSalida.setVisible(false);
			}
		});
		
		comboBoxPaquete = new JComboBox<String>();
		comboBoxPaquete.setVisible(false);
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 2;
		gbc_comboBoxPaquete.gridy = 13;
		getContentPane().add(comboBoxPaquete, gbc_comboBoxPaquete);
		
		GridBagConstraints gbc_consultaPaquete = new GridBagConstraints();
		gbc_consultaPaquete.fill = GridBagConstraints.BOTH;
		gbc_consultaPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_consultaPaquete.gridx = 3;
		gbc_consultaPaquete.gridy = 13;
		getContentPane().add(consultaPaquete, gbc_consultaPaquete);
		GridBagConstraints gbc_BotonSalir = new GridBagConstraints();
		gbc_BotonSalir.insets = new Insets(0, 0, 0, 5);
		gbc_BotonSalir.gridx = 2;
		gbc_BotonSalir.gridy = 14;
		getContentPane().add(BotonSalir, gbc_BotonSalir);
		
	}
	
	public boolean cargarDepartamentos(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controladorDepto.listarDepartamentos());
			comboBoxDepartamento.setModel(model);
			comboBoxDepartamento.addItem("");
			comboBoxDepartamento.setSelectedItem("");
			return true;
		}catch(NoExistenDepartamentosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public void llamadaExterna(String string) {
		comboBoxDepartamento.setSelectedItem(null);
		
		setVisible(true);
		
		act = string;
		nombreAct.setText(controlAT.obtenerDatosActividad(act).getNombre());
		descAct.setText(controlAT.obtenerDatosActividad(act).getDescripcion());
		duracionAct.setText(""+controlAT.obtenerDatosActividad(act).getDuracion());
		costoAct.setText(""+controlAT.obtenerDatosActividad(act).getCostoPT());
		ciudadAct.setText(controlAT.obtenerDatosActividad(act).getCiudad());
		fechaAct.setText(""+controlAT.obtenerDatosActividad(act).getFechaAlta());
		String[] cats= controlAT.obtenerDatosActividad(act).getCategoria();
		String muestraCat="";
		int i=0;
		for(i=0;i<cats.length-1;i++) {
			muestraCat=muestraCat+cats[i]+", ";
		}
		muestraCat=muestraCat+cats[cats.length-1].toString()+".";
		textAreaCat.setText(muestraCat);
		EstadoText.setText(""+ controlAT.obtenerDatosActividad(act).getEstadoActual());
		
		try {
			comboBoxSalidas.removeAllItems();
			comboBoxSalidas.setModel(new DefaultComboBoxModel<String>(controlAT.listarSalidasActividad(act)));
			comboBoxSalidas.addItem("");
			comboBoxSalidas.setSelectedItem("");
			comboBoxSalidas.setVisible(true);
			SelecSalida.setVisible(true);
			consultaSalida.setVisible(true);
		}catch(NoExistenSalidasEnActividadException e1) {
			JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			comboBoxSalidas.setVisible(false);
			SelecSalida.setVisible(false);
			consultaSalida.setVisible(false);
		}
		try {
			comboBoxPaquete.removeAllItems();
			comboBoxPaquete.setModel(new DefaultComboBoxModel<String>(controlAT.listarPaquetesActividad(act)));
			comboBoxPaquete.addItem("");
			comboBoxPaquete.setSelectedItem("");
			comboBoxPaquete.setVisible(true);
			SelecPaquete.setVisible(true);
			consultaPaquete.setVisible(true);
		}catch(NoExistenPaquetesEnLaActividadException e1) {
			JOptionPane.showMessageDialog(comboBoxActividad, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			comboBoxPaquete.setVisible(false);
			SelecPaquete.setVisible(false);
			consultaPaquete.setVisible(false);
		}
	}
}

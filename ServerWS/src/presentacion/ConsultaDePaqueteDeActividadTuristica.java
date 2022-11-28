package presentacion;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.IPaquete;
import logica.IATYST;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;


import excepciones.NoExistenActividadesEnElPaqueteException;

import excepciones.NoExistenPaquetesException;


import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ConsultaDePaqueteDeActividadTuristica extends JInternalFrame {
	private IPaquete controlPaq;
	private IATYST controlAT;

	private JComboBox<String> comboBoxPaquete;
	private JComboBox<String> comboBoxActividad;
	private JButton Cancelar;
	private String paq;
	private String act;
	private JLabel SelecPaq;
	private JTextArea DatosPaq;
	private JLabel DatosPaquete;
	private JLabel SelecAct;
	private GridBagConstraints gbc_Cancelar;
	private JButton btnNewButton;
	private ConsultaActividadTuristica ConsultaATInternalFrame;
	private JLabel NombrePaq;
	private JTextArea TextNomPaq;
	private JLabel DescripcionPaq;
	private JTextArea TextDesPaq;
	private JLabel ValidezPaq;
	private JTextArea TextValidezPaq;
	private JLabel DescuentoPaq;
	private JTextArea TextDescuentoPaq;
	private JLabel CostoP;
	private JLabel FechaAlta;
	private JTextArea TextCostoP;
	private JTextArea TextFechaAlta;
	private JLabel cats;
	private JTextArea Categorias;
	
	
	public ConsultaDePaqueteDeActividadTuristica(IPaquete iPaq, IATYST iat) {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

		setTitle("Consulta Paquete De Actividad Turistica");
		
		setBounds(100, 100, 552, 332);
		
		controlPaq = iPaq;
		controlAT = iat;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 38, 0, 45, 0, 0, 42, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		SelecPaq = new JLabel("Seleccionar Paquete");
		GridBagConstraints gbc_SelecPaq = new GridBagConstraints();
		gbc_SelecPaq.gridwidth = 2;
		gbc_SelecPaq.insets = new Insets(0, 0, 5, 5);
		gbc_SelecPaq.gridx = 2;
		gbc_SelecPaq.gridy = 1;
		getContentPane().add(SelecPaq, gbc_SelecPaq);
		
		comboBoxPaquete = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.gridwidth = 7;
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 4;
		gbc_comboBoxPaquete.gridy = 1;
		getContentPane().add(comboBoxPaquete, gbc_comboBoxPaquete);
		
		DatosPaquete = new JLabel("Datos del Paquete");
		GridBagConstraints gbc_DatosPaquete = new GridBagConstraints();
		gbc_DatosPaquete.gridwidth = 5;
		gbc_DatosPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_DatosPaquete.gridx = 4;
		gbc_DatosPaquete.gridy = 2;
		getContentPane().add(DatosPaquete, gbc_DatosPaquete);
		DatosPaquete.setVisible(false);
		
		NombrePaq = new JLabel("Nombre Paquete");
		GridBagConstraints gbc_NombrePaq = new GridBagConstraints();
		gbc_NombrePaq.gridwidth = 2;
		gbc_NombrePaq.insets = new Insets(0, 0, 5, 5);
		gbc_NombrePaq.gridx = 2;
		gbc_NombrePaq.gridy = 3;
		getContentPane().add(NombrePaq, gbc_NombrePaq);
		NombrePaq.setVisible(false);
		
		TextNomPaq = new JTextArea();
		GridBagConstraints gbc_TextNomPaq = new GridBagConstraints();
		gbc_TextNomPaq.gridwidth = 5;
		gbc_TextNomPaq.insets = new Insets(0, 0, 5, 5);
		gbc_TextNomPaq.fill = GridBagConstraints.BOTH;
		gbc_TextNomPaq.gridx = 4;
		gbc_TextNomPaq.gridy = 3;
		getContentPane().add(TextNomPaq, gbc_TextNomPaq);
		TextNomPaq.setVisible(false);
		
		DatosPaq = new JTextArea();
		DatosPaq.setEditable(false);
		GridBagConstraints gbc_DatosPaq = new GridBagConstraints();
		gbc_DatosPaq.insets = new Insets(0, 0, 5, 0);
		gbc_DatosPaq.fill = GridBagConstraints.BOTH;
		gbc_DatosPaq.gridx = 11;
		gbc_DatosPaq.gridy = 3;
		getContentPane().add(DatosPaq, gbc_DatosPaq);
		DatosPaq.setVisible(false);
		
		DescripcionPaq = new JLabel("Descripci\u00F3n");
		GridBagConstraints gbc_DescripcionPaq = new GridBagConstraints();
		gbc_DescripcionPaq.gridwidth = 2;
		gbc_DescripcionPaq.insets = new Insets(0, 0, 5, 5);
		gbc_DescripcionPaq.gridx = 2;
		gbc_DescripcionPaq.gridy = 4;
		getContentPane().add(DescripcionPaq, gbc_DescripcionPaq);
		DescripcionPaq.setVisible(false);
		
		TextDesPaq = new JTextArea();
		TextDesPaq.setEditable(false);
		GridBagConstraints gbc_TextDesPaq = new GridBagConstraints();
		gbc_TextDesPaq.gridwidth = 5;
		gbc_TextDesPaq.insets = new Insets(0, 0, 5, 5);
		gbc_TextDesPaq.fill = GridBagConstraints.BOTH;
		gbc_TextDesPaq.gridx = 4;
		gbc_TextDesPaq.gridy = 4;
		getContentPane().add(TextDesPaq, gbc_TextDesPaq);
		TextDesPaq.setVisible(false);
		
		ValidezPaq = new JLabel("Validez");
		GridBagConstraints gbc_ValidezPaq = new GridBagConstraints();
		gbc_ValidezPaq.gridwidth = 2;
		gbc_ValidezPaq.insets = new Insets(0, 0, 5, 5);
		gbc_ValidezPaq.gridx = 2;
		gbc_ValidezPaq.gridy = 5;
		getContentPane().add(ValidezPaq, gbc_ValidezPaq);
		ValidezPaq.setVisible(false);
		
		TextValidezPaq = new JTextArea();
		TextValidezPaq.setEditable(false);
		GridBagConstraints gbc_TextValidezPaq = new GridBagConstraints();
		gbc_TextValidezPaq.gridwidth = 5;
		gbc_TextValidezPaq.insets = new Insets(0, 0, 5, 5);
		gbc_TextValidezPaq.fill = GridBagConstraints.BOTH;
		gbc_TextValidezPaq.gridx = 4;
		gbc_TextValidezPaq.gridy = 5;
		getContentPane().add(TextValidezPaq, gbc_TextValidezPaq);
		TextValidezPaq.setVisible(false);
		
		DescuentoPaq = new JLabel("Descuento");
		GridBagConstraints gbc_DescuentoPaq = new GridBagConstraints();
		gbc_DescuentoPaq.gridwidth = 2;
		gbc_DescuentoPaq.insets = new Insets(0, 0, 5, 5);
		gbc_DescuentoPaq.gridx = 2;
		gbc_DescuentoPaq.gridy = 6;
		getContentPane().add(DescuentoPaq, gbc_DescuentoPaq);
		DescuentoPaq.setVisible(false);
		
		TextDescuentoPaq = new JTextArea();
		TextDescuentoPaq.setEditable(false);
		GridBagConstraints gbc_TextDescuentoPaq = new GridBagConstraints();
		gbc_TextDescuentoPaq.gridwidth = 5;
		gbc_TextDescuentoPaq.insets = new Insets(0, 0, 5, 5);
		gbc_TextDescuentoPaq.fill = GridBagConstraints.BOTH;
		gbc_TextDescuentoPaq.gridx = 4;
		gbc_TextDescuentoPaq.gridy = 6;
		getContentPane().add(TextDescuentoPaq, gbc_TextDescuentoPaq);
		TextDescuentoPaq.setVisible(false);
		
		CostoP = new JLabel("Costo Por Persona");
		GridBagConstraints gbc_CostoP = new GridBagConstraints();
		gbc_CostoP.gridwidth = 2;
		gbc_CostoP.insets = new Insets(0, 0, 5, 5);
		gbc_CostoP.gridx = 2;
		gbc_CostoP.gridy = 7;
		getContentPane().add(CostoP, gbc_CostoP);
		CostoP.setVisible(false);
		
		TextCostoP = new JTextArea();
		GridBagConstraints gbc_TextCostoP = new GridBagConstraints();
		gbc_TextCostoP.gridwidth = 5;
		gbc_TextCostoP.insets = new Insets(0, 0, 5, 5);
		gbc_TextCostoP.fill = GridBagConstraints.BOTH;
		gbc_TextCostoP.gridx = 4;
		gbc_TextCostoP.gridy = 7;
		getContentPane().add(TextCostoP, gbc_TextCostoP);
		TextCostoP.setVisible(false);
		
		FechaAlta = new JLabel("Fecha de Alta");
		GridBagConstraints gbc_FechaAlta = new GridBagConstraints();
		gbc_FechaAlta.gridwidth = 2;
		gbc_FechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_FechaAlta.gridx = 2;
		gbc_FechaAlta.gridy = 8;
		getContentPane().add(FechaAlta, gbc_FechaAlta);
		FechaAlta.setVisible(false);
		
		TextFechaAlta = new JTextArea();
		GridBagConstraints gbc_TextFechaAlta = new GridBagConstraints();
		gbc_TextFechaAlta.gridwidth = 5;
		gbc_TextFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_TextFechaAlta.fill = GridBagConstraints.BOTH;
		gbc_TextFechaAlta.gridx = 4;
		gbc_TextFechaAlta.gridy = 8;
		getContentPane().add(TextFechaAlta, gbc_TextFechaAlta);
		TextFechaAlta.setVisible(false);
		
		
		//CARGAR DATOS PAQUETE
		comboBoxPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxPaquete.getSelectedItem()!=null && comboBoxPaquete.getSelectedItem().toString()!="") {
					DatosPaquete.setVisible(true);
					Cancelar.setVisible(true);
					paq = comboBoxPaquete.getSelectedItem().toString();
					NombrePaq.setVisible(true);
					TextNomPaq.setVisible(true);
					TextNomPaq.setText("");
					TextNomPaq.append("" + controlPaq.obtenerDatosPaquete(paq).getNombre());
					DescripcionPaq.setVisible(true);
					TextDesPaq.setText("");
					TextDesPaq.setVisible(true);
					TextDesPaq.append("" + controlPaq.obtenerDatosPaquete(paq).getDescripcion());
					ValidezPaq.setVisible(true);
					TextValidezPaq.setVisible(true);
					TextValidezPaq.setText("");
					TextValidezPaq.append("" + controlPaq.obtenerDatosPaquete(paq).getValidez());
					DescuentoPaq.setVisible(true);
					TextDescuentoPaq.setVisible(true);
					TextDescuentoPaq.setText("");
					TextDescuentoPaq.append("" + controlPaq.obtenerDatosPaquete(paq).getDescuento());
					CostoP.setVisible(true);
					TextCostoP.setVisible(true);
					TextCostoP.setText("");
					TextCostoP.append("" + controlPaq.obtenerDatosPaquete(paq).getCostoPorPersona());
					FechaAlta.setVisible(true);
					TextFechaAlta.setVisible(true);
					TextFechaAlta.setText("");
					TextFechaAlta.append("" + controlPaq.obtenerDatosPaquete(paq).getFechaAlta());
					Categorias.setVisible(true);
					cats.setVisible(true);
					cargarCategoriasPaquete(paq);
				}	
			}
		}); 
		
		//LISTAR ACTIVIDADES PERTENECIENTES AL PAQUETE
		comboBoxPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxPaquete.getSelectedItem() != null && comboBoxPaquete.getSelectedItem().toString()!="") {
					paq = comboBoxPaquete.getSelectedItem().toString();
					SelecAct.setVisible(false);
					comboBoxActividad.setVisible(false);
					btnNewButton.setVisible(false);
					Cancelar.setVisible(true);
					try {
						
						comboBoxActividad.setModel(new DefaultComboBoxModel<String>(controlPaq.listarActividadesDePaquete(paq)));
						comboBoxActividad.addItem("");
						comboBoxActividad.setSelectedItem("");
						SelecAct.setVisible(true);
						comboBoxActividad.setVisible(true);
						btnNewButton.setVisible(true);
					}catch(NoExistenActividadesEnElPaqueteException e1) {
						JOptionPane.showMessageDialog(comboBoxPaquete, e1.getMessage(), "Consulta de Paquete de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		
		SelecAct = new JLabel("SeleccionarActividad");
		GridBagConstraints gbc_SelecAct = new GridBagConstraints();
		gbc_SelecAct.gridwidth = 2;
		gbc_SelecAct.insets = new Insets(0, 0, 5, 5);
		gbc_SelecAct.gridx = 2;
		gbc_SelecAct.gridy = 9;
		getContentPane().add(SelecAct, gbc_SelecAct);
		SelecAct.setVisible(false);
		
		comboBoxActividad = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxActividad = new GridBagConstraints();
		gbc_comboBoxActividad.gridwidth = 7;
		gbc_comboBoxActividad.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividad.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividad.gridx = 4;
		gbc_comboBoxActividad.gridy = 9;
		getContentPane().add(comboBoxActividad, gbc_comboBoxActividad);
		comboBoxActividad.setVisible(false);
		
		Cancelar = new JButton("Salir");
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxPaquete.removeAllItems();
				comboBoxActividad.removeAllItems();
				DatosPaquete.setVisible(false);
				SelecAct.setVisible(false);
				comboBoxActividad.setVisible(false);
				setVisible(false);
				btnNewButton.setVisible(false);
				DatosPaquete.setVisible(false);
				NombrePaq.setVisible(false);
				TextNomPaq.setVisible(false);
				DescripcionPaq.setVisible(false);
				TextDesPaq.setVisible(false);
				ValidezPaq.setVisible(false);
				TextValidezPaq.setVisible(false);
				DescuentoPaq.setVisible(false);
				TextDescuentoPaq.setVisible(false);
				CostoP.setVisible(false);
				TextCostoP.setVisible(false);
				FechaAlta.setVisible(false);
				TextFechaAlta.setVisible(false);
				Categorias.setText("");
				Categorias.setVisible(false);
				cats.setVisible(false);
			}
		});
		
		btnNewButton = new JButton("Consulta de Actividad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxActividad.getSelectedItem().toString()!="" && comboBoxActividad.getSelectedItem().toString()!=null) {
					ConsultaATInternalFrame.cargarDepartamentos();
					ConsultaATInternalFrame.llamadaExterna(comboBoxActividad.getSelectedItem().toString());
				}
			}
		});
		btnNewButton.setVisible(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 11;
		gbc_btnNewButton.gridy = 9;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		cats = new JLabel("Categorias");
		GridBagConstraints gbc_cats = new GridBagConstraints();
		gbc_cats.gridwidth = 2;
		gbc_cats.insets = new Insets(0, 0, 5, 5);
		gbc_cats.gridx = 2;
		gbc_cats.gridy = 10;
		getContentPane().add(cats, gbc_cats);
		cats.setVisible(false);
		
		Categorias = new JTextArea();
		Categorias.setVisible(false);
		Categorias.setEditable(false);
		GridBagConstraints gbc_Categorias = new GridBagConstraints();
		gbc_Categorias.gridwidth = 5;
		gbc_Categorias.insets = new Insets(0, 0, 5, 5);
		gbc_Categorias.fill = GridBagConstraints.BOTH;
		gbc_Categorias.gridx = 4;
		gbc_Categorias.gridy = 10;
		getContentPane().add(Categorias, gbc_Categorias);
		gbc_Cancelar = new GridBagConstraints();
		gbc_Cancelar.insets = new Insets(0, 0, 5, 5);
		gbc_Cancelar.gridx = 9;
		gbc_Cancelar.gridy = 11;
		getContentPane().add(Cancelar, gbc_Cancelar);
		
	}
	
	public void cargarCategoriasPaquete(String paq) {
		try {
			String []categorias114=controlPaq.listarCategoriasPaquete(paq);
			String parallenar="";
			for(int i=0;i<categorias114.length-1;i++) {
				if(!categorias114[i].equals("") && categorias114[i]!=null) {
					parallenar=parallenar+","+categorias114[i];
				}
			}
			if(!parallenar.equals("")) {
				parallenar=parallenar+","+categorias114[categorias114.length-1]+".";				
			}else if(categorias114!=null){
				parallenar=categorias114[categorias114.length-1]+".";
			}
			Categorias.setText(parallenar);
			
		} catch (NoExistenActividadesEnElPaqueteException e) {
		}
	}
	
	public boolean cargarPaquetes(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlPaq.listarPaquetes());
			comboBoxPaquete.setModel(model);
			comboBoxPaquete.addItem("");
			comboBoxPaquete.setSelectedItem("");
			return true;
		}catch(NoExistenPaquetesException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public void llamadaExterna(String nombrePaquete) {
		this.setVisible(true);
		cargarCategoriasPaquete(nombrePaquete);
		comboBoxPaquete.setSelectedItem(nombrePaquete);
		DatosPaquete.setVisible(true);
		Cancelar.setVisible(true);
		SelecAct.setVisible(false);
		comboBoxActividad.setVisible(false);
		btnNewButton.setVisible(false);
		try {
			comboBoxActividad.setModel(new DefaultComboBoxModel<String>(controlPaq.listarActividadesDePaquete(nombrePaquete)));
			comboBoxActividad.addItem("");
			comboBoxActividad.setSelectedItem("");
			SelecAct.setVisible(true);
			comboBoxActividad.setVisible(true);
			btnNewButton.setVisible(true);
		}catch(NoExistenActividadesEnElPaqueteException e1) {
			JOptionPane.showMessageDialog(comboBoxPaquete, e1.getMessage(), "Consulta de Paquete de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setConsultaActividad(ConsultaActividadTuristica consultaATInternalFrame2) {
		ConsultaATInternalFrame=consultaATInternalFrame2;
	}
}

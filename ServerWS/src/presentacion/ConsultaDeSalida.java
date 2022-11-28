package presentacion;


import javax.swing.JInternalFrame;


import logica.DTSalidasAT;
import logica.IATYST;
import logica.IDepartamento;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.NoExistenDepartamentosException;
import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenSalidasEnActividadException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Font;

public class ConsultaDeSalida extends JInternalFrame {
	private IATYST controlAt;
	private IDepartamento controlDep;
	private JComboBox<String> comboBoxListarDepartamentos;
	private JComboBox<String> comboBoxListarActividades;
	private JComboBox<String> comboBoxListarSalidas;
	private String depto;
	private String act;
	private String sal;
	private JButton Cancelar;
	private JLabel txtDatos;
	private JButton Finalizar;
	private JLabel SeleccionarSalida;
	private JButton Confirmar_Salida;
	private JLabel SeleccionarActividad;
	private JButton Confirmar_departamento;
	private JButton Confirmar_Actividad;
	private JLabel lblNombre;
	private JLabel lblLugar;
	private JLabel lblCantidad;
	private JLabel lblfechaHora;
	private JLabel lblIngreseLaFecha;
	private JTextArea nombreSal;
	private JTextArea lugarSal;
	private JTextArea fechahoraSal;
	private JTextArea cantidadSal;
	private JTextArea fechaAltaSal;
	private JLabel lblCantidadActualDe;
	private JTextArea cantidadActualSal;
	private JLabel labelSeleccione;
	
	public String getDep() {
		return depto;
	}
	public String getAct() {
		return act;
	}
	
	public ConsultaDeSalida(IATYST iAT,IDepartamento iDep) {
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Consulta De Salida Turistica");
		
		controlAt = iAT;
		controlDep= iDep;
		setBounds(100, 100, 544, 325);
		getContentPane().setLayout(null);
		
	// CASO SELECCIONAR SALIDA	
		
		Cancelar = new JButton("Cancelar");
		
		txtDatos = new JLabel("Datos de la salida");
		txtDatos.setHorizontalAlignment(SwingConstants.CENTER);
		txtDatos.setBounds(118, 11, 299, 50);
		getContentPane().add(txtDatos);
		txtDatos.setVisible(false);
		
		 Finalizar = new JButton("Finalizar");
		Finalizar.setBounds(230, 250, 128, 38);
		getContentPane().add(Finalizar);
		Finalizar.setVisible(false);
		
		SeleccionarSalida = new JLabel("Seleccionar salida:");
		SeleccionarSalida.setVisible(false);
		SeleccionarSalida.setBounds(70, 76, 151, 14);
		getContentPane().add(SeleccionarSalida);
		
		comboBoxListarSalidas = new JComboBox<String>();
		comboBoxListarSalidas.setVisible(false);
		comboBoxListarSalidas.setBounds(277, 72, 231, 22);
		getContentPane().add(comboBoxListarSalidas);
		
		Confirmar_Salida = new JButton("Confirmar salida");
		Confirmar_Salida.setVisible(false);
		Confirmar_Salida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxListarSalidas.setVisible(false);
				SeleccionarSalida.setVisible(false);
				Confirmar_Salida.setVisible(false);
				txtDatos.setVisible(true);
				//textArea.setVisible(true);
				
				cantidadActualSal.setVisible(true);
				lblCantidadActualDe.setVisible(true);
				fechaAltaSal.setVisible(true);
				cantidadSal.setVisible(true);
				fechahoraSal.setVisible(true);
				lugarSal.setVisible(true);
				nombreSal.setVisible(true);
				lblIngreseLaFecha.setVisible(true);
				lblCantidad.setVisible(true);
				lblfechaHora.setVisible(true);
				lblLugar.setVisible(true);
				lblNombre.setVisible(true);
				
				Finalizar.setVisible(true);
				Cancelar.setVisible(false);
				sal=comboBoxListarSalidas.getSelectedItem().toString();
				//textArea.append("Nombre de la salida: " + controlAt.obtenerDatosSalida(act,sal).getNombre());
				nombreSal.setText( controlAt.obtenerDatosSalida(act,sal).getNombre());
				//textArea.append("\nCantidad maxima de turistas: " + controlAt.obtenerDatosSalida(act,sal).getCantMaxT());
				cantidadSal.setText(""+ controlAt.obtenerDatosSalida(act,sal).getCantMaxT());
				//textArea.append("\nFecha del alta: "+controlAt.obtenerDatosSalida(act,sal).getFechaAlta());
				fechaAltaSal.setText(controlAt.obtenerDatosSalida(act,sal).getFechaAlta());
				//textArea.append("\nFecha: "+controlAt.obtenerDatosSalida(act,sal).getFecha());
				//textArea.append("\nHora: "+controlAt.obtenerDatosSalida(act,sal).getHora());
				fechahoraSal.setText(controlAt.obtenerDatosSalida(act,sal).getFecha()+"-"+"Hora: "+controlAt.obtenerDatosSalida(act,sal).getHora());
				//textArea.append("\nLugar: "+controlAt.obtenerDatosSalida(act,sal).getLugar());
				lugarSal.setText(controlAt.obtenerDatosSalida(act,sal).getLugar());
				//textArea.append("\nCantidad total de turistas: "+controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas());
				cantidadActualSal.setText(""+controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas());
			}
		});
		Confirmar_Salida.setBounds(199, 164, 196, 38);
		getContentPane().add(Confirmar_Salida);
		
		// CASO SELECCIONAR ACTIVIDAD
		SeleccionarActividad = new JLabel("Seleccione una actividad:");
		SeleccionarActividad.setBounds(70, 76, 151, 14);
		getContentPane().add(SeleccionarActividad);
		SeleccionarActividad.setVisible(false);
		
		comboBoxListarActividades = new JComboBox<String>();
		comboBoxListarActividades.setBounds(277, 71, 231, 22);
		getContentPane().add(comboBoxListarActividades, BorderLayout.CENTER);
		comboBoxListarActividades.setVisible(false);
		
		 Confirmar_departamento = new JButton("Confirmar departamento");
		labelSeleccione = new JLabel("Seleccione un departamento: ");
		labelSeleccione.setBounds(86, 76, 181, 14);
		getContentPane().add(labelSeleccione);
		
		Confirmar_Actividad = new JButton("Confirmar Actividad");
		Confirmar_Actividad.setVisible(false);
		Confirmar_Actividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxListarActividades.setVisible(false);
				SeleccionarActividad.setVisible(false);
				act=comboBoxListarActividades.getSelectedItem().toString();
				try {
					comboBoxListarSalidas.setModel(new DefaultComboBoxModel<String>(controlAt.listarSalidasActividad(getAct())));
					Confirmar_Actividad.setVisible(false);
					comboBoxListarSalidas.setVisible(true);
					SeleccionarSalida.setVisible(true);
					Confirmar_Salida.setVisible(true);
				}catch(NoExistenSalidasEnActividadException e1) {
					JOptionPane.showMessageDialog(comboBoxListarActividades, e1.getMessage(), "Consulta de Salida", JOptionPane.ERROR_MESSAGE);
					comboBoxListarDepartamentos.removeAllItems();
					comboBoxListarActividades.removeAllItems();
					comboBoxListarSalidas.removeAllItems();
					comboBoxListarDepartamentos.setVisible(true);
					comboBoxListarActividades.setVisible(false);
					comboBoxListarSalidas.setVisible(false);
					Confirmar_departamento.setVisible(true);
					Confirmar_Actividad.setVisible(false);
					Confirmar_Salida.setVisible(false);
					labelSeleccione.setVisible(true);
					SeleccionarActividad.setVisible(false);
					SeleccionarSalida.setVisible(false);
					setVisible(false);					
				}
				
			}
		});
		Confirmar_Actividad.setBounds(199, 164, 196, 38);
		getContentPane().add(Confirmar_Actividad);
	
		
		// CASO SELECCIONAR DEPARTAMENTO

		
		comboBoxListarDepartamentos = new JComboBox<String>();
		comboBoxListarDepartamentos.setBounds(287, 72, 231, 23);
		getContentPane().add(comboBoxListarDepartamentos, BorderLayout.CENTER);
		
		
		Confirmar_departamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxListarDepartamentos.setVisible(false);
				labelSeleccione.setVisible(false);
				depto=comboBoxListarDepartamentos.getSelectedItem().toString();
				try {
					comboBoxListarActividades.setModel(new DefaultComboBoxModel<String>(controlDep.listarActividadesDepartamento(getDep())));
					comboBoxListarActividades.setVisible(true);
					SeleccionarActividad.setVisible(true);
					Confirmar_departamento.setVisible(false);
					Confirmar_Actividad.setVisible(true);
				}catch(NoExistenActividadesEnDepartamentoException e1) {
					JOptionPane.showMessageDialog(comboBoxListarDepartamentos, e1.getMessage(), "Consulta de Salida", JOptionPane.ERROR_MESSAGE);

					comboBoxListarActividades.removeAllItems();
					comboBoxListarSalidas.removeAllItems();
					comboBoxListarDepartamentos.setVisible(true);
					comboBoxListarActividades.setVisible(false);
					comboBoxListarSalidas.setVisible(false);
					Confirmar_departamento.setVisible(true);
					Confirmar_Actividad.setVisible(false);
					Confirmar_Salida.setVisible(false);
					labelSeleccione.setVisible(true);
					SeleccionarActividad.setVisible(false);
					SeleccionarSalida.setVisible(false);
				}
			}
		});
		Confirmar_departamento.setBounds(199, 164, 196, 38);
		getContentPane().add(Confirmar_departamento);
		

		// BOTON CANCELAR
		
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxListarDepartamentos.removeAllItems();
				comboBoxListarActividades.removeAllItems();
				comboBoxListarSalidas.removeAllItems();
				comboBoxListarDepartamentos.setVisible(true);
				comboBoxListarActividades.setVisible(false);
				comboBoxListarSalidas.setVisible(false);
				Confirmar_departamento.setVisible(true);
				Confirmar_Actividad.setVisible(false);
				Confirmar_Salida.setVisible(false);
				labelSeleccione.setVisible(true);
				SeleccionarActividad.setVisible(false);
				SeleccionarSalida.setVisible(false);
				setVisible(false);
			}
		});
		Finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxListarDepartamentos.removeAllItems();
				comboBoxListarActividades.removeAllItems();
				comboBoxListarSalidas.removeAllItems();
				comboBoxListarDepartamentos.setVisible(true);
				comboBoxListarActividades.setVisible(false);
				comboBoxListarSalidas.setVisible(false);
				Confirmar_departamento.setVisible(true);
				Confirmar_Actividad.setVisible(false);
				Confirmar_Salida.setVisible(false);
				labelSeleccione.setVisible(true);
				SeleccionarActividad.setVisible(false);
				SeleccionarSalida.setVisible(false);
				txtDatos.setVisible(false);
				
				cantidadActualSal.setVisible(false);
				lblCantidadActualDe.setVisible(false);
				fechaAltaSal.setVisible(false);
				cantidadSal.setVisible(false);
				fechahoraSal.setVisible(false);
				lugarSal.setVisible(false);
				nombreSal.setVisible(false);
				lblIngreseLaFecha.setVisible(false);
				lblCantidad.setVisible(false);
				lblfechaHora.setVisible(false);
				lblLugar.setVisible(false);
				lblNombre.setVisible(false);
				//textArea.setVisible(false);
				//textArea.setText("");
				Finalizar.setVisible(false);
				setVisible(false);
				Cancelar.setVisible(true);
			}
		});
		Cancelar.setBounds(89, 164, 100, 38);
		getContentPane().add(Cancelar);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setVisible(false);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNombre.setBounds(152, 100, 59, 15);
		getContentPane().add(lblNombre);
		
		lblLugar = new JLabel("Lugar");
		lblLugar.setVisible(false);
		lblLugar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLugar.setBounds(155, 125, 34, 15);
		getContentPane().add(lblLugar);
		
		lblfechaHora = new JLabel("Fecha-Hora de la Salida");
		lblfechaHora.setVisible(false);
		lblfechaHora.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblfechaHora.setBounds(90, 150, 177, 15);
		getContentPane().add(lblfechaHora);
		
		lblCantidad = new JLabel("Cantidad maxima de turistas");
		lblCantidad.setVisible(false);
		lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidad.setBounds(86, 175, 181, 15);
		getContentPane().add(lblCantidad);
		
		lblIngreseLaFecha = new JLabel("Ingrese la fecha de alta");
		lblIngreseLaFecha.setVisible(false);
		lblIngreseLaFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIngreseLaFecha.setBounds(97, 200, 170, 15);
		getContentPane().add(lblIngreseLaFecha);
		
		
		
		nombreSal = new JTextArea();
		nombreSal.setVisible(false);
		nombreSal.setEditable(false);
		nombreSal.setBounds(266, 96, 196, 22);
		getContentPane().add(nombreSal);
		
		lugarSal = new JTextArea();
		lugarSal.setVisible(false);
		lugarSal.setEditable(false);
		lugarSal.setBounds(266, 121, 196, 22);
		getContentPane().add(lugarSal);
		
		fechahoraSal = new JTextArea();
		fechahoraSal.setVisible(false);
		fechahoraSal.setEditable(false);
		fechahoraSal.setBounds(266, 146, 196, 22);
		getContentPane().add(fechahoraSal);
		
		cantidadSal = new JTextArea();
		cantidadSal.setVisible(false);
		cantidadSal.setEditable(false);
		cantidadSal.setBounds(266, 171, 196, 22);
		getContentPane().add(cantidadSal);
		
		fechaAltaSal = new JTextArea();
		fechaAltaSal.setVisible(false);
		fechaAltaSal.setEditable(false);
		fechaAltaSal.setBounds(266, 196, 196, 22);
		getContentPane().add(fechaAltaSal);
		
		lblCantidadActualDe = new JLabel("Cantidad actual de Turistas");
		lblCantidadActualDe.setVisible(false);
		lblCantidadActualDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidadActualDe.setBounds(71, 225, 196, 15);
		getContentPane().add(lblCantidadActualDe);
		
		cantidadActualSal = new JTextArea();
		cantidadActualSal.setVisible(false);
		cantidadActualSal.setEditable(false);
		cantidadActualSal.setBounds(266, 221, 196, 22);
		getContentPane().add(cantidadActualSal);
		

		

		
	// FUNCIONES	
	}
	
	public boolean cargarDepartamentos(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlDep.listarDepartamentos());
			comboBoxListarDepartamentos.setModel(model);
			return true;
		}catch(NoExistenDepartamentosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consulta de Salida", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public void llamadaExterna(IDepartamento controlDep, String nombreDepartamento, String nombreActividad, String nombreSalida) throws NoExistenDepartamentosException, NoExistenActividadesEnDepartamentoException {
		comboBoxListarSalidas.setVisible(false);
		comboBoxListarDepartamentos.setVisible(false);
		Confirmar_departamento.setVisible(false);
		SeleccionarSalida.setVisible(false);
		Confirmar_Salida.setVisible(false);
		txtDatos.setVisible(true);
		//textArea.setVisible(true);
		Finalizar.setVisible(true);
		Cancelar.setVisible(false);
		sal=nombreSalida;
		act=nombreActividad;
		cantidadActualSal.setVisible(true);
		lblCantidadActualDe.setVisible(true);
		fechaAltaSal.setVisible(true);
		cantidadSal.setVisible(true);
		fechahoraSal.setVisible(true);
		lugarSal.setVisible(true);
		nombreSal.setVisible(true);
		lblIngreseLaFecha.setVisible(true);
		lblCantidad.setVisible(true);
		lblfechaHora.setVisible(true);
		lblLugar.setVisible(true);
		lblNombre.setVisible(true);
		setVisible(true);
		labelSeleccione.setVisible(false);
		
		//textArea.append("Nombre de la salida: " + controlAt.obtenerDatosSalida(act,sal).getNombre());
		nombreSal.setText( controlAt.obtenerDatosSalida(act,sal).getNombre());
		//textArea.append("\nCantidad maxima de turistas: " + controlAt.obtenerDatosSalida(act,sal).getCantMaxT());
		cantidadSal.setText(""+ controlAt.obtenerDatosSalida(act,sal).getCantMaxT());
		//textArea.append("\nFecha del alta: "+controlAt.obtenerDatosSalida(act,sal).getFechaAlta());
		fechaAltaSal.setText(controlAt.obtenerDatosSalida(act,sal).getFechaAlta());
		//textArea.append("\nFecha: "+controlAt.obtenerDatosSalida(act,sal).getFecha());
		//textArea.append("\nHora: "+controlAt.obtenerDatosSalida(act,sal).getHora());
		fechahoraSal.setText(controlAt.obtenerDatosSalida(act,sal).getFecha()+"-"+"Hora: "+controlAt.obtenerDatosSalida(act,sal).getHora());
		//textArea.append("\nLugar: "+controlAt.obtenerDatosSalida(act,sal).getLugar());
		lugarSal.setText(controlAt.obtenerDatosSalida(act,sal).getLugar());
		//textArea.append("\nCantidad total de turistas: "+controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas());
		if(controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas()==0) {
			cantidadActualSal.setText("0");
		}
		
		cantidadActualSal.setText(""+String.valueOf(controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas()));
		
		
//		textArea.append("Nombre de la salida: " + controlAt.obtenerDatosSalida(act,sal).getNombre());
//		textArea.append("\nCantidad maxima de turistas: " + controlAt.obtenerDatosSalida(act,sal).getCantMaxT());
//		textArea.append("\nFecha del alta: "+controlAt.obtenerDatosSalida(act,sal).getFechaAlta());
//		textArea.append("\nFecha: "+controlAt.obtenerDatosSalida(act,sal).getFecha());
//		textArea.append("\nHora: "+controlAt.obtenerDatosSalida(act,sal).getHora());
//		textArea.append("\nLugar: "+controlAt.obtenerDatosSalida(act,sal).getLugar());
//		textArea.append("\nCantidad total de turistas: "+controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas());
//		setVisible(true);
	}
	public void llamadaExternados(DTSalidasAT sal2) {
		comboBoxListarSalidas.setVisible(false);
		comboBoxListarDepartamentos.setVisible(false);
		Confirmar_departamento.setVisible(false);
		SeleccionarSalida.setVisible(false);
		Confirmar_Salida.setVisible(false);
		txtDatos.setVisible(true);
		//textArea.setVisible(true);
		Finalizar.setVisible(true);
		Cancelar.setVisible(false);
		cantidadActualSal.setVisible(true);
		lblCantidadActualDe.setVisible(true);
		fechaAltaSal.setVisible(true);
		cantidadSal.setVisible(true);
		fechahoraSal.setVisible(true);
		lugarSal.setVisible(true);
		nombreSal.setVisible(true);
		lblIngreseLaFecha.setVisible(true);
		lblCantidad.setVisible(true);
		lblfechaHora.setVisible(true);
		lblLugar.setVisible(true);
		lblNombre.setVisible(true);
		setVisible(true);
		labelSeleccione.setVisible(false);
		
		//textArea.append("Nombre de la salida: " + controlAt.obtenerDatosSalida(act,sal).getNombre());
				nombreSal.setText( sal2.getNombre());
				//textArea.append("\nCantidad maxima de turistas: " + controlAt.obtenerDatosSalida(act,sal).getCantMaxT());
				cantidadSal.setText(""+ sal2.getCantMaxT());
				//textArea.append("\nFecha del alta: "+controlAt.obtenerDatosSalida(act,sal).getFechaAlta());
				fechaAltaSal.setText(sal2.getFechaAlta());
				//textArea.append("\nFecha: "+controlAt.obtenerDatosSalida(act,sal).getFecha());
				//textArea.append("\nHora: "+controlAt.obtenerDatosSalida(act,sal).getHora());
				fechahoraSal.setText(sal2.getFecha()+"-"+"Hora: "+sal2.getHora());
				//textArea.append("\nLugar: "+controlAt.obtenerDatosSalida(act,sal).getLugar());
				lugarSal.setText(sal2.getLugar());
				//textArea.append("\nCantidad total de turistas: "+controlAt.obtenerDatosSalida(act,sal).getCantTotalTuristas());
				if(sal2.getCantTotalTuristas()==0) {
					cantidadActualSal.setText("0");
				}
				
				cantidadActualSal.setText(""+sal2.getCantTotalTuristas());
	}
}


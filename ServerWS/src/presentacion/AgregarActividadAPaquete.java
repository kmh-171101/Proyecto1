package presentacion;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenDepartamentosException;
import excepciones.NoExistenPaquetesException;
import logica.IDepartamento;
import logica.IPaquete;

import java.awt.GridBagConstraints;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AgregarActividadAPaquete extends JInternalFrame {

	private JLabel lblDepartamento;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxDepartamento;
	private JLabel lblPaquete;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPaquete;
	private JLabel lblActividadesAgregar;
	private JButton btnconfirmarDepPaq;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxActividades;
	private JButton btnSalir;
	private JButton btnAtras;
	private JButton btnNewButton;
	private IDepartamento controlDep;
	private IPaquete controlPaq;
	/**
	 * Create the frame.
	 */
	public AgregarActividadAPaquete(IPaquete iPaq,IDepartamento iDep) {
		controlPaq=iPaq;
		controlDep=iDep;
		setBounds(100, 100, 457, 172);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 31, 0, 47, 0, 0, 33, 32, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 27, 27, 27, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		
		setTitle("Agregar Actividad Turistica A Paquete");
		
		lblDepartamento = new JLabel("Seleccione un Departamento");
		lblDepartamento.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.gridwidth = 7;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 0;
		gbc_lblDepartamento.gridy = 1;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);
		
		comboBoxDepartamento = new JComboBox();
		GridBagConstraints gbc_comboBoxDepartamento = new GridBagConstraints();
		gbc_comboBoxDepartamento.gridwidth = 7;
		gbc_comboBoxDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepartamento.gridx = 7;
		gbc_comboBoxDepartamento.gridy = 1;
		getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamento);
		
		lblPaquete = new JLabel("Seleccione un Paquete");
		lblPaquete.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblPaquete = new GridBagConstraints();
		gbc_lblPaquete.gridwidth = 7;
		gbc_lblPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquete.gridx = 0;
		gbc_lblPaquete.gridy = 2;
		getContentPane().add(lblPaquete, gbc_lblPaquete);
		
		comboBoxPaquete = new JComboBox();
		GridBagConstraints gbc_comboBoxPaquete = new GridBagConstraints();
		gbc_comboBoxPaquete.gridwidth = 7;
		gbc_comboBoxPaquete.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquete.gridx = 7;
		gbc_comboBoxPaquete.gridy = 2;
		getContentPane().add(comboBoxPaquete, gbc_comboBoxPaquete);
		
		lblActividadesAgregar = new JLabel("Actividades Disponibles para agregar");
		lblActividadesAgregar.setVisible(false);
		lblActividadesAgregar.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblActividadesAgregar = new GridBagConstraints();
		gbc_lblActividadesAgregar.fill = GridBagConstraints.BOTH;
		gbc_lblActividadesAgregar.gridwidth = 7;
		gbc_lblActividadesAgregar.insets = new Insets(0, 0, 5, 5);
		gbc_lblActividadesAgregar.gridx = 0;
		gbc_lblActividadesAgregar.gridy = 3;
		getContentPane().add(lblActividadesAgregar, gbc_lblActividadesAgregar);
		
		btnconfirmarDepPaq = new JButton("\t\tConfirmar \r\nSeleccion");
		btnconfirmarDepPaq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnconfirmarDepPaq.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String departamento= comboBoxDepartamento.getSelectedItem().toString();
				String paquete= comboBoxPaquete.getSelectedItem().toString();
				DefaultComboBoxModel<String> model;
				try {
					model = new DefaultComboBoxModel<String>(controlPaq.listarActividadesNoPertenecientesAPaquete(departamento,paquete));
					String[] cos=controlPaq.listarActividadesNoPertenecientesAPaqueteConfirmada(departamento,paquete);
					if(cos.length==0) {
						throw new NoExistenActividadesEnDepartamentoException("No existen actividades en el departamento seleccionado");
					}
					comboBoxActividades.setModel(model);
					comboBoxDepartamento.setVisible(false);
					comboBoxPaquete.setVisible(false);
					lblDepartamento.setVisible(false);
					lblPaquete.setVisible(false);
					btnconfirmarDepPaq.setVisible(false);
					lblActividadesAgregar.setVisible(true);
					btnAtras.setVisible(true);
					btnNewButton.setVisible(true);
					comboBoxActividades.setVisible(true);
				}catch(NoExistenActividadesEnDepartamentoException e1) {
					JOptionPane.showMessageDialog(btnAtras, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		comboBoxActividades = new JComboBox();
		comboBoxActividades.setVisible(false);
		GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
		gbc_comboBoxActividades.gridwidth = 6;
		gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividades.gridx = 7;
		gbc_comboBoxActividades.gridy = 3;
		getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);
		GridBagConstraints gbc_btnconfirmarDepPaq = new GridBagConstraints();
		gbc_btnconfirmarDepPaq.gridwidth = 5;
		gbc_btnconfirmarDepPaq.insets = new Insets(0, 0, 5, 0);
		gbc_btnconfirmarDepPaq.gridx = 9;
		gbc_btnconfirmarDepPaq.gridy = 3;
		getContentPane().add(btnconfirmarDepPaq, gbc_btnconfirmarDepPaq);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.gridwidth = 2;
		gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalir.gridx = 3;
		gbc_btnSalir.gridy = 4;
		getContentPane().add(btnSalir, gbc_btnSalir);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxActividades.setVisible(false);
				btnAtras.setVisible(false);
				btnNewButton.setVisible(false);
				comboBoxDepartamento.setVisible(true);
				comboBoxPaquete.setVisible(true);
				btnconfirmarDepPaq.setVisible(true);
				lblDepartamento.setVisible(true);
				lblPaquete.setVisible(true);
			}
		});
		btnAtras.setVisible(false);
		btnAtras.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 0, 5);
		gbc_btnAtras.gridx = 5;
		gbc_btnAtras.gridy = 4;
		getContentPane().add(btnAtras, gbc_btnAtras);
		
		btnNewButton = new JButton("Agregar Actividad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePaq=comboBoxPaquete.getSelectedItem().toString();
				String nombreat=comboBoxActividades.getSelectedItem().toString();
				controlPaq.ingresarActividadAlPaquete(nombreat, nombrePaq);
				JOptionPane.showMessageDialog(comboBoxActividades, "La Actividad Turistica se agrego al Paquete con exito", "Agregar Actividad Turistica a Paquete", JOptionPane.INFORMATION_MESSAGE);
				limpiar();
				setVisible(false);
			}
		});
		btnNewButton.setVisible(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridwidth = 6;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 4;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
	
		
	}
	public boolean cargarDepartamentos(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlDep.listarDepartamentos());
			comboBoxDepartamento.setModel(model);
			return true;
		}catch(NoExistenDepartamentosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public boolean cargarPaquetes(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlPaq.listarPaquetesNoComprados());
			comboBoxPaquete.setModel(model);
			return true;
		}catch(NoExistenPaquetesException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private void limpiar(){
		comboBoxActividades.removeAllItems();
		comboBoxDepartamento.removeAllItems();
		comboBoxPaquete.removeAllItems();
		comboBoxActividades.setVisible(false);
		comboBoxDepartamento.setVisible(true);
		comboBoxPaquete.setVisible(true);
		
		
		btnAtras.setVisible(false);
		btnNewButton.setVisible(false);
		lblActividadesAgregar.setVisible(false);
		btnconfirmarDepPaq.setVisible(true);
		btnSalir.setVisible(true);
		lblDepartamento.setVisible(true);
		lblPaquete.setVisible(true);
	}

}

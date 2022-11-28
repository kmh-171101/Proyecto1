package presentacion;


import java.awt.Font;

import javax.swing.JInternalFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;


import excepciones.NoExistenDepartamentosException;
import excepciones.FechaIncoherenteException;
import excepciones.NoExistenActividadesEnDepartamentoException;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import logica.IDepartamento;
import logica.IATYST;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaSalidaTuristica extends JInternalFrame {
	@SuppressWarnings("unused")
	private JTextField textFieldSalida;
	private JTextField textFieldNombreSalida;
	private JTextField textFieldCantidad;
	private IDepartamento IDep;
	@SuppressWarnings("unused")
	private IATYST IAS;
	private JComboBox<String> comboBoxDepartamentos;
	private JComboBox<String> comboBoxActividades;
	private JTextField textFieldLugar;
	@SuppressWarnings("unused")
	private JTextField textFieldFecha;
	private JTextField textFieldHora;

	/**
	 * Create the frame.
	 */
	public AltaSalidaTuristica(IDepartamento IDepto, IATYST IAST) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
	
	
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);

		setTitle("Alta Salida Turistica");
		IDep = IDepto;
		IAS = IAST;
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{149, 151, 0};
		gridBagLayout.rowHeights = new int[]{26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel labelDepartamento = new JLabel("Departamento");
		labelDepartamento.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelDepartamento = new GridBagConstraints();
		gbc_labelDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_labelDepartamento.gridx = 0;
		gbc_labelDepartamento.gridy = 0;
		getContentPane().add(labelDepartamento, gbc_labelDepartamento);
		
		comboBoxDepartamentos = new JComboBox<String>();
		comboBoxDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> modelo;
				comboBoxActividades.removeAllItems();
				if(comboBoxDepartamentos.getSelectedItem() != null && comboBoxDepartamentos.getSelectedItem().toString() != "") {
					try {
						modelo = new DefaultComboBoxModel<String>(IDep.listarActividadesDepartamento(comboBoxDepartamentos.getSelectedItem().toString()));
						comboBoxActividades.setModel(modelo);
						comboBoxActividades.addItem("");
						comboBoxActividades.setSelectedItem("");
					}catch(NoExistenActividadesEnDepartamentoException e1) {
						//JOptionPane.showMessageDialog(this, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		GridBagConstraints gbc_comboBoxDepartamentos = new GridBagConstraints();
		gbc_comboBoxDepartamentos.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDepartamentos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepartamentos.gridx = 1;
		gbc_comboBoxDepartamentos.gridy = 0;
		getContentPane().add(comboBoxDepartamentos, gbc_comboBoxDepartamentos);
		
		JLabel labelActividades = new JLabel("Actividades Departamento");
		labelActividades.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelActividades = new GridBagConstraints();
		gbc_labelActividades.insets = new Insets(0, 0, 5, 5);
		gbc_labelActividades.gridx = 0;
		gbc_labelActividades.gridy = 1;
		getContentPane().add(labelActividades, gbc_labelActividades);
		
		comboBoxActividades = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
		gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividades.gridx = 1;
		gbc_comboBoxActividades.gridy = 1;
		getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);
		
		JLabel lblNewLabel_2 = new JLabel("Ingresar datos Salida");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		

		JLabel lblNewLabel_4 = new JLabel("Nombre");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textFieldNombreSalida = new JTextField();
		GridBagConstraints gbc_textFieldNombreSalida = new GridBagConstraints();
		gbc_textFieldNombreSalida.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreSalida.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreSalida.gridx = 1;
		gbc_textFieldNombreSalida.gridy = 3;
		getContentPane().add(textFieldNombreSalida, gbc_textFieldNombreSalida);
		textFieldNombreSalida.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Lugar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldLugar = new JTextField();
		GridBagConstraints gbc_textFieldLugar = new GridBagConstraints();
		gbc_textFieldLugar.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldLugar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLugar.gridx = 1;
		gbc_textFieldLugar.gridy = 4;
		getContentPane().add(textFieldLugar, gbc_textFieldLugar);
		textFieldLugar.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		
		JDateChooser DateChooserFecha = new JDateChooser();
		DateChooserFecha.setDateFormatString("dd MMMM yyyy");
		GridBagConstraints gbc_DateChooserFecha = new GridBagConstraints();
		gbc_DateChooserFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_DateChooserFecha.insets = new Insets(0, 0, 5, 0);
		gbc_DateChooserFecha.gridx = 1;
		gbc_DateChooserFecha.gridy = 5;
		getContentPane().add(DateChooserFecha, gbc_DateChooserFecha);
		
		
		
		
		JLabel lblNewLabel_6 = new JLabel("Hora (24hs hh:mm)");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		textFieldHora = new JTextField();
		textFieldHora.setToolTipText("hh:mm");
		GridBagConstraints gbc_textFieldHora = new GridBagConstraints();
		gbc_textFieldHora.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHora.gridx = 1;
		gbc_textFieldHora.gridy = 6;
		getContentPane().add(textFieldHora, gbc_textFieldHora);
		textFieldHora.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Cantidad maxima de turistas");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 7;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		textFieldCantidad = new JTextField();
		GridBagConstraints gbc_textFieldCantidad = new GridBagConstraints();
		gbc_textFieldCantidad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidad.gridx = 1;
		gbc_textFieldCantidad.gridy = 7;
		getContentPane().add(textFieldCantidad, gbc_textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				comboBoxActividades.removeAllItems();
				textFieldNombreSalida.setText(null);
				textFieldLugar.setText(null);
				textFieldCantidad.setText(null);
				textFieldHora.setText(null);
				DateChooserFecha.setCalendar(null);
			}
		});
		
		JLabel lblIngreseLaFecha = new JLabel("Ingrese la fecha de alta");
		lblIngreseLaFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblIngreseLaFecha = new GridBagConstraints();
		gbc_lblIngreseLaFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseLaFecha.gridx = 0;
		gbc_lblIngreseLaFecha.gridy = 8;
		getContentPane().add(lblIngreseLaFecha, gbc_lblIngreseLaFecha);
		
		JDateChooser dateChooserNacimiento = new JDateChooser();
		GridBagConstraints gbc_dateChooserNacimiento = new GridBagConstraints();
		gbc_dateChooserNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserNacimiento.fill = GridBagConstraints.BOTH;
		gbc_dateChooserNacimiento.gridx = 1;
		gbc_dateChooserNacimiento.gridy = 8;
		getContentPane().add(dateChooserNacimiento, gbc_dateChooserNacimiento);
		
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean CBAct = comboBoxActividades.getSelectedItem().toString().isEmpty();
					boolean TFNomSal = textFieldNombreSalida.getText().isEmpty();
					boolean TFLug = textFieldLugar.getText().isEmpty();
					boolean TFCant = textFieldCantidad.getText().isEmpty();
					boolean TFHora = textFieldHora.getText().isEmpty();
					if (CBAct || TFNomSal || TFLug || TFCant || TFHora) {
						JOptionPane.showMessageDialog(btnNewButton_1, "Debe rellenar todos los campos para dar de alta", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
					} else {
						if (IAST.existeSalida(textFieldNombreSalida.getText() )) {
							textFieldNombreSalida.setText(null);
							JOptionPane.showMessageDialog(btnNewButton_1, "El nombre de la salida turistica que ha ingresado se encuentra en uso", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
						} else {
							try {
								LocalDate fecha = DateChooserFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								LocalDate fechaAlta= dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
								LocalTime hora = LocalTime.parse(textFieldHora.getText());
								if (LocalDate.now().isAfter(fecha) || (LocalDate.now().isEqual(fecha) && LocalTime.now().isAfter(hora)) || fechaAlta.isAfter(fecha)) {
									throw new FechaIncoherenteException();
								}
								int cantidad = Integer.parseInt(textFieldCantidad.getText());
								if(cantidad <0) {
									JOptionPane.showMessageDialog(btnNewButton_1, "La Cantidad Maxima debe ser un numero mayor a 0", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
								}else {									
									IAST.darAltaSalida(comboBoxActividades.getSelectedItem().toString(), textFieldNombreSalida.getText(), fecha, textFieldLugar.getText(), cantidad, hora,fechaAlta);
									IAST.setURLSalida( textFieldNombreSalida.getText(),comboBoxActividades.getSelectedItem().toString(),"vacioAct.webp");
									JOptionPane.showMessageDialog(btnNewButton_1, "La salida ha sido dada de Alta Correctamente", "Alta de Salida Turistica", JOptionPane.OK_OPTION, frameIcon);
									setVisible(false);
									comboBoxActividades.removeAllItems();
									textFieldNombreSalida.setText(null);
									textFieldLugar.setText(null);
									textFieldCantidad.setText(null);
									textFieldHora.setText(null);
									DateChooserFecha.setCalendar(null);
									dateChooserNacimiento.setCalendar(null);
								}
								
							} catch (DateTimeParseException e1){
								JOptionPane.showMessageDialog(btnNewButton_1, "La Hora esta en formato incorrecto", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
								textFieldHora.setText(null);
							} catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(btnNewButton_1, "La Cantidad Maxima debe ser un numero", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
								textFieldCantidad.setText(null);
							} catch (NullPointerException e3) {
								JOptionPane.showMessageDialog(btnNewButton_1, "La Fecha esta en formato incorrecto", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
								DateChooserFecha.setCalendar(null);
								dateChooserNacimiento.setCalendar(null);
							} catch (FechaIncoherenteException e4) {
								JOptionPane.showMessageDialog(btnNewButton_1, "Debe ingresar una fecha y hora mayor o igual a la actual y a la de Alta", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
								DateChooserFecha.setCalendar(null);
								dateChooserNacimiento.setCalendar(null);
								
								textFieldHora.setText(null);
							}
							
							
						}					
					}
					
				} catch (NullPointerException e5){
					JOptionPane.showMessageDialog(btnNewButton_1, "Debe seleccionar una Actividad Turistica", "Alta de Salida Turistica", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 9;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		

	}
	public boolean cargarDepartamentos(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(IDep.listarDepartamentos());
			comboBoxDepartamentos.setModel(model);
			comboBoxDepartamentos.addItem("");
			comboBoxDepartamentos.setSelectedItem("");
			return true;
		}catch(NoExistenDepartamentosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consulta de Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

}

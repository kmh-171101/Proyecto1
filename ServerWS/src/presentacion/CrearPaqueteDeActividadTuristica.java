package presentacion;


import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import logica.IPaquete;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import excepciones.ExistePaqueteException;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;
import java.awt.Component;

import java.time.LocalDate;
import java.time.ZoneId;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class CrearPaqueteDeActividadTuristica extends JInternalFrame {
	private IPaquete controlPaq;
	private JTextField textFieldNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldDescuento;
	private JTextField textFieldPeriodo;
	private JLabel lblfecha;
	private JDateChooser dateChooserNacimiento;
	
	public CrearPaqueteDeActividadTuristica(IPaquete iPaq) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

        setTitle("Crear Paquete De Actividad Turistica");
		
		
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{41, 55, 0, 0, 109, 56, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 32, 32, 29, 29, 32, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabelNombre = new JLabel("Ingresar Nombre Del Paquete: ");
		lblNewLabelNombre.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabelNombre = new GridBagConstraints();
		gbc_lblNewLabelNombre.gridwidth = 3;
		gbc_lblNewLabelNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabelNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelNombre.gridx = 1;
		gbc_lblNewLabelNombre.gridy = 1;
		getContentPane().add(lblNewLabelNombre, gbc_lblNewLabelNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 4;
		gbc_textFieldNombre.gridy = 1;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabelDescripcion = new JLabel("Ingrese la Descripcion del Paquete:");
		lblNewLabelDescripcion.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabelDescripcion = new GridBagConstraints();
		gbc_lblNewLabelDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelDescripcion.gridwidth = 3;
		gbc_lblNewLabelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelDescripcion.gridx = 1;
		gbc_lblNewLabelDescripcion.gridy = 2;
		getContentPane().add(lblNewLabelDescripcion, gbc_lblNewLabelDescripcion);
		
		textFieldDescripcion = new JTextField();
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.gridwidth = 3;
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescripcion.gridx = 4;
		gbc_textFieldDescripcion.gridy = 2;
		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblNewLabelDescuento = new JLabel("Ingrese el porcentaje de Descuento del Paquete:");
		lblNewLabelDescuento.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabelDescuento = new GridBagConstraints();
		gbc_lblNewLabelDescuento.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelDescuento.gridwidth = 3;
		gbc_lblNewLabelDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelDescuento.gridx = 1;
		gbc_lblNewLabelDescuento.gridy = 3;
		getContentPane().add(lblNewLabelDescuento, gbc_lblNewLabelDescuento);
		
		textFieldDescuento = new JTextField();
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescuento.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescuento.gridx = 4;
		gbc_textFieldDescuento.gridy = 3;
		getContentPane().add(textFieldDescuento, gbc_textFieldDescuento);
		textFieldDescuento.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese el perido de validez en dias:");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 4;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldPeriodo = new JTextField();
		GridBagConstraints gbc_textFieldPeriodo = new GridBagConstraints();
		gbc_textFieldPeriodo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeriodo.fill = GridBagConstraints.BOTH;
		gbc_textFieldPeriodo.gridx = 4;
		gbc_textFieldPeriodo.gridy = 4;
		getContentPane().add(textFieldPeriodo, gbc_textFieldPeriodo);
		textFieldPeriodo.setColumns(10);
		
		JButton btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		lblfecha = new JLabel("Ingrese la fecha de alta:");
		lblfecha.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblfecha = new GridBagConstraints();
		gbc_lblfecha.anchor = GridBagConstraints.WEST;
		gbc_lblfecha.gridwidth = 3;
		gbc_lblfecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblfecha.gridx = 1;
		gbc_lblfecha.gridy = 5;
		getContentPane().add(lblfecha, gbc_lblfecha);
		
		dateChooserNacimiento = new JDateChooser();
		GridBagConstraints gbc_dateChooserNacimiento = new GridBagConstraints();
		gbc_dateChooserNacimiento.gridwidth = 2;
		gbc_dateChooserNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserNacimiento.fill = GridBagConstraints.BOTH;
		gbc_dateChooserNacimiento.gridx = 4;
		gbc_dateChooserNacimiento.gridy = 5;
		getContentPane().add(dateChooserNacimiento, gbc_dateChooserNacimiento);
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.fill = GridBagConstraints.BOTH;
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.gridx = 3;
		gbc_btnCerrar.gridy = 6;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPaqueteDeActividadTuristica(e);
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 4;
		gbc_btnAceptar.gridy = 6;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		controlPaq= iPaq;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
		setTitle("Crear Paquete De Actividades Turisticas");
		
	}
	
	private boolean checkFormulario() {
		String nombre = this.textFieldNombre.getText();
		String descripcion = this.textFieldDescripcion.getText();
		String descuento = this.textFieldDescuento.getText();
		String periodo = this.textFieldPeriodo.getText();
        
//		 if(this.dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())){
//     		JOptionPane.showMessageDialog(this, "La Fecha Ingresada es Incorrecta", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
//     		return false;
//     	}
		try {
			dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Ingreso invalido de Fehca", "Crear Paquete De Actividades Turisticas",JOptionPane.ERROR_MESSAGE);
        	dateChooserNacimiento.setCalendar(null);
        	return false;
		}
        if (nombre.isEmpty() || descuento.isEmpty() || periodo.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Crear Paquete De Actividades Turisticas",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int numero = Integer.parseInt(descuento);
            if(numero<0 || numero>100) {
            	JOptionPane.showMessageDialog(this, "El descuento debe ser un numero entre 0 y 100", "Crear Paquete De Actividades Turisticas", JOptionPane.ERROR_MESSAGE);
            	return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El descuento debe ser un numero", "Crear Paquete De Actividades Turisticas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
        	Integer.parseInt(periodo);
        	if(Integer.parseInt(periodo)<0) {
        		JOptionPane.showMessageDialog(this, "El periodo debe ser un numero mayor a 0", "Crear Paquete De Actividades Turisticas", JOptionPane.ERROR_MESSAGE);
        		return false;
        	}
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El periodo debe ser un numero", "Crear Paquete De Actividades Turisticas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	
	protected void crearPaqueteDeActividadTuristica(ActionEvent e) {
		String nombre = this.textFieldNombre.getText();
        String descripcion = this.textFieldDescripcion.getText();
        String descuento = this.textFieldDescuento.getText();
        String periodo = this.textFieldPeriodo.getText();
        try {
        	LocalDate fechaNac = this.dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();         	
        	if(checkFormulario()) {
        		try {
        			controlPaq.darAltaPaquete(nombre,descripcion, Integer.parseInt(descuento), Integer.parseInt(periodo),fechaNac);
        			JOptionPane.showMessageDialog(this, "El Paquete se ha creado con exito", "Crear Paquete de Actividades Turisticas", JOptionPane.INFORMATION_MESSAGE);
        			limpiarFormulario();
        			setVisible(false);
        		}catch(ExistePaqueteException e1) {
        			JOptionPane.showMessageDialog(this, e1.getMessage(), "Crear Paquete de Actividades Turisticas", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        }catch(NullPointerException e1) {
			JOptionPane.showMessageDialog(this, "Ingreso invalido de Fehca", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
        	dateChooserNacimiento.setCalendar(null);
		}
	}
	
	private void limpiarFormulario() {
        textFieldNombre.setText("");
        textFieldDescripcion.setText("");
        textFieldDescuento.setText("");
        textFieldPeriodo.setText("");
        dateChooserNacimiento.setCalendar(null);
        
    }
	
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}

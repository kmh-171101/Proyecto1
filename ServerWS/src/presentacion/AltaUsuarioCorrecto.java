package presentacion;


import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

//import org.jdatepicker.impl.JDatePanelImpl;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import logica.IUsuario;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;


public class AltaUsuarioCorrecto extends JInternalFrame {
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCorreo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JDateChooser dateChooserNacimiento;
	private JComboBox comboBoxTuristaProveedor;
	private JLabel labelNacionalidad;
	private JLabel labelDescripcion;
	private JLabel labelLink;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private IUsuario controlUsr;
	private JLabel lblContra;
	private JLabel lblNewLabel_7;
	private JPasswordField password1;
	private JPasswordField password2;

	/**
	 * Create the frame.
	 */
	public AltaUsuarioCorrecto(IUsuario iUsr) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

        setTitle("Alta De Usuario");
		
		
		controlUsr = iUsr;
		setTitle("Alta Usuario");
		setBounds(100, 100, 470, 377);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -10, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel_4 = new JLabel("Ingrese los datos del nuevo Usuario:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridheight = 2;
		gbc_lblNewLabel_4.gridwidth = 13;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Nickname:");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textFieldNickname = new JTextField();
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNickname.gridwidth = 11;
		gbc_textFieldNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 2;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);
		textFieldNickname.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 11;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		
		lblNewLabel_2 = new JLabel("Apellido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 11;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 4;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		
		lblNewLabel_3 = new JLabel("Correo Electrónico:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridwidth = 11;
		gbc_textFieldCorreo.gridx = 1;
		gbc_textFieldCorreo.gridy = 5;
		getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);
		
		lblNewLabel_5 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 6;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		dateChooserNacimiento = new JDateChooser();
		GridBagConstraints gbc_dateChooserNacimiento = new GridBagConstraints();
		gbc_dateChooserNacimiento.gridwidth = 11;
		gbc_dateChooserNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserNacimiento.fill = GridBagConstraints.BOTH;
		gbc_dateChooserNacimiento.gridx = 1;
		gbc_dateChooserNacimiento.gridy = 6;
		getContentPane().add(dateChooserNacimiento, gbc_dateChooserNacimiento);
		
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.gridwidth = 4;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 5;
		gbc_calendar.gridy = 8;
		
		lblContra = new JLabel("Ingrese Contrase\u00F1a:");
		lblContra.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblContra = new GridBagConstraints();
		gbc_lblContra.insets = new Insets(0, 0, 5, 5);
		gbc_lblContra.gridx = 0;
		gbc_lblContra.gridy = 7;
		getContentPane().add(lblContra, gbc_lblContra);
		
		password1 = new JPasswordField();
		password1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_password1 = new GridBagConstraints();
		gbc_password1.gridwidth = 11;
		gbc_password1.insets = new Insets(0, 0, 5, 5);
		gbc_password1.fill = GridBagConstraints.HORIZONTAL;
		gbc_password1.gridx = 1;
		gbc_password1.gridy = 7;
		getContentPane().add(password1, gbc_password1);
		
		lblNewLabel_7 = new JLabel("Confirmar Contrase\u00F1a:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		password2 = new JPasswordField();
		password2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_password2 = new GridBagConstraints();
		gbc_password2.gridwidth = 11;
		gbc_password2.insets = new Insets(0, 0, 5, 5);
		gbc_password2.fill = GridBagConstraints.HORIZONTAL;
		gbc_password2.gridx = 1;
		gbc_password2.gridy = 8;
		getContentPane().add(password2, gbc_password2);
		
		lblNewLabel_6 = new JLabel("Desea dar de alta un Turista o Proveedor?");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 9;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		comboBoxTuristaProveedor = new JComboBox();
		GridBagConstraints gbc_comboBoxTuristaProveedor = new GridBagConstraints();
		gbc_comboBoxTuristaProveedor.gridwidth = 11;
		gbc_comboBoxTuristaProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTuristaProveedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTuristaProveedor.gridx = 1;
		gbc_comboBoxTuristaProveedor.gridy = 9;
		getContentPane().add(comboBoxTuristaProveedor, gbc_comboBoxTuristaProveedor);
		comboBoxTuristaProveedor.addItem("");
		comboBoxTuristaProveedor.addItem("Turista");
		comboBoxTuristaProveedor.addItem("Proveedor");		
		comboBoxTuristaProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxTuristaProveedor.getSelectedItem().toString() == "Turista") {
					labelNacionalidad.setVisible(true);
					textFieldNacionalidad.setVisible(true);
					textFieldNacionalidad.setText(null);
					labelDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					labelLink.setVisible(false);
					textFieldLink.setVisible(false);
				} else if (comboBoxTuristaProveedor.getSelectedItem().toString() == "") {
						labelNacionalidad.setVisible(false);
						textFieldNacionalidad.setVisible(false);
						labelDescripcion.setVisible(false);
						textFieldDescripcion.setVisible(false);
						labelLink.setVisible(false);
						textFieldLink.setVisible(false);
				} else {
					labelNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					labelDescripcion.setVisible(true);
					textFieldDescripcion.setVisible(true);
					textFieldDescripcion.setText(null);
					labelLink.setVisible(true);
					textFieldLink.setVisible(true);
					textFieldLink.setText(null);
				}
			}
		});
		
		labelNacionalidad = new JLabel("Nacionalidad:");
		labelNacionalidad.setFont(new Font("Tahoma", Font.ITALIC, 14));
		labelNacionalidad.setVisible(false);
		GridBagConstraints gbc_labelNacionalidad = new GridBagConstraints();
		gbc_labelNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_labelNacionalidad.gridx = 0;
		gbc_labelNacionalidad.gridy = 10;
		getContentPane().add(labelNacionalidad, gbc_labelNacionalidad);
		
		textFieldNacionalidad = new JTextField();
		textFieldNacionalidad.setVisible(false);
		GridBagConstraints gbc_textFieldNacionalidad = new GridBagConstraints();
		gbc_textFieldNacionalidad.gridwidth = 11;
		gbc_textFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNacionalidad.gridx = 1;
		gbc_textFieldNacionalidad.gridy = 10;
		getContentPane().add(textFieldNacionalidad, gbc_textFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);
		
		labelDescripcion = new JLabel("Descripción:");
		labelDescripcion.setVisible(false);
		labelDescripcion.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_labelDescripcion = new GridBagConstraints();
		gbc_labelDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDescripcion.gridx = 0;
		gbc_labelDescripcion.gridy = 11;
		getContentPane().add(labelDescripcion, gbc_labelDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setVisible(false);
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.gridwidth = 11;
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcion.gridx = 1;
		gbc_textFieldDescripcion.gridy = 11;
		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		labelLink = new JLabel("Link a sitio web (opcional):");
		labelLink.setVisible(false);
		labelLink.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_labelLink = new GridBagConstraints();
		gbc_labelLink.insets = new Insets(0, 0, 5, 5);
		gbc_labelLink.gridx = 0;
		gbc_labelLink.gridy = 12;
		getContentPane().add(labelLink, gbc_labelLink);
		
		textFieldLink = new JTextField();
		textFieldLink.setVisible(false);
		GridBagConstraints gbc_textFieldLink = new GridBagConstraints();
		gbc_textFieldLink.gridwidth = 11;
		gbc_textFieldLink.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLink.gridx = 1;
		gbc_textFieldLink.gridy = 12;
		getContentPane().add(textFieldLink, gbc_textFieldLink);
		textFieldLink.setColumns(10);
		
		btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 13;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				darAltaUsuario(e);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.gridwidth = 11;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 13;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	private boolean checkFormulario() {
		
        String nickname = this.textFieldNickname.getText();
        String nombre = this.textFieldNombre.getText();
        String apellido = this.textFieldApellido.getText();
        String correo = this.textFieldCorreo.getText();
        Date fechaNacimiento = this.dateChooserNacimiento.getDate();
        String pass1= this.password1.getText();
        String pass2= this.password2.getText();
        try {
        	if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || pass1.isEmpty() ) {
        		JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
        		return false;
        	}else if(this.dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())){
        		JOptionPane.showMessageDialog(this, "La Fecha Ingresada es Incorrecta", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
        		return false;
        	}
        }catch(NullPointerException e) {
        	JOptionPane.showMessageDialog(this, "Ingreso invalido de Fecha", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
        	dateChooserNacimiento.setCalendar(null);
        	return false;
        }
        
        if (comboBoxTuristaProveedor.getSelectedItem() == "") {
        	JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de Usuario", "Alta Usuario", JOptionPane.ERROR_MESSAGE);
        	return false;
        } else {
        	if ((comboBoxTuristaProveedor.getSelectedItem() == "Turista") && (this.textFieldNacionalidad.getText().isEmpty())) {
        		JOptionPane.showMessageDialog(this, "Debe ingresar la Nacionalidad del Turista", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
                return false;
        	} else {
        		if ((comboBoxTuristaProveedor.getSelectedItem() == "Proveedor") && (this.textFieldDescripcion.getText().isEmpty())) {
        			JOptionPane.showMessageDialog(this, "Debe completar el campo de Descripción del Proveedor", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
                    return false;
        		}
        	}
        } 
        
        if (controlUsr.existeNickname(nickname)) {
    		JOptionPane.showMessageDialog(this, "Ya existe un Usuario con el Nickname ingresado, por favor ingrese uno nuevo.", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
    		return false;
        }
        
        if (controlUsr.existeEmail(correo)) {
    		JOptionPane.showMessageDialog(this, "Ya existe un Usuario con el Email ingresado, por favor ingrese uno nuevo.", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
    		return false;
        }
        
        if(!pass1.equals(pass2)) {
        	JOptionPane.showMessageDialog(this, "Las contrase�as no coinciden.", "Alta Usuario",JOptionPane.ERROR_MESSAGE);
    		password1.setText("");
    		password2.setText("");
        	return false;
        }
        
        return true;
    }
	
	protected void darAltaUsuario(ActionEvent e){
		
        
		
		if(checkFormulario()) {
			String nickname = this.textFieldNickname.getText();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
			String correo = this.textFieldCorreo.getText();
			LocalDate fechaNac = this.dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();        	
			@SuppressWarnings("deprecation")
			String password=this.password1.getText();
			if(comboBoxTuristaProveedor.getSelectedItem().toString() == "Turista") {
				String nacionalidad = this.textFieldNacionalidad.getText();
				controlUsr.darAltaTurista(nickname, nombre, apellido, correo, fechaNac, nacionalidad,password);
			} else {
				String descripcion = this.textFieldDescripcion.getText();
				String link = this.textFieldLink.getText();
				controlUsr.darAltaProveedor(nickname, nombre, apellido, correo, fechaNac, descripcion, link, password);
			}
			controlUsr.setURL(nickname, "visitante.jpg");
			JOptionPane.showMessageDialog(this, "El Usuario se dio de alta con Exito.", "Alta Actividad Turistica", JOptionPane.INFORMATION_MESSAGE);
			limpiarFormulario();
			setVisible(false);
			
		}
	}
	
	private void limpiarFormulario() {
		textFieldNickname.setText(null);
		textFieldNombre.setText(null);
		textFieldApellido.setText(null);
		textFieldCorreo.setText(null);
		labelNacionalidad.setVisible(false);
		textFieldNacionalidad.setVisible(false);
		textFieldNacionalidad.setText(null);
		labelDescripcion.setVisible(false);
		textFieldDescripcion.setVisible(false);
		textFieldDescripcion.setText(null);
		labelLink.setVisible(false);
		textFieldLink.setVisible(false);
		textFieldLink.setText(null);
		comboBoxTuristaProveedor.setSelectedItem("");
		dateChooserNacimiento.setCalendar(null);
	}
	
}

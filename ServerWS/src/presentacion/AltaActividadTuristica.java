package presentacion;


import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import excepciones.ExisteActividadException;
import excepciones.NoExisteDepartamentoException;
import excepciones.NoExisteProveedorException;
import excepciones.NoExistenCategoriasException;
import excepciones.NoExistenDepartamentosException;
import excepciones.NoExistenProveedoresException;
import logica.IATYST;
import logica.IDepartamento;
import logica.IUsuario;
import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class AltaActividadTuristica extends JInternalFrame {
	private IATYST controlAt;
	private IDepartamento controlDep;
	private IUsuario controlUsr;
	private JComboBox<String> comboBoxDepartamentos;
	private JComboBox<String> comboBoxProveedores;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textFieldNombreAct;
	private JTextField textFieldDescripcionAct;
	private JLabel lblNewLabel_4;
	private JTextField textFieldCiudad;
	private JTextField textFieldCosto;
	private JLabel lblNewLabelCosto;
	private JLabel lblNewDuracion;
	private JTextField textFieldDuracion;
	private JDateChooser dateChooserNacimiento;
	private JLabel lblfecha;
	private JLabel Categoria;
	private JComboBox<String> comboBoxCat;
	private JComboBox<String> comboBoxAgregadas;
	private JLabel lblAgregadas;
	private JButton btnQuitar;
	private final JButton btnAgregar = new JButton("Agregar");
	
	
	/**
	 * Create the frame.
	 * @param iAT 
	 */
	public AltaActividadTuristica(IATYST iAT,IDepartamento iDep,IUsuario iUsr) {
		
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		controlAt = iAT;
		controlDep= iDep;
		controlUsr= iUsr;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta Actividad Turistica");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{410, 309, 0, 0};
		gridBagLayout.rowHeights = new int[]{31, 28, 26, 24, 24, 23, 24, 24, 24, 30, 29, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		lblNewLabel_1 = new JLabel("Ingresar Datos de la Actividad Nueva");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblIngreseUnProveedor = new JLabel("Seleccione un Proveedor para el alta:");
		lblIngreseUnProveedor.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblIngreseUnProveedor = new GridBagConstraints();
		gbc_lblIngreseUnProveedor.fill = GridBagConstraints.BOTH;
		gbc_lblIngreseUnProveedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseUnProveedor.gridx = 0;
		gbc_lblIngreseUnProveedor.gridy = 2;
		getContentPane().add(lblIngreseUnProveedor, gbc_lblIngreseUnProveedor);
		
		comboBoxProveedores = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxProveedores = new GridBagConstraints();
		gbc_comboBoxProveedores.gridwidth = 2;
		gbc_comboBoxProveedores.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxProveedores.fill = GridBagConstraints.BOTH;
		gbc_comboBoxProveedores.gridx = 1;
		gbc_comboBoxProveedores.gridy = 2;
		getContentPane().add(comboBoxProveedores, gbc_comboBoxProveedores);
		
		JLabel lblNewLabel = new JLabel("Seleccione un Departamento para el alta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		comboBoxDepartamentos = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxDepartamentos = new GridBagConstraints();
		gbc_comboBoxDepartamentos.gridwidth = 2;
		gbc_comboBoxDepartamentos.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDepartamentos.fill = GridBagConstraints.BOTH;
		gbc_comboBoxDepartamentos.gridx = 1;
		gbc_comboBoxDepartamentos.gridy = 3;
		getContentPane().add(comboBoxDepartamentos, gbc_comboBoxDepartamentos);
		
		lblNewLabel_2 = new JLabel("Nombre de la Actividad Turistica :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textFieldNombreAct = new JTextField();
		GridBagConstraints gbc_textFieldNombreAct = new GridBagConstraints();
		gbc_textFieldNombreAct.gridwidth = 2;
		gbc_textFieldNombreAct.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombreAct.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombreAct.gridx = 1;
		gbc_textFieldNombreAct.gridy = 4;
		getContentPane().add(textFieldNombreAct, gbc_textFieldNombreAct);
		textFieldNombreAct.setColumns(10);
		
		lblNewLabelCosto = new JLabel("Ingrese el costo por Turista:");
		lblNewLabelCosto.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabelCosto = new GridBagConstraints();
		gbc_lblNewLabelCosto.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabelCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelCosto.gridx = 0;
		gbc_lblNewLabelCosto.gridy = 5;
		getContentPane().add(lblNewLabelCosto, gbc_lblNewLabelCosto);
		
		textFieldCosto = new JTextField();
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.gridwidth = 2;
		gbc_textFieldCosto.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
		gbc_textFieldCosto.gridx = 1;
		gbc_textFieldCosto.gridy = 5;
		getContentPane().add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Descripcion de la Actividad Turistica :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 6;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textFieldDescripcionAct = new JTextField();
		GridBagConstraints gbc_textFieldDescripcionAct = new GridBagConstraints();
		gbc_textFieldDescripcionAct.gridwidth = 2;
		gbc_textFieldDescripcionAct.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDescripcionAct.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescripcionAct.gridx = 1;
		gbc_textFieldDescripcionAct.gridy = 6;
		getContentPane().add(textFieldDescripcionAct, gbc_textFieldDescripcionAct);
		textFieldDescripcionAct.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Ciudad donde se realiza la Actividad Turistica :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 7;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		textFieldCiudad = new JTextField();
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.gridwidth = 2;
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCiudad.fill = GridBagConstraints.BOTH;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 7;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);
		
		lblNewDuracion = new JLabel("Ingrese la duracion de la Actividad :");
		lblNewDuracion.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewDuracion = new GridBagConstraints();
		gbc_lblNewDuracion.fill = GridBagConstraints.BOTH;
		gbc_lblNewDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewDuracion.gridx = 0;
		gbc_lblNewDuracion.gridy = 8;
		getContentPane().add(lblNewDuracion, gbc_lblNewDuracion);
		
		textFieldDuracion = new JTextField();
		GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
		gbc_textFieldDuracion.gridwidth = 2;
		gbc_textFieldDuracion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDuracion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDuracion.gridx = 1;
		gbc_textFieldDuracion.gridy = 8;
		getContentPane().add(textFieldDuracion, gbc_textFieldDuracion);
		textFieldDuracion.setColumns(10);
		
		JButton btnCerrar = new JButton("Cancelar");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCerrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        comboBoxDepartamentos.removeAllItems();
		        comboBoxProveedores.removeAllItems();
		        setVisible(false);
		    }
		});
		
		lblfecha = new JLabel("Ingrese la fecha de alta:");
		lblfecha.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblfecha = new GridBagConstraints();
		gbc_lblfecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblfecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblfecha.gridx = 0;
		gbc_lblfecha.gridy = 9;
		getContentPane().add(lblfecha, gbc_lblfecha);
		
		dateChooserNacimiento = new JDateChooser();
		GridBagConstraints gbc_dateChooserNacimiento = new GridBagConstraints();
		gbc_dateChooserNacimiento.gridwidth = 2;
		gbc_dateChooserNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserNacimiento.fill = GridBagConstraints.BOTH;
		gbc_dateChooserNacimiento.gridx = 1;
		gbc_dateChooserNacimiento.gridy = 9;
		getContentPane().add(dateChooserNacimiento, gbc_dateChooserNacimiento);
		
		Categoria = new JLabel("Categorias para agregar:");
		Categoria.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_Categoria = new GridBagConstraints();
		gbc_Categoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_Categoria.insets = new Insets(0, 0, 5, 5);
		gbc_Categoria.gridx = 0;
		gbc_Categoria.gridy = 10;
		getContentPane().add(Categoria, gbc_Categoria);
		
		comboBoxCat = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxCat = new GridBagConstraints();
		gbc_comboBoxCat.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCat.fill = GridBagConstraints.BOTH;
		gbc_comboBoxCat.gridx = 1;
		gbc_comboBoxCat.gridy = 10;
		getContentPane().add(comboBoxCat, gbc_comboBoxCat);
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		
		
		lblAgregadas = new JLabel("Categorias agregadas:");
		lblAgregadas.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblAgregadas = new GridBagConstraints();
		gbc_lblAgregadas.fill = GridBagConstraints.BOTH;
		gbc_lblAgregadas.insets = new Insets(0, 0, 5, 5);
		gbc_lblAgregadas.gridx = 0;
		gbc_lblAgregadas.gridy = 11;
		getContentPane().add(lblAgregadas, gbc_lblAgregadas);
		
		comboBoxAgregadas = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxAgregadas = new GridBagConstraints();
		gbc_comboBoxAgregadas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAgregadas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAgregadas.gridx = 1;
		gbc_comboBoxAgregadas.gridy = 11;
		getContentPane().add(comboBoxAgregadas, gbc_comboBoxAgregadas);
		
		
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCerrar.fill = GridBagConstraints.BOTH;
		gbc_btnCerrar.gridx = 0;
		gbc_btnCerrar.gridy = 12;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
		
		JButton btnAceptar1 = new JButton("Aceptar");
		btnAceptar1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				darAltaActividadTuristica(e);
			}
		});
		GridBagConstraints gbc_btnAceptar1 = new GridBagConstraints();
		gbc_btnAceptar1.gridwidth = 2;
		gbc_btnAceptar1.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar1.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar1.gridx = 1;
		gbc_btnAceptar1.gridy = 12;
		getContentPane().add(btnAceptar1, gbc_btnAceptar1);
		
		gbc_btnAgregar.fill = GridBagConstraints.BOTH;
		gbc_btnAgregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregar.gridx = 2;
		gbc_btnAgregar.gridy = 10;
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCat.getSelectedItem()!=null && comboBoxCat.getSelectedItem()!="") {
					String nuevaCatAct=comboBoxCat.getSelectedItem().toString();
					comboBoxCat.removeItem(nuevaCatAct);
					comboBoxAgregadas.addItem(nuevaCatAct);
					comboBoxAgregadas.setSelectedItem(nuevaCatAct);
				}
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(btnAgregar, gbc_btnAgregar);
		
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCat.getSelectedItem()!=null && comboBoxCat.getSelectedItem()!="") {
					String quitCatAct=comboBoxAgregadas.getSelectedItem().toString();
					comboBoxCat.addItem(quitCatAct);
					comboBoxAgregadas.removeItem(quitCatAct);
				}
			}
		});
		btnQuitar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
		gbc_btnQuitar.fill = GridBagConstraints.BOTH;
		gbc_btnQuitar.insets = new Insets(0, 0, 5, 0);
		gbc_btnQuitar.gridx = 2;
		gbc_btnQuitar.gridy = 11;
		getContentPane().add(btnQuitar, gbc_btnQuitar);
		
	}
	
	
	private boolean checkFormulario() {
		
        String nombreU = this.textFieldNombreAct.getText();
        
        String ciudad = this.textFieldCiudad.getText();
        String descripcion = this.textFieldDescripcionAct.getText();

        String costo = this.textFieldCosto.getText();
        String duracion = this.textFieldDuracion.getText();
        
        try {
			dateChooserNacimiento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Ingreso invalido de Fecha", "Alta Actividad Turistica",JOptionPane.ERROR_MESSAGE);
        	dateChooserNacimiento.setCalendar(null);
        	return false;
		}
        
        if (nombreU.isEmpty() || duracion.isEmpty() || costo.isEmpty() || ciudad.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta Actividad Turistica",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(comboBoxAgregadas.getSelectedItem()==null || comboBoxAgregadas.getSelectedItem()=="") {
        	JOptionPane.showMessageDialog(this, "Tiene que agregar al menos una categoria", "Alta Actividad Turistica",JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        try {
			int cos= Integer.parseInt(costo);
			if(cos<0) {
				JOptionPane.showMessageDialog(this, "El costo debe ser un numero mayor a 0", "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			}
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El costo debe ser un numero", "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
        	int dur= Integer.parseInt(duracion);
        	if(dur<0)
        		 JOptionPane.showMessageDialog(this, "La duracion debe ser un numero mayor a 0", "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duracion debe ser un numero", "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
	
	
	protected void darAltaActividadTuristica(ActionEvent e){
		
	

        
		if(checkFormulario()) {
			String nombreAt = this.textFieldNombreAct.getText();
			String departamento = this.comboBoxDepartamentos.getSelectedItem().toString();
			String ciudad = this.textFieldCiudad.getText();
			String descripcion = this.textFieldDescripcionAct.getText();
			String proveedor = this.comboBoxProveedores.getSelectedItem().toString();
			String duracion= (this.textFieldDuracion.getText());
			String costo= (this.textFieldCosto.getText());
			Date fech=this.dateChooserNacimiento.getDate();
			LocalDate f = fech.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			try {
				Component[] categoriasObt= comboBoxAgregadas.getComponents();
				String[] catOk= new String[categoriasObt.length];
				for(int i=0; i<categoriasObt.length;i++) {
					if(comboBoxAgregadas.getSelectedItem()!=null && comboBoxAgregadas.getSelectedItem()!="") {
						catOk[i]=comboBoxAgregadas.getSelectedItem().toString();						
						comboBoxAgregadas.removeItem(catOk[i]);
					}
				}
				
				controlAt.darAltaActividadTuristica(nombreAt, departamento, ciudad, descripcion, null, proveedor, Integer.parseInt(duracion), Integer.parseInt(costo), f, catOk);
				controlAt.setURLActividad(nombreAt, "vacioAct.webp");
				JOptionPane.showMessageDialog(this, "La Actividad Turistica se creo con exito", "Alta Actividad Turistica", JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				setVisible(false);
				
			} catch (ExisteActividadException  e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
				cargarCategorias();
			} catch(NoExisteDepartamentoException e2) {
				JOptionPane.showMessageDialog(this, e2.getMessage(), "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			} catch(NoExisteProveedorException e3) {
				JOptionPane.showMessageDialog(this, e3.getMessage(), "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	
	public boolean cargarDepartamentos(){
		comboBoxAgregadas.removeAllItems();
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlDep.listarDepartamentos());
			comboBoxDepartamentos.setModel(model);
			return true;
		}catch(NoExistenDepartamentosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			limpiarFormulario();
			setVisible(false);
			return false;
		}
	}
	
	public boolean cargarCategorias(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlAt.listarCategorias());
			comboBoxCat.setModel(model);
			return true;
		}catch(NoExistenCategoriasException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			limpiarFormulario();
			setVisible(false);
			return false;
		}
	}
	
	public boolean cargarProveedores() {
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlUsr.listarProveedores());
			comboBoxProveedores.setModel(model);
			return true;
		} catch (NoExistenProveedoresException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta Actividad Turistica", JOptionPane.ERROR_MESSAGE);
			limpiarFormulario();
			setVisible(false);
			return false;
		}
	}
	
	private void limpiarFormulario() {
        textFieldNombreAct.setText("");
        textFieldCiudad.setText("");
        textFieldDescripcionAct.setText("");
        textFieldCosto.setText("");
        textFieldDuracion.setText("");
        dateChooserNacimiento.setDate(null);
        comboBoxAgregadas.removeAllItems();
    }

}

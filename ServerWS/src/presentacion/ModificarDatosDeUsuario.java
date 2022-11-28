package presentacion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import java.time.ZoneId;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.CampoVacioException;
import excepciones.FechaIncoherenteException;

import excepciones.NoExistenUsuariosException;

import logica.IUsuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class ModificarDatosDeUsuario extends JInternalFrame {
	
	private IUsuario controlUsr;
	private JComboBox<String> comboBoxSeleccionarUsuario;
	private JComboBox<String> comboBoxModificar;
	private String nom;
	private JTextField txtNuevo;

	public ModificarDatosDeUsuario(IUsuario iUsr) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        
        setTitle("Modificar Datos de Usuario");
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		controlUsr = iUsr;
		
		JButton Confirmar_Usuario = new JButton("Confirmar Usuario");
		Confirmar_Usuario.setBounds(184, 228, 184, 42);
		getContentPane().add(Confirmar_Usuario);
		
		JLabel SeleccionarUsuario = new JLabel("Seleccionar Usuario:");
		SeleccionarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		SeleccionarUsuario.setBounds(21, 42, 144, 14);
		getContentPane().add(SeleccionarUsuario);
		
		comboBoxSeleccionarUsuario = new JComboBox<String>();
		comboBoxSeleccionarUsuario.setBounds(175, 38, 193, 22);
		getContentPane().add(comboBoxSeleccionarUsuario);
		
		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setBounds(21, 228, 98, 42);
		getContentPane().add(Cancelar);
		
		JLabel Modificar = new JLabel("Modificar");
		Modificar.setHorizontalAlignment(SwingConstants.CENTER);
		Modificar.setBounds(45, 42, 120, 14);
		getContentPane().add(Modificar);
		Modificar.setVisible(false);
		
		comboBoxModificar = new JComboBox();
		comboBoxModificar.setBounds(175, 38, 193, 22);
		getContentPane().add(comboBoxModificar);
		comboBoxModificar.setVisible(false);
		comboBoxModificar.addItem("Nombre");
		comboBoxModificar.addItem("Apellido");
		comboBoxModificar.addItem("Nacimiento");
		
		JButton BotonModificar = new JButton("Modificar");
		BotonModificar.setBounds(184, 228, 184, 42);
		getContentPane().add(BotonModificar);
		BotonModificar.setVisible(false);
		
		JLabel NuevoNombre = new JLabel("Digite el nuevo nombre:");
		NuevoNombre.setBounds(31, 67, 134, 14);
		getContentPane().add(NuevoNombre);
		NuevoNombre.setVisible(false);
		
		txtNuevo = new JTextField();
		txtNuevo.setText("");
		txtNuevo.setBounds(175, 64, 134, 20);
		getContentPane().add(txtNuevo);
		txtNuevo.setColumns(10);
		txtNuevo.setVisible(false);
		
		JLabel NuevoApellido = new JLabel("Digite el nuevo apellido");
		NuevoApellido.setBounds(31, 67, 134, 14);
		getContentPane().add(NuevoApellido);
		NuevoApellido.setVisible(false);
		
		JButton ConfirmarModificacion = new JButton("Confirmar Modificacion");
		ConfirmarModificacion.setBounds(184, 228, 184, 42);
		getContentPane().add(ConfirmarModificacion);
		ConfirmarModificacion.setVisible(false);
		
		JLabel NuevaFecha = new JLabel("Digite nueva fecha:");
		NuevaFecha.setBounds(31, 67, 134, 14);
		getContentPane().add(NuevaFecha);
		NuevaFecha.setVisible(false);
		
		JDateChooser calendario = new JDateChooser();
		calendario.setDateFormatString("dd/MM/yyyy");
		calendario.setBounds(175, 64, 193, 20);
		getContentPane().add(calendario);
		calendario.setVisible(false);
		
		//ACCION CONFIRMAR MODIFICACIONES
		ConfirmarModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String opc=comboBoxModificar.getSelectedItem().toString();
					if(opc=="Nombre") {
						String NuevoNom=txtNuevo.getText();
						if (txtNuevo.getText().isEmpty()) {
							throw new CampoVacioException();							
						}
						controlUsr.modificarNombreUsuario(NuevoNom,nom);
						JOptionPane.showMessageDialog(ConfirmarModificacion, "Se ha modificado el Nombre correctamente", "Modificar Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
						txtNuevo.setText("");
						comboBoxSeleccionarUsuario.removeAll();
						Modificar.setVisible(false);
						comboBoxModificar.setVisible(false);
						BotonModificar.setVisible(false);
						NuevoApellido.setVisible(false);
						NuevoNombre.setVisible(false);
						setVisible(false);
						txtNuevo.setVisible(false);
						comboBoxSeleccionarUsuario.setVisible(true);
						SeleccionarUsuario.setVisible(true);
						Confirmar_Usuario.setVisible(true);
						Cancelar.setVisible(true);
					}else if(opc=="Apellido") {
						if (txtNuevo.getText().isEmpty()) {
							throw new CampoVacioException();							
						}
						String NuevoApe=txtNuevo.getText();
						controlUsr.modificarApellidoUsuario(NuevoApe, nom);
						JOptionPane.showMessageDialog(ConfirmarModificacion, "Se ha modificado el Apellido correctamente", "Modificar Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
						comboBoxSeleccionarUsuario.removeAll();
						Modificar.setVisible(false);
						comboBoxModificar.setVisible(false);
						BotonModificar.setVisible(false);
						NuevoNombre.setVisible(false);
						NuevoApellido.setVisible(false);
						setVisible(false);
						txtNuevo.setVisible(false);
						comboBoxSeleccionarUsuario.setVisible(true);
						SeleccionarUsuario.setVisible(true);
						Confirmar_Usuario.setVisible(true);
						Cancelar.setVisible(true);
						txtNuevo.setText("");
					}else {
						LocalDate nuevo=calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						if (nuevo.isAfter(LocalDate.now())) {
							throw new FechaIncoherenteException();
						}
						controlUsr.modificarNacimientoUsuario(nuevo, nom);
						JOptionPane.showMessageDialog(ConfirmarModificacion, "Se ha modificado la Fecha de Nacimiento correctamente", "Modificar Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
						comboBoxSeleccionarUsuario.removeAll();
						setVisible(false);
						Modificar.setVisible(false);
						comboBoxModificar.setVisible(false);
						BotonModificar.setVisible(false);
						NuevoNombre.setVisible(false);
						NuevoApellido.setVisible(false);
						calendario.setVisible(false);
						NuevaFecha.setVisible(false);
						txtNuevo.setVisible(false);
						comboBoxSeleccionarUsuario.setVisible(true);
						SeleccionarUsuario.setVisible(true);
						Confirmar_Usuario.setVisible(true);
						Cancelar.setVisible(true);
						calendario.setCalendar(null);
					}
					
				} catch (CampoVacioException | NullPointerException e2) {
					JOptionPane.showMessageDialog(ConfirmarModificacion, "El campo a modificar no puede estar vacio", "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
				} catch (FechaIncoherenteException e3) {
					JOptionPane.showMessageDialog(ConfirmarModificacion, "Debe ingresar una fecha valida", "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
					calendario.setCalendar(null);
				}
				
			}
		});		
		//ACCION BOTON MODIFICAR
		BotonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opc=comboBoxModificar.getSelectedItem().toString();
				if(opc=="Nombre") {
					NuevoNombre.setVisible(true);
					txtNuevo.setVisible(true);
					ConfirmarModificacion.setVisible(true);
					Modificar.setVisible(false);
					comboBoxModificar.setVisible(false);
					BotonModificar.setVisible(false);
				}else if(opc=="Apellido") {
					NuevoApellido.setVisible(true);
					txtNuevo.setVisible(true);
					ConfirmarModificacion.setVisible(true);
					Modificar.setVisible(false);
					comboBoxModificar.setVisible(false);
					BotonModificar.setVisible(false);
				}else {
					NuevaFecha.setVisible(true);
					calendario.setVisible(true);
					ConfirmarModificacion.setVisible(true);
					Modificar.setVisible(false);
					comboBoxModificar.setVisible(false);
					BotonModificar.setVisible(false);
				}
				
			}
		});
		
		// ACCCION BOTON CANCELAR
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSeleccionarUsuario.removeAll();
				comboBoxModificar.removeAll();
				setVisible(false);
				txtNuevo.setText("");
				Modificar.setVisible(false);
				comboBoxModificar.setVisible(false);
				BotonModificar.setVisible(false);
				NuevoNombre.setVisible(false);
				NuevoApellido.setVisible(false);
				NuevaFecha.setVisible(false);
				calendario.setVisible(false);
				calendario.setCalendar(null);
				txtNuevo.setVisible(false);
				comboBoxSeleccionarUsuario.setVisible(true);
				SeleccionarUsuario.setVisible(true);
				Confirmar_Usuario.setVisible(true);
				Cancelar.setVisible(true);
				
			}
		});
		

		// ACCION BOTON CONFIRMAR
		Confirmar_Usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSeleccionarUsuario.setVisible(false);
				nom=comboBoxSeleccionarUsuario.getSelectedItem().toString();
				SeleccionarUsuario.setVisible(false);
				Cancelar.setVisible(true);
				Confirmar_Usuario.setVisible(false);
				Modificar.setVisible(true);
				comboBoxModificar.setVisible(true);
				BotonModificar.setVisible(true);
			}
		});
	}
	
	public boolean cargarUsuarios() {
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlUsr.listarUsuarios());
			comboBoxSeleccionarUsuario.setModel(model);
			return true;
		} catch (NoExistenUsuariosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Modificar Datos de Usuario", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}

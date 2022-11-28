package presentacion;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import excepciones.ExcedeCapacidadException;
import excepciones.NoExistenActividadesEnDepartamentoException;
import excepciones.NoExistenDepartamentosException;
import excepciones.NoExistenUsuariosException;
import excepciones.NoHaySalidasDisponiblesException;
import excepciones.YaTieneInscripcionAEstaSalidaException;
import logica.DTSalidasAT;
import logica.IATYST;
import logica.IDepartamento;
import logica.IUsuario;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import javax.swing.JTextField;



@SuppressWarnings("serial")
public class InscripcionASalidaTuristica extends JInternalFrame {
	private IATYST controlAt;
	private IUsuario controlUsr;
	private IDepartamento controlDep;
	private JComboBox<String> comboBoxDepartamento;
	private JComboBox<String> comboBoxActividades;
	private JComboBox<String> comboBoxSalidasDisponibles;
	private JComboBox<String> comboBoxTuristas;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAcepTurisSal;
	private JButton btnAceptarDepartamento;
	private JButton btnAceptarActividades;
	private JButton btnCancelar;
	private JLabel lblInfoDatos;
	private JLabel lblSeleccionarSalida;
	private JLabel lblSeleccionarTurista;
	private JLabel lblSeleccionarActividad;
	private JLabel lblSeleccioneDep;
	private JLabel lblCantidadIntegrantes;
	private JTextField textFieldIntegrantes;
	private DefaultTableModel modelo;
	
	/**
	 * Create the frame.
	 */
	public InscripcionASalidaTuristica(IATYST iAT, IDepartamento iDep, IUsuario iUsr) {
		
		controlAt = iAT;
		controlDep= iDep;
		controlUsr= iUsr;
		
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);

        setTitle("Inscripcion A Salida Turistica");
        
		setBounds(100, 100, 599, 307);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{83, 0, 0, 65, 68, 103, 0};
		gridBagLayout.rowHeights = new int[]{31, 0, 0, 0, 31, 33, 32, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Object[] columnas= {"Nombre", "Cantidad Maxima Turistas","Fecha Alta","Fecha y Hora a Realizarse","Lugar"};
		modelo= new DefaultTableModel();
		modelo.setColumnIdentifiers(columnas);
		
		lblCantidadIntegrantes = new JLabel("Ingrese el total de Integrantes");
		lblCantidadIntegrantes.setVisible(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		getContentPane().add(scrollPane, gbc_scrollPane);
		table = new JTable(modelo);
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		lblCantidadIntegrantes.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblCantidadIntegrantes = new GridBagConstraints();
		gbc_lblCantidadIntegrantes.fill = GridBagConstraints.BOTH;
		gbc_lblCantidadIntegrantes.gridwidth = 3;
		gbc_lblCantidadIntegrantes.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadIntegrantes.gridx = 0;
		gbc_lblCantidadIntegrantes.gridy = 6;
		getContentPane().add(lblCantidadIntegrantes, gbc_lblCantidadIntegrantes);
		
		lblInfoDatos = new JLabel("Datos de las Salidas Disponibles");
		lblInfoDatos.setVisible(false);
		lblInfoDatos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		GridBagConstraints gbc_lblInfoDatos = new GridBagConstraints();
		gbc_lblInfoDatos.gridwidth = 6;
		gbc_lblInfoDatos.insets = new Insets(0, 0, 5, 0);
		gbc_lblInfoDatos.gridx = 0;
		gbc_lblInfoDatos.gridy = 0;
		getContentPane().add(lblInfoDatos, gbc_lblInfoDatos);
		
		JScrollPane scrl = new JScrollPane();
		scrl.setViewportBorder(UIManager.getBorder("TitledBorder.border"));
		scrl.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrl = new GridBagConstraints();
		gbc_scrl.gridheight = 3;
		gbc_scrl.insets = new Insets(0, 0, 5, 5);
		gbc_scrl.fill = GridBagConstraints.BOTH;
		gbc_scrl.gridx = 11;
		gbc_scrl.gridy = 3;
		
		
		lblSeleccioneDep = new JLabel("Seleccione un Departamento");
		lblSeleccioneDep.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSeleccioneDep = new GridBagConstraints();
		gbc_lblSeleccioneDep.fill = GridBagConstraints.BOTH;
		gbc_lblSeleccioneDep.gridwidth = 2;
		gbc_lblSeleccioneDep.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccioneDep.gridx = 0;
		gbc_lblSeleccioneDep.gridy = 2;
		getContentPane().add(lblSeleccioneDep, gbc_lblSeleccioneDep);
		
		comboBoxDepartamento = new JComboBox<String>();
		comboBoxDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_comboBoxDepartamento = new GridBagConstraints();
		gbc_comboBoxDepartamento.gridwidth = 3;
		gbc_comboBoxDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepartamento.gridx = 2;
		gbc_comboBoxDepartamento.gridy = 2;
		getContentPane().add(comboBoxDepartamento, gbc_comboBoxDepartamento);
		
		//selecciono departamento
		
		btnAceptarDepartamento = new JButton("Confirmar");
		btnAceptarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String depto=comboBoxDepartamento.getSelectedItem().toString();
				try {
					comboBoxActividades.setModel(new DefaultComboBoxModel<String>(controlDep.listarActividadesDepartamento(depto)));
					btnAceptarDepartamento.setVisible(false);
					comboBoxDepartamento.setVisible(false);
					lblSeleccioneDep.setVisible(false);
					
					btnAceptarActividades.setVisible(true);
					comboBoxActividades.setVisible(true);
					lblSeleccionarActividad.setVisible(true);
				}catch(NoExistenActividadesEnDepartamentoException e1){
					JOptionPane.showMessageDialog(scrollPane, e1.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptarDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnAceptarDepartamento = new GridBagConstraints();
		gbc_btnAceptarDepartamento.fill = GridBagConstraints.VERTICAL;
		gbc_btnAceptarDepartamento.insets = new Insets(0, 0, 5, 0);
		gbc_btnAceptarDepartamento.gridx = 5;
		gbc_btnAceptarDepartamento.gridy = 2;
		getContentPane().add(btnAceptarDepartamento, gbc_btnAceptarDepartamento);
		
		lblSeleccionarActividad = new JLabel("Seleccione una Actividad ");
		lblSeleccionarActividad.setVisible(false);
		lblSeleccionarActividad.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSeleccionarActividad = new GridBagConstraints();
		gbc_lblSeleccionarActividad.fill = GridBagConstraints.BOTH;
		gbc_lblSeleccionarActividad.gridwidth = 2;
		gbc_lblSeleccionarActividad.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarActividad.gridx = 0;
		gbc_lblSeleccionarActividad.gridy = 3;
		getContentPane().add(lblSeleccionarActividad, gbc_lblSeleccionarActividad);
		
		//selecciono actividad
		
		btnAceptarActividades = new JButton("Confirmar");
		btnAceptarActividades.setVisible(false);
		btnAceptarActividades.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptarActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreActividad= comboBoxActividades.getSelectedItem().toString();
				DefaultComboBoxModel<String> model2;
				try {
					model2 = new DefaultComboBoxModel<String>(controlUsr.listarTuristas());
					comboBoxTuristas.setModel(model2);
					
					try {
						DTSalidasAT[] salidas= controlAt.listarDatosSalidasVigentes(nombreActividad);
						
						Object[] obj= new Object[5];
						for (int i=0;i<salidas.length;i++) {			
							obj[0]=salidas[i].getNombre();
							obj[1]=salidas[i].getCantMaxT();
							obj[2]=salidas[i].getFechaAlta();
							obj[3]=salidas[i].getFecha()+ " , "+salidas[i].getHora();
							obj[4]=salidas[i].getLugar();
							modelo.addRow(obj);
						}
						DefaultComboBoxModel<String> model1;
						String[] salidasNombre= new String[salidas.length];
						for(int k=0; k<salidas.length;k++) {
							salidasNombre[k]=salidas[k].getNombre();
						}
						model1 = new DefaultComboBoxModel<String>(salidasNombre);
						comboBoxSalidasDisponibles.setModel(model1);
						
						btnAceptarActividades.setVisible(false);
						lblSeleccionarActividad.setVisible(false);
						comboBoxActividades.setVisible(false);
						
						lblInfoDatos.setVisible(true);
						btnAcepTurisSal.setVisible(true);
						table.setVisible(true);
						scrollPane.setVisible(true);
						
						lblSeleccionarTurista.setVisible(true);
						comboBoxTuristas.setVisible(true);
						
						lblCantidadIntegrantes.setVisible(true);
						textFieldIntegrantes.setVisible(true);
						
						lblSeleccionarSalida.setVisible(true);
						comboBoxSalidasDisponibles.setVisible(true);
						
					}catch(NoHaySalidasDisponiblesException e1){
						JOptionPane.showMessageDialog(scrollPane, e1.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
					}
					
				}catch(NoExistenUsuariosException e1) {
					JOptionPane.showMessageDialog(table, e1.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
					limpiar();
				}
				
				
			}
		});
		
		comboBoxActividades = new JComboBox<String>();
		comboBoxActividades.setVisible(false);
		comboBoxActividades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_comboBoxActividades = new GridBagConstraints();
		gbc_comboBoxActividades.gridwidth = 3;
		gbc_comboBoxActividades.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxActividades.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxActividades.gridx = 2;
		gbc_comboBoxActividades.gridy = 3;
		getContentPane().add(comboBoxActividades, gbc_comboBoxActividades);
		GridBagConstraints gbc_btnAceptarActividades = new GridBagConstraints();
		gbc_btnAceptarActividades.fill = GridBagConstraints.VERTICAL;
		gbc_btnAceptarActividades.insets = new Insets(0, 0, 5, 0);
		gbc_btnAceptarActividades.gridx = 5;
		gbc_btnAceptarActividades.gridy = 3;
		getContentPane().add(btnAceptarActividades, gbc_btnAceptarActividades);
		
		lblSeleccionarSalida = new JLabel("Seleccione una de las Salidas Disponibles");
		lblSeleccionarSalida.setVisible(false);
		lblSeleccionarSalida.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSeleccionarSalida = new GridBagConstraints();
		gbc_lblSeleccionarSalida.fill = GridBagConstraints.BOTH;
		gbc_lblSeleccionarSalida.gridwidth = 3;
		gbc_lblSeleccionarSalida.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarSalida.gridx = 0;
		gbc_lblSeleccionarSalida.gridy = 4;
		getContentPane().add(lblSeleccionarSalida, gbc_lblSeleccionarSalida);
		
		comboBoxSalidasDisponibles = new JComboBox<String>();
		comboBoxSalidasDisponibles.setVisible(false);
		comboBoxSalidasDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_comboBoxSalidasDisponibles = new GridBagConstraints();
		gbc_comboBoxSalidasDisponibles.gridwidth = 3;
		gbc_comboBoxSalidasDisponibles.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSalidasDisponibles.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSalidasDisponibles.gridx = 3;
		gbc_comboBoxSalidasDisponibles.gridy = 4;
		getContentPane().add(comboBoxSalidasDisponibles, gbc_comboBoxSalidasDisponibles);
		
		lblSeleccionarTurista = new JLabel("Seleccione el Turista a Inscribir");
		lblSeleccionarTurista.setVisible(false);
		lblSeleccionarTurista.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblSeleccionarTurista = new GridBagConstraints();
		gbc_lblSeleccionarTurista.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSeleccionarTurista.gridwidth = 3;
		gbc_lblSeleccionarTurista.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeleccionarTurista.gridx = 0;
		gbc_lblSeleccionarTurista.gridy = 5;
		getContentPane().add(lblSeleccionarTurista, gbc_lblSeleccionarTurista);
		
		comboBoxTuristas = new JComboBox<String>();
		comboBoxTuristas.setVisible(false);
		GridBagConstraints gbc_comboBoxTuristas = new GridBagConstraints();
		gbc_comboBoxTuristas.gridwidth = 3;
		gbc_comboBoxTuristas.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxTuristas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxTuristas.gridx = 3;
		gbc_comboBoxTuristas.gridy = 5;
		getContentPane().add(comboBoxTuristas, gbc_comboBoxTuristas);
		
		//selecciono y compruebo todo
		
		btnAcepTurisSal = new JButton("Inscribir");
		btnAcepTurisSal.setVisible(false);
		btnAcepTurisSal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//comienza lo dificil
				
				if(checkFormulario()) { //esta listo para dar el alta
					String nombreTurista= (String) comboBoxTuristas.getSelectedItem();
					String nombreActividad= (String) comboBoxActividades.getSelectedItem();
					int integrantes=  Integer.parseInt(textFieldIntegrantes.getText());
					String nombreSalida=(String) comboBoxSalidasDisponibles.getSelectedItem();
					try {						
						controlAt.inscribirASalida(nombreTurista,nombreActividad,nombreSalida, integrantes, LocalDate.now());
						
						JOptionPane.showMessageDialog(comboBoxSalidasDisponibles, "La Inscripcion se realizo con exito", "Inscripcion a Salida Turistica", JOptionPane.INFORMATION_MESSAGE);
						limpiar();
						setVisible(false);
					
					}catch(ExcedeCapacidadException e1) {
						JOptionPane.showMessageDialog(table, e1.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
					}catch(YaTieneInscripcionAEstaSalidaException e2) {
						JOptionPane.showMessageDialog(table, e2.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		textFieldIntegrantes = new JTextField();
		textFieldIntegrantes.setVisible(false);
		GridBagConstraints gbc_textFieldIntegrantes = new GridBagConstraints();
		gbc_textFieldIntegrantes.gridwidth = 2;
		gbc_textFieldIntegrantes.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIntegrantes.fill = GridBagConstraints.BOTH;
		gbc_textFieldIntegrantes.gridx = 3;
		gbc_textFieldIntegrantes.gridy = 6;
		getContentPane().add(textFieldIntegrantes, gbc_textFieldIntegrantes);
		textFieldIntegrantes.setColumns(10);
		btnAcepTurisSal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnAcepTurisSal = new GridBagConstraints();
		gbc_btnAcepTurisSal.fill = GridBagConstraints.BOTH;
		gbc_btnAcepTurisSal.insets = new Insets(0, 0, 5, 5);
		gbc_btnAcepTurisSal.gridx = 4;
		gbc_btnAcepTurisSal.gridy = 7;
		getContentPane().add(btnAcepTurisSal, gbc_btnAcepTurisSal);
		
		btnCancelar= new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 7;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
	}
	
	public boolean cargarDepartamentos(){
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlDep.listarDepartamentos());
			comboBoxDepartamento.setModel(model);
			return true;
		}catch(NoExistenDepartamentosException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private void limpiar() {
		btnAcepTurisSal.setVisible(false);
		btnAceptarActividades.setVisible(false);
		scrollPane.setVisible(false);
		table.setVisible(false);
		lblInfoDatos.setVisible(false);
		lblSeleccionarSalida.setVisible(false);
		lblSeleccionarTurista.setVisible(false);
		lblSeleccionarActividad.setVisible(false);
		comboBoxSalidasDisponibles.setVisible(false);
		comboBoxTuristas.setVisible(false);
		comboBoxActividades.setVisible(false);
		comboBoxDepartamento.setVisible(true);
		lblSeleccioneDep.setVisible(true);
		btnAceptarDepartamento.setVisible(true);
		textFieldIntegrantes.setVisible(false);
		lblCantidadIntegrantes.setVisible(false);
		
		for (int i = 0; i < table.getRowCount(); i++) {
			modelo.removeRow(i);
			i-=1;
		}
		dispose();
	}
	
	private boolean checkFormulario() {
		
        String integrantes = this.textFieldIntegrantes.getText();
        if (integrantes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Inscripcion a Salida Turistica",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            Integer.parseInt(integrantes);
            if(Integer.parseInt(integrantes)<1) {
            	JOptionPane.showMessageDialog(this, "Debe haber al menos un integrante en la inscripcion", "Inscripcion a Salida Turistica",JOptionPane.ERROR_MESSAGE);
            	return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Integrantes debe ser un numero", "Inscripcion a Salida Turistica", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
    }
}

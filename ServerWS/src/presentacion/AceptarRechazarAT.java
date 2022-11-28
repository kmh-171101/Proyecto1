package presentacion;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.NoExistenActividadesException;
import excepciones.NoExistenDepartamentosException;
import logica.IATYST;

import java.awt.Insets;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AceptarRechazarAT extends JInternalFrame {

	private JComboBox<String> comboBox;
	private IATYST controlAt;
	private JButton Confirmar;
	/**
	 * Create the frame.
	 */
	public AceptarRechazarAT(IATYST iAT) {
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Aceptar o Rechazar Actividad");
		controlAt=iAT;
		getContentPane().setFont(new Font("Tahoma", Font.ITALIC, 14));
		setBounds(100, 100, 533, 160);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Seleccione una Actividad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		
		
		JButton Rechazar = new JButton("Rechazar Actividad");
		Rechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna Actividad", "Aceptar o Rechazar Actividad",JOptionPane.ERROR_MESSAGE);
				
				}else {
					controlAt.cambiarEstado(logica.ActividadAT.Estado.Rechazada, comboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Se cambio su estado a Rechazada correctamente", "Aceptar o Rechazar Actividad",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
		GridBagConstraints gbc_Rechazar = new GridBagConstraints();
		gbc_Rechazar.insets = new Insets(0, 0, 5, 5);
		gbc_Rechazar.gridx = 5;
		gbc_Rechazar.gridy = 2;
		getContentPane().add(Rechazar, gbc_Rechazar);
		
		Confirmar = new JButton("Confirmar Actividad");
		Confirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna Actividad", "Aceptar o Rechazar Actividad",JOptionPane.ERROR_MESSAGE);
				}else {
					controlAt.cambiarEstado(logica.ActividadAT.Estado.Confirmada, comboBox.getSelectedItem().toString());
					JOptionPane.showMessageDialog(null, "Se cambio su estado a Confirmada correctamente", "Aceptar o Rechazar Actividad",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
				}
			}
		});
		GridBagConstraints gbc_Confirmar = new GridBagConstraints();
		gbc_Confirmar.insets = new Insets(0, 0, 5, 0);
		gbc_Confirmar.gridx = 8;
		gbc_Confirmar.gridy = 2;
		getContentPane().add(Confirmar, gbc_Confirmar);
		
		JButton Cancelar = new JButton("CANCELAR");
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GridBagConstraints gbc_Cancelar = new GridBagConstraints();
		gbc_Cancelar.insets = new Insets(0, 0, 0, 5);
		gbc_Cancelar.gridx = 2;
		gbc_Cancelar.gridy = 3;
		getContentPane().add(Cancelar, gbc_Cancelar);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setSelectedItem(null);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBox, gbc_comboBox);

	}
	public boolean cargarActividades() {
		DefaultComboBoxModel<String> model;
		try {
			model = new DefaultComboBoxModel<String>(controlAt.listarActividadesAgregadas());
			comboBox.setModel(model);
			return true;
		}catch(NoExistenActividadesException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Aceptar o Rechazar Actividad", JOptionPane.ERROR_MESSAGE);
			setVisible(false);
			return false;
		}
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;

import model.Configuration;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class ConfigurationDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private InputPanel ip;
	private Configuration conf;
	private JCheckBox chckbxDummyInterface;

	/**
	 * Create the dialog.
	 */
	public ConfigurationDialog(Configuration conf, InputPanel panel) {
		this.ip = panel;
		this.conf = conf;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLocalRole = new JLabel("Local Role");
		lblLocalRole.setBounds(6, 6, 102, 28);
		contentPanel.add(lblLocalRole);
		
		textField = new JTextField();
		textField.setBounds(125, 6, 134, 28);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setText(conf.getLocalRoleValue());
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 43, 102, 28);
		contentPanel.add(lblPassword);
		
		JLabel lblCsvSeperator = new JLabel("CSV Seperator");
		lblCsvSeperator.setBounds(6, 83, 102, 28);
		contentPanel.add(lblCsvSeperator);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(125, 46, 134, 28);
		contentPanel.add(textField_1);
		textField_1.setText(conf.getPasswordValue());
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(125, 83, 134, 28);
		contentPanel.add(textField_2);
		textField_2.setText(conf.getCSVSymbol());
		
		JLabel lblLoginPrefix = new JLabel("Login Prefix");
		lblLoginPrefix.setBounds(6, 163, 102, 28);
		contentPanel.add(lblLoginPrefix);
		
		JLabel lblNumberOfUsers = new JLabel("Number of users");
		lblNumberOfUsers.setBounds(6, 203, 118, 28);
		contentPanel.add(lblNumberOfUsers);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(125, 163, 134, 28);
		contentPanel.add(textField_3);
		textField_3.setText("ilias");
//		textField_3.setEditable(false);
		
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(125, 203, 134, 28);
		contentPanel.add(textField_4);
		textField_4.setText("10");
//		textField_4.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 115, 450, 12);
		contentPanel.add(separator);
		
		chckbxDummyInterface = new JCheckBox("Dummy Interface");
		chckbxDummyInterface.setBounds(6, 128, 156, 23);
		chckbxDummyInterface.setSelected(conf.isGenerateDummy());
		chckbxDummyInterface.addActionListener(this);
		contentPanel.add(chckbxDummyInterface);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 227, 450, 12);
		contentPanel.add(separator_1);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			dispose();
		}
		
		if(e.getActionCommand().equals("On")) {
			System.out.println("OK");

		}
		
		if(e.getActionCommand().equals("Dummy Interface")) {
			if(chckbxDummyInterface.isSelected()) {
				ip.getGenerate().setText("Generate XML");
				ip.getGenerate().setEnabled(true);
				ip.getOpen().setEnabled(false);
				System.out.println("OK WE ARE IN");
			}
			else {
				ip.getOpen().setEnabled(true);
				ip.getGenerate().setText("Generate XML");
				ip.getGenerate().setEnabled(false);
			}
		}
		
		if(e.getActionCommand().equals("OK")) {
			conf.setLocalRoleValue(textField.getText());
			conf.setPasswordValue(textField_1.getText());
			conf.setCSVSymbol(textField_2.getText());
			conf.setGenerateDummy(chckbxDummyInterface.isSelected());
			dispose();
		}
	}
}

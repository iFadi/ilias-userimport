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
import java.awt.Color;
import javax.swing.SwingConstants;

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
	private Configuration configuration;
	private JCheckBox chckbxDummyInterface, chckbxLimitedAccess, chckbxStudipCsv;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the dialog.
	 */
	public ConfigurationDialog(Configuration configuration, InputPanel panel) {
		this.ip = panel;
		this.configuration = configuration;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLocalRole = new JLabel("Local Role");
		lblLocalRole.setToolTipText("Add a common local role to all imported users.");
		lblLocalRole.setBounds(6, 6, 102, 28);
		contentPanel.add(lblLocalRole);
		
		textField = new JTextField();
		textField.setBounds(125, 6, 134, 28);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setText(configuration.getLocalRoleValue());
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setToolTipText("Add a password to all imported users.");
		lblPassword.setBounds(6, 43, 102, 28);
		contentPanel.add(lblPassword);
		
		JLabel lblCsvSeperator = new JLabel("CSV Seperator");
		lblCsvSeperator.setBounds(6, 83, 102, 28);
		lblCsvSeperator.setToolTipText("You can change the CSV seperator, i.e. if your imported CSV has ',' als seprator.");
		contentPanel.add(lblCsvSeperator);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(125, 46, 134, 28);
		contentPanel.add(textField_1);
		textField_1.setText(configuration.getPasswordValue());
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(125, 83, 134, 28);
		contentPanel.add(textField_2);
		textField_2.setText(configuration.getCSVSymbol());
		
		JLabel lblLoginPrefix = new JLabel("Login Prefix");
		lblLoginPrefix.setToolTipText("the login user will be for example ilias6 or ilias10.");
		lblLoginPrefix.setBounds(6, 163, 102, 28);
		contentPanel.add(lblLoginPrefix);
		
		JLabel lblNumberOfUsers = new JLabel("Number of users");
		lblNumberOfUsers.setBounds(6, 203, 118, 28);
		lblNumberOfUsers.setToolTipText("Enter the number of the dummy users you want to generate.");
		contentPanel.add(lblNumberOfUsers);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(125, 163, 134, 28);
		contentPanel.add(textField_3);
		textField_3.setText(configuration.getLoginPrefix());
//		textField_3.setEditable(false);
		
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(125, 203, 134, 28);
		contentPanel.add(textField_4);
		textField_4.setText(configuration.getNumberOfUsers()+"");
//		textField_4.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 115, 450, 12);
		contentPanel.add(separator);
		
		chckbxDummyInterface = new JCheckBox("Dummy Interface");
		chckbxDummyInterface.setBounds(6, 128, 156, 23);
		chckbxDummyInterface.setToolTipText("Enable the dummy interface, in order to generate dummy user accounts.");
		chckbxDummyInterface.setSelected(configuration.isGenerateDummy());
		chckbxDummyInterface.addActionListener(this);
		contentPanel.add(chckbxDummyInterface);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 227, 450, 12);
		contentPanel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(260, 0, 11, 121);
		contentPanel.add(separator_2);
		
		chckbxLimitedAccess = new JCheckBox("Limited Access");
		chckbxLimitedAccess.setToolTipText("Enable Limited Access for the users, i.e. account is aktiv only for 1 day, starting at 00:00 until 00:00 next day.");
		chckbxLimitedAccess.setSelected(configuration.isLimited());
		chckbxLimitedAccess.setBounds(271, 8, 128, 23);
		chckbxLimitedAccess.addActionListener(this);
		contentPanel.add(chckbxLimitedAccess);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setToolTipText("example: 01.01.2013");
		lblFrom.setBounds(271, 43, 102, 28);
		contentPanel.add(lblFrom);
		
		JLabel lblUntil = new JLabel("Until");
		lblUntil.setToolTipText("example: 02.01.2013");
		lblUntil.setBounds(271, 83, 102, 28);
		contentPanel.add(lblUntil);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(310, 43, 134, 28);
//		textField_5.setEditable(false);
		contentPanel.add(textField_5);
		textField_5.setText(configuration.getTimeLimitFrom());
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(310, 83, 134, 28);
//		textField_6.setEditable(false);
		contentPanel.add(textField_6);
		textField_6.setText(configuration.getTimeLimitUntil());
		
		chckbxStudipCsv = new JCheckBox("StudIP CSV");
		chckbxStudipCsv.setToolTipText("Can import the CSV exported from StudIP Courses. (German Translated Headers)");
		chckbxStudipCsv.setSelected(configuration.isStudip());
		chckbxStudipCsv.setBounds(271, 128, 128, 23);
		contentPanel.add(chckbxStudipCsv);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(260, 118, 11, 113);
		contentPanel.add(separator_3);
		
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
		if(e.getActionCommand().equals("Limited Access")) {
			if(chckbxLimitedAccess.isSelected()) {
//				textField_5.setEditable(true);
//				textField_6.setEditable(true);
			}
			else {
//				textField_5.setEditable(false);
//				textField_6.setEditable(false);
			}
			
		}
		if(e.getActionCommand().equals("Dummy Interface")) {
			if(chckbxDummyInterface.isSelected()) {
				ip.getGenerate().setText("Generate XML");
				ip.getGenerate().setEnabled(true);
				ip.getOpen().setEnabled(false);
				ip.getOpen().setText("Dummy Mode");
//				textField_3.setEditable(true);
//				textField_4.setEditable(true);
//				System.out.println("OK WE ARE IN");
			}
			else {
				ip.getOpen().setEnabled(true);
				ip.getOpen().setText("Open");
				ip.getGenerate().setText("Input Mode");
				ip.getGenerate().setEnabled(false);
//				textField_3.setEditable(false);
//				textField_4.setEditable(false);
			}
		}
		
		if(e.getActionCommand().equals("OK")) {
			configuration.setLocalRoleValue(textField.getText());
			configuration.setPasswordValue(textField_1.getText());
			configuration.setCSVSymbol(textField_2.getText());
			configuration.setGenerateDummy(chckbxDummyInterface.isSelected());
			configuration.setLoginPrefix(textField_3.getText());
			configuration.setNumberOfUsers(Integer.parseInt(textField_4.getText()));
			configuration.setTimeLimitUnlimited(chckbxLimitedAccess.isSelected());
			configuration.setTimeLimitFrom(textField_5.getText());
			configuration.setTimeLimitUntil(textField_6.getText());
			configuration.setStudip(chckbxStudipCsv.isSelected());
			dispose();
		}
	}
}

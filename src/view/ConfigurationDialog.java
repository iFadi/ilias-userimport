package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Configuration;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;

import javax.swing.Box;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;

/**
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @copyright 2013
 * 
 * TERMS AND CONDITIONS:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
public class ConfigurationDialog extends JDialog implements ActionListener, Observer {

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
	private JCheckBox chckbxDummyInterface, chckbxLimitedAccess, chckbxStudipCsv, chckbxOutput;
	private JTextField textField_5;
	private JTextField textField_6;
	private JComboBox comboBox;
	private JTextField textField_7;
	private JButton okButton;
	private JRadioButton rdbtnRandom, radioButton;

	/**
	 * Create the dialog.
	 */
	public ConfigurationDialog(Configuration configuration, InputPanel panel) {
		this.ip = panel;
		this.configuration = configuration;
		setBounds(100, 100, 450, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		configuration.addObserver(this);
		
		chckbxStudipCsv = new JCheckBox("StudIP CSV");
		comboBox = new JComboBox();

		chckbxStudipCsv.setToolTipText("Can import the CSV exported from StudIP Courses. (German Translated Headers)");
		chckbxStudipCsv.setSelected(configuration.isStudip());
		chckbxStudipCsv.setBounds(271, 166, 128, 23);
		chckbxStudipCsv.addActionListener(this);
		contentPanel.add(chckbxStudipCsv);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"matriculation", "first.lastname", "email", "random"}));
		comboBox.setSelectedItem(configuration.getStudipLogin());
		comboBox.setBounds(310, 203, 134, 27);
		comboBox.addActionListener(this);
		
		contentPanel.add(comboBox);
		

		JLabel lblLocalRole = new JLabel("Local Role");
		lblLocalRole.setToolTipText("Add a common local role to all imported users.");
		lblLocalRole.setBounds(6, 46, 102, 28);
		contentPanel.add(lblLocalRole);
		
		textField = new JTextField();
		textField.setBounds(125, 46, 134, 28);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.setText(configuration.getLocalRoleValue());
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setToolTipText("Add a password to all imported users.");
		lblPassword.setBounds(6, 86, 102, 28);
		contentPanel.add(lblPassword);
		
		JLabel lblCsvSeperator = new JLabel("CSV");
		lblCsvSeperator.setBounds(271, 126, 102, 28);
		lblCsvSeperator.setToolTipText("You can change the CSV seperator, i.e. if your imported CSV has ',' als seprator.");
		contentPanel.add(lblCsvSeperator);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(125, 86, 134, 28);
		contentPanel.add(textField_1);
		textField_1.setText(configuration.getPasswordValue());
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(310, 126, 134, 28);
		contentPanel.add(textField_2);
		textField_2.setText(configuration.getCSVSymbol());
		
		JLabel lblLoginPrefix = new JLabel("Login Prefix");
		lblLoginPrefix.setToolTipText("the login user will be for example ilias6 or ilias10.");
		lblLoginPrefix.setBounds(6, 201, 102, 28);
		contentPanel.add(lblLoginPrefix);
		
		JLabel lblNumberOfUsers = new JLabel("Number of users");
		lblNumberOfUsers.setBounds(6, 241, 118, 28);
		lblNumberOfUsers.setToolTipText("Enter the number of the dummy users you want to generate.");
		contentPanel.add(lblNumberOfUsers);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(125, 201, 134, 28);
		contentPanel.add(textField_3);
		textField_3.setText(configuration.getLoginPrefix());
				
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(125, 241, 134, 28);
		contentPanel.add(textField_4);
		textField_4.setText(configuration.getNumberOfUsers()+"");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 156, 456, 12);
		contentPanel.add(separator);
		
		chckbxDummyInterface = new JCheckBox("Dummy Interface");
		chckbxDummyInterface.setBounds(6, 166, 156, 23);
		chckbxDummyInterface.setToolTipText("Enable the dummy interface, in order to generate dummy user accounts.");
		chckbxDummyInterface.setSelected(configuration.isGenerateDummy());
		chckbxDummyInterface.addActionListener(this);
		contentPanel.add(chckbxDummyInterface);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 276, 450, 7);
		contentPanel.add(separator_1);
		
		chckbxLimitedAccess = new JCheckBox("Limited Access");
		chckbxLimitedAccess.setToolTipText("Enable Limited Access for the users, i.e. account is aktiv only for 1 day, starting at 00:00 until 00:00 next day.");
		if(configuration.getTimeLimitUnlimited() == "0") 
			chckbxLimitedAccess.setSelected(true);
		else
			chckbxLimitedAccess.setSelected(false);
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
		contentPanel.add(textField_5);
		textField_5.setText(configuration.getTimeLimitFrom());
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(310, 83, 134, 28);
		contentPanel.add(textField_6);
		textField_6.setText(configuration.getTimeLimitUntil());
		
		if(chckbxLimitedAccess.isSelected()) {
			textField_5.setEnabled(true);
			textField_6.setEnabled(true);
		}
		else {
			textField_5.setEnabled(false);
			textField_6.setEnabled(false);
		}
		
		if(chckbxDummyInterface.isSelected()) {
			textField_3.setEnabled(true);
			textField_4.setEnabled(true);
			chckbxStudipCsv.setEnabled(false);
			comboBox.setEnabled(false);
		}
		else {
			textField_3.setEnabled(false);
			textField_4.setEnabled(false);
			chckbxStudipCsv.setEnabled(true);
			comboBox.setEnabled(true);
		}

		

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setToolTipText("the login will be for example the matriculation or email.");
		lblLogin.setBounds(271, 201, 102, 28);
		contentPanel.add(lblLogin);
		
		JLabel lblGlobalRole = new JLabel("Global Role");
		lblGlobalRole.setToolTipText("The standard Global Role in ILIAS is USER.");
		lblGlobalRole.setBounds(6, 6, 102, 28);
		contentPanel.add(lblGlobalRole);
		
		textField_7 = new JTextField();
		textField_7.setText(configuration.getGlobalRoleValue());
		textField_7.setColumns(10);
		textField_7.setBounds(125, 6, 134, 28);
		contentPanel.add(textField_7);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(259, 158, 19, 125);
		contentPanel.add(separator_2);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 288, 450, 34);
			contentPanel.add(buttonPane);
			
			chckbxOutput = new JCheckBox("Generate output");
			chckbxOutput.setSelected(configuration.isGenerateOutput());
			chckbxOutput.setToolTipText("If you choose Random Login/Password, you can check this box.");
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
				gl_buttonPane.setHorizontalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_buttonPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(chckbxOutput)
							.addGap(145)
							.addComponent(okButton)
							.addGap(5)
							.addComponent(cancelButton))
				);
				gl_buttonPane.setVerticalGroup(
					gl_buttonPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_buttonPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(okButton)
								.addComponent(chckbxOutput)))
						.addGroup(gl_buttonPane.createSequentialGroup()
							.addGap(5)
							.addComponent(cancelButton))
				);
				buttonPane.setLayout(gl_buttonPane);
				
				rdbtnRandom = new JRadioButton("Random");
				rdbtnRandom.setBounds(103, 128, 141, 23);
				rdbtnRandom.setSelected(configuration.isGeneratePassword());
				contentPanel.add(rdbtnRandom);
				
				radioButton = new JRadioButton("");
				radioButton.setBounds(103, 86, 141, 23);
				contentPanel.add(radioButton);
				cancelButton.addActionListener(this);
			}
		}
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnRandom);
		bg.add(radioButton);
						
		if(chckbxStudipCsv.isSelected()) {
			comboBox.setEnabled(true);
			chckbxDummyInterface.setEnabled(false);
		}
		else {
			comboBox.setEnabled(false);
			chckbxDummyInterface.setEnabled(true);
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) {
			dispose();
		}
		if(e.getActionCommand().equals("Limited Access")) {
			if(chckbxLimitedAccess.isSelected()) {
				textField_5.setEnabled(true);
				textField_6.setEnabled(true);
			}
			else {
				textField_5.setEnabled(false);
				textField_6.setEnabled(false);
			}
			
		}
		if(e.getActionCommand().equals("Dummy Interface")) {
			if(chckbxDummyInterface.isSelected()) {
				ip.getGenerate().setText("Generate XML");
				ip.getGenerate().setEnabled(true);
				ip.getOpen().setEnabled(false);
				ip.getOpen().setText("Dummy Mode");
				textField_3.setEnabled(true);
				textField_4.setEnabled(true);
				chckbxStudipCsv.setEnabled(false);
				comboBox.setEnabled(false);
			}
			else {
				ip.getOpen().setEnabled(true);
				ip.getOpen().setText("Open");
				ip.getGenerate().setText("Input Mode");
				ip.getGenerate().setEnabled(false);
				textField_3.setEnabled(false);
				textField_4.setEnabled(false);
				chckbxStudipCsv.setEnabled(true);
//				comboBox.setEnabled(true);
			}
		}
		
		if(e.getActionCommand().equals("StudIP CSV")) {
			if(chckbxStudipCsv.isSelected()) {
				comboBox.setEnabled(true);
				chckbxDummyInterface.setEnabled(false);
				textField_3.setEnabled(false);
				textField_4.setEnabled(false);
			}
			else {
				comboBox.setEnabled(false);
				chckbxDummyInterface.setEnabled(true);
//				textField_3.setEnabled(true);
//				textField_4.setEnabled(true);
			}
		}
		
		if(e.getActionCommand().equals("OK")) {
			if(!chckbxDummyInterface.isSelected()) {
				ip.getGenerate().setText("Input Mode");
				ip.getGenerate().setEnabled(false);
			}
			
			configuration.setGeneratePassword(rdbtnRandom.isSelected());
			configuration.setGenerateOutput(chckbxOutput.isSelected());
			configuration.setLocalRoleValue(textField.getText());
			configuration.setGlobalRoleValue(textField_7.getText());
			configuration.setPasswordValue(textField_1.getText());
			configuration.setCSVSymbol(textField_2.getText());
			configuration.setGenerateDummy(chckbxDummyInterface.isSelected());
			configuration.setLoginPrefix(textField_3.getText());
			configuration.setNumberOfUsers(Integer.parseInt(textField_4.getText()));
//			configuration.setTimeLimitUnlimited(chckbxLimitedAccess.isSelected());
			configuration.setTimeLimitFrom(textField_5.getText());
			configuration.setTimeLimitUntil(textField_6.getText());
			configuration.setStudip(chckbxStudipCsv.isSelected());
			configuration.setStudipLogin((String)comboBox.getSelectedItem());
			if(chckbxLimitedAccess.isSelected()) // Reverse verhalten in ILIAS
				configuration.setTimeLimitUnlimited("0");
			else 
				configuration.setTimeLimitUnlimited("1");
			dispose();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(rdbtnRandom.isSelected()) {
			textField_1.setEditable(false);
			System.out.println("Randmn");
		}
		else {
			configuration.setGeneratePassword(false);
			textField_1.setEditable(true);
			System.out.println("one p");
		}
		
	}
}

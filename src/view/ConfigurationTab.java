/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * 
 * ConfigurationTab.java
 * 
 * $Author$
 * $Revision$
 * $HeadURL$
 * $Id$
 * $LastChangedDate$
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

public class ConfigurationTab extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1025794571581526405L;
	private Properties properties;
	private JLabel[] label;
	private JTextField[] textField;
	private BufferedInputStream stream;
	
	public ConfigurationTab() {
		JPanel sp = new JPanel(new GridLayout(0,1));
		JPanel lpanel = new JPanel(new GridLayout(0,2));
		JPanel vpanel = new JPanel(new GridLayout(0,2));
		JPanel bpanel = new JPanel(new GridLayout(0,2));
		
		lpanel.setBorder(new TitledBorder("Column Header Title"));
		vpanel.setBorder(new TitledBorder("Values"));
		bpanel.setBorder(new TitledBorder("Auto Generate"));
		
		JButton save = new JButton("Save");
		label = new JLabel[20];
		textField = new JTextField[20];
		int i=0;
		try {

			properties = new Properties();
			stream = new BufferedInputStream(new FileInputStream("configuration.properties"));
			properties.load(stream);
//			stream.close();
			
			for(String key : properties.stringPropertyNames()) {
				  String value = properties.getProperty(key);
//				  System.out.println(key + " = " + value);
				  label[i] = new JLabel(key);
				  textField[i] = new JTextField(value);
				  
				  if(label[i].getText().contains("Label")) {
					  lpanel.add(label[i]);
					  lpanel.add(textField[i]);
				  }
				  else if(label[i].getText().contains("Value")) {
					  vpanel.add(label[i]);
					  vpanel.add(textField[i]);
				  }
				  else {
					  bpanel.add(label[i]);
					  bpanel.add(textField[i]);
				  }
				  i++;
				}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		sp.add(lpanel);
		sp.add(vpanel);
		sp.add(bpanel);
		
		setLayout(new BorderLayout());
		JScrollPane scroll =  new JScrollPane(sp);
		scroll.setBounds(3, 3, 300, 200);
		add(scroll);
		add(save, BorderLayout.AFTER_LAST_LINE);
		save.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) {
			for(int i=0; i<16; i++) {
				properties.setProperty(label[i].getText(), textField[i].getText());
				try {
					properties.store(new FileOutputStream("configuration.properties"), null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(label[i].getText()+" "+ textField[i].getText());
			}
		}
	}

}

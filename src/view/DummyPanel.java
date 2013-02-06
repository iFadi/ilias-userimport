package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GenerateXML;
import model.ParseExcel;

/**
 * $Id$
 * $LastChangedDate$
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @version $Revision$
 * @copyright $Date$
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
public class DummyPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = -335799796636612645L;
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH:mm:ss";
	private JLabel loginLabel;
	private JLabel passwordLabel;
	private JLabel numberLabel;
	private JTextField loginField;
	private JTextField passwordField;
	private JTextField numberField;
	


	private JButton generate;
	private JButton exit;
	private JButton bug;
	private String filename;
	private String dir;
	private String path;
	private GenerateXML xml;
	private ParseExcel excel;
	private View frame;
	public Desktop d;
	
	public DummyPanel(GenerateXML xml, View frame) {
		this.xml = xml;
		this.frame = frame;
		

		
		this.setLayout(new BorderLayout());
		
		loginLabel = new JLabel("Login Prefix: ");
		loginField = new JTextField("ilias");
		passwordLabel = new JLabel("Password: ");
		passwordField = new JTextField("testing");
		numberLabel = new JLabel("Number of Users: ");
		numberField = new JTextField("99");
		generate = new JButton("Generate XML");
		exit = new JButton("Exit");
		bug = new JButton("Bug/Issue Report");
//		generate.setEnabled(false);
		loginLabel.setToolTipText("the login user will be for example ilias6 or ilias99.");
		passwordLabel.setToolTipText("This password applies for all users.");


		this.setBorder(new TitledBorder("Generates XML File to Import in ILIAS e-Learning System"));
		this.add(loginLabel);
		this.add(loginField);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(numberLabel);
		this.add(numberField);
		
		this.add(generate);
//		this.add(bug);
		this.add(exit);
		this.setLayout(new GridLayout(4, 1));
		
		generate.addActionListener(this);
//		open.addActionListener(this);
		exit.addActionListener(this);
		bug.addActionListener(this);
		
		setFilename("dummy_"+now());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Open")) {
			this.Browse();
		}
		if (e.getActionCommand().equals("Generate XML")) {
			try {
				xml.GenerateXMLFile(getFilename()+".xml");
				frame.getStatus().setText("XML File has been Generated");
				frame.getStatus().setForeground(Color.green.darker());
			} catch (Exception e1) {
				e1.printStackTrace();
				frame.getStatus().setText("ERROR");
				frame.getStatus().setForeground(Color.red.darker());
			}
		}
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("Bug/Issue Report")) {
				try {
					 
						URI u;
						d = Desktop.getDesktop();
						u = new URI("http://code.google.com/p/ilias-userimport/issues/list");
						d.browse(u); 
					
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					frame.getStatus().setText("ERROR");
					frame.getStatus().setForeground(Color.red.darker());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					frame.getStatus().setText("ERROR");
					frame.getStatus().setForeground(Color.red.darker());
				}
		}
	}

	public String getFileNameWithoutExtension(String file) {
		int index = file.lastIndexOf('.');
		if (index > 0 && index <= file.length() - 2) {
			return file.substring(0, index);
		}
		return file.substring(0, index);
	}
	
	public void Browse() {
		JFileChooser c = new JFileChooser();
		c.setMultiSelectionEnabled(false);
		c.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel 97-2004", "xls");
		c.setFileFilter(filter);
		// Demonstrate "Open" dialog:
		int rVal = c.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			setFilename(c.getSelectedFile().getName());
			dir = c.getCurrentDirectory().toString();
			setPath(dir + "/" + getFilename());
			try {
				excel.ReadFile(getPath());
//				excel = new ReadExcel(getPath());
				frame.getStatus().setText("READY TO GO");
				frame.getStatus().setForeground(Color.blue.darker());
				generate.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				frame.getStatus().setText("ERROR");
				frame.getStatus().setForeground(Color.red.darker());
			}
			// System.out.println(dir+"/"+getFilename());
		}
	}
	
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public JButton getGenerate() {
		return generate;
	}

	public void setGenerate(JButton generate) {
		this.generate = generate;
	}
	
	public int getNumberField() {
		return Integer.parseInt(numberField.getText());
	}

	public void setNumberField(JTextField numberField) {
		this.numberField = numberField;
	}
	
	public String getLoginField() {
		return loginField.getText();
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}

	public String getPasswordField() {
		return passwordField.getText();
	}

	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}

}

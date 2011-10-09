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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GenerateXML;
import model.ReadExcel;
import model.UpdateNotifier;

/**
 * 
 * UpdateView.java
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.1.0  09/10/2011
 * @copyright 2011
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
public class UpdateView extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177350218996491783L;
	private JButton open;
	private JButton generate;
	private JButton exit;
	private JButton bug;
	private JTextField status;
	private String filename;
	private String dir;
	private String path;
	private GenerateXML xml;
	private ReadExcel excel;
	public Desktop d;

	public UpdateView(ReadExcel excel, GenerateXML xml) throws Exception {
		this.xml = xml;
		this.excel = excel;

		this.setTitle("ILIAS User Import");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setSize(400, 150);
		this.setLocation(500, 100);

		open = new JButton("Open");
		generate = new JButton("DOWNLOAD NOW");
		exit = new JButton("Exit");
		bug = new JButton("Bug/Issue Report");
		status = new JTextField();
		status.setHorizontalAlignment(JTextField.CENTER);
		status.setEditable(false);
		status.setText("NEW VERSION IS AVAILABLE");
		status.setForeground(Color.blue.darker());

		this.add(status, BorderLayout.NORTH);
		panel.setBorder(new TitledBorder("Generates XML File to Import in ILIAS e-Learning System"));
		panel.add(generate);
		panel.add(open);
		panel.add(bug);
		panel.add(exit);
		panel.setLayout(new GridLayout(2, 2));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		panel.setVisible(true);
		this.setVisible(true);

		generate.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		bug.addActionListener(this);
		
		if(!d.isDesktopSupported())
			bug.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Open")) {
			this.Browse();
		}
		if (e.getActionCommand().equals("Generate XML")) {
			try {
				xml.GenerateXML(excel, getFileNameWithoutExtension(filename)+".xml");
				this.getStatus().setText("XML File has been Generated");
				this.getStatus().setForeground(Color.green.darker());
			} catch (Exception e1) {
				e1.printStackTrace();
				this.getStatus().setText("ERROR");
				this.getStatus().setForeground(Color.red.darker());
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
					this.getStatus().setText("ERROR");
					this.getStatus().setForeground(Color.red.darker());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					this.getStatus().setText("ERROR");
					this.getStatus().setForeground(Color.red.darker());
				}
		}
		if (e.getActionCommand().equals("DOWNLOAD NOW")) {
			try {
				 
					URI u;
					d = Desktop.getDesktop();
					u = new URI("http://code.google.com/p/ilias-userimport/downloads/list");
					d.browse(u); 
				
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.getStatus().setText("ERROR");
				this.getStatus().setForeground(Color.red.darker());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				this.getStatus().setText("ERROR");
				this.getStatus().setForeground(Color.red.darker());
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
		int rVal = c.showOpenDialog(UpdateView.this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			filename = c.getSelectedFile().getName();
			dir = c.getCurrentDirectory().toString();
			setPath(dir + "/" + filename);
			try {
				excel.ReadExcel(getPath());
//				excel = new ReadExcel(getPath());
				this.getStatus().setText("READY TO GO");
				this.getStatus().setForeground(Color.blue.darker());
				generate.setText("Generate XML");
				generate.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.getStatus().setText("ERROR");
				this.getStatus().setForeground(Color.red.darker());
			}
			// System.out.println(dir+"/"+filename);
		}
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public JTextField getStatus() {
		return status;
	}

	public void setStatus(JTextField status) {
		this.status = status;
	}
}

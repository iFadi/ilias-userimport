/**
 * 
 */
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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GenerateXML;
import model.ReadExcel;

/**
 * 
 * mainTab.java
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.2.0  04/07/2012
 * @copyright 2012
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
public class mainTab extends JPanel implements ActionListener{

	private static final long serialVersionUID = -335799796636612645L;
	private JButton open;
	private JButton generate;
	private JButton exit;
	private JButton bug;
	private String filename;
	private String dir;
	private String path;
	private GenerateXML xml;
	private ReadExcel excel;
	private View view;
	public Desktop d;
	
	public mainTab(final ReadExcel excel, GenerateXML xml, View view) {
		this.xml = xml;
		this.excel = excel;
		this.view = view;
		
		this.setLayout(new BorderLayout());
		
		open = new JButton("Open");
		generate = new JButton("Generate XML");
		exit = new JButton("Exit");
		bug = new JButton("Bug/Issue Report");
		generate.setEnabled(false);


		this.setBorder(new TitledBorder("Generates XML File to Import in ILIAS e-Learning System"));
		this.add(generate);
		this.add(open);
		this.add(bug);
		this.add(exit);
		this.setLayout(new GridLayout(2, 2));
		
		generate.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		bug.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Open")) {
			this.Browse();
		}
		if (e.getActionCommand().equals("Generate XML")) {
			try {
				xml.GenerateXML(excel, getFileNameWithoutExtension(getFilename())+".xml");
				view.getStatus().setText("XML File has been Generated");
				view.getStatus().setForeground(Color.green.darker());
			} catch (Exception e1) {
				e1.printStackTrace();
				view.getStatus().setText("ERROR");
				view.getStatus().setForeground(Color.red.darker());
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
					view.getStatus().setText("ERROR");
					view.getStatus().setForeground(Color.red.darker());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					view.getStatus().setText("ERROR");
					view.getStatus().setForeground(Color.red.darker());
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
				excel.ReadExcel(getPath());
//				excel = new ReadExcel(getPath());
				view.getStatus().setText("READY TO GO");
				view.getStatus().setForeground(Color.blue.darker());
				generate.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.getStatus().setText("ERROR");
				view.getStatus().setForeground(Color.red.darker());
			}
			// System.out.println(dir+"/"+getFilename());
		}
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
}

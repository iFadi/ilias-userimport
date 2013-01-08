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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.IFile;

import model.GenerateXML;
import model.ParseCSV;
import model.ParseExcel;

/**
 * 
 * mainTab.java
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.2.1  18/12/2012
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
public class InputTab extends JPanel implements ActionListener{

	private static final long serialVersionUID = -335799796636612645L;
	private JButton open;
	private JButton generate;
	private JButton exit;
	private JButton bug;
	private String filename;
	private String dir;
	private String path;
	private GenerateXML xml;
	private IFile input;
	private IView frame;
	public Desktop d;
	
	public InputTab(final IFile input, GenerateXML xml, JFrame frame) {
		this.xml = xml;
//		this.input = input;
//		this.view = (View)frame;
		
		if(frame instanceof View)
			this.frame = (View)frame;
		else 
			this.frame = (Update)frame;
		
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
				xml.GenerateXMLFile(input, getFileNameWithoutExtension(getFilename())+".xml");
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

	/**
	 * 
	 * @param input
	 * @return input name without the extension. i.e. tmp.txt --> tmp
	 */
	public String getFileNameWithoutExtension(String input) {
		int index = input.lastIndexOf('.');
		if (index > 0 && index <= input.length() - 2) {
			return input.substring(0, index);
		}
		return input.substring(0, index);
	}
	
	public void Browse() {
		JFileChooser c = new JFileChooser();
		c.setMultiSelectionEnabled(false);
		c.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Excel 97-2004", "xls");
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("CSV (;)", "csv");
		c.setFileFilter(filter1);
		c.setFileFilter(filter2);
		
		// Demonstrate "Open" dialog:
		int rVal = c.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			if(c.getFileFilter() == filter1)
				input = new ParseExcel();
			if(c.getFileFilter() == filter2)
				input = new ParseCSV();
			setFilename(c.getSelectedFile().getName());
			dir = c.getCurrentDirectory().toString();
			setPath(dir + "/" + getFilename());
			try {
				input.ReadFile(getPath());
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

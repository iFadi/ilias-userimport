/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Configuration;
import model.GenerateXML;
import model.ParseCSV;
import model.ParseExcel;
import controller.IFile;

/**
 * $Id$
 * $LastChangedDate$
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @version $Revision$
 * @copyright $Date$
 * 
 * TERMS AND CONDITIONS: This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 */
public class InputPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -335799796636612645L;
	private final static String REVISION = "$Rev$";
	private JButton open;
	private JButton generate;
	private JButton exit;
	private JButton bugOrDownload;
	private JButton config;
	private JLabel version;
	private String filename;
	private String dir;
	private String path;
	private GenerateXML xml;
	private IFile input;
	private View frame;
	public Desktop d;
	ConfigurationDialog cd;
	private Configuration conf;

	public InputPanel(final IFile input, GenerateXML xml, View frame, Configuration conf) {
		this.xml = xml;
		this.frame = frame;
		this.conf = conf;

		setLayout(null);
		
		ImageIcon icon = new ImageIcon("img/config.png");
		config = new JButton(icon);
//		config.setPreferredSize(new Dimension(25, 25));
		config.setEnabled(true);
		config.addActionListener(this);
		config.setBorder(new MatteBorder(null));
		config.setToolTipText("Show Config Dialog");
		config.setBounds(15, 20, 35, 35);
		
//		ImageIcon icon2 = new ImageIcon("img/config.png");
//		config2 = new JButton(icon2);
//		config2.setPreferredSize(new Dimension(25, 25));
//		config2.setEnabled(true);
//		config2.addActionListener(this);
//		config2.setBorder(new MatteBorder(null));
//		config2.setToolTipText("Show Config Dialog");
		
		setOpen(new JButton("Open"));
		getOpen().setBounds(230, 20, 160, 35);
		setGenerate(new JButton("Version: " + xml.getConfiguration().getVersion().toString()));
		version = new JLabel(xml.getConfiguration().getVersion().toString());
		version.setBounds(15,60,35,35);
		getGenerate().setBounds(60, 20, 160, 35);
		exit = new JButton("Exit");
		exit.setBounds(230, 60, 160, 35);
		setBugOrDownload(new JButton("Bug/Issue Report"));
		getBugOrDownload().setBounds(60, 60, 160, 35);
		getGenerate().setEnabled(false);

		this.setBorder(new TitledBorder(
				"Generates XML File to Import in ILIAS e-Learning System"));
		this.add(config);
		this.add(version);
		this.add(getGenerate());
		this.add(open);
//		this.add(config2);
		this.add(getBugOrDownload());
		this.add(exit);
//		this.setLayout(new GridLayout(2, 2));

		getGenerate().addActionListener(this);
		getOpen().addActionListener(this);
		exit.addActionListener(this);
		getBugOrDownload().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Open")) {
			this.Browse();
		}
		if (e.getActionCommand().equals("Generate XML")) {
			try {
				xml.GenerateXMLFile(input,
						getFileNameWithoutExtension(getFilename()) + ".xml");
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
				u = new URI(
						"http://code.google.com/p/ilias-userimport/issues/list");
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
		if (e.getActionCommand().equals("DOWNLOAD NOW")) {
			try {

				URI u;
				d = Desktop.getDesktop();
				u = new URI(
						"http://code.google.com/p/ilias-userimport/downloads/list");
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
		
		if (e.getSource() == config) {
			cd = new ConfigurationDialog(conf, this);
			cd.setVisible(true);
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
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter(
				"Excel 97-2004", "xls");
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("CSV",
				"csv");
		c.setFileFilter(filter1);
		c.setFileFilter(filter2);

		// Demonstrate "Open" dialog:
		int rVal = c.showOpenDialog(this);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			if (c.getFileFilter() == filter1)
				input = new ParseExcel();
			if (c.getFileFilter() == filter2)
				input = new ParseCSV();
			setFilename(c.getSelectedFile().getName());
			dir = c.getCurrentDirectory().toString();
			setPath(dir + "/" + getFilename());
			try {
				input.ReadFile(getPath());
				frame.getStatus().setText("READY TO GO");
				frame.getStatus().setForeground(Color.blue.darker());
				generate.setText("Generate XML");
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

	/**
	 * @return the revision
	 */
	public static String getRevision() {
		return REVISION.replaceAll("[\\D]", "");
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public JButton getBugOrDownload() {
		return bugOrDownload;
	}

	public void setBugOrDownload(JButton bugOrDownload) {
		this.bugOrDownload = bugOrDownload;
	}
	
	/**
	 * 
	 * Just used to load the images from a JAR file
	 * 
	 * @param path where the images are saved
	 * @return the image from the JAR file
	 */		
	private ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = this.getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

	public JButton getGenerate() {
		return generate;
	}

	public void setGenerate(JButton generate) {
		this.generate = generate;
	}

	public JButton getOpen() {
		return open;
	}

	public void setOpen(JButton open) {
		this.open = open;
	}
}

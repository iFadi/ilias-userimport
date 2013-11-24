/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Configuration;
import model.GenerateXML;
import model.ParseCSV;
import model.ParseExcel;
import controller.IFile;

/**
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @copyright 2013
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
	private static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH:mm:ss";
	private static final String REVISION = "$Rev$";
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
	private Configuration configuration;

	public InputPanel(final IFile input, GenerateXML xml, View frame, Configuration configuration) {
		this.xml = xml;
		this.frame = frame;
		this.configuration = configuration;

		setLayout(null);

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL resource = classLoader.getResource("img/config.png");
		ImageIcon icon = new ImageIcon(resource);

		config = new JButton(icon);		
		config.setEnabled(true);
		config.addActionListener(this);
		config.setBorder(new MatteBorder(null));
		config.setToolTipText("Show the configuartion dialog");
		config.setBounds(15, 20, 35, 35);
				
		setOpen(new JButton("Open"));
		getOpen().setBounds(230, 20, 160, 35);
		setGenerate(new JButton("Input Mode"));
		version = new JLabel(xml.getConfiguration().getVersion().toString());
		version.setBounds(15,60,35,35);
		version.setToolTipText("Revision: " + getRevision());
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
		this.add(getBugOrDownload());
		this.add(exit);

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
				if(configuration.isGenerateDummy()) {
					xml.GenerateXMLFile("dummy_"+now()+".xml");
					frame.getStatus().setText("XML File has been Generated");
					frame.getStatus().setForeground(Color.green.darker());
				}
				else {
					xml.GenerateXMLFile(input, getFileNameWithoutExtension(getFilename()) + ".xml");
					frame.getStatus().setText("XML File has been Generated");
					frame.getStatus().setForeground(Color.green.darker());
				}
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
						"https://github.com/iFadi/ilias-userimport/issues");
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
			cd = new ConfigurationDialog(configuration, this);
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
				input = new ParseCSV(configuration);
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
	
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());

	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.util.HSSFColor.BROWN;

import model.GenerateXML;
import model.ReadExcel;

/**
 * 
 * View.java 
 * The GUI of this App
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.0.0 26/08/2011
 * @copyright 2011
 * 
 * 
 */
public class View extends JFrame implements ActionListener {

	private JButton open;
	private JButton generate;
	private JButton exit;
	private JButton bug;
	private JTextField status;
	// private JProgressBar bar;
	private JMenuItem about;
	private JMenuItem exitItem;
	private TimerTask t;
	private String filename;
	private String dir;
	private String path;
	private GenerateXML xml;
	private ReadExcel excel;
	public Desktop d = Desktop.getDesktop();

	public View() throws Exception {
		// this.xml = xml;

		this.setTitle("ILIAS User Import");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setSize(400, 150);
		this.setLocation(500, 100);

		//
		// filename = new JTextField();
		// dir = new JTextField();
		open = new JButton("Open");
		generate = new JButton("Generate XML");
		exit = new JButton("Exit");
		bug = new JButton("Bug/Issue Report");
		status = new JTextField();
		// input = new JTextField("1", 15);
		// input.setEditable(true);
		status.setHorizontalAlignment(JTextField.CENTER);
		// input.setHorizontalAlignment(JTextField.CENTER);
		status.setEditable(false);
		generate.setEnabled(false);
		status.setText("Click Open to Choose File");
		status.setForeground(Color.black.darker());
		// bar = new JProgressBar();
		// bar.setValue(0);
		// bar.setStringPainted(true);

		this.add(status, BorderLayout.NORTH);
		panel.setBorder(new TitledBorder("Generates XML File to Import in ILIAS e-Learning System"));
		// panel.add(input);
		panel.add(generate);
		panel.add(open);
		panel.add(bug);
		panel.add(exit);
		panel.setLayout(new GridLayout(2, 2));
		//

		// Creates the menu bar with the menu items
		// JMenuBar menubar = new JMenuBar();
		// JMenu file = new JMenu("File");
		// JMenu info = new JMenu("Info");
		// about = new JMenuItem("About");
		// info.add(about);
		// exitItem = new JMenuItem("Exit");
		// file.add(exitItem);
		// menubar.add(file);
		// menubar.add(info);

		// this.setJMenuBar(menubar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		panel.setVisible(true);
		this.setVisible(true);

		generate.addActionListener(this);
		// about.addActionListener(this);
		// exitItem.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		bug.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Open")) {
			JFileChooser c = new JFileChooser();
			c.setMultiSelectionEnabled(false);
			c.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Excel 97-2004", "xls");
			c.setFileFilter(filter);
			// Demonstrate "Open" dialog:
			int rVal = c.showOpenDialog(View.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				filename = c.getSelectedFile().getName();
				dir = c.getCurrentDirectory().toString();
				setPath(dir + "/" + filename);
				try {
					excel = new ReadExcel(getPath());
					this.getStatus().setText("FILE LOADED, Click now on \"Generate XML\"");
					this.getStatus().setForeground(Color.blue.darker());
					generate.setEnabled(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					this.getStatus().setText("ERROR");
					this.getStatus().setForeground(Color.red.darker());
				}
				// System.out.println(dir+"/"+filename);
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				// filename.setText("You pressed cancel");
				// dir.setText("");
			}
		}
		if (e.getActionCommand().equals("Generate XML")) {
			try {
				xml = new GenerateXML(excel, getFileNameWithoutExtension(filename)+".xml");
//				xml.setFileName(getFileNameWithoutExtension(filename)+".xml");
//				System.out.println(getFileNameWithoutExtension(filename));
				this.getStatus().setText("XML File have been Generated");
				this.getStatus().setForeground(Color.green.darker());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				this.getStatus().setText("ERROR");
				this.getStatus().setForeground(Color.red.darker());
			}
		}
		if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("Bug/Issue Report")) {
			
				URI u;
				try {
					u = new URI("http://code.google.com/p/ilias-userimport/issues/list");
					d.browse(u);
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}


		}
	}

	public static String getFileNameWithoutExtension(String file) {
		int index = file.lastIndexOf('.');
		if (index > 0 && index <= file.length() - 2) {
			return file.substring(0, index);
		}
		return file.substring(0, index);
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

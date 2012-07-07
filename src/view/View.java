package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.GenerateXML;
import model.ReadExcel;
import net.iharder.dnd.FileDrop;

/**
 * 
 * View.java
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
public class View extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177350218996491783L;
	private JTextField status;

	public View(final ReadExcel excel, GenerateXML xml) throws Exception {

		this.setTitle("ILIAS User Import"); //The Title of the Window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When clicking on the x the window will close.

		JTabbedPane tabbedPane = new JTabbedPane();
		
		final mainTab mt = new mainTab(excel, xml, this); //Main Tab
		JPanel configuration = new JPanel();
		configuration.setLayout(new BorderLayout());
		
		tabbedPane.addTab("Main", null, mt, null);
		tabbedPane.addTab("Configuration", null, configuration, null);
		
		this.add(tabbedPane, BorderLayout.CENTER);
		this.pack();
		this.setSize(400, 200);
		this.setLocation(500, 100);

		status = new JTextField();
		status.setHorizontalAlignment(JTextField.CENTER);
		status.setEditable(false);
		status.setText("Click Open to Choose File");
		status.setForeground(Color.black.darker());

		this.add(status, BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		mt.setVisible(true);
		this.setVisible(true);

        //Drag n Drop functionality.
        new  FileDrop(this, new FileDrop.Listener()
        {   public void  filesDropped( java.io.File[] files )
            {   
                mt.setPath(files[0].getAbsolutePath());
                mt.setFilename(files[0].getName());
                        try {
                                excel.ReadExcel(mt.getPath());
                                getStatus().setText("READY TO GO");
                                getStatus().setForeground(Color.blue.darker());
                                mt.getGenerate().setEnabled(true);
                        } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                                getStatus().setText("ERROR");
                                getStatus().setForeground(Color.red.darker());
                        }
            }
                        // System.out.println(dir+"/"+filename);
        }); // end FD
	}
	
	public JTextField getStatus() {
		return status;
	}

	public void setStatus(JTextField status) {
		this.status = status;
	}

}

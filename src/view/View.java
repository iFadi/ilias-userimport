package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.IFile;

import model.Configuration;
import model.GenerateXML;
import model.ParseExcel;
import net.iharder.dnd.FileDrop;

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
public class View extends JFrame implements ChangeListener, IView  {

	private static final long serialVersionUID = 6177350218996491783L;
	private JTextField status;

	public View(final IFile input, GenerateXML xml, Configuration conf) throws Exception {

		this.setTitle("ILIAS User Import"); //The Title of the Window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When clicking on the x the window will close.

//		JTabbedPane tabbedPane = new JTabbedPane();
		
		final InputTab it = new InputTab(input, xml, this); //Main Tab
//		ConfigurationTab ct = new ConfigurationTab(); //Configuration Tab
//		DummyTab tb = new DummyTab(xml, this);
		
//		tabbedPane.addTab("Input", null, it, "Here you need to choose an input file(Excel or CSV).");
//		tabbedPane.addTab("Configuration", null, ct, null);
//		tabbedPane.addTab("Dummy", null, tb, "Here you can generate an XML file without an input.");
		
		this.add(it, BorderLayout.CENTER);
		this.pack();
		this.setSize(400, 220);
		this.setLocation(500, 100);

		status = new JTextField();
		status.setHorizontalAlignment(JTextField.CENTER);
		status.setEditable(false);
		status.setText("Click Open to Choose a File or Drag one here.");
		status.setForeground(Color.black.darker());

		this.add(status, BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
//		it.addChangeListener(this);

        //Drag n Drop functionality.
        new  FileDrop(this, new FileDrop.Listener()
        {   public void  filesDropped( java.io.File[] files )
            {   
                it.setPath(files[0].getAbsolutePath());
                it.setFilename(files[0].getName());
                        try {
                            input.ReadFile(it.getPath());
                            getStatus().setText("READY TO GO");
                            getStatus().setForeground(Color.blue.darker());
                            it.getGenerate().setEnabled(true);
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

	@Override
	public void stateChanged(ChangeEvent changeEvent) {
		JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
//		System.out.println("Tab changed to: " + sourceTabbedPane.getSelectedIndex());
		if(sourceTabbedPane.getSelectedIndex() == 0) {
			getStatus().setText("Click Open to Choose a File or Drag one here.");
			getStatus().setForeground(Color.black);
		}
		if(sourceTabbedPane.getSelectedIndex() == 1) {
			getStatus().setText("Here you can change the settings.");
			getStatus().setForeground(Color.black);
		}
		if(sourceTabbedPane.getSelectedIndex() == 2) {
			getStatus().setText("Generate dummy user accounts i.e. for test purposes.");
			getStatus().setForeground(Color.black);
		}
		
	}

}

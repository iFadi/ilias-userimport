package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Configuration;
import model.GenerateXML;
import model.UpdateNotifier;
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
public class View extends JFrame implements Observer {

	private static final long serialVersionUID = 6177350218996491783L;
	private Configuration configuration;
	private JTextField status;
	private InputPanel panel;

	public View(final IFile input, GenerateXML xml, Configuration configuration) throws Exception {
		this.configuration = configuration;
		UpdateNotifier un = new UpdateNotifier(); // Notify if Update is available 

		this.setTitle("ILIAS User Import"); //The Title of the Window.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //When clicking on the x the window will close.
		

		InputPanel panel = new InputPanel(input, xml, this, configuration);  //Input Panel
		
		if(un.IsNewVersionAvailable()) {
			panel.getBugOrDownload().setText("DOWNLOAD NOW"); //Download link to the new App
		}
		
		this.add(panel, BorderLayout.CENTER);
		
		this.pack();
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);

		status = new JTextField();
		status.setHorizontalAlignment(JTextField.CENTER);
		status.setEditable(false);
		
		if(configuration.isGenerateDummy()) {
			getStatus().setText("Generate dummy user accounts i.e. for test purposes.");
			getStatus().setForeground(Color.black);
		}
		else if(un.IsNewVersionAvailable()) {
			getStatus().setText("NEW VERSION IS AVAILABLE");
			getStatus().setForeground(Color.MAGENTA);
		}
		else {
			getStatus().setText("Click Open to Choose a File.");
			getStatus().setForeground(Color.black);
		}

		this.add(status, BorderLayout.NORTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

	}
	
	public JTextField getStatus() {
		return status;
	}

	public void setStatus(JTextField status) {
		this.status = status;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(configuration.isGenerateDummy()) {
			getStatus().setText("Generate dummy user accounts i.e. for test purposes.");
			getStatus().setForeground(Color.black);
		}
		else {
			getStatus().setText("Click Open to Choose a File.");
			getStatus().setForeground(Color.black);
		}
		
	}
}

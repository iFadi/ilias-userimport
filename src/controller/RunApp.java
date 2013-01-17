/**
 * 
 */
package controller;

import model.Configuration;
import model.GenerateXML;
import model.ParseCSV;
import model.ParseExcel;
import model.UpdateNotifier;
import view.Update;
import view.View;

/**
 * 
 * RunApp.java
 * The Main Class to Start the App.
 * 
 * $Author$
 * $Revision$
 * $HeadURL$
 * $Id$
 * $LastChangedDate$
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
public class RunApp {

	public static void main(String[] args) throws Exception {
		
		IFile input = null; // Interface for different input File Types.
		
//		input = new ParseExcel(); // Load the ReadExcel Model
//		input = new ParseCSV(); // Load the ReadCSV Model
		
		Configuration configuration = new Configuration(); // Load the Standard Configuration
		
		GenerateXML xml = new GenerateXML(); // Load the GenerateXML Model 
		UpdateNotifier un = new UpdateNotifier(); // Notify if Update is available 
		
		
		if(un.IsNewVersionAvailable()) {
			Update av = new Update(input, xml, configuration); // Load The Update View
		}
		else {
			View view = new View(input, xml, configuration); // Load The Normal App View
		}	
	}
}

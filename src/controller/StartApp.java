/**
 * 
 */
package controller;

import model.Configuration;
import model.GenerateXML;
import model.Version;
import view.View;

/**
 * The Main Class to Start the App.
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
public class StartApp {

	public static void main(String[] args) throws Exception {
		
		Version version = new Version(1, 3, 0); // The App Version.
		IFile input = null; // Interface for different input File Types.
		Configuration configuration = new Configuration(version); // Load the Standard Configuration		
		GenerateXML xml = new GenerateXML(configuration); // Load the GenerateXML Model 
		View view = new View(input, xml, configuration); // Load the GUI.
	}
}

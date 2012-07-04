/**
 * 
 */
package controller;

import model.GenerateXML;
import model.ReadExcel;
import model.UpdateNotifier;
import view.UpdateView;
import view.View;

/**
 * 
 * RunApp.java
 * The Main Class to Start the App.
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.2.0  31/01/2012
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
public class RunApp {

	public static void main(String[] args) throws Exception {
		/** Load the ReadExcel Class **/
		ReadExcel excel = new ReadExcel();
		/** Load the GenerateXML Class **/
		GenerateXML xml = new GenerateXML();
		/**Notifiy if Update is available **/
		UpdateNotifier un = new UpdateNotifier();
		
		if(un.IsNewVersionAvailable()) {
			/** Load The Update View **/
			UpdateView av = new UpdateView(excel, xml);
		}
		else {
			/** Load The App View **/
			View view = new View(excel, xml);
		}	
	}
}

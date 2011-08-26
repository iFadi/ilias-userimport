/**
 * 
 */
package main;

import controller.Controller;
import view.View;
import model.GenerateXML;
import model.ReadExcel;

/**
 * 
 * RunApp.java
 * The Main Class to Start the App.
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.0.0  26/08/2011
 * @copyright 2011
 * 
 * 
 */
public class RunApp {

	public static void main(String[] args) throws Exception {
		/** Launch The View **/
		View view = new View();
		
		/** Launch The Controller **/
		Controller controller = new Controller(view);
	}

}

package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * 
 * UpdateNotifier.java
 * 
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.1.0  09/10/2011
 * @copyright 2011
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
public class UpdateNotifier {
	final String majorUpdate = "http://ilias-userimport.googlecode.com/files/IUI_1.2.0.jar";
	final String minorUpdate = "http://ilias-userimport.googlecode.com/files/IUI_1.1.1.jar";
	
	public UpdateNotifier() {
		
	}
	
	public boolean check(String update)
    {
            try {
                    //make a URL to a known source
                    URL url = new URL(update);

                    //open a connection to that source
                    HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

                    //trying to retrieve data from the source. If there
                    //is no connection, this line will fail
                    Object objData = urlConnect.getContent();
//                    System.out.println(objData);

            } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
//                    e.printStackTrace();
                    return false;
            }
            catch (IOException e) {
                    // TODO Auto-generated catch block
//                    e.printStackTrace();
                    return false;
            }
            return true;
    }
	
	public boolean IsNewVersionAvailable() {
		if(check(majorUpdate) || check(minorUpdate))
			return true;
		else
			return false;
	}	
}

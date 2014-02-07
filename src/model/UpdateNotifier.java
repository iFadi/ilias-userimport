package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @copyright 2013
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
	private String projectURL;
	private String downloadURL;
	private String latestVersion;
	private Version version;
	
	public UpdateNotifier(Version version) throws IOException {
		setVersion(version);
		setProjectURL("https://github.com/iFadi/ilias-userimport/releases/latest");
		parsePage(); // Parse the project page.
	}
	
	/**
	 * 
	 * @param update
	 * @return if there is a connection to the server
	 */
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
                    return false;
            }
            catch (IOException e) {
                    return false;
            }
            return true;
    }
	
	/**
	 * 
	 * @return true if new version is available
	 */
	public boolean IsNewVersionAvailable() {
		if(check(getProjectURL())) {
			try {
				String[] version = getLatestVersion().split("\\.");
				if(Integer.parseInt(version[0]) > getVersion().getMajor()) 
					return true;
				else if(Integer.parseInt(version[1]) > getVersion().getMinor())
					return true;
				else if(Integer.parseInt(version[2]) > getVersion().getBug())
					return true;
				else
					return false;
			}
			catch(NullPointerException e) {
				System.out.println("SHIT!");
				return false;
			}


		}
		else 
			return false;
	}

	/**
	 * @return the version
	 */
	public Version getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Version version) {
		this.version = version;
	}

	/**
	 * @return the projectURL
	 */
	public String getProjectURL() {
		return projectURL;
	}

	/**
	 * @param projectURL the projectURL to set
	 */
	public void setProjectURL(String projectURL) {
		this.projectURL = projectURL;
	}
	
	public void parsePage() throws IOException {
		if(check(getProjectURL())) {
			Document document = Jsoup.connect(getProjectURL()).get();
			Element links = document.getElementsByClass("release-downloads").first().getElementsContainingOwnText("jar").first();

			String url = links.attr("abs:href");
			String[] result = url.split("/");

			try {
				setLatestVersion(result[(result.length)-2]); // Get the Tag number to compare if there is a new version.
			}
			catch(ArrayIndexOutOfBoundsException e){
				System.out.println("SHIT!");
			}
			setDownloadURL(url);// download link for the latest version.
		}
		else
			System.out.println("No Internet Connection.");
	}

	/**
	 * @return the downloadURL
	 */
	public String getDownloadURL() {
		return downloadURL;
	}

	/**
	 * @param downloadURL the downloadURL to set
	 */
	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	/**
	 * @return the latestVersion
	 */
	public String getLatestVersion() {
		return latestVersion;
	}

	/**
	 * @param latestVersion the latestVersion to set
	 */
	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}
}

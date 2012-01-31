package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * 
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.1.0  26/08/2011
 * @copyright 2011
 * 
 * 
 */
public class AutoUpdate {
	
	File f = new File(".");
	
	public static void downloadFile() throws IOException {
		URL google = new URL("http://ilias-userimport.googlecode.com/files/IUI_1.0.0.jar");
	    ReadableByteChannel rbc = Channels.newChannel(google.openStream());
	    FileOutputStream fos = new FileOutputStream("IUI_1.0.0.jar");
	    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
	}
	
	public static boolean isInternetReachable()
    {
            try {
                    //make a URL to a known source
                    URL url = new URL("http://code.google.com/p/ilias-userimport/downloads/list");

                    //open a connection to that source
                    HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();

                    //trying to retrieve data from the source. If there
                    //is no connection, this line will fail
                    Object objData = urlConnect.getContent();
//                    System.out.println(objData);

            } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
            }
            catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
            }
            return true;
    }
	
	public static void main(String[] args) throws IOException
    {
//		System.out.println(isInternetReachable());
		downloadFile();
//        URL url = new URL("http://code.google.com/p/ilias-userimport/downloads/list");
//		URLConnection conn = url.openConnection();
//		DataInputStream in = new DataInputStream ( conn.getInputStream (  )  ) ;
//		BufferedReader d = new BufferedReader(new InputStreamReader(in));
//		while(d.ready())
//		{
//		System.out.println( d.readLine());
//		}
    }
}

package de.unihannover.elsa.iui.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater {
	private final String versionURL = "https://github.com/iFadi/ilias-userimport/blob/master/version.html";
    private final String historyURL = "https://github.com/iFadi/ilias-userimport/blob/master/history.html";
    private InputStream version = getClass().getResourceAsStream("/version.html");
    private String currentVersion;
    private String newVersion;
    private int currentMajor,currentMinor,currenPatch;
    
    public int getMajorVersion() throws Exception {
    	String data = getData(versionURL);
    	return Integer.parseInt(data.substring(data.indexOf("[major]")+7,data.indexOf("[/major]")));
    }
    
    public int getMinorVersion() throws Exception {
    	String data = getData(versionURL);
    	return Integer.parseInt(data.substring(data.indexOf("[minor]")+7,data.indexOf("[/minor]")));
    }
    
    public int getPatchVersion() throws Exception {
    	String data = getData(versionURL);
    	return Integer.parseInt(data.substring(data.indexOf("[patch]")+7,data.indexOf("[/patch]")));
    }
    
    public Boolean getLatestVersion() throws Exception
    {    	
    	String data = getData(version);
//    	System.out.println(data);
    	currentMajor = Integer.parseInt(data.substring(data.indexOf("[major]")+7,data.indexOf("[/major]")));
    	currentMinor = Integer.parseInt(data.substring(data.indexOf("[minor]")+7,data.indexOf("[/minor]")));
    	currenPatch = Integer.parseInt(data.substring(data.indexOf("[patch]")+7,data.indexOf("[/patch]")));

    	if(getMajorVersion() > currentMajor || getMinorVersion() > currentMinor || getPatchVersion() > currenPatch) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public String getWhatsNew() throws Exception
    {
        String data = getData(historyURL);
        return data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }
    
    private String getData(InputStream file) {
    	StringBuilder contentBuilder = new StringBuilder();
    	try {
    	    BufferedReader in = new BufferedReader(new InputStreamReader(file));
//    	    System.out.println(in);
//    	    BufferedReader in = new BufferedReader(new FileReader(file));
    	    String str;
    	    while ((str = in.readLine()) != null) {
    	        contentBuilder.append(str);
//    	        System.out.println(contentBuilder);
    	    }
    	    in.close();
    	} catch (IOException e) {
    	}
    	String content = contentBuilder.toString();
//    	System.out.println(content);
    	return content;
    }
    
    private String getData(String address) throws IOException
    {
    	URL url = new URL(address);
        
        InputStream html = null;

        html = url.openStream();
        
        int c = 0;
        StringBuffer buffer = new StringBuffer("");

        while(c != -1) {
            c = html.read();
            
        buffer.append((char)c);
        }
        return buffer.toString();
    }

	public String getCurrentVersion() throws Exception {
		return currentMajor+"."+currentMinor+"."+currenPatch;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getNewVersion() throws Exception {
		return getMajorVersion()+"."+getMinorVersion()+"."+getPatchVersion();
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}
}

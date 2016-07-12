package de.unihannover.elsa.iui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Updater {
	private final String versionURL = "https://github.com/iFadi/ilias-userimport/blob/master/resources/version.html";
    private final String historyURL = "https://github.com/iFadi/ilias-userimport/blob/master/resources/history.html";
    private InputStream version = getClass().getResourceAsStream("/version.html");
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
    	String changes = "";
        String data = getData(historyURL);
        String history = data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
        
        System.out.println(history);
        for(int i=1; i<=2; i++) {
        	String change = history.substring(history.indexOf("[new"+i+"]")+5,history.indexOf("[/new"+i+"]"));
        	changes = changes + change + "\n";
        }
        
////    	System.out.println(changes);
////    	System.out.println(history);
////
////
////        String newString = changes.replaceFirst("[new]"+changes+"[/new]", "");
////        changes = newString.substring(newString.indexOf("[new]")+5,newString.indexOf("[/new]"));
//        	int i = history.indexOf("[new]");
//        	String changes = history.substring(i+5,history.indexOf("[/new]"));
//        	while(i >= 0) {
//        	     System.out.println(i);
//        	     i = history.indexOf("[new]", i+1);
//             	System.out.println(changes);
//        	}
//        	changes = history.substring(i+5,history.indexOf("[/new]"));
//        	System.out.println(changes);
////        for(String s:changes)
////        	System.out.println(history);

        return changes;
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

	public String getNewVersion() throws Exception {
		return getMajorVersion()+"."+getMinorVersion()+"."+getPatchVersion();
	}

}

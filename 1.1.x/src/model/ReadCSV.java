package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * ReadCSV.java
 * Reads/Analyse a CSV File and Save it to a Vector.
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.1.0
 * @copyright 2011
 * 
 * 
 */
public class ReadCSV {

	private String path;
	File file = new File("test.csv");
	
	
	public ReadCSV() throws FileNotFoundException {
		Vector<String[]> stringVektor = new Vector<String[]>();
		File file = new File("test.csv");
		BufferedReader bufRdr  = new BufferedReader(new FileReader(file));
//		String line = null;
		
		try {
			while(bufRdr.readLine() != null)
//			System.out.println(bufRdr.readLine());
			stringVektor.add(bufRdr.readLine().split(";"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(stringVektor.elementAt(0));
	}
	/**
	 * Get the n Line
	 * @param input
	 * @param n
	 * @return line
	 */
	public String getLine(String input, int n) {
		String[] lines = input.split("\n");
		String line = lines[n-1];
		return line;
	}
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

/**
 * 
 */
package model;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import controller.IFile;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Reads/Analyse a CSV File and Save it to a Vector.
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
public class ParseCSV implements IFile {

	private Vector<String> table;
	private List<String[]> myEntries;
	private Configuration configuration;
	
	public ParseCSV(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public void ReadFile(String file) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(file), configuration.getCSVSymbol().charAt(0));
		myEntries = reader.readAll();
		reader.close();
	}

	@Override
	public Vector<String> getColumn(String title) {
		table = new Vector<String>();
		int mark=0;
		String[] tmp = myEntries.get(0); // Get the Titles.
		
		for(int j=0; j<tmp.length; j++) {
			if(title.matches(tmp[j]))
				mark = j;
		}
		
		if(mark != 0)
			for (String[] row : myEntries) {
				table.addElement(row[mark]);
		}
//		System.out.println(getNumberOfColumns());
//		System.out.println(table);
		return table;
	}
	
	public int getNumberOfColumns() {
		return myEntries.get(0).length;
	}
}

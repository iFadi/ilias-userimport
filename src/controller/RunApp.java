/**
 * 
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import model.GenerateXML;
import model.ReadExcel;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.util.PDFTextStripper;

import view.View;

/**
 * 
 * RunApp.java
 * The Main Class to Start the App.
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.1.0  26/08/2011
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
public class RunApp {

	public static void main(String[] args) throws Exception {
//		/** Load the ReadExcel Class **/
		ReadExcel excel = new ReadExcel();
//		/** Load the GenerateXML Class **/
		GenerateXML xml = new GenerateXML();
//		/** Load The View **/
		View view = new View(excel, xml);
		
		/** Testing the new Class ReadCSV **/
//		ReadCSV csv = new ReadCSV();
		
		/** Testing the new Class ReadPDF **/
//		PDDocument pddDocument=PDDocument.load(new File("Fotoseite.pdf"));
//		PDFTextStripper textStripper=new PDFTextStripper();
//		System.out.println(textStripper.getText(pddDocument));
//		pddDocument.close();
//		Document luceneDocument = LucenePDFDocument.getDocument("a.pdf");
//		InputStream in=new FileInputStream(new File("Fotoseite.pdf"));
//		PDFParser parser=new PDFParser(in);
//		parser.parse();
//		PDMetadata metadata = parser.getPDDocument().getDocumentCatalog().getMetadata();
//		if(metadata!=null)
//		 {
//		 System.out.println(metadata.getInputStreamAsString());
//		 }
//		view.setVisible(true);	
	}
}

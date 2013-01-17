package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import controller.IFile;
/**
 * 
 * ReadExcel.java
 * Reads/Analyse an Excel File and Save it to a Vector.
 * 
 * $Author$
 * $Revision$
 * $HeadURL$
 * $Id$
 * $LastChangedDate$
 * 
 * 
 */
public class ParseExcel implements IFile{
	/**
	 * --Define a Vector --Holds Vectors Of Cells
	 */
	private Vector table;
	private Iterator rowIter;
	private String path;
	private String fileName;

	public ParseExcel() {
		
	}
	
	public void ReadFile(String file) throws Exception {
		/**
		 * --Define a Vector --Holds Vectors Of Cells
		 */
		Vector cellVectorHolder = new Vector();

		/** Creating Input Stream **/
		FileInputStream myInput = new FileInputStream(file);

		/** Create a POIFSFileSystem object **/
		POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

		/** Create a workbook using the File System **/
		HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

		/** Get the first sheet from workbook **/
		HSSFSheet mySheet = myWorkBook.getSheetAt(0);

		/** We now need something to iterate through the cells. **/
		Iterator rowIter = mySheet.rowIterator();

		while (rowIter.hasNext()) {
			HSSFRow myRow = (HSSFRow) rowIter.next();
			Iterator cellIter = myRow.cellIterator();
			Vector cellStoreVector = new Vector();
			while (cellIter.hasNext()) {
				HSSFCell myCell = (HSSFCell) cellIter.next();
				cellStoreVector.addElement(myCell);
			}
			cellVectorHolder.addElement(cellStoreVector);
		}
		this.setTable(cellVectorHolder);
	}
	
	public Vector getColumn(String title) {
		Vector column = new Vector();
		boolean found = false;
		int k=0;
		for(int i=0; i<this.table.size(); i++) {
			Vector cellStoreVector = (Vector) this.table.elementAt(i);
//			System.out.println(cellStoreVector+" size: "+cellStoreVector.size());
			if(found && (cellStoreVector.size()>k)) {
				HSSFCell Cell = (HSSFCell) cellStoreVector.elementAt(k);
				String CellValue = Cell.toString();
				if(CellValue.isEmpty())
					break;
//				System.out.println(CellValue);
				column.addElement(CellValue);
			}
			for(int j=0; j<cellStoreVector.size(); j++) {
				HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
				String stringCellValue = myCell.toString();
				if(stringCellValue.equals(title)) {
					k = j;
//					System.out.println(stringCellValue+":"+k);
					column.addElement(stringCellValue);
					found = true;
					break;
				}
			}
		}
		return column;
	}

	
	public Vector getTable() {
		return table;
	}

	public void setTable(Vector table) {
		this.table = table;
	}

	public Iterator getRowIter() {
		return rowIter;
	}

	public void setRowIter(Iterator rowIter) {
		this.rowIter = rowIter;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void start(String file) {
		
	}

	@Override
	public int getNumberOfColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

}

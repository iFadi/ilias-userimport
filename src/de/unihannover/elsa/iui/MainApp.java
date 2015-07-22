package de.unihannover.elsa.iui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.dialog.Dialogs;

import sun.print.resources.serviceui;
import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import de.unihannover.elsa.iui.model.Password;
import de.unihannover.elsa.iui.model.User;
import de.unihannover.elsa.iui.model.UserListWrapper;
import de.unihannover.elsa.iui.view.ChooseCSVHeaderDialog;
import de.unihannover.elsa.iui.view.ChooseExcel97HeaderDialog;
import de.unihannover.elsa.iui.view.DummyAccountsDialogController;
import de.unihannover.elsa.iui.view.RootLayoutController;
import de.unihannover.elsa.iui.view.SettingsDialogController;
import de.unihannover.elsa.iui.view.UserEditDialogController;
import de.unihannover.elsa.iui.view.UserOverviewController;
import de.unihannover.elsa.iui.view.XLSXSheetDialogController;

/**
 *
 * @author Fadi Asbih
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private int numberOfSheets;
	private int selectedSheet;
	private String[] sheetNames;
	private String[] headers;
	private int firstNameIndex;
	private int lastNameIndex;
	private int matriculationIndex;
	private int emailIndex;

	
	private char[] randomPassword = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray(); 
//	private char[] randomLogin = "abcdefghijklmnopqrstuvwxyz".toCharArray(); 

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<User> userData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {

	}

	/**
	 * Opens a Dialog, which gives the user the possibility to add a global/local role,
	 * set the user to be limited or add a password to the imported users.
	 * 
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showSettingsDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/SettingsDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Settings");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Give the controller access to the main app.
			SettingsDialogController controller = loader.getController();
			controller.setMainApp(this);

			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Opens a Dialog, which gives the user the possibility to generate a
	 * specific number of ILIAS dummy accounts.
	 * 
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showDummyAccountsDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DummyAccountsDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Generate Dummy Accounts");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Give the controller access to the main app.
			DummyAccountsDialogController controller = loader.getController();
			controller.setMainApp(this);

			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Opens a Dialog, which gives the user the possibility
	 * to choose which headers to use in ILIAS.
	 * 
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showChooseCSVHeaderDialog(File file) {
		try {
			// get the CSVHeaders;
			this.getCSVHeaders(file);
			
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChooseCSVHeaderDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Choose Headers");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Give the controller access to the main app.
			ChooseCSVHeaderDialog controller = loader.getController();
			controller.setMainApp(this);
			controller.setFile(file);

			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Opens a Dialog, which gives the user the possibility
	 * to choose which headers to use in ILIAS.
	 * 
	 * @return true if the user clicked OK, false otherwise.
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean showChooseExcel97HeaderDialog(File file) throws NoSuchAlgorithmException {
		try {
			// get the Headers;
			this.getExcel97Headers(file);
			
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ChooseExcel97HeaderDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Choose Headers");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Give the controller access to the main app.
			ChooseExcel97HeaderDialog controller = loader.getController();
//			ChooseExcel97HeaderDialog controller = loader.getController();
			controller.setMainApp(this);
			controller.setFile(file);

			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Opens a dialog to choose which sheet from the Excel file should
	 * be imported.
	 * 
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showXLSXSheetDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/XLSXSheetDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Choose sheet");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Give the controller access to the main app.
			XLSXSheetDialogController controller = loader.getController();
			controller.setMainApp(this);

			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param user the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showUserEditDialog(User user) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/UserEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit User");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			UserEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setUser(user);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<User> getUserData() {
		return userData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ILIAS User Import");

		this.primaryStage.getIcons().add(new Image("file:resources/images/iui.png"));

		initRootLayout();

		showPersonOverview();
	}

	/**
	 * Initializes the root layout and tries to load the last opened person
	 * file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Try to load last opened person file.
		File file = getUserFilePath();
		if (file != null) {
			loadUserDataFromFile(file);
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/UserOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the main app.
			UserOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getUserFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file
	 *            the file or null to remove the path
	 */
	public void setUserFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("ILIAS User Import - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("ILIAS User Import");
		}
	}

	/**
	 * Loads user data from the specified file. The current user data will be
	 * replaced.
	 * 
	 * @param file
	 */
	public void loadUserDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(UserListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			UserListWrapper wrapper = (UserListWrapper) um.unmarshal(file);
			
			userData.clear();
			userData.addAll(wrapper.getUsers());

			// Save the file path to the registry.
			setUserFilePath(file);

		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Error").masthead("Could not load data from file:\n" + file.getPath())
					.showException(e);
		}
	}

	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void saveUserDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext.newInstance(UserListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			UserListWrapper wrapper = new UserListWrapper();
			wrapper.setUsers(userData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);

			// Save the file path to the registry.
			setUserFilePath(file);
		} catch (Exception e) { // catches ANY exception
			Dialogs.create().title("Error").masthead("Could not save data to file:\n" + file.getPath())
					.showException(e);
		}
	}
	
	/**
	 * 
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public void getExcel97Headers(File file) throws IOException, NoSuchAlgorithmException {
	
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

		/** Get the the selected sheet from workbook **/
		HSSFSheet mySheet = myWorkBook.getSheetAt(getSelectedSheet());

		/** We now need something to iterate through the cells. **/
		Iterator rowIter = mySheet.rowIterator();

		DataFormatter df = new DataFormatter();

		HSSFRow row = (HSSFRow) rowIter.next();
//		System.out.println(row.getPhysicalNumberOfCells());
		String[] headers = new String[row.getPhysicalNumberOfCells()];
		
		int i=0;
		for(Cell h : row) {	
//			System.out.println(h.getRichStringCellValue().getString());
			headers[i] = h.getRichStringCellValue().getString();
			i++;
		}
		setHeaders(headers);
	}

	/**
	 * Get the Headers of a CSV File and save them
	 * in an array.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void getCSVHeaders(File file) throws IOException {
		CSVReader headers = new CSVReader(new FileReader(file), ';', CSVParser.DEFAULT_QUOTE_CHARACTER, 0);

		setHeaders(headers.readNext());
//		for(String h : header) {
//			System.out.println(h);
//		}
		
		headers.close();
	}
	
	/**
	 * Parses a CSV File and passes the Parameters to
	 * the User Object.
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public void parseCSV(File file) throws IOException, NoSuchAlgorithmException {

		CSVReader reader = new CSVReader(new FileReader(file), ';', CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
		
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
//			 System.out.println(nextLine[0] + nextLine[1] + nextLine[2]);
//			 nextLine[3] + nextLine[4] + nextLine[5] + nextLine[6] +
//			 nextLine[7] + nextLine[8] + nextLine[9] + nextLine[10] +
//			 nextLine[11] + nextLine[12] + nextLine[13] +
//			 nextLine[14]+"etc...");
			// nextLine[1] is for firstName, nextLine[2] for secondName in Stud.IP CSV.
			
//			User user = new User(nextLine[1], nextLine[2]);
			User user = new User(nextLine[getFirstNameIndex()], nextLine[getLastNameIndex()]);
			// user.setLogin(nextLine[4]); // Here is the Login same as Stud.IP Login
			// user.setLogin(nextLine[22]+randomString(randomLogin, 2)); // The Login is a Combination of m-nr with a random String.
			// user.setLogin(nextLine[22]); // The Login is same as the M-nr.
			user.setLogin(nextLine[getMatriculationIndex()]);
			user.setPassword(new Password(randomString(randomPassword, 5)));
			if(getEmailIndex() > 0) {
				user.setEmail(nextLine[getEmailIndex()]);
			}
//			user.setMatriculation(nextLine[22]);
			user.setMatriculation(nextLine[getMatriculationIndex()]);
			userData.add(user);
			
		}
		reader.close();
	}

	/**
	 * Parses an Excel 97 - 2004 XLS File
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public void parseExcel97(File file) throws IOException, NoSuchAlgorithmException {
	
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

		/** Get the the selected sheet from workbook **/
		HSSFSheet mySheet = myWorkBook.getSheetAt(getSelectedSheet());

		/** We now need something to iterate through the cells. **/
		Iterator rowIter = mySheet.rowIterator();

		DataFormatter df = new DataFormatter();

		while (rowIter.hasNext()) {
			HSSFRow row = (HSSFRow) rowIter.next();

			if (row.getRowNum() != 0) { // Skip first Row, Headers.
				String firstName = df.formatCellValue(row.getCell(getFirstNameIndex()));
				String lastName = df.formatCellValue(row.getCell(getLastNameIndex()));
				String mnr = df.formatCellValue(row.getCell(getMatriculationIndex()));

				User user = new User(firstName, lastName);
				if(getEmailIndex() > 0) {
					String mail = df.formatCellValue(row.getCell(getEmailIndex()));
					user.setEmail(mail);
				}
				user.setLogin(mnr); // The Login is same as the M-nr.
				user.setPassword(new Password(randomString(randomPassword, 5)));

				user.setMatriculation(mnr);
				userData.add(user);

//				System.out.println(firstName + " " + lastName + " " + mnr + " " + mail + "\t ");
			}
		}
	}
	
	/**
	 * Parses an Excel XLSX File, in the order
	 * firstname, lastname, M-Nr, email
	 * 
	 * @param file
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public void parseExcel(File file) throws IOException, NoSuchAlgorithmException {
		FileInputStream fileStream = new FileInputStream(file);

		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fileStream);
		
		setNumberOfSheets(myWorkBook.getNumberOfSheets());
		
		// Pop up a Dialog to choose which sheet to import.
		if(myWorkBook.getNumberOfSheets() > 1) {
			String[] sheets = new String[getNumberOfSheets()];
        	for(int i=0; i<sheets.length; i++) {
        		sheets[i] = myWorkBook.getSheetName(i);
        	}
			setSheetNames(sheets);
			this.showXLSXSheetDialog();
		}
		
		// Return the selected sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(getSelectedSheet());
//		System.out.println(myWorkBook.getSheetName(getSelectedSheet()));

		DataFormatter df = new DataFormatter();

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			if (row.getRowNum() != 0) { // Skip first Row, Headers.
				String firstName = df.formatCellValue(row.getCell(0));
				String lastName = df.formatCellValue(row.getCell(1));
				String mnr = df.formatCellValue(row.getCell(2));
				String mail = df.formatCellValue(row.getCell(3));

				User user = new User(firstName, lastName);
				user.setLogin(mnr); // The Login is same as the M-nr.
				user.setPassword(new Password(randomString(randomPassword, 5)));
				user.setEmail(mail);
				user.setMatriculation(mnr);
				userData.add(user);

				System.out.println(firstName + " " + lastName + " " + mnr + " " + mail + "\t ");
			}
		}
		myWorkBook.close();
		fileStream.close();
	}

	/**
	 * Generate a Random String.
	 * 
	 * @param characterSet
	 * @param length
	 * @return
	 */
	public static String randomString(char[] characterSet, int length) {
		Random random = new SecureRandom();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			// picks a random index out of character set > random character
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}
		return new String(result);
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @return the numberOfSheets
	 */
	public int getNumberOfSheets() {
		return numberOfSheets;
	}

	/**
	 * @param numberOfSheets the numberOfSheets to set
	 */
	public void setNumberOfSheets(int numberOfSheets) {
		this.numberOfSheets = numberOfSheets;
	}

	/**
	 * @return the selectedSheet
	 */
	public int getSelectedSheet() {
		return selectedSheet;
	}

	/**
	 * @param selectedSheet the selectedSheet to set
	 */
	public void setSelectedSheet(int selectedSheet) {
		this.selectedSheet = selectedSheet;
	}

	/**
	 * @return the sheetNames
	 */
	public String[] getSheetNames() {
		return sheetNames;
	}

	/**
	 * @param sheetNames the sheetNames to set
	 */
	public void setSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public int getFirstNameIndex() {
		return firstNameIndex;
	}

	public void setFirstNameIndex(int firstNameIndex) {
		this.firstNameIndex = firstNameIndex;
	}

	public int getLastNameIndex() {
		return lastNameIndex;
	}

	public void setLastNameIndex(int lastNameIndex) {
		this.lastNameIndex = lastNameIndex;
	}

	public int getMatriculationIndex() {
		return matriculationIndex;
	}

	public void setMatriculationIndex(int matriculationIndex) {
		this.matriculationIndex = matriculationIndex;
	}

	public int getEmailIndex() {
		return emailIndex;
	}

	public void setEmailIndex(int emailIndex) {
		this.emailIndex = emailIndex;
	}

}
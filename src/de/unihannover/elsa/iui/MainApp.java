package de.unihannover.elsa.iui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.dialog.Dialogs;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import de.unihannover.elsa.iui.model.User;
import de.unihannover.elsa.iui.model.UserListWrapper;
import de.unihannover.elsa.iui.view.RootLayoutController;
import de.unihannover.elsa.iui.view.SettingsDialogController;
import de.unihannover.elsa.iui.view.UserEditDialogController;
import de.unihannover.elsa.iui.view.UserOverviewController;

/**
 *
 * @author Marco Jakob
 * @author Fadi Asbih
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
 // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<User> userData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {

    }
    
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
     * @return
     */
    public ObservableList<User> getUserData() {
        return userData;
    }

    // ... THE REST OF THE CLASS ...
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ILIAS User Import");

        this.primaryStage.getIcons().add(new Image("file:resources/images/iui.png"));
        
        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
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
     * @param file the file or null to remove the path
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
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadUserDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(UserListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            UserListWrapper wrapper = (UserListWrapper) um.unmarshal(file);

            userData.clear();
            userData.addAll(wrapper.getUsers());

            // Save the file path to the registry.
            setUserFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + file.getPath())
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
            JAXBContext context = JAXBContext
                    .newInstance(UserListWrapper.class);
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
            Dialogs.create().title("Error")
                    .masthead("Could not save data to file:\n" + file.getPath())
                    .showException(e);
        }
    }
    
    /**
     * Parses a Stud.IP CSV File in German Language and passes the Parameters to the User Object.
     * 
     * @param file
     * @throws IOException
     */
    public void parseCSV(File file) throws IOException {
    	CSVReader reader = new CSVReader(new FileReader(file),';',CSVParser.DEFAULT_QUOTE_CHARACTER,1);
	    String [] nextLine;
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
//	        System.out.println(nextLine[0] + nextLine[1] + nextLine[2] + nextLine[3] + nextLine[4] + nextLine[5] +  nextLine[6] + nextLine[7] + nextLine[8] + nextLine[9] + nextLine[10] + nextLine[11] + nextLine[12] + nextLine[13] + nextLine[14]+"etc...");
	        //nextLine[1] is for firstName, nextLine[2] for secondName in Stud.IP CSV.
	        User user = new User(nextLine[1], nextLine[2]);
	        user.setLogin(nextLine[4]);
	        user.setEmail(nextLine[7]);
	        user.setMatriculation(nextLine[22]);
	        userData.add(user);
	    }
	    reader.close();
    }
    
	/**
	 * Parses an Excel File, sill experimental.
	 * 
	 * @param file
	 * @throws IOException 
	 */
	public void parseExcel(File file) throws IOException {
		 FileInputStream fileStream = new FileInputStream(file);
		 
         //Create Workbook instance holding reference to .xls file
         Workbook workbook = new HSSFWorkbook(fileStream); 

         //Get first/desired sheet from the workbook
         Sheet sheet = workbook.getSheetAt(0);

         //Iterate through each rows one by one
         Iterator<Row> rowIterator = sheet.iterator();
         while (rowIterator.hasNext()) 
         {
             Row row = rowIterator.next();
             //For each row, iterate through all the columns
             Iterator<Cell> cellIterator = row.cellIterator();
              
             while (cellIterator.hasNext()) 
             {
                 Cell cell = cellIterator.next();
                 //Check the cell type and format accordingly
                 switch (cell.getCellType()) 
                 {
                     case Cell.CELL_TYPE_NUMERIC:
                         System.out.print(cell.getNumericCellValue() + "\t");
                         break;
                     case Cell.CELL_TYPE_STRING:
                         System.out.print(cell.getStringCellValue() + "\t");
                         break;
                 }

             }
             System.out.println("");
         }
         fileStream.close();
		
	}
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
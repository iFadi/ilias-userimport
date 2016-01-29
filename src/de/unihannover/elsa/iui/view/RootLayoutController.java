package de.unihannover.elsa.iui.view;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FilenameUtils;

import de.unihannover.elsa.iui.MainApp;
import de.unihannover.elsa.iui.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;


/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 * @author Fadi Asbih
 * 
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getUserData().clear();
        mainApp.setUserFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadUserDataFromFile(file);
        }
    }
    
    /**
     * Opens a FileChooser to let the user select a file to load.
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     */
    @FXML
    private void handleImport() throws IOException, NoSuchAlgorithmException {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter        
        FileChooser.ExtensionFilter filterCSV = new FileChooser.ExtensionFilter(
                "CSV file (*.csv)", "*.csv");
        FileChooser.ExtensionFilter filterXLS = new FileChooser.ExtensionFilter(
        		"Excel file (*.xls)", "*.xls");
        FileChooser.ExtensionFilter filterXLSX = new FileChooser.ExtensionFilter(
        		"Excel file (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(filterCSV);
        fileChooser.getExtensionFilters().add(filterXLS);
        fileChooser.getExtensionFilters().add(filterXLSX);
		
        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(file != null) {
            String fileExtension = FilenameUtils.getExtension(file.getPath());
            if (fileExtension.equals("csv")) {
            	mainApp.getCSVHeaders(file);
            	mainApp.showChooseHeaderDialog(file, "csv");
            }
            else if (fileExtension.equals("xlsx")) {
    	    	mainApp.setSelectedSheet(0); // if before another sheet imported.
            	mainApp.getExcelHeaders(file);
            	mainApp.showChooseHeaderDialog(file, "xlsx");
            }
            else if (fileExtension.equals("xls")) {
    	    	mainApp.setSelectedSheet(0);
            	mainApp.getExcel97Headers(file);
            	mainApp.showChooseHeaderDialog(file, "xls");
            }
        }
    }
    
    /**
     * 	
     */
    @FXML
    private void handlePrint() {
         TableView<User> userTable = new TableView<>();
         userTable.setItems(mainApp.getUserData());

         
//    	 PrinterJob printerJob = PrinterJob.createPrinterJob();
//    	 System.out.println("Trying to print.");
//    	 
//    	   if(printerJob.showPrintDialog(mainApp.getPrimaryStage()) && printerJob.printPage(userTable)) {
//    	       printerJob.endJob();
//               System.out.println("printed");
//    	   }
//         Printer printer = Printer.getDefaultPrinter();
//         Stage dialogStage = new Stage(StageStyle.DECORATED);            
//         PrinterJob job = PrinterJob.createPrinterJob(printer);
//             if (job != null) {                    
//                 boolean showDialog = job.showPageSetupDialog(dialogStage);
//                 if (showDialog) {                        
//                	 userTable.setScaleX(0.60);
//                	 userTable.setScaleY(0.60);
//                	 userTable.setTranslateX(-220);
//                	 userTable.setTranslateY(-70);
//                 boolean success = job.printPage(userTable);
//                     if (success) {
//                          job.endJob(); 
//                     } 
//                     userTable.setTranslateX(0);
//                     userTable.setTranslateY(0);               
//                     userTable.setScaleX(1.0);
//                     userTable.setScaleY(1.0);                                              
//                                 }    
//                             }
    }
    
    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getUserFilePath();
        if (personFile != null) {
            mainApp.saveUserDataToFile(personFile);
        } else {
            export();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void export() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveUserDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleSettings() {
    	mainApp.showSettingsDialog();
    }
    
    /**
     * Opens the dialog to generate dummy accounts.
     */
    @FXML
    private void handleDummyAccounts() {
    	mainApp.showDummyAccountsDialog();
    }
    
    /**
     * Opens an about dialog.
     * @throws Exception 
     */
    @FXML
    private void handleAbout() throws Exception {
//    	FxDialogs.showInformation("About", "Version: "+mainApp.getCurrentVersion()+"\n\nProject: https://github.com/iFadi/ilias-userimport");
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.initStyle(StageStyle.UTILITY);
//        alert.setTitle("ILIAS User Import");
//        alert.setHeaderText(title);
//        alert.setContentText(message);

//        alert.showAndWait();
    	Alert alert = new Alert(
    	Alert.AlertType.INFORMATION);
    	alert.setTitle("ILIAS User Import");
    	alert.setHeaderText("About");

    	FlowPane fp = new FlowPane();
    	Label lbl = new Label("Version: "+mainApp.getCurrentVersion());
    	Hyperlink link = new Hyperlink("Check the Project on Github.");
    	fp.getChildren().addAll( lbl, link);

    	link.setOnAction( (evt) -> {
    	                alert.close();
    	                mainApp.getHostServices().showDocument("https://github.com/iFadi/ilias-userimport");
    	} );

    	alert.getDialogPane().contentProperty().set( fp );
    	alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
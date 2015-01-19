package de.unihannover.elsa.iui.view;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.stage.FileChooser;

import org.apache.commons.io.FilenameUtils;
import org.controlsfx.dialog.Dialogs;

import de.unihannover.elsa.iui.MainApp;


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
     * Opens a FileChooser to let the user select an address book to load.
     * @throws IOException 
     */
    @FXML
    private void handleImport() throws IOException {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter        
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter(
                "Stud.IP CSV file (*.csv)", "*.csv");
//        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter(
//        		"Excel 97-2004 (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(filter1);
//        fileChooser.getExtensionFilters().add(filter2);
		
        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if(file != null) {
            String fileExtension = FilenameUtils.getExtension(file.getPath());
            if (fileExtension.equals("csv")) {
                mainApp.parseCSV(file);
            }
            if (fileExtension.equals("xls")) {
                mainApp.parseExcel(file);
            }
        }
    }
    
    /**
     * 	
     */
    @FXML
    private void handlePrint() {
//    	 PrinterJob printerJob = PrinterJob.createPrinterJob();

    	 System.out.println("Printing.");
//    	   if(printerJob.showPrintDialog(mainApp.getPrimaryStage().getOwner()))
//    	       printerJob.endJob();
//    	 Printer printer = Printer.getDefaultPrinter();
//
//    	    PrinterJob job = PrinterJob.createPrinterJob();
//    	    if (job != null) {
//    	        boolean success = job.printPage(mainApp.getUserData());
//    	        if (success) {
//    	            job.endJob();
//    	        }
//    	    }
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
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
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
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Dialogs.create()
            .title("ILIAS User Import")
            .masthead("About")
            .message("\nMost of Code Originally from Marco Jakob\nWebsite: http://code.makery.ch\n\nAuthor: Fadi Asbih\nVersion: 2.0 beta\nProject: https://github.com/iFadi/ilias-userimport")
            .showInformation();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
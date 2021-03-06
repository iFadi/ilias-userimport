package de.unihannover.elsa.iui.view;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import de.unihannover.elsa.iui.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChooseHeaderDialog {

		@FXML
    	private CheckBox containsHeaders;
		@FXML
	    private ComboBox<String> firstNameBox;
	    @FXML
	    private ComboBox<String> lastNameBox;
	    @FXML
	    private ComboBox<String> matriculationBox;
	    @FXML
	    private ComboBox<String> emailBox;
	    @FXML
	    private ComboBox<String> loginBox;
	    
	    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();
	    private Stage dialogStage;
	    private boolean okClicked = false;
	    
	 // Reference to the main application.
	    private MainApp mainApp;
	    private File file;
	    private String type;
	    
	    
	    public ChooseHeaderDialog() {
	    	firstNameBox = new ComboBox<String>();
	    	lastNameBox = new ComboBox<String>();
	    	matriculationBox = new ComboBox<String>();
	    	emailBox = new ComboBox<String>();
	    	loginBox = new ComboBox<String>();

		}
	    
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	firstNameBox.setItems(comboBoxData);
	    	lastNameBox.setItems(comboBoxData);
	    	matriculationBox.setItems(comboBoxData);
	    	emailBox.setItems(comboBoxData);
	    	loginBox.setItems(comboBoxData);

	    	

	    	firstNameBox.setOnAction((event) -> {
	    	    Integer selectedIndex = firstNameBox.getSelectionModel().getSelectedIndex();
	    	    System.out.println("firstname ComboBox Action (selected: " + selectedIndex.toString() + ")");
	    	    mainApp.setFirstNameIndex(selectedIndex);
	    	});
	    	
	    	firstNameBox.setOnShown((event) -> {
	        	mainApp.setFirstNameIndex(0);
	    	});
	    	
	    	lastNameBox.setOnAction((event) -> {
	    	    Integer selectedIndex = lastNameBox.getSelectionModel().getSelectedIndex();
	    	    System.out.println("lastname ComboBox Action (selected: " + selectedIndex.toString() + ")");
	    	    mainApp.setLastNameIndex(selectedIndex);
	    	});
	    	
	    	lastNameBox.setOnShown((event) -> {
	        	mainApp.setLastNameIndex(1);
	    	});
	    	
	    	matriculationBox.setOnAction((event) -> {
	    	    Integer selectedIndex = matriculationBox.getSelectionModel().getSelectedIndex();
	    	    System.out.println("matriculation ComboBox Action (selected: " + selectedIndex.toString() + ")");
	    	    mainApp.setMatriculationIndex(selectedIndex);
	    	});
	    	
	    	matriculationBox.setOnShown((event) -> {
	        	mainApp.setMatriculationIndex(2);
	    	});
	    	
	    	emailBox.setOnAction((event) -> {
	    	    Integer selectedIndex = emailBox.getSelectionModel().getSelectedIndex();
	    	    System.out.println("emailBox ComboBox Action (selected: " + selectedIndex.toString() + ")");
	    	    mainApp.setEmailIndex(selectedIndex);
	    	});
	    	
	    	emailBox.setOnShown((event) -> {
	        	mainApp.setEmailIndex(-1);
	    	});
	    	
	    	loginBox.setOnAction((event) -> {
	    	    Integer selectedIndex = loginBox.getSelectionModel().getSelectedIndex();
	    	    System.out.println("loginBox ComboBox Action (selected: " + selectedIndex.toString() + ")");
	    	    mainApp.setLoginIndex(selectedIndex);
	    	});
	    	
	    	loginBox.setOnShown((event) -> {
	        	mainApp.setLoginIndex(-1);
	    	});
	    	
	    	containsHeaders.setOnAction((event) -> {
	        	mainApp.setContainsHeaders(containsHeaders.isSelected());
	        	System.out.println("Contains Headers selected/diselected");
	    	});
	    }
	    
	    /**
	     * 
	     */
	    public void loadHeaders() {
        	for(int i=0; i<mainApp.getHeaders().length; i++) {
        		comboBoxData.add(mainApp.getHeaders()[i]);
        		System.out.println(mainApp.getHeaders()[i]);
        	}
	    }
	    
	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	        this.dialogStage.getIcons().add(new Image("file:resources/images/iui.png"));
	    }
	    
	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	    	loadHeaders();
	    	mainApp.setEmailIndex(-1);
	    	mainApp.setLoginIndex(-1);
	    	mainApp.setContainsHeaders(true);
//	    	mainApp.setSelectedSheet(0);
	    }
	    
	    public void setFile(File file) {
	    	this.file = file;
	    }
	    
	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }
	    
	    /**
	     * 
	     * @throws IOException 
	     * @throws NoSuchAlgorithmException 
	     */
	    @FXML
	    private void handleImport() throws NoSuchAlgorithmException, IOException {
	    	switch (type) {
	    		case "csv": mainApp.parseCSV(file);
	    			break;
	    		case "xls": mainApp.parseExcel97(file);
	    			break;
	    		case "xlsx": mainApp.parseExcel(file);
	    			break;
	    		default: System.out.println("No parsing method is choosen."); 
	    			break;
	    	}
	    	dialogStage.close();
	    }
	    
	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

}

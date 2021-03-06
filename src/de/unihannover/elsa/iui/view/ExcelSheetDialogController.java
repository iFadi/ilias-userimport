package de.unihannover.elsa.iui.view;

import java.security.NoSuchAlgorithmException;

import de.unihannover.elsa.iui.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Fadi Asbih
 *
 * Copyright (c) 2015
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
public class ExcelSheetDialogController {

    @FXML
    private ComboBox<String> comboBox;
    private ObservableList<String> comboBoxData = FXCollections.observableArrayList();
    
    private Stage dialogStage;
    private boolean okClicked = false;
    
 // Reference to the main application.
    private MainApp mainApp;
    
    /**
	 * 
	 */
	public ExcelSheetDialogController() {
    	comboBox = new ComboBox<String>();
	}
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	comboBox.setItems(comboBoxData);
    	
    	comboBox.setOnAction((event) -> {
    	    Integer selectedSheet = comboBox.getSelectionModel().getSelectedIndex();
    	    System.out.println("ComboBox Action (selected: " + selectedSheet.toString() + ")");
    	    mainApp.setSelectedSheet(selectedSheet);
    	});
    	
    	comboBox.setOnShown((event) -> {
        	for(int i=0; i<mainApp.getNumberOfSheets(); i++) {
        		comboBoxData.add(mainApp.getSheetNames()[i]);
        	}
        	mainApp.setNumberOfSheets(0);
    	});
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.dialogStage.getIcons().add(new Image("/images/iui.png"));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
     * Called when the user clicks ok.
     * @throws NoSuchAlgorithmException 
     */
    @FXML
    private void handleOk() throws NoSuchAlgorithmException {
    	okClicked = true;
        dialogStage.close();   
    }
}

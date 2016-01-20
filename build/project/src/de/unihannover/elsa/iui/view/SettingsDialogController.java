package de.unihannover.elsa.iui.view;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import de.unihannover.elsa.iui.MainApp;
import de.unihannover.elsa.iui.model.Password;
import de.unihannover.elsa.iui.model.User;
import de.unihannover.elsa.iui.util.DateUtil;
import de.unihannover.elsa.iui.util.PasswordUtility;

/**
 * @author Fadi Asbih
 *
 * Copyright (c) 2014
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
public class SettingsDialogController {

	@FXML
    private TextField globalRoleField;
    @FXML
    private TextField localRoleField;
	@FXML
    private TextField timeLimitFromField;
    @FXML
    private TextField timeLimitUntilField;
    @FXML
    private TextField passwordField;
    @FXML
    private ToggleButton limitedButton;
    @FXML
    private CheckBox generatePassword;
    
    private Stage dialogStage;
    private boolean okClicked = false;
    
 // Reference to the main application.
    private MainApp mainApp;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
 
    	
    	limitedButton.setOnAction((event) -> {
    	    boolean selected = limitedButton.isSelected();
    	    System.out.println("Toggle Button Action (selected: " + selected + ")");
    		timeLimitFromField.setDisable(!selected);
    		timeLimitUntilField.setDisable(!selected);
    	});
    	
    	generatePassword.setOnAction((event) -> {
    	    boolean selected = generatePassword.isSelected();
    	    System.out.println("CheckBox Action (selected: " + selected + ")");
    	    passwordField.setDisable(selected);
    	});
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
        getSavedSettings();
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
     * This methods loads the settings from the first user.
     * 
     */
    public void getSavedSettings() {
    	if(!mainApp.getUserData().isEmpty()) {
    		System.out.println(mainApp.getUserData().get(0).getLocalRole().getValue());
        	localRoleField.setText(mainApp.getUserData().get(0).getLocalRole().getValue());
        	passwordField.setText(mainApp.getUserData().get(0).getPassword().getPasswordToHash());
        	timeLimitFromField.setText(mainApp.getUserData().get(0).getTimeLimitFrom());
        	timeLimitUntilField.setText(mainApp.getUserData().get(0).getTimeLimitUntil());
//        	limitedButton.setSelected(!Boolean.parseBoolean(mainApp.getUserData().get(0).getTimeLimitUnlimited()));
    	}
    	else {
    		System.out.println("empty... Load default values.");
    	}
    	
    }
    /**
     * Called when the user clicks ok.
     * @throws NoSuchAlgorithmException 
     */
    @FXML
    private void handleOk() throws NoSuchAlgorithmException {

//    	System.out.println(mainApp.getUserData());
    	for(User user : mainApp.getUserData()) {
    		user.getGlobalRole().setId(globalRoleField.getText());
    		user.getLocalRole().setId(localRoleField.getText());

    		if(generatePassword.isSelected()) {
    			System.out.println("Password is auto generated. Check the method parseExcel.");
    			user.setPassword(new Password(PasswordUtility.randomString(5)));
    		}
    		else {
        		user.setPassword(new Password(passwordField.getText()));
    		}
    		
    		if(limitedButton.isSelected()) {
    			user.setTimeLimitUnlimited("0"); // Time Limit is activated.
    			user.setTimeLimitFrom(timeLimitFromField.getText());
    			user.setTimeLimitUntil(timeLimitUntilField.getText());
    		}
    		else {
    			user.setTimeLimitUnlimited("1"); // Time Limit is deactivated.
    		}
    	}
    	
    	okClicked = true;
        dialogStage.close();
        
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}

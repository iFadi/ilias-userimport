package de.unihannover.elsa.iui.view;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.unihannover.elsa.iui.MainApp;
import de.unihannover.elsa.iui.model.Password;
import de.unihannover.elsa.iui.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
    	timeLimitFromField.setText(getTodaysDate());
    	timeLimitUntilField.setText(getDateLater(10));
    	
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

//    	System.out.println(mainApp.getUserData());
    	for(User user : mainApp.getUserData()) {
    		user.getGlobalRole().setId(globalRoleField.getText());
    		user.getLocalRole().setId(localRoleField.getText());

    		if(generatePassword.isSelected()) {
    			System.out.println("Password is auto generated. Check the method parseExcel.");
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
    
    private String getDateLater(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +n);
        Date date = cal.getTime();    
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	System.out.println(sdf.format(date));
    	return sdf.format(date);
    }
    
    private String getTodaysDate() {
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	System.out.println(sdf.format(date));
    	return sdf.format(date);
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}

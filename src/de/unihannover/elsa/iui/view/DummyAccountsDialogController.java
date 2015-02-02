package de.unihannover.elsa.iui.view;

import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import de.unihannover.elsa.iui.MainApp;
import de.unihannover.elsa.iui.model.Password;
import de.unihannover.elsa.iui.model.User;

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
public class DummyAccountsDialogController {

	@FXML
    private TextField numberOfAccountsField;
    @FXML
    private TextField loginPrefixField;
    @FXML
    private TextField passwordField;
	@FXML
    private TextField globalRoleField;
    @FXML
    private TextField localRoleField;
    @FXML
    private ToggleButton limitedButton;
	@FXML
    private TextField timeLimitFromField;
    @FXML
    private TextField timeLimitUntilField;

    
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
    	    System.out.println("CheckBox Action (selected: " + selected + ")");
    		timeLimitFromField.setDisable(!selected);
    		timeLimitUntilField.setDisable(!selected);
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
    private void handleGenerate() throws NoSuchAlgorithmException {

		User tempPerson;
		int count = 1;		
		// Generate a specific number of dummy accounts
		for(int i=1; i<=Integer.parseInt(numberOfAccountsField.getText()); i++) {
			tempPerson = new User();
			mainApp.getUserData().add(tempPerson);
		}
				
//    	System.out.println(mainApp.getUserData());
    	for(User user : mainApp.getUserData()) {
    		user.getGlobalRole().setId(globalRoleField.getText());
    		user.getLocalRole().setId(localRoleField.getText());
    		user.setFirstName("Ersatz Account");
    		user.setLastName(count+"");
    		user.setEmail("ersatz@account.de");
    		user.setMatriculation("123456");
    		user.setLogin(loginPrefixField.getText()+"_"+count);
    		user.setPassword(new Password(passwordField.getText()));
    		
    		if(limitedButton.isSelected()) {
    			user.setTimeLimitUnlimited("0"); // Time Limit is activated.
    			user.setTimeLimitFrom(timeLimitFromField.getText());
    			user.setTimeLimitUntil(timeLimitUntilField.getText());
    		}
    		else {
    			user.setTimeLimitUnlimited("1"); // Time Limit is deactivated.
    		}
    		count++;
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
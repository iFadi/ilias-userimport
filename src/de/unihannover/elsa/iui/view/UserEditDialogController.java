package de.unihannover.elsa.iui.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import de.unihannover.elsa.iui.model.User;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 * @author Fadi Asbih
 * 
 */
public class UserEditDialogController {

	@FXML
    private TextField globalRoleField;
    @FXML
    private TextField localRoleField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField matriculationField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;


    private Stage dialogStage;
    private User user;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
     * Sets the person to be edited in the dialog.
     * 
     * @param user
     */
    public void setUser(User user) {
        this.user = user;

        globalRoleField.setText(user.getGlobalRole().toString());
        localRoleField.setText(user.getLocalRole().toString());
        loginField.setText(user.getLogin());
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        matriculationField.setText(user.getMatriculation());
        genderField.setText(user.getGender());
        streetField.setText(user.getStreet());
        postalCodeField.setText(Integer.toString(user.getPostalCode()));
        cityField.setText(user.getCity());
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
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	user.setGlobalRole(globalRoleField.getText());
        	user.setLocalRole(localRoleField.getText());
        	user.setLogin(loginField.getText());
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setMatriculation(matriculationField.getText());
            user.setGender(genderField.getText());
            user.setStreet(streetField.getText());
            user.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            user.setCity(cityField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                .title("Invalid Fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}
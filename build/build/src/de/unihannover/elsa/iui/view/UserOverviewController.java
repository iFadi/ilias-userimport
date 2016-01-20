package de.unihannover.elsa.iui.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.controlsfx.dialog.Dialogs;

import de.unihannover.elsa.iui.MainApp;
import de.unihannover.elsa.iui.model.User;

/**
 *
 * @author Marco Jakob
 * @author Fadi Asbih
 */

public class UserOverviewController {
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> firstNameColumn;
	@FXML
	private TableColumn<User, String> lastNameColumn;
	@FXML
	private TableColumn<User, String> loginColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;

	@FXML
	private Label globalRoleLabel;
	@FXML
	private Label localRoleLabel;
	@FXML
	private Label loginLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label matriculationLabel;
	@FXML
	private Label genderLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label numberOfUsersLabel;
	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public UserOverviewController() {
	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param user the person or null
	 *            
	 */
	private void showPersonDetails(User user) {
		if (user != null) {
			// Fill the labels with info from the person object.
			globalRoleLabel.setText(user.getGlobalRole().getValue());
			System.out.println("global: " + user.getGlobalRole().getValue());
			localRoleLabel.setText(user.getLocalRole().getValue());
			System.out.println("Local: " + user.getLocalRole().getValue());
			loginLabel.setText(user.getLogin());
			passwordLabel.setText(user.getPassword().getPasswordToHash());
			// System.out.println(user.getLogin());
			firstNameLabel.setText(user.getFirstName());
			lastNameLabel.setText(user.getLastName());
			emailLabel.setText(user.getEmail());
			matriculationLabel.setText(user.getMatriculation());
			genderLabel.setText(user.getGender());
			streetLabel.setText(user.getStreet());
			postalCodeLabel.setText(Integer.toString(user.getPostalCode()));
			cityLabel.setText(user.getCity());

		} else {
			// Person is null, remove all the text.
			globalRoleLabel.setText("");
			localRoleLabel.setText("");
			loginLabel.setText("");
			passwordLabel.setText("");
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			emailLabel.setText("");
			matriculationLabel.setText("");
			genderLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
		}
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		loginColumn.setCellValueFactory(cellData -> cellData.getValue().LoginProperty());
		passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()
				.getPasswordToHash()));

		// Clear person details.
		showPersonDetails(null);

		// Listen for selection changes and show the person details when
		// changed.
		userTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		
		userTable.setOnMouseClicked((event) -> {
    	    System.out.println("Mouse is moving "+getNumberOfUsers());
    	    numberOfUsersLabel.setText(getNumberOfUsers()+"");

    	});
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		userTable.setItems(mainApp.getUserData());
		getNumberOfUsers();
	}

	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
		User tempPerson = new User();
		boolean okClicked = mainApp.showUserEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getUserData().add(tempPerson);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		User selectedPerson = userTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showUserEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Nothing selected.
			Dialogs.create().title("No Selection").masthead("No Person Selected")
					.message("Please select a person in the table.").showWarning();
		}
	}

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			userTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Dialogs.create().title("No Selection").masthead("No Person Selected")
					.message("Please select a person in the table.").showWarning();
		}
	}

	/**
	 * Testing a Printing function of the TableView.
	 */
	@FXML
	private void handlePrint() {
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		System.out.println("Trying to print.");

		if (printerJob.showPrintDialog(mainApp.getPrimaryStage()) && printerJob.printPage(userTable)) {
			printerJob.endJob();
			System.out.println("printed");
		}
	}
	
	public int getNumberOfUsers(){
		return mainApp.getNumberOfUsers();
	}
}
package de.unihannover.elsa.iui.view;

import java.security.NoSuchAlgorithmException;

import de.unihannover.elsa.iui.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * 
 * 
 * @author Fadi Asbih
 * 
 */
public class UpdateDialog {

	@FXML
    private TextFlow textFlow;
	@FXML
    private Label labelCurrentVersion;
	@FXML
    private Label labelNewVersion;
    
    private Stage dialogStage;
    private boolean okClicked = false;
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws Exception 
     */
    @FXML
    private void initialize() {

    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     * @throws Exception 
     */
    public void setMainApp(MainApp mainApp) throws Exception {
        this.mainApp = mainApp;
        getNewVersion();
        getCurrentVersion();
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     * @throws Exception 
     */
    public void setDialogStage(Stage dialogStage) throws Exception {
        this.dialogStage = dialogStage;
        this.dialogStage.getIcons().add(new Image("file:resources/images/iui.png"));
    	labelNewVersion.setText(getNewVersion());
    	labelCurrentVersion.setText(getCurrentVersion());
    	
    	
    	Text text1 = new Text("There is a new version available.\n");
    	Text text2 = new Text("You can download it at:");
    	Hyperlink text3 = new Hyperlink();
    	text3.setText("https://github.com/iFadi/ilias-userimport");
    	text3.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent e) {
    	    	mainApp.getHostServices().showDocument("https://github.com/iFadi/ilias-userimport/releases");
//    	        System.out.println("This link is clicked");
    	    }
    	});
//    	Text text4 = new Text("\nChanges: \n" + getWhatsNew());
    	textFlow.getChildren().addAll(text1,text2,text3);


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
     * Called when the user clicks close.
     * @throws NoSuchAlgorithmException 
     */
    @FXML
    private void handleClose() throws NoSuchAlgorithmException {
            okClicked = true;
            dialogStage.close();
    }
    
	public String getNewVersion() throws Exception {
		return mainApp.getNewVersion();
	}
	
	public String getCurrentVersion() throws Exception {
		return mainApp.getCurrentVersion();
	}
	
	public String getWhatsNew() throws Exception {
		 return mainApp.getWhatsNew();
	}
}
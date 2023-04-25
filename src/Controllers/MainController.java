package Controllers;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class MainController implements Initializable {
	 @FXML
	    private MenuItem closeMenuItem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void loadBookList (ActionEvent event) {
		loadWindow("/App/BookList.fxml","Book List");
	}
	public void loadMemberList (ActionEvent event) {
		loadWindow("/App/MemberList.fxml","Member List");
	}
	public void loadIssueList(ActionEvent event) {
		loadWindow("/App/IssuedBookList.fxml","Issue List");
	}
	public void loadBook (ActionEvent event) {
		loadWindow("/App/Book.fxml","Books Management");
	}
	public void loadMembers(ActionEvent event) {
		loadWindow("/App/Member.fxml","Members Management");
	}
	public void loadIssue (ActionEvent event) {
		loadWindow("/App/Issue.fxml","Issue Management");
	}
	public void loadWindow(String loc , String title)  {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException e) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
	}
	public void close(ActionEvent event) {
		Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.close();
	}
}

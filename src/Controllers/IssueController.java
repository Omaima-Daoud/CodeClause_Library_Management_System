package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import Database.SConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import Database.*;

public class IssueController {
	@FXML
	private TextField book_id ;
	@FXML
	private TextField member_id ;
	@FXML
	private Button returnbtn ;
	@FXML
	private Button cancelbtn ;
	@FXML
	private Button issuebtn ;
	private Window rootPane;
	
	public void intialize(URL url , ResourceBundle rb) {
		
		Connection conn = SConnection.getInstance(); 
		
		
	}
	
	public void issueBook (ActionEvent event) {
		
		DatabaseHandler conn = new DatabaseHandler();
		String BookId =book_id.getText();
		String  MemberId =member_id.getText();
		if(BookId.isEmpty()|| MemberId.isEmpty()) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return ;
		}
		
		/*if(!(conn.isMemberExists(MemberId))||!(conn.isBookExists(BookId))) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("The book or the member doesn't exist");
            alert.showAndWait();
            return ;*/
		LocalDate today = LocalDate.now();
		LocalDate fifteenLater =today.plus(15, ChronoUnit.DAYS);
		boolean success = conn.issueBook(BookId, MemberId, fifteenLater);
		if(success) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Well Done ");
            alert.setContentText("Book Issued Successfully ");
            alert.showAndWait();
            return ;
		}	
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to Issue the Book ");
            alert.showAndWait();
            return ;
		}
	}
	
	public void returnBook (ActionEvent event) {
		DatabaseHandler conn = new DatabaseHandler();
		String BookId =book_id.getText();
		String  MemberId =member_id.getText();
		if(BookId.isEmpty()|| MemberId.isEmpty()) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return ;
		}
		
		if((!(conn.isMemberExists(MemberId))||!(conn.isBookExists(BookId)))) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("One of the fields doesn't exist");
            alert.showAndWait();
            return ;
		}
		boolean success = conn.returnBook(BookId, MemberId);
		if(success) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Well Done ");
            alert.setContentText("Book Returned Successfully ");
            alert.showAndWait();
            return ;
		}	
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to Return the Book ");
            alert.showAndWait();
            return ;
		}
	}
	public void cancel (ActionEvent event) {
		  book_id.setText("");
		  member_id.setText("");
	}
	
	
	

}

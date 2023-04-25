package Controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import Database.SConnection;
import Models.Book;
import Models.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MemberController {
	@FXML
	private TextField id ;
	@FXML
	private TextField name ;
	@FXML
	private TextField email ;
	@FXML
	private TextField phone;
	@FXML
	private Button savebtn ;
	@FXML
	private Button cancelbtn ;
	@FXML
	private Button deletebtn ;
	Window rootPane;
	
	public void intialize(URL url , ResourceBundle rb) {
		
		Connection conn = SConnection.getInstance(); 
		
		
	}
	public void addMember(ActionEvent event) {
		
		String MemberId =id.getText();
		String MemberName =name.getText();
		String MemberEmail =email.getText();
		String MemberPhone =phone.getText();
		
		
		if (MemberId.isEmpty()||MemberName.isEmpty()||MemberEmail.isEmpty()||MemberPhone.isEmpty()){
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return ;
		}

		DatabaseHandler conn = new DatabaseHandler();
		Member member = new Member(MemberId,MemberName,MemberEmail,MemberPhone);
		if (conn.isMemberExists(MemberId)) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Member  Already exists ");
            alert.showAndWait();
            return ;
		}
		else {
		boolean success= conn.addMember(member);
		if(success) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Well Done ");
            alert.setContentText("Member Added Successfully ");
            alert.showAndWait();
            return ;
		}	
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to Add the Member ");
            alert.showAndWait();
            return ;
		}
	}
	}
	
	public void cancel (ActionEvent event) {
		  id.setText("");
		    name.setText("");
		    email.setText("");
		    phone.setText("");
	}
	
	public void deleteMember (ActionEvent event) {
		String  MemberId = id.getText();
		if (MemberId.isEmpty()){
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return ;
		}
			DatabaseHandler conn = new DatabaseHandler();
			boolean find = conn.isMemberExists(MemberId);
			if (find) {
			boolean success= conn.deleteMember(MemberId);
			if(success) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Well Done ");
	            alert.setContentText("Member Deleted Successfully ");
	            alert.showAndWait();
	            return ;
			}	
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setContentText("Failed to delete the Member ");
	            alert.showAndWait();
	            return ;
			}
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setContentText("This Member doesn't exist in our Library  ");
	            alert.showAndWait();
	            return ;
			}
		}
}



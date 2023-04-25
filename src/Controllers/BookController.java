package Controllers;
import java.net.URL;

import Database.*;
import Models.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import Database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BookController {
	@FXML
	private TextField id ;
	@FXML
	private TextField author ;
	@FXML
	private TextField title ;
	@FXML
	private TextField publisher;
	@FXML
	private TextField year;
	@FXML
	private Button savebtn ;
	@FXML
	private Button cancelbtn ;
	@FXML
	private Button deletebtn ;
	Window rootPane;

	@FXML
	public void initialize(URL url, ResourceBundle rb, Window rootPane) {
	    this.rootPane = savebtn.getScene().getWindow();;
	    DatabaseHandler conn = new DatabaseHandler(); 
	}


	public void addBook(ActionEvent event) {
		
		String bookId =id.getText();
		String bookAuthor =author.getText();
		String bookTitle =title.getText();
		String bookPublisher =publisher.getText();
		String bookYearPublished =year.getText();
		
		
		
		if (bookId.isEmpty()||bookAuthor.isEmpty()||bookTitle.isEmpty()||bookYearPublished.isEmpty()||bookPublisher.isEmpty()){
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return ;
		}
			DatabaseHandler conn = new DatabaseHandler();
			Book b = new Book(bookId,bookTitle ,bookAuthor,bookPublisher,bookYearPublished);
			if (conn.isBookExists(bookId)) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setContentText("Book Already exists ");
	            alert.showAndWait();
	            return ;
			}
			else {
			boolean success= conn.addBook(b);
			if(success) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Well Done ");
	            alert.setContentText("Book Added Successfully ");
	            alert.showAndWait();
	            return ;
			}	
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setContentText("Failed to Add the Book ");
	            alert.showAndWait();
	            return ;
			}
		}
	}
	
	public void cancel (ActionEvent event) {
		    id.setText("");
		    title.setText("");
		    author.setText("");
		    publisher.setText("");
		    year.setText("");
		}

	public void deleteBook (ActionEvent event) {
		String bookId =id.getText();
		if (bookId.isEmpty()){
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Data ");
            alert.setContentText("Please Enter in all Fields");
            alert.showAndWait();
            return ;
		}
			DatabaseHandler conn = new DatabaseHandler();
			boolean find = conn.isBookExists(bookId);
			if (find) {
			boolean success= conn.deleteBook(bookId);
			if(success) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Well Done ");
	            alert.setContentText("Book Deleted Successfully ");
	            alert.showAndWait();
	            return ;
			}	
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setContentText("Failed to delete the Book ");
	            alert.showAndWait();
	            return ;
			}
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setContentText("This Book doesn't exist in our Library  ");
	            alert.showAndWait();
	            return ;
			}
		}
	}

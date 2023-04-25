package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Database.DatabaseHandler;
import Controllers.*;
import Models.*;
//import library.assistant.ui.main.MainController;

	
	public class BookListController implements Initializable {

	    ObservableList<Book> list = FXCollections.observableArrayList();
	    @FXML
	    private TableView<Book> tableView;
	    @FXML
	    private TableColumn<Book, String> titleCol;
	    @FXML
	    private TableColumn<Book, String> idCol;
	    @FXML
	    private TableColumn<Book, String> authorCol;
	    @FXML
	    private TableColumn<Book, String> publisherCol;
	    @FXML
	    private TableColumn<Book, String> yearCol;
	    @FXML
	    private AnchorPane contentPane;

	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        initCol();
	        loadData();
	    }
	    /*public void showTable() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/App/BookList.fxml"));
	            AnchorPane root = (AnchorPane) loader.load();
	            Scene scene = new Scene(root);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }*/

	    private Stage getStage() {
	        return (Stage) tableView.getScene().getWindow();
	    }

	    private void initCol() {
	        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
	        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
	        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
	        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
	        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
	    }

	    private void loadData() {
	        list.clear();

	        DatabaseHandler conn = new DatabaseHandler();
	        ResultSet rs = conn.getAllBooks();
	        try {
	        	while (rs.next()) {
	        	    String id = rs.getString("id");
	        	    String title = rs.getString("title");
	        	    String author = rs.getString("author");
	        	    String publisher = rs.getString("publisher");
	        	    String year = rs.getString("year_published");
	        	    System.out.println("Retrieved book: " + id + ", " + title + ", " + author + ", " + publisher + ", " + year);
	        	    list.add(new Book(id, title, author, publisher, year));
	        	}

	        } catch (SQLException ex) {
	        	System.out.println(ex);
	        }

	        finally {
	        	System.out.println("Loaded " + list.size() + " books.");
	        	tableView.getItems().setAll(list);

	        }
	    }


	    @FXML
	    private void handleRefresh(ActionEvent event) {
	        loadData();
	    }

	    @FXML
	    private void closeStage(ActionEvent event) {
	        getStage().close();
	    }

	   

	}



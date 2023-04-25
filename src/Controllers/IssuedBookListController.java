package Controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import Database.DatabaseHandler;

import Models.*;

	public class IssuedBookListController implements Initializable {

			    ObservableList<bookIssue> list = FXCollections.observableArrayList();
			    @FXML
			    private TableView<bookIssue> tableView;
			    @FXML
			    private TableColumn<bookIssue, String> bookIdCol;
			    @FXML
			    private TableColumn<bookIssue, String> memberIdCol;
			    @FXML
			    private TableColumn<bookIssue, Date> issueDCol;
			    @FXML
			    private TableColumn<bookIssue, Date> dueDCol;
			    @FXML
			    private TableColumn<bookIssue, Date> returnDCol;
			    
			    @FXML
			    private AnchorPane contentPane;

			    @Override
			    public void initialize(URL url, ResourceBundle rb) {
			        initCol();
			        loadData();
			    }
			   

			    private Stage getStage() {
			        return (Stage) tableView.getScene().getWindow();
			    }

			    private void initCol() {
			    	bookIdCol.setCellValueFactory(new PropertyValueFactory<>("member_id"));
			    	memberIdCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));
			    	issueDCol.setCellValueFactory(new PropertyValueFactory<>("issue_date"));
			    	dueDCol.setCellValueFactory(new PropertyValueFactory<>("due_date"));
			    	returnDCol.setCellValueFactory(new PropertyValueFactory<>("return_date"));
			    	
			    }

			    private void loadData() {
			        list.clear();

			        DatabaseHandler conn = new DatabaseHandler();
			        ResultSet rs = conn.getAllBookIssues();
			        try {
			        	while (rs.next()) {
			        	    String bookId = rs.getString("book_id");
			        	    String memberId = rs.getString("member_id");
			        	    Date issueDate = rs.getDate("issue_date");
			        	    Date dueDate = rs.getDate("due_date");
			        	    Date returnDate = rs.getDate("return_date");
			        	    list.add(new bookIssue(bookId, memberId , issueDate, dueDate,returnDate));
			        	}

			        } catch (SQLException ex) {
			        	System.out.println(ex);
			        }

			        finally {
			        	System.out.println("Loaded " + list.size() + " bookIssues.");
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



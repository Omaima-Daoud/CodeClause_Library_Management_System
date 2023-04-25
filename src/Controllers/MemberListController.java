package Controllers;

import java.net.URL;

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


		
		public class MemberListController implements Initializable {

		    ObservableList<Member> list = FXCollections.observableArrayList();
		    @FXML
		    private TableView<Member> tableView;
		    @FXML
		    private TableColumn<Member, String> nameCol;
		    @FXML
		    private TableColumn<Member, String> idCol;
		    @FXML
		    private TableColumn<Member, String> emailCol;
		    @FXML
		    private TableColumn<Member, String> phoneCol;
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
		        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		    }

		    private void loadData() {
		        list.clear();

		        DatabaseHandler conn = new DatabaseHandler();
		        ResultSet rs = conn.getAllMembers();
		        try {
		        	while (rs.next()) {
		        	    String id = rs.getString("id");
		        	    String name = rs.getString("name");
		        	    String email = rs.getString("email");
		        	    String phone = rs.getString("phone");
		        	    System.out.println("Retrieved Member: " + id + ", " + name + ", " + email + ", " + phone );
		        	    list.add(new Member(id, name, email, phone));
		        	}

		        } catch (SQLException ex) {
		        	System.out.println(ex);
		        }

		        finally {
		        	System.out.println("Loaded " + list.size() + " Members.");
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





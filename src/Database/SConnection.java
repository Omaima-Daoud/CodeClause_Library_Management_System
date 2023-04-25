package Database ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;

public class SConnection {
	private static String url = "jdbc:mysql://localhost:3306/dbLibrary?autoReconnect=true&useSSL=false";;
	private static String utilisateur = "root";
	private static String motPasse = "";
	private static Connection cnx;

	public SConnection() {
	}

	public static Connection getInstance() {
		try {
			
		if(cnx==null || cnx.isClosed()) {
			cnx = DriverManager.getConnection(url, utilisateur, motPasse);
			System.out.println("Connected to the Library Database Successfully.");}
		} catch (SQLException e) {
			System.out.println("Failed To connect ."
					+ "Please verify that you have added the  jdbc to your  build path");
			System.exit(0);
		}
		return cnx;
	}

	public static void close() {
		try {
			if (cnx != null && !cnx.isClosed())
				cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*public ResultSet executeQuery(String query) {
		ResultSet result = null ;
		try {
        	Connection conn = SConnection.getInstance();
            Statement statement = conn.createStatement();
            result =statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return result;
        }
        finally {
        	return result ;
        }
    }
	 public boolean executeAction(String query) {
	        try {
	        	Connection conn = SConnection.getInstance();
	            Statement statement = conn.createStatement();
	            return statement.execute(query);
	        } catch (SQLException e) {
	        	 Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setTitle("Error");
	             alert.setHeaderText("Failed to execute Action");
	             alert.setContentText(e.getMessage());
	             alert.showAndWait();
	            System.out.println("Error: " + e.getMessage());
	        }
			return false;
	    }*/

}

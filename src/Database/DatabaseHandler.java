package Database;
import Models.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseHandler {

    private static final String USERS_TABLE = "members";
    private static final String BOOKS_TABLE = "books";
    private static final String ISSUES_TABLE="book_issues";

    private Connection connection;

    public DatabaseHandler() {
        connection = SConnection.getInstance();
    }

    public boolean executeAction(String query) {
        try {
            return SConnection.getInstance().createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return SConnection.getInstance().createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //Member 
    
    public boolean isMemberExists(String id) {
        String query = "SELECT COUNT(*) FROM " + USERS_TABLE + " WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMember(Member member) {
        String insertQuery = "INSERT INTO " + USERS_TABLE + " (id,name,email,phone) VALUES(?, ?, ?, ?)"+";";
        try {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, member.getId());
            statement.setString(2, member.getName());
            statement.setString(3, member.getEmail());
            statement.setString(4, member.getPhone());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteMember(String memberId) {
        String deleteQuery = "DELETE FROM " + USERS_TABLE + " WHERE id=?"+";";
        try {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, memberId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ResultSet getAllMembers() {
        String query = "SELECT * FROM " + USERS_TABLE+";";
        return executeQuery(query);
    }

    //Books 
    
   /* public boolean isBookExists(String title, String author) {
        String query = "SELECT COUNT(*) FROM " + BOOKS_TABLE + " WHERE title=? AND author=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, author);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    public boolean isBookExists(String id) {
        String query = "SELECT COUNT(*) FROM " + BOOKS_TABLE + " WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    

    public boolean addBook(Book book) {
    	String insertQuery = "INSERT INTO " + BOOKS_TABLE + " (id, title, author, publisher, year_published) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            String year = book.getYear();
            if (year.length() > 10) {
                year = year.substring(0, 10);
            }
            statement.setString(5, year);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    
    public boolean deleteBook(String bookId) {
        String deleteQuery = "DELETE FROM " + BOOKS_TABLE + " WHERE id=?"+";";
        try {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, bookId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public ResultSet getAllBooks() {
        String query = "SELECT * FROM " + BOOKS_TABLE+";";
        return executeQuery(query);
    }

    public ResultSet getBookByTitle(String title) {
    	String query = "SELECT * FROM " + BOOKS_TABLE + " WHERE title='" + title + "';";
        return executeQuery(query);
    }

    public ResultSet getBookById(int id) {
    	String query = "SELECT * FROM " + BOOKS_TABLE + " WHERE id='" + id + "';";

        return executeQuery(query); 
    }
    
    public ResultSet getBookByAuthor(String author) {
    	String query = "SELECT * FROM " + BOOKS_TABLE + " WHERE author='" + author + "';";

        return executeQuery(query); 
    }
    
    public ResultSet getBookByYearPublished(String yearPublished) {
    	String query = "SELECT * FROM " + BOOKS_TABLE + " WHERE year_published='" +yearPublished + "';";

        return executeQuery(query); 
    }
    
    
    
    //Issue 
    
    public ResultSet getAllBookIssues() {
        String query = "SELECT * FROM "+ISSUES_TABLE+";";
        return executeQuery(query);
    }
    
    public boolean isBookIssued(String bookId) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT * FROM book_issues WHERE book_id=? AND return_date IS NULL"+";";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookId);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean issueBook(String bookId, String memberId, LocalDate dueDate) {
        // Check if the book is available for issue
        if (this.isBookIssued(bookId)) {
            return false;
        }

        // Check if the member exists in the database
        if (!isMemberExists(memberId)) {
            return false;
        }
        if (!this.isBookExists(bookId)) {
        	return false;
        }

        // Issue the book
        String insertQuery = "INSERT INTO book_issues (book_id, member_id, issue_date, due_date) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, bookId);
            statement.setString(2, memberId);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setDate(4, Date.valueOf(dueDate));
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean returnBook(String bookId, String memberId ) {
        try {
        	LocalDate d =LocalDate.now();
        	Date date = Date.valueOf(d);
            String query = "UPDATE book_issues SET return_date = ? WHERE book_id = ? AND member_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1,date );
            statement.setString(2, bookId);
            statement.setString(3, memberId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}


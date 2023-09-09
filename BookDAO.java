package org.openlogix;
import java.sql.*;
import java.util.ArrayList;
 
public class BookDAO {

    static final String url = "jdbc:mysql://localhost:3306/library";
    static final String username = "root";
    static final String password = "password";
    Connection con;

    BookDAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    void closeConnection(Connection con)  throws SQLException {
        if(!con.isClosed())
            con.close();
    }

    void addBook(Book book) {
        try {
            con = getConnection();
            Statement st = con.createStatement();
            System.out.println("INSERT INTO books(name, publisherName, authorName) VALUES('" + book.name + "', '" + book.publisherName + "', '" + book.authorName + "');");
            st.execute(
                "INSERT INTO books(name, publisherName, authorName) VALUES('" + book.name + "', '" + book.publisherName + "', '" + book.authorName + "');"
            );
            closeConnection(con);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Book> searchBooks(String bookName) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            con = getConnection();
            Statement st = con.createStatement();
            System.out.println("SELECT * from books WHERE name = '"+ bookName + "';");
            ResultSet rs = st.executeQuery(
                "SELECT * from books WHERE name = '"+ bookName + "';"
            );
            while(rs.next()) {
                Book book = new Book(rs.getString("name"), rs.getString("publisherName"), rs.getString("authorName"));
                books.add(book);
            }
            closeConnection(con);
            return books; 
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    }   
        


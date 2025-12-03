package server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import client.Book;

import client.DatabaseConnection;




public class clientHandler implements Runnable {
    private Socket socket;

    public clientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            String command = (String) in.readObject();

            switch (command) {
                case "ADD_BOOK" -> handleaddbook(in, out);
                case "UPDATE_BOOK"->handleupdatebook(in,out);
                case "GET_BOOKS"->handlegetbooks(in,out);
                case "DELETE_BOOK"->handledeletebook(in,out);
               
                default -> {
                    out.writeObject("Unknown command");
                    out.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleaddbook(ObjectInputStream in, ObjectOutputStream out) throws Exception {
        Book book = (Book) in.readObject();
        try (Connection con = DatabaseConnection.getConnection()) {
        	  String sql = """
                      INSERT INTO books (
                          title,author,year
                      ) VALUES (?,?,?)
                  """;

                  PreparedStatement ps = con.prepareStatement(sql);
                  ps.setString(1, book.getTitle());
                  ps.setString(2, book.getAuthor());
                  ps.setInt(3,book.getYear());
            int rs = ps.executeUpdate();

           

            out.writeObject("Book added succesfully");
            out.flush();
        }
    }

    
    private void handleupdatebook(ObjectInputStream in, ObjectOutputStream out) throws Exception{
    	  int bookid = (int) in.readObject();
    	  String booktitle=(String) in.readObject();
          try (Connection con = DatabaseConnection.getConnection()) {
          	  String sql = """
                       UPDATE books SET title=? WHERE id=?
                    """;

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1,booktitle);
                    ps.setInt(2, bookid);
                    
              int rs = ps.executeUpdate();

             

              out.writeObject("Book updated succesfully");
              out.flush();
          } 
    }
    
    
    private void handlegetbooks(ObjectInputStream in, ObjectOutputStream out) throws Exception{
    	 try (Connection con = DatabaseConnection.getConnection()) {
             PreparedStatement ps = con.prepareStatement(
                 "SELECT title,author,year FROM books"
             );
             
             ResultSet rs = ps.executeQuery();

             List<Book> books = new ArrayList<>();
             while (rs.next()) {
                 Book s = new Book();
                 s.setTitle(rs.getString("title"));
                 s.setAuthor(rs.getString("author"));
                 s.setYear(rs.getInt("year"));
               
                 books.add(s);
             }

             out.writeObject(books);
             out.flush();
         }
    }
    
    
    
    private void handledeletebook(ObjectInputStream in, ObjectOutputStream out) throws Exception{
    	 int bookid = (int) in.readObject();
   	 
         try (Connection con = DatabaseConnection.getConnection()) {
         	  String sql = """
                      DELETE FROM books WHERE id=?
                   """;

                   PreparedStatement ps = con.prepareStatement(sql);
              
                   ps.setInt(1, bookid);
                   
             int rs = ps.executeUpdate();

            

             out.writeObject("Book deleted succesfully");
             out.flush();
         } 
    }

}
package databasecon;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Databaseconnection {
  public static final String URL="jdbc:mysql://localhost:3306/studentdb2";
  public static final String User="root";
  public static final String password="";
  
  
  
  
  public static Connection getConnection() {
	  Connection conn = null;
	  try {
		  conn=DriverManager.getConnection(URL, User, password);
	      if(conn!=null) {
	    	  JOptionPane.showMessageDialog(null,"Connected to server","JDbc server status",JOptionPane.INFORMATION_MESSAGE);
	      return conn;
	      }
	  }catch(Exception e) {
		  e.getMessage();
	  }
	  
	  if(conn==null) {
		  System.out.print("null");
	  }
return conn;
  }
  
  
  
}

package moodlelabtestpack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import databasecon.Databaseconnection;

public class Student {
	public Connection conn;
	public String name;
	public String email;
	public String course;
public Student() {
	conn=Databaseconnection.getConnection();
	this.name=" ";
	this.email=" ";
	this.course=" ";
}


public Student(String n,String e,String c) {
	this.name=n;
	this.email=e;
	this.course=c;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getCourse() {
	return course;
}


public void setCourse(String course) {
	this.course = course;
}


public void save(String n,String e,String c) {
	try {
	String sql="INSERT INTO students(name,email,course)VALUES('"+n+"','"+e+"','"+c+"')";
    Statement stat=conn.createStatement();
    int result=stat.executeUpdate(sql);
    
    System.out.print(result);
	}catch(Exception ex) {
		ex.getMessage();
	}

}


public String Fetchall() {
	String FullString=" ";
	try {
		String sql="SELECT * FROM students";
		Statement stat=conn.createStatement();
		ResultSet result=stat.executeQuery(sql);
		
		while(result.next()){
			FullString+=result.getString("name")+"\n"+result.getString("email")+"\n"+result.getString("Course")+"\n";
		}
	}catch(Exception e) {
		e.getMessage();
	}
	
	return FullString;
}
}

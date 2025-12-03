package client;

import java.io.Serializable;

public class Book implements Serializable {
private String title;
private String author;
private int year;





public Book() {
	this.title=" ";
	this.author=" ";
	this.year=0;
}



public Book(String t,String a,int y) {
	title=t;
	author=a;
	year=y;
}



public String getTitle() {
	return title;
}



public void setTitle(String title) {
	this.title = title;
}



public String getAuthor() {
	return author;
}



public void setAuthor(String author) {
	this.author = author;
}



public int getYear() {
	return year;
}



public void setYear(int year) {
	this.year = year;
}







}

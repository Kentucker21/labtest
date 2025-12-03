package server;

import java.net.ServerSocket;
import java.net.Socket;

public class server {
 public server(int port) {
	 try {
	 ServerSocket Socket=new ServerSocket(port);
	 System.out.print("Server started on port "+port);
	 
	 while (true) {
		 Socket clientSocket=Socket.accept();
		 System.out.print("client connected");
		 
		 new Thread(new clientHandler(clientSocket)).start();;
	 }
	 
	 }catch(Exception e) {
		 e.getMessage();
	 }
 }
 
 
 public static void main(String[] args) {
     new server(5000); // Server starts here
 }
}

package client;

import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.List;



public class ClientConnection {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8000;

    
   

    // ADD Book
    public static String addBook(Book book) {
        try (
        	//Socket to establish connection to server
        	Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        		// input output stream 
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
           
        	//outputs command and shipment object to server
            out.writeObject("ADD_BOOK");
            out.writeObject(book);
            out.flush();
            
            //catches server response
            Object response = in.readObject();
            if (response instanceof String) {
                return (String) response;
            }
            

        } catch (EOFException e) {
            System.err.println("Server closed connection unexpectedly.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ERROR: No response from server";
    }
    
    
    public static String updateBook(int Id,String title) {
    	try (
            	//Socket to establish connection to server
            	Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            		// input output stream 
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
               
            	//outputs command and shipment object to server
                out.writeObject("UPDATE_BOOK");
                out.writeObject(Id);
                out.writeObject(title);
                out.flush();
                
                //catches server response
                Object response = in.readObject();
                if (response instanceof String) {
                    return (String) response;
                }
                

            } catch (EOFException e) {
                System.err.println("Server closed connection unexpectedly.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "ERROR: No response from server";
    }
    
    public static List<Book> getAllBooks() {
    	try (
            	//opens new client socket
            	Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            	 
            	//opens output and input stream
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                // Send command and customerId
                out.writeObject("GET_BOOKS");
               
                out.flush();

                // Read server response
                Object response = in.readObject();
                
                //checks if response is of type list 
                if (response instanceof List<?>) {
                    return (List<Book>) response;
                } else {
                    System.err.println("Unexpected response from server: " + response);
                }

            } catch (EOFException e) { //catches end of file 
                System.err.println("Server closed connection unexpectedly.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return Collections.emptyList(); // just in case list is empty return something
    }
    
    public static String deleteBook(int Id) {
    	try (
            	//Socket to establish connection to server
            	Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            		// input output stream 
                 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
               
            	//outputs command and shipment object to server
                out.writeObject("DELETE_BOOK");
                out.writeObject(Id);
               
                out.flush();
                
                //catches server response
                Object response = in.readObject();
                if (response instanceof String) {
                    return (String) response;
                }
                

            } catch (EOFException e) {
                System.err.println("Server closed connection unexpectedly.");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "ERROR: No response from server";
    }
}

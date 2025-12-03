package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class clientHandler implements Runnable{
 
	private Socket socket;
	public clientHandler(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
			
			
			Double fPrincipal=(double) in.readObject();
			Double fRate=(double) in.readObject();
			int fTime=(int) in.readObject();
			
			out.writeObject(Calculate(fPrincipal,fRate,fTime));
			out.flush();
			
			
			
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
	
	
	
	public Double Calculate(double principal,double rate,int time) {
		
		double result=principal*rate*time/100;
		return  result;
	}

}

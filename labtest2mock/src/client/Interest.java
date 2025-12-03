package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interest extends JFrame {
	public static final String Server_name="localhost";
	public static final int Server_port=5000;
	
     JLabel Plabel=new JLabel("Principal");
     JLabel RLabel=new JLabel("Rate");
     JLabel Tlabel=new JLabel("Time");
     JTextField Prinicipal=new JTextField();
     JTextField Rate=new JTextField();
     JTextField Time=new JTextField();
     JTextField result=new JTextField();
     JLabel Relabel=new JLabel("result");
     JButton Submit=new JButton("submit");
     
	
	public Interest() {
		super("Compound interest");
		setSize(400,400);
		setLayout(new BorderLayout());
		InitializeComponents();
		setVisible(true);
	}
	
	public JPanel createPairs(JLabel label, JTextField field) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        label.setAlignmentX(LEFT_ALIGNMENT);
        field.setAlignmentX(LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(200, 40));

        panel.add(label);
        panel.add(field);

        return panel;
    }
	
	
	
	
	public void InitializeComponents() {
		JPanel MainPanel=new JPanel();
		MainPanel.setLayout(new BoxLayout(MainPanel,BoxLayout.Y_AXIS));
		MainPanel.add(createPairs(Plabel,Prinicipal));
		MainPanel.add(createPairs(RLabel,Rate));
		MainPanel.add(createPairs(Tlabel,Time));
		MainPanel.add(createPairs(Relabel,result));
		
		add(MainPanel,BorderLayout.NORTH);
		add(Submit,BorderLayout.SOUTH);
		
		Submit.addActionListener(e->{
			send();
		});
	}
	
	public void send() {
		System.out.print("hello1");
		double Ratetext=Double.parseDouble(Rate.getText());
		double Principaltext=Integer.parseInt(Prinicipal.getText());
		int Timetext=Integer.parseInt(Time.getText());
		
		
		try {
			Socket socket=new Socket(Server_name,Server_port);
			ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
			
			
			out.writeObject(Principaltext);
			out.writeObject(Ratetext);
			out.writeObject(Timetext);
			out.flush();
			
			
			
			Object response=(double) in.readObject();
			
			
			result.setText(response.toString());
			
			
		}catch(Exception ex) {
			ex.getMessage();
		}
		
		
		
		
		
	
	}
	
	
	
	
	
	
	public static void  main(String args[]) {
		new Interest();
	}

}



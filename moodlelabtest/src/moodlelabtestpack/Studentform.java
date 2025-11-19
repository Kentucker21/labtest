package moodlelabtestpack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Studentform extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel Name;
	JLabel Email;
	JLabel Course;
	JTextField Namefield;
	JTextField Emailfield;
	JTextField Coursefield;
	JTextArea ViewArea;
	JButton Save;
	JButton Viewall;

	public Studentform() {
		super("Student Form");
	     setSize(500,500);
	     setLayout(new BorderLayout());
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     InitializeComponents();
	     setVisible(true);
	     
	   
	     
	}
	
	
	
	
	public void InitializeComponents() {
		Name=new JLabel("Name:");
		Email=new JLabel("Email:");
		Course=new JLabel("Course:");
		Namefield=new JTextField();
		Emailfield=new JTextField();
		Coursefield=new JTextField();
		Save=new JButton("Save");
		Viewall=new JButton("View All");
		
		ViewArea=new JTextArea();
		ViewArea.setPreferredSize(new Dimension(300,200));
		
		JPanel ViewAreaPanel=new JPanel();
		JScrollPane pane=new JScrollPane(ViewArea);
		ViewAreaPanel.add(pane);
		
		
		
		
		JPanel Mainpanel=new JPanel();
		Mainpanel.setLayout(new BoxLayout(Mainpanel,BoxLayout.Y_AXIS));
		
		Mainpanel.add(CreatePairs(Name,Namefield));
		Mainpanel.add(CreatePairs(Email,Emailfield));
		Mainpanel.add(CreatePairs(Course,Coursefield));
		Mainpanel.setBorder(BorderFactory.createEmptyBorder(0,100, 0, 0));
		
		
		
		
		JPanel Buttons=new JPanel();
		Buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		Save.setSize(50,50);
		Viewall.setMaximumSize(new Dimension(400,400));
		Viewall.setPreferredSize(new Dimension(200,29));
		
		
		
		Save.addActionListener(e->SaveAction());
		Viewall.addActionListener(e->viewallAction());
		
		
		Buttons.add(Save);
		Buttons.add(Box.createRigidArea(new Dimension(20,0)));
		Buttons.add(Viewall);
		
		
		
		add(Buttons,BorderLayout.CENTER);
		
		add(Mainpanel,BorderLayout.NORTH);
		add(ViewAreaPanel,BorderLayout.SOUTH);
		
		
//		add(Box.createRigidArea(new Dimension(0,80)),BorderLayout.SOUTH);
		
	}
	
	
	
	public  JPanel CreatePairs(JLabel label,JTextField field) {
		JPanel fieldCombo=new JPanel();
		fieldCombo.setLayout(new BoxLayout(fieldCombo,BoxLayout.Y_AXIS));
		fieldCombo.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		
		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setAlignmentX(LEFT_ALIGNMENT);
		field.setMaximumSize(new Dimension(400,40));
		
		fieldCombo.add(label);
		fieldCombo.add(field);
		
		return fieldCombo;
	}
	
	public void SaveAction() {
		Student s=new Student();
		String fetchname=Namefield.getText();	
		String fetchemail=Emailfield.getText();
		String fetchcourse=Course.getText();
		
		s.save(fetchname, fetchemail, fetchcourse);
	}
	public void viewallAction() {
		Student s=new Student();
		String response=s.Fetchall();
		ViewArea.setText(response);
	}
	
}

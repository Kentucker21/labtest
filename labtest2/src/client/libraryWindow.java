package client;





import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class libraryWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    JLabel lblID, lblName, lblauthor, lblyear;
    JTextField txtID, txtName, txtauthor, txtyear;
    JTextArea viewArea;
    JButton addBtn, updateBtn, viewAllBtn, Deletebtn;

    public libraryWindow() {
        super("library window");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initializeComponents();
        setVisible(true);
    }

    public void initializeComponents() {

        lblID=new JLabel("Id:for update");
        lblName = new JLabel("title:");
        lblauthor = new JLabel("author");
        lblyear = new JLabel("year");

        txtID = new JTextField();
        txtName = new JTextField();
        txtauthor = new JTextField();
        txtyear= new JTextField();

        addBtn = new JButton("Add Book");
        updateBtn = new JButton("Update book");
        viewAllBtn = new JButton("view all book");
        Deletebtn=new JButton("delete");

        viewArea = new JTextArea();

        JScrollPane scrollPane = new JScrollPane(viewArea);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

       mainPanel.add(createPairs(lblID, txtID));
        mainPanel.add(createPairs(lblName, txtName));
        mainPanel.add(createPairs(lblauthor, txtauthor));
        mainPanel.add(createPairs(lblyear, txtyear));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonsPanel.add(updateBtn);
        buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonsPanel.add(viewAllBtn);
        buttonsPanel.add(Deletebtn);

        addBtn.addActionListener(e -> addbookAction());
        updateBtn.addActionListener(e -> updatebookAction());
        viewAllBtn.addActionListener(e -> viewAllbooksAction());
        Deletebtn.addActionListener(e->deletebookAction());

        add(mainPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
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

    

    public void addbookAction() {
     String ftitle=txtName.getText();
     String fauthor=txtName.getText();
     int fyear=Integer.parseInt(txtyear.getText());
     
     
     Book b1=new Book(ftitle,fauthor,fyear);
     String Response=ClientConnection.addBook(b1);
     
     
     viewArea.setText(Response);
       
    }
    public void updatebookAction() {
   int fId=Integer.parseInt(txtID.getText());
   String ftitle=txtName.getText();
   String Response=ClientConnection.updateBook(fId, ftitle);
       viewArea.setText(Response);
    }


    public void viewAllbooksAction() {
       Book b1=new Book();
       
       List <Book> fbooks=ClientConnection.getAllBooks();
       
       
       for (Book b: fbooks) {
    	   String bookstring =" ";
    	   
    	   bookstring+="title"+b.getTitle()+"\n"+"author:"+b.getAuthor()+"\n"+"year"+b.getYear()+"\n";
    	   
    	   viewArea.setText(bookstring);
       }
    }
    
    
    public void deletebookAction() {
    	int fId=Integer.parseInt(txtID.getText());
    	String Response=ClientConnection.deleteBook(fId);
    	 viewArea.setText(Response);
    	
    }
}

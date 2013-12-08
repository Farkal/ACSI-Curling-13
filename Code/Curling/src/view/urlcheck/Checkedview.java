package view.urlcheck;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.utils.MyModel;

public class Checkedview extends JFrame implements ActionListener{

	private JTable checkedURLs;
	private JScrollPane tabPanel;
	private JPanel bPanel;
	private JButton bOk;
	
	public Checkedview (String pTitle){
		
		super(pTitle);
		
		String[] columns = {"Absolute path","Type","Last checked"};
		Object[][] data = {
				{"C:\\Program Files\\etc...",".txt","12/11/2013"},
				{"insert path here",".exe","Hier"},
				{"Jambon","beurre","fromage"}
		};
		MyModel model;
		
		this.setBounds(450,200,600,200);
		
		model = new MyModel(data,columns);
		checkedURLs = new JTable(model);

		tabPanel = new JScrollPane(checkedURLs);
		checkedURLs.setFillsViewportHeight(true);
		
		bOk = new JButton("OK");
		bPanel = new JPanel();
		
		bPanel.setLayout(new GridLayout(1,3));
		bPanel.add(new JLabel(""));
		bPanel.add(bOk);
		bPanel.add(new JLabel(""));

		
		this.add(tabPanel,BorderLayout.CENTER);
		this.add(bPanel,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bOk.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		this.setVisible(false);
		new Mainview("Curling").setVisible(true);
		
	}

}

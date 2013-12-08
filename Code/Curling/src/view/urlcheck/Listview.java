package view.urlcheck;
import javax.swing.*;

import view.utils.MyModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Listview extends JFrame implements ActionListener {

	private JButton b;
	private JScrollPane jsp;
	private JPanel panel;
	private JList<String> listNOK; 
	
	public Listview (String pTitle, Vector<String> vList){
		super (pTitle);
		this.setBounds(450,200, 600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new JPanel();
		this.b = new JButton ("OK");

		
		listNOK = new JList<String>(vList);
		
		this.jsp = new JScrollPane();
		
		jsp.setViewportView(listNOK);
		
		this.add(jsp);
		
		
		
		panel.setLayout(new GridLayout(1,3));
		panel.add(new JLabel(""));
		panel.add(b);
		panel.add(new JLabel(""));
		
		this.add(panel, BorderLayout.SOUTH);
		
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		this.setVisible(false);
		new Mainview("Curling").setVisible(true);
		
		
	}
	

}

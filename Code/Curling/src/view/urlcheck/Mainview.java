package view.urlcheck;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;

import controller.urlcheck.CheckUrls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;



public class Mainview extends JFrame implements ActionListener{
	
	private JButton browse, checkUrl, listChecked;
	private JTextField url; 
	private Vector<String> v;
	private String filePath;
	
	
	public Mainview(String pTitle){
		
		super(pTitle);
		this.setLayout(null);
		this.setBounds(450,200,600,200);
		
		browse = new JButton("Browse");
		checkUrl = new JButton("Check URLs");
		listChecked = new JButton("List of checked files");
		url = new JTextField("URL path");
		
		url.setBounds(25,30,325,25);
		browse.setBounds(355, 30, 80, 25);
		checkUrl.setBounds(440, 30, 120, 25);
		listChecked.setBounds(25,100,150,30);

		this.add(browse);
		this.add(checkUrl);
		this.add(listChecked);
		this.add(url);
		
		url.setEditable(false);
		
		browse.addActionListener(this);
		
		v = new Vector<String>();
		checkUrl.addActionListener(this);
		
		listChecked.addActionListener(this);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.browse){
			Chooser frame = new Chooser();
			url.setText(frame.fileName);
			filePath = frame.fileName;
		}
		else if (e.getSource() == this.checkUrl){
			new Listview("List of bad URLs", new CheckUrls(filePath).get_list()).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource() == this.listChecked){
			new Checkedview("List of checked files").setVisible(true);
			this.setVisible(false);
		}
		
	}
	
	class Chooser extends JFrame {

		JFileChooser chooser;
		String fileName;

		public Chooser() {
			chooser = new JFileChooser();
			int r = chooser.showOpenDialog(new JFrame());
			if (r == JFileChooser.APPROVE_OPTION) {
			fileName = chooser.getSelectedFile().getPath();
			}
		}
	}
}

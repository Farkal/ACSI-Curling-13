package view.urlcheck;
import javax.swing.*;

import controller.files.AccessTextFiles;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Listview extends JFrame implements ActionListener {

	private Mainview mv;
	private JButton b;
	private JScrollPane jsp;
	private JPanel panel;
	private JList<String> listNOK; 
	private AccessTextFiles atf;
	
	/**
	 * Class constructor. Gets the name, type and date of check of the file that is checked. Creates a window showing the
	 * bad Urls of the file that is checked.
	 * When a file is checked, it is added to the AccessTextFile vector (replaced if this file is already in it) and its informations.
	 * Then those informations are written in the text file containing the files checked.
	 * 
	 * @param pTitle the title of the frame
	 * @param vList the vector that will get the bad Urls
	 * @param filePath the path of the file that is checked
	 * @param pAtf the AccessTextFile used to access the files initialized in the main view.
	 * @param pmv the main frame.
	 *
	 * @see AccessTextFiles
	 * @see Mainview
	 */
	public Listview (String pTitle, Vector<String> vList, String filePath, AccessTextFiles pAtf, Mainview pmv){
		super (pTitle);
		
		mv = pmv;
		
		this.setBounds(450,200, 600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new JPanel();
		this.b = new JButton ("OK");

		atf = pAtf;
		
		atf.setFileName(filePath);
		int i = filePath.indexOf('.');
		atf.setTypeFile(filePath.substring(i));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		atf.setDate(dateFormat.format(date).toString());
		atf.addVector();
		atf.writeChecked();
				
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
	
	/**
	 * Determine the events done in case of a ActionEvent. This very frame will be hidden then the main frame will be shown.
	 *
	 * @param  e The action event
	 *
	 */
	public void actionPerformed(ActionEvent e){
		this.setVisible(false);
		mv.setVisible(true);
		
		
	}
	

}

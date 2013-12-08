package view.urlcheck;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.files.AccessTextFiles;
import controller.urlcheck.CheckUrls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;



public class Mainview extends JFrame implements ActionListener, DocumentListener{
	
	private JButton browse, checkUrl, listChecked;
	private JTextField url; 
	private Vector<String> v;
	private String filePath;
	private AccessTextFiles atf;
	
	/**
	 * Class Constructor
	 * 
	 * Creates the main window of the application. Also initializes the vector containing the files already checked
	 * and the AccessTextFiles object used to access files in the application.
	 *
	 * @param  pTitle the title of the window
	 *
	 * @see AccessTextFiles
	 */
	public Mainview(String pTitle){
		
		super(pTitle);
		setLayout(null);
		setBounds(450,200,600,200);
		
		browse = new JButton("Browse");
		checkUrl = new JButton("Check URLs");
		listChecked = new JButton("List of checked files");
		url = new JTextField(null);
		
		url.setBounds(25,30,325,25);
		browse.setBounds(355, 30, 80, 25);
		checkUrl.setBounds(440, 30, 120, 25);
		listChecked.setBounds(25,100,150,30);

		add(browse);
		add(checkUrl);
		add(listChecked);
		add(url);
		
		url.setEditable(false);
		checkUrl.setEnabled(false);
		url.getDocument().addDocumentListener(this);
		browse.addActionListener(this);
		
		v = new Vector<String>();
		
		checkUrl.setEnabled(false);
		checkUrl.addActionListener(this);
		
		listChecked.addActionListener(this);
		
		atf = new AccessTextFiles();
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Determine the events done in case of a ActionEvent. If the browse button is clicked, will show a file Chooser Frame.
	 * If the check Urls button is checked, will check the urls of the file chosen and show the buggy ones.
	 * Finally, if list of checked files is clicked, will show the list of checked files.
	 *
	 * @param  e The action event
	 * 
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.browse){
			Chooser frame = new Chooser();
			url.setText(frame.fileName);
			filePath = frame.fileName;
		}
		else if (e.getSource() == this.checkUrl){
			new Listview("List of bad URLs", new CheckUrls(filePath).get_list(), filePath, atf,this).setVisible(true);
			this.setVisible(false);
		}
		else if (e.getSource() == this.listChecked){
			new Checkedview("List of checked files", atf,this).setVisible(true);
			this.setVisible(false);
		}
		
	}
	
	/**
	 * Class that generates a file chooser frame.
	 */
	class Chooser extends JFrame {

		JFileChooser chooser;
		String fileName;
		
		/**
		 * Class Constructor.
		 *
		 */
		public Chooser() {
			chooser = new JFileChooser();
			int r = chooser.showOpenDialog(new JFrame());
			if (r == JFileChooser.APPROVE_OPTION) {
			fileName = chooser.getSelectedFile().getPath();
			}
		}
	}
	
	/**
	 * Determine the events done in case of an change of a Document.
	 *
	 * @param  arg0 the DocumentEvent
	 *
	 */
	public void changedUpdate(DocumentEvent arg0) {
	}
	/**
	 * Determine the events done in case of an insert of a Document.
	 * Enables the button "Check Urls"
	 *
	 * @param  arg0 the DocumentEvent
	 *
	 */
	public void insertUpdate(DocumentEvent arg0) {
		checkUrl.setEnabled(true);
		
	}
	/**
	 * Determine the events done in case of an remove of a Document.
	 *
	 * @param  arg0 the DocumentEvent
	 *
	 */
	public void removeUpdate(DocumentEvent arg0) {
		
	}
}

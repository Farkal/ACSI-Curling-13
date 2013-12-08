package view.urlcheck;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.files.AccessTextFiles;
import controller.files.ReadToRow;
import view.utils.MyModel;

public class Checkedview extends JFrame implements ActionListener{

	private JTable checkedURLs;
	private JScrollPane tabPanel;
	private JPanel bPanel;
	private JButton bOk;
	private Vector<String> v;
	private AccessTextFiles atf;
	private ReadToRow rtr;
	private Mainview mv;
	
	/**
	 * Class constructor. Creates a JTable where is shown the informations about the files that were checked.
	 * To do that, it reads the text file containing the files checked.
	 * 
	 * @param pTitle the title of the frame
	 * @param pAtf the AccessTextFile used to access the files initialized in the main view.
	 * @param pmv the main frame.
	 *
	 * @see AccessTextFiles
	 * @see Mainview
	 */
	public Checkedview (String pTitle, AccessTextFiles pAtf,Mainview pmv){
		
		super(pTitle);
		
		mv = pmv;
		
		atf = pAtf;
				
		String[] columns = {"Absolute path","Type","Last checked"};
		Object[][] data = null;
		MyModel model;
		
		this.setBounds(450,200,600,200);
		
		model = new MyModel(data,columns);
		
		v = atf.readFileUrls();
		if(v == null){
			Object[][] err = {{"Aucun fichier n'a été vérifié"}};
			model.addRow(err[0]);
		}
		else{
			for (int i = 0;i<v.size();i++){
				rtr = new ReadToRow();
				rtr.toRow(v.elementAt(i));
				model.addRow(rtr.getRow()[0]);
			}
		}
		
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

package controller.files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

public class AccessTextFiles {
	
	private String line;
	private Vector<String> v;
	private String fileType;
	private String fileName;
	private String date;
	private String workingDir = System.getProperty("user.dir");
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	/**
	 * Read lines from a file and stores each one in a vector.
	 *
	 * @param  pFileName the name of the file we whant to read.
	 *
	 */
	public void read(String pFileName) throws IOException {
		v = new Vector<String>();
	    Path path = Paths.get(pFileName);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	    	line = scanner.nextLine();
	    	v.add(line);

	      }  
	    }
	 }
	
	/**
	 * Writes into a file from a vector. Each line of the file will be an element of the vector.
	 *
	 * @param aFileName the name of the file we whant to read.
	 * @param aLines the vector we whant to write from.
	 *
	 */
	 public void write(String aFileName, Vector<String> aLines) throws IOException {
		    Path path = Paths.get(aFileName);
		    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
		      for(String line : aLines){
		        writer.write(line);
		        writer.newLine();
		      }
		    }
	 }
	 
	 /**
	 * Reads the specific file that we use to store the files checked using the method read.
	 * Returns a vector containing each line of this file.
	 *
	 * @see Vector
	 * @return v the vector containing each line of the file read.
	 */
	 public Vector<String> readFileUrls(){
		 	if (workingDir == System.getProperty("user.dir"))
		 			workingDir += "\\checked.txt";

			
			try {
				read(workingDir);
			} catch (IOException e) {
				return null;
			}
			return (v);
		 
	 }
	 
	 /**
	 * Sets the fileName attribute using the parameter.
	 *
	 * @param pfileName the name of the file.
	 */
	 public void setFileName(String pfileName){
		 fileName = pfileName;
	 }
	 
	 /**
	 * Sets the type attribute using the parameter.
	 *
	 * @param pfileType the name of the file.
	 */
	 public void setTypeFile(String pfileType){
		 
		 fileType = pfileType;
		
	 }
	 
	 /**
	 * Sets the date attribute using the parameter.
	 *
	 * @param pdate the name of the file.
	 */
	 public void setDate(String pdate){
		 
		 date = pdate;
		 
	 }
	 
	 /**
	 * Adds a line to the vector containing the files checked. If this file was already in it, replaces the line in question
	 * with the new one.
	 *
	 */
	 public void addVector(){
		int i =0;
		int j;
		boolean found = false;
		
		while (i !=  v.size() && found == false){
			
			j = v.elementAt(i).indexOf(' ');
			if (v.elementAt(i).substring(0,j).equals(fileName)){
				found = true;
			}
		}
		if (found){
			v.removeElementAt(i);
		}
		v.add(fileName + ' ' + fileType + ' ' + date);
		
	 }
	 /**
	 * Writes the new vector containing the new set of checked files into the data file using the method write.
	 *
	 */
	 public void writeChecked(){
		 try {
			write("checked.txt", v);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
	 }
	 /**
	 * Returns the vector containing the checked files.
	 *
	 * @return v the containing the checked files
	 * 
	 * @see Vector
	 * 
	 */
	 public Vector<String> getVector(){
		 return v;
	 }
	

}

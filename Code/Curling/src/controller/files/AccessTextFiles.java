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
	
	 public void write(String aFileName, Vector<String> aLines) throws IOException {
		    Path path = Paths.get(aFileName);
		    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
		      for(String line : aLines){
		        writer.write(line);
		        writer.newLine();
		      }
		    }
	 }
	 
	 public Vector<String> readFileUrls(){
		 	if (workingDir == System.getProperty("user.dir"))
		 			workingDir += "\\bd.txt";

			
			try {
				read(workingDir);
			} catch (IOException e) {
				return null;
			}
			return (v);
		 
	 }
	 
	 public void setFileName(String pfileName){
		 fileName = pfileName;
	 }
	 
	 public void setTypeFile(String pfileType){
		 
		 fileType = pfileType;
		
	 }
	 
	 public void setDate(String pdate){
		 
		 date = pdate;
		 
	 }
	 
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
	 public void writeUrls(){
		 try {
			write("bd.txt", v);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
	 }
	 
	 public Vector<String> getVector(){
		 return v;
	 }
	

}

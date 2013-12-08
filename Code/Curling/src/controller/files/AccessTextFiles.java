package controller.files;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JList;

public class AccessTextFiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	void read(String pFileName) throws IOException {
	    Path path = Paths.get(pFileName);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        //process each line in some way
	        log(scanner.nextLine());
	      }      
	    }
	 }
	
	 void write(String aFileName, JList<String> aLines) throws IOException {
		    Path path = Paths.get(aFileName);
		    try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
		      for(String line : aLines){
		        writer.write(line);
		        writer.newLine();
		      }
		    }
	 }
	
	private static void log(Object aMsg){
	    System.out.println(String.valueOf(aMsg));
	 }

}

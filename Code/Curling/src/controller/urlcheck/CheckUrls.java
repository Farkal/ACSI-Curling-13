package controller.urlcheck;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUrls {
		
	private  String contentOfFile;
	private  Vector<String> buggyurls;
	
	public CheckUrls(String pPath) {
		 
		try {
	       BufferedInputStream in = new BufferedInputStream(new FileInputStream(pPath));
	       StringWriter out = new StringWriter();
	       int b;
	       while ((b=in.read()) != -1)
	           out.write(b);
	       out.flush();
	       out.close();
	       in.close();
	       contentOfFile = out.toString();
	    }
	    catch (IOException ie)
	    {
	         ie.printStackTrace(); 
	    }
		
		Vector<String> links = new Vector<String>();
		 
		String regex = "\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(contentOfFile);
		while(m.find()) {
		String urlStr = m.group();
		if (urlStr.startsWith("(") && urlStr.endsWith(")"))
		{
			urlStr = urlStr.substring(1, urlStr.length() - 1);
		}
		links.add(urlStr);
		}
		
		buggyurls = new Vector<String>();
		for (int i= 0; i < links.size(); i++)
		{
		
			try {
			    URL url = new URL(links.get(i));
			    HttpURLConnection huc =  (HttpURLConnection)  url.openConnection(); 
			    huc.setRequestMethod("GET"); 
			    huc.connect();
			} catch (MalformedURLException e) {
			    // the URL is not in a valid form
				buggyurls.add(links.get(i));
			} catch (IOException e) {
			    // the connection couldn't be established
				buggyurls.add(links.get(i));
			}
		}
		for (int i = 0; i < buggyurls.size(); i ++)
		{
			System.out.println(buggyurls.get(i));
		}
		
	}
	
	public Vector<String> get_list(){
		return this.buggyurls;
	}
}
	



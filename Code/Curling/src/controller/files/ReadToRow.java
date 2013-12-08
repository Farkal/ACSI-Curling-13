package controller.files;



public class ReadToRow {
	
	private String path;
	private String type;
	private String date;

	
	public void toRow(String line){
		
		int i = line.indexOf(' ');
		path = line.substring(0, i);
		type = line.substring(i+1);
		i = type.indexOf(' ');
		date = type.substring(i+1);
		type = type.substring(0,i);
	}
	
	public Object[][] getRow(){
		
		Object[][] data = {{path,type,date}}; 
		return data;
	}

}

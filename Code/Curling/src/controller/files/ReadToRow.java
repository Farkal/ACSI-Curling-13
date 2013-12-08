package controller.files;



public class ReadToRow {
	
	private String path;
	private String type;
	private String date;

	/**
	 * From a line containing three strings separated by a space, store the three strings in different variables. 
	 *
	 * @param  line the line composed of three words.
	 *
	 */
	public void toRow(String line){
		
		int i = line.indexOf(' ');
		path = line.substring(0, i);
		type = line.substring(i+1);
		i = type.indexOf(' ');
		date = type.substring(i+1);
		type = type.substring(0,i);
	}
	
	/**
	 * returns a two entry array with a single row containing the three words extracted from the line.
	 * 
	 * @return data the two entry array with a single row containing the three words extracted from the line.
	 *
	 * @see Object
	 */
	public Object[][] getRow(){
		
		Object[][] data = {{path,type,date}}; 
		return data;
	}

}

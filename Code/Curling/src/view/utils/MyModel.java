package view.utils;
import javax.swing.table.DefaultTableModel;

public class MyModel extends DefaultTableModel{
	/**
	 * 
	 * Class Constructor
	 * 
	 * Sets the model of a JTable according to parameters tableData and colNames 
	 *
	 * @param  tableData a two entries table containing the data
	 * @param  colNames the names of the columns of the JTable
	 *
	 */
	public MyModel(Object[][] tableData, Object[] colNames) {
	      super(tableData, colNames);
	}
	
	/**
	 * Sets the cells non-editable
	 *
	 * @param  row the row of the cell
	 * @param  column the column of the cell
	 *
	 *
	 */
	public boolean isCellEditable(int row,int column){
		return false;
	}
} 



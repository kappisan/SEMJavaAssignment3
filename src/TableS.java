import javax.swing.table.AbstractTableModel;


public class TableS extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

    String[] columnNames = {"SNO", "SNAME", "STATUS", "CITY"};
    Object[][] data = {{"-","-","-","-"}};

	SQLCLass sql = new SQLCLass();
    
    public TableS() {
    	populateTableFromDB();
    }
    
    public void importFromTxtToDB() {
    	sql.deleteAllFromTable("STable");
    	sql.importSTableFromTxtToDB();
    	data = sql.importTableFromDB("STable");
    	fireTableDataChanged();
    	System.out.println("IMPORT FROM DB");
    }
    
    public void populateTableFromDB() {
    	data = sql.importTableFromDB("STable");
    }
    
    public void deleteFromDB(String sno) {
    	System.out.println("DELETE SNO: " + sno);
    	sql.deleteFromTableBySNO(sno);
    	data = sql.importTableFromDB("STable");
    	fireTableDataChanged();
    }
    
    public void deleteAllFromTable() {
    	sql.deleteAllFromTable("STable");
    	data = new Object[][] {{"-","-","-","-"}};
    	fireTableDataChanged();
    }
    
    public void insertRowIntoDB(String row1, String row2, String row3, String row4) {
    	sql.addToDatabase("STable", row1, row2, row3, row4);
    	data = sql.importTableFromDB("STable");
    	fireTableDataChanged();
    }
    
    @Override
    public int getRowCount()
    {
        return data.length;
    }
    
    @Override
    public int getColumnCount()            
    {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int row, int column)
    {        
        return data[row][column];
    }
    
    //Used by the JTable object to set the column names
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    //Used by the JTable object to render different
    @SuppressWarnings({ "unchecked", "rawtypes" })
	//functionality based on the data type
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int column)
    {
       if (column == 0 || column == 1) {
        return false;
	   }
	   else {
		return true;
	   }
    }
}

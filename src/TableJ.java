import javax.swing.table.AbstractTableModel;


public class TableJ extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

    String[] columnNames = {"JNO", "JNAME", "CITY"};
    Object[][] data = {{"-","-","-"}};

	SQLCLass sql = new SQLCLass();
    
    public TableJ() {
    	populateTableFromDB();
    }
    
    public void importFromTxtToDB() {
    	sql.deleteAllFromTable("JTable");
    	sql.importJTableFromTxtToDB();
    	data = sql.importTableFromDB("JTable");
    	fireTableDataChanged();
    	System.out.println("IMPORT FROM DB");
    }
    
    public void populateTableFromDB() {
    	data = sql.importTableFromDB("JTable");
    }
    
    public void deleteFromDB(String jno) {
    	System.out.println("DELETE JNO: " + jno);
    	sql.deleteFromTableByJNO(jno);
    	data = sql.importTableFromDB("JTable");
    	fireTableDataChanged();
    }
    
    public void deleteAllFromTable() {
    	sql.deleteAllFromTable("JTable");
    	data = new Object[][] {{"-","-","-"}};
    	fireTableDataChanged();
    }
    
    public void insertRowIntoDB(String row1, String row2, String row3) {
    	sql.addToDatabase("JTable", row1, row2, row3, "");
    	System.out.println("ADD TO J TABLE: " + row1 + " " + row2 + " " + row3);
    	data = sql.importTableFromDB("JTable");
    	fireTableDataChanged();
    }
    
    public void showProjectsBySupplierSnoName(String sname) {
    	String sno = sql.getSNameBySno(sname);
    	System.out.println("SNO = " + sno);
    	data = sql.getProjectsBySupplier(sno);
    	fireTableDataChanged();    	
    }
    
    public void showProjectsBySupplierSno(String sno) {
    	data = sql.getProjectsBySupplier(sno);
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

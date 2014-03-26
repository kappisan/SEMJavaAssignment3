import javax.swing.table.AbstractTableModel;


public class TableP extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

    String[] columnNames = {"PNO", "PNAME", "COLOR", "WEIGHT", "CITY"};
    Object[][] data = {{"-","-","-","-","-"}};

	SQLCLass sql = new SQLCLass();
    
    public TableP() {
    	populateTableFromDB();
    }
    
    public void importFromTxtToDB() {
    	sql.deleteAllFromTable("PTable");
    	sql.importPTableFromTxtToDB();
    	data = sql.importTableFromDB("PTable");
    	fireTableDataChanged();
    	System.out.println("IMPORT FROM DB");
    }
    
    public void populateTableFromDB() {
    	data = sql.importTableFromDB("PTable");
    }
    
    public void deleteFromDB(String pno) {
    	System.out.println("DELETE PNO: " + pno);
    	sql.deleteFromTableByPNO(pno);
    	data = sql.importTableFromDB("PTable");
    	fireTableDataChanged();
    }
    
    public void deleteAllFromTable() {
    	sql.deleteAllFromTable("PTable");
    	data = new Object[][] {{"-","-","-","-","-"}};
    	fireTableDataChanged();
    }
    
    public void insertRowIntoDB(String row1, String row2, String row3, String row4, String row5) {
    	sql.addToDatabaseP(row1, row2, row3, row4, row5);
    	data = sql.importTableFromDB("PTable");
    	fireTableDataChanged();
    }
    
    public void showPartsBySupplierSnoName(String sname) {
    	String sno = sql.getSNameBySno(sname);
    	data = sql.getPartsBySupplier(sno);
    	fireTableDataChanged();    	
    }
    
    public void showPartsBySupplierSno(String sno) {
    	data = sql.getPartsBySupplier(sno);
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

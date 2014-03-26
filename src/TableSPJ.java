import javax.swing.table.AbstractTableModel;


public class TableSPJ extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

    String[] columnNames = {"SNO", "PNO", "JNO", "QTY"};
    Object[][] data = {{"-","-","-","-"}};

	SQLCLass sql = new SQLCLass();
    
    public TableSPJ() {
    	populateTableFromDB();
    }
    
    public void importFromTxtToDB() {
    	sql.deleteAllFromTable("SPJTable");
    	sql.importSPJTableFromTxtToDB();
    	data = sql.importTableFromDB("SPJTable");
    	fireTableDataChanged();
    	System.out.println("IMPORT FROM DB");
    }
    
    public void populateTableFromDB() {
    	data = sql.importTableFromDB("SPJTable");
    }
    
    public void deleteFromDB(String sno, String pno, String jno) {
    	System.out.println("DELETE SNO: " + sno);
    	sql.deleteFromTableSPJBySNO(sno, pno, jno);
    	data = sql.importTableFromDB("SPJTable");
    	fireTableDataChanged();
    }
    
    public void deleteAllFromTable() {
    	sql.deleteAllFromTable("SPJTable");
    	data = new Object[][] {{"-","-","-","-"}};
    	fireTableDataChanged();
    }
    
    public void insertRowIntoDB(String row1, String row2, String row3, String row4) {
    	sql.addToDatabase("SPJTable", row1, row2, row3, row4);
    	data = sql.importTableFromDB("SPJTable");
    	fireTableDataChanged();
    }
    
    public String getQuantityOfPNO(String pno) {
    	return sql.getQuantityOfPNO(pno);
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

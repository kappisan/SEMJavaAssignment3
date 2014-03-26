import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SQLCLass {
	
	String databaseLocation = "src/Assignment3DB.db";
	String textFile = "src/tables.txt";
	
	public Statement getS() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException cnfe) {
			System.err.println("Couldn't find driver class: " + cnfe.getMessage());
			return null;
		}
		System.out.println("Sucessfully loaded driver");
		
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+databaseLocation,"","");
		} catch(SQLException se) {
			System.out.println("ERROR GETTING CONNECTION " + se.getMessage());
			return null;
		}
		System.out.println("Sucessfully Connected to DB");
		
		Statement s = null;
		
		try {
			s = c.createStatement();
		} catch(SQLException se) {
			System.out.println("ERROR CREATING STATEMENT " + se.getMessage());
			return null;
		}
		System.out.println("Statement created");		
		
		return s;
	}
	
	@SuppressWarnings("resource")
	public void importSTableFromTxtToDB() {
		System.out.println("IMPORT FROM TXT FILE");
		String tableName = "STable";
		Statement s = getS();
		
		//open reader
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFile));
		} catch (FileNotFoundException e1) {
			System.out.println("FILE NOT FOUND: " + e1);
			e1.printStackTrace();
			return;
		}

		String line = null;
		
		String rex = ".*S[0-9]+\\s[^(P[0-9])].*";
		try {
			while (((line = reader.readLine()) != null)) {
				if(line.matches(rex)) {
					System.out.println("LINE = " + line);
					
					String inString = line.trim();
					String[] splitString = inString.split(" ");
					System.out.println("SPLIT STRING = " + splitString[0] + " " + splitString[1] + " " + splitString[2] + " " + splitString[3]);
					
					try {
						s.executeUpdate("INSERT INTO `"+tableName+"` VALUES (\""+splitString[0]+"\",\""+splitString[1]+"\",\""+splitString[2]+"\",\""+splitString[3]+"\")");
					} catch(SQLException se) {
						System.out.println("ERROR IN SQL QUERY " + se.getMessage());
						return;
					}
					System.out.println("SQL Insert successful");	

				}
			}

		} catch (IOException e) {
			System.out.println("Error getting line by line: " + e);
			e.printStackTrace();
		}
		System.out.println("RegEx sucessful");
	}
	
	@SuppressWarnings("resource")
	public void importPTableFromTxtToDB() {
		System.out.println("IMPORT FROM TXT FILE");
		String tableName = "PTable";
		Statement s = getS();
		
		//open reader
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFile));
		} catch (FileNotFoundException e1) {
			System.out.println("FILE NOT FOUND: " + e1);
			e1.printStackTrace();
			return;
		}

		String line = null;
		
		String rex = ".*P[0-9]+\\s[^(J[0-9])].*";
		try {
			while (((line = reader.readLine()) != null)) {
				if(line.matches(rex)) {
					System.out.println("LINE = " + line);
					
					String inString = line.trim();
					String[] splitString = inString.split(" ");
					System.out.println("SPLIT STRING = " + splitString[0] + " " + splitString[1] + " " + splitString[2] + " " + splitString[3] + " " + splitString[4]);
					
					
					try {
						s.executeUpdate("INSERT INTO `"+tableName+"` VALUES (\""+splitString[0]+"\",\""+splitString[1]+"\",\""+splitString[2]+"\",\""+splitString[3]+"\",\""+splitString[4]+"\")");
					} catch(SQLException se) {
						System.out.println("ERROR IN SQL QUERY " + se.getMessage());
						return;
					}
					System.out.println("SQL Insert successful");	
					
				}
			}

		} catch (IOException e) {
			System.out.println("Error getting line by line: " + e);
			e.printStackTrace();
		}
		System.out.println("RegEx sucessful");
	}
	
	@SuppressWarnings("resource")
	public void importJTableFromTxtToDB() {
		System.out.println("IMPORT FROM TXT FILE");
		String tableName = "JTable";
		Statement s = getS();
		
		//open reader
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFile));
		} catch (FileNotFoundException e1) {
			System.out.println("FILE NOT FOUND: " + e1);
			e1.printStackTrace();
			return;
		}

		String line = null;
		
		String rex = ".*J[0-9]\\s[a-zA-Z].*";
		try {
			while (((line = reader.readLine()) != null)) {
				if(line.matches(rex)) {
					System.out.println("LINE = " + line);
					
					String inString = line.trim();
					String[] splitString = inString.split(" ");
					System.out.println("SPLIT STRING = " + splitString[0] + " " + splitString[1] + " " + splitString[2]);
					
					try {
						s.executeUpdate("INSERT INTO `"+tableName+"` VALUES (\""+splitString[0]+"\",\""+splitString[1]+"\",\""+splitString[2]+"\")");
					} catch(SQLException se) {
						System.out.println("ERROR IN SQL QUERY " + se.getMessage());
						return;
					}
					System.out.println("SQL Insert successful");	
				}
			}

		} catch (IOException e) {
			System.out.println("Error getting line by line: " + e);
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public void importSPJTableFromTxtToDB() {
		System.out.println("IMPORT FROM TXT FILE");
		String tableName = "SPJTable";
		Statement s = getS();
		
		//open reader
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(textFile));
		} catch (FileNotFoundException e1) {
			System.out.println("FILE NOT FOUND: " + e1);
			e1.printStackTrace();
			return;
		}

		String line = null;
		
		String rex = ".*S[0-9]+\\sP[0-9].*";
		try {
			while (((line = reader.readLine()) != null)) {
				if(line.matches(rex)) {
					System.out.println("LINE = " + line);
					
					String inString = line.trim();
					String[] splitString = inString.split(" ");
					System.out.println("SPLIT STRING = " + splitString[0] + " " + splitString[1] + " " + splitString[2] + " " + splitString[3]);
					
					try {
						s.executeUpdate("INSERT INTO `"+tableName+"` VALUES (\""+splitString[0]+"\",\""+splitString[1]+"\",\""+splitString[2]+"\",\""+splitString[3]+"\")");
					} catch(SQLException se) {
						System.out.println("ERROR IN SQL QUERY " + se.getMessage());
						return;
					}
					System.out.println("SQL Insert successful");	

				}
			}

		} catch (IOException e) {
			System.out.println("Error getting line by line: " + e);
			e.printStackTrace();
		}
	}
	
	public void deleteFromTableBySNO(String sno) {
		String tableName = "STable";
		Statement s = getS();

		try {
			s.executeUpdate("DELETE FROM `"+tableName+"` WHERE `SNO`=\""+sno+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
	}
	
	public void deleteFromTableByJNO(String jno) {
		String tableName = "JTable";
		Statement s = getS();

		try {
			s.executeUpdate("DELETE FROM `"+tableName+"` WHERE `JNO`=\""+jno+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
		System.out.println("SQL Delete JNO successful");		
	}
	
	public void deleteFromTableByPNO(String pno) {
		String tableName = "PTable";
		Statement s = getS();

		try {
			s.executeUpdate("DELETE FROM `"+tableName+"` WHERE `PNO`=\""+pno+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
		System.out.println("SQL Delete PNO successful");		
	}
	
	public void deleteFromTableSPJBySNO(String sno, String pno, String jno) {
		String tableName = "SPJTable";
		Statement s = getS();

		try {
			s.executeUpdate("DELETE FROM `"+tableName+"` WHERE `SNO`=\""+sno+"\" AND `PNO`=\""+pno+"\" AND `JNO`=\""+jno+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
		System.out.println("SQL Delete SNO successful");		
	}
	
	public void deleteAllFromTable(String tableName) {
		Statement s = getS();

		try {
			s.executeUpdate("DELETE FROM `"+tableName+"`");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
		System.out.println("SQL Delete all successful");		
	}

	public void addToDatabaseP(String col1, String col2, String col3, String col4, String col5) {
		
		System.out.println("ADD TO DATABASE P");
		
		String tableName = "PTable";
		Statement s = getS();

		try {
			s.executeQuery("INSERT INTO `"+tableName+"` VALUES (\""+col1+"\",\""+col2+"\",\""+col3+"\",\""+col4+"\",\""+col5+"\")");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
		System.out.println("SQL Insert successful");		
	}
	
	public void addToDatabase(String tableName, String col1, String col2, String col3, String col4) {
		Statement s = getS();
		try {
			if(tableName == "JTable") {
				s.executeQuery("INSERT INTO `"+tableName+"` VALUES (\""+col1+"\",\""+col2+"\",\""+col3+"\")");				
			} else {
				s.executeQuery("INSERT INTO `"+tableName+"` VALUES (\""+col1+"\",\""+col2+"\",\""+col3+"\",\""+col4+"\")");				
			}

		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return;
		}
		System.out.println("SQL Insert successful");		
	}
	
	public String getQuantityOfPNO(String pno) {
		
		Statement s = getS();
		
		ResultSet rs = null;
		try {
			rs = s.executeQuery("SELECT * FROM `SPJTable` WHERE PNO=\"" + pno +"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return "0";
		}
		
		int count = 0;
		
		String rex = "[0-9].*"; // regex to prevent errors from counting non numbers
		try {
			while(rs.next()) {
				if(rs.getString(4).trim().matches(rex)) {
					count += Integer.parseInt(rs.getString(4));
				}
				System.out.println("QTY = " + rs.getString(4));
			}
		} catch(SQLException se) {
			System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
			return "0";
		}
		
		return "" + count;
	}
	
	public Object[][] importTableFromDB(String tableName) {

		int numberOfColumns = 4;
		
		Object[][] emptyTable = {{"","","","",""}};
		
		Statement s = getS();
		
		ResultSet rs = null;
		try {
			rs = s.executeQuery("SELECT COUNT(*) FROM "+tableName);
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return emptyTable;
		}
		System.out.println("SQL Query get table size successful");
		
		int resultSetSize = 1;
		try {
			resultSetSize = Integer.parseInt(rs.getString(1));
		} catch (NumberFormatException e) {
			System.out.println("UNABLE TO CONVERT STRING TO INT FOR ARRAY SIZE");
			e.printStackTrace();
			return emptyTable;
		} catch (SQLException e) {
			System.out.println("SQL EXCEPTION");
			e.printStackTrace();
			return emptyTable;
		}

		try {
			rs = s.executeQuery("SELECT * FROM "+tableName);
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return emptyTable;
		}
		System.out.println("SQL Query successful");
			
		Object[][] returnObject = new Object[resultSetSize][numberOfColumns];
		
		int index = 0;
		try {
			while(rs.next()) {
				if(numberOfColumns != 3) {
					System.out.println("ROW = " + index +" " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
				} else {
					System.out.println("ROW = " + index +" " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				}
				returnObject[index][0] = rs.getString(1);
				returnObject[index][1] = rs.getString(2);
				returnObject[index][2] = rs.getString(3);
				if(numberOfColumns != 3) {
					returnObject[index][3] = rs.getString(4);
					if(numberOfColumns == 5) {
						returnObject[index][4] = rs.getString(5);					
					}
				}
				index++;
			}
		} catch(SQLException se) {
			System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
			return emptyTable;
		}
		
		return returnObject;
	}
	
	public String getSNameBySno(String sname) {
		String emptyTable = "";
		Statement s = getS();
		
		ResultSet rs = null;
		try {
			rs = s.executeQuery("SELECT * FROM `STable` WHERE SNAME=\""+sname+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return emptyTable;
		}
		
		try {
			return rs.getString(1);
		} catch(SQLException se) {
			System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
			return emptyTable;
		}
	}
	
	public Object[][] getPartsBySupplier(String sno) {

		System.out.println("SQL GET BY SNO = " + sno);
		Object[][] emptyTable = {{"","","","",""}};
		
		Statement s = getS();
		
		ResultSet rs = null;
		try {
			rs = s.executeQuery("SELECT * FROM `SPJTable` WHERE SNO=\""+sno+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return emptyTable;
		}
		System.out.println("SQL Query get sno matches from SPJ sucessful");
		
		ArrayList<String> pnoList = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				System.out.println("MATCH WHERE PNO = " + rs.getString(2));
				if(!pnoList.contains(rs.getString(2))) {
					pnoList.add(rs.getString(2));
				}
			}
		} catch(SQLException se) {
			System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
			return emptyTable;
		}
		System.out.println("LIST CREATED");
		
		if(pnoList.size() > 0) {
			Object[][] returnObject = new Object[pnoList.size()][5];
			
			String sqlWhereString = "(";
			for (int i = 0; i < pnoList.size(); i++) {
				System.out.println("PNO = " + pnoList.get(i));
				sqlWhereString += "\"" + pnoList.get(i) + "\"";
				if(i < (pnoList.size() - 1)) {
					sqlWhereString += ",";
				}
			}
			sqlWhereString += ")";
			System.out.println("SQL WHERE STRING = " + sqlWhereString);
			
			try {
				rs = s.executeQuery("SELECT * FROM `PTable` WHERE PNO IN "+sqlWhereString);
			} catch(SQLException se) {
				System.out.println("ERROR IN SQL QUERY " + se.getMessage());
				return emptyTable;
			}
			System.out.println("SQL Query get sno matches from SPJ sucessful");
			
			int index = 0;
			try {
				while(rs.next()) {
					returnObject[index][0] = rs.getString(1);
					returnObject[index][1] = rs.getString(2);
					returnObject[index][2] = rs.getString(3);
					returnObject[index][3] = rs.getString(4);
					returnObject[index][4] = rs.getString(5);
					index++;
				}
				
				return returnObject;
			} catch(SQLException se) {
				System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
				return emptyTable;
			}
		} else {
			return emptyTable;
		}
	}
	
	public Object[][] getProjectsBySupplier(String sno) {
		System.out.println("SQL GET BY SNO = " + sno);
		Object[][] emptyTable = {{"","","","",""}};
		
		Statement s = getS();
		
		ResultSet rs = null;
		try {
			rs = s.executeQuery("SELECT * FROM `SPJTable` WHERE SNO=\""+sno+"\"");
		} catch(SQLException se) {
			System.out.println("ERROR IN SQL QUERY " + se.getMessage());
			return emptyTable;
		}
		System.out.println("SQL Query get sno matches from SPJ sucessful");
		
		ArrayList<String> jnoList = new ArrayList<String>();
		
		try {
			while(rs.next()) {
				System.out.println("MATCH WHERE JNO = " + rs.getString(3));
				if(!jnoList.contains(rs.getString(3))) {
					jnoList.add(rs.getString(3));
				}
			}
		} catch(SQLException se) {
			System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
			return emptyTable;
		}
		System.out.println("LIST CREATED");
		
		if(jnoList.size() > 0) {
			Object[][] returnObject = new Object[jnoList.size()][3];
			
			String sqlWhereString = "(";
			for (int i = 0; i < jnoList.size(); i++) {
				System.out.println("PNO = " + jnoList.get(i));
				sqlWhereString += "\"" + jnoList.get(i) + "\"";
				if(i < (jnoList.size() - 1)) {
					sqlWhereString += ",";
				}
			}
			sqlWhereString += ")";
			System.out.println("SQL WHERE STRING = " + sqlWhereString);
			
			try {
				rs = s.executeQuery("SELECT * FROM `JTable` WHERE JNO IN "+sqlWhereString);
			} catch(SQLException se) {
				System.out.println("ERROR IN SQL QUERY " + se.getMessage());
				return emptyTable;
			}
			System.out.println("SQL Query get sno matches from SPJ sucessful");
			
			int index = 0;
			try {
				while(rs.next()) {
					returnObject[index][0] = rs.getString(1);
					returnObject[index][1] = rs.getString(2);
					returnObject[index][2] = rs.getString(3);
					index++;
				}
				
				return returnObject;
			} catch(SQLException se) {
				System.out.println("ERROR RETREIVING VALUES: " + se.getMessage());
				return emptyTable;
			}
		} else {
			return emptyTable;
		}
	}
}

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JPanelWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JPanelWindow frame;
	

	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;
	
	JScrollPane tableScrollPane;
	JLabel tableHeader = new JLabel();

    Color cellColor = new Color(250,250,250); 
	
	// 0 = Table S
	// 1 = Table P
	// 2 = Table J
	// 3 = Table SPJ
    // 4 = Query
	int currentTableId = 0; 

	TableS tbS = new TableS();
	TableP tbP = new TableP();
	TableJ tbJ = new TableJ();
	TableSPJ tbSPJ = new TableSPJ();
	
	public JTable table;
	
	public JPanelWindow() {
		
		frame = this;
		
		makeMenuBar();
		makeBoxes();
		setTitle("ASSIGNMENT 3 WILKOSZ");
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,450);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createTable(JPanel panel) {

		table = new JTable(tbS); //populateTableFromDB()
        table.setAutoCreateRowSorter(true); //Set the column sorting functionality on
        //table.setShowGrid(false);
        table.setGridColor(Color.BLACK);
        table.setBackground(cellColor);
        tableScrollPane = new JScrollPane(table);
        
        Dimension tablePaneSize = new Dimension(550,200);
        tableScrollPane.setPreferredSize(tablePaneSize);
        panel.add(tableScrollPane);
        
	}
	
	private void makeBoxes() {
		
		Color lightGrey = new Color(215, 215, 215);
		Color mediumGrey = new Color(155, 155, 155);	
		Color darkGrey = new Color(110, 110, 110);
		
		topPanel = new JPanel();	
		centerPanel = new JPanel();
		bottomPanel = new JPanel();
		
		topPanel.setPreferredSize(new Dimension(480, 40));
		centerPanel.setPreferredSize(new Dimension(480, 80));
		bottomPanel.setPreferredSize(new Dimension(480, 80));
		
		topPanel.setBackground(mediumGrey);	
		centerPanel.setBackground(lightGrey);
		bottomPanel.setBackground(darkGrey);
		
		tableHeader.setText("S Table");
		tableHeader.setFont(new Font("Impact", Font.PLAIN, 24));
		topPanel.add(tableHeader);

		createTable(centerPanel);
		makeTableSwitchButtons(centerPanel);
		makeControlPanel(bottomPanel);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void makeTableSwitchButtons(JPanel panel) {
		
		JButton sTableButton = new JButton("S Table");
		JButton pTableButton = new JButton("P Table");
		JButton jTableButton = new JButton("J Table");
		JButton spjTableButton = new JButton("SPJ Table");
		
		panel.add(sTableButton);
		panel.add(pTableButton);
		panel.add(jTableButton);
		panel.add(spjTableButton);	
		
		sTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToTable(0);
			}
		});	
		
		pTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToTable(1);
			}
		});	
		
		jTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToTable(2);
			}
		});	
		
		spjTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToTable(3);
			}
		});	
	}
	
	private void switchToTable(int tableCode) {
		currentTableId = tableCode;
		if(tableCode == 0) {
			System.out.println("S TABLE BUTTON PRESSED");
			tableHeader.setText("S Table");
			tbS.populateTableFromDB();
			table = new JTable(tbS);	
		} else if(tableCode == 1) {
			System.out.println("P TABLE BUTTON PRESSED");
			tableHeader.setText("P Table");
			tbP.populateTableFromDB();
			table = new JTable(tbP);
		} else if(tableCode == 2) {
			System.out.println("J TABLE BUTTON PRESSED");
			tableHeader.setText("J Table");
			tbJ.populateTableFromDB();
			table = new JTable(tbJ);
		} else if(tableCode == 3) {
			System.out.println("SPJ TABLE BUTTON PRESSED");
			tableHeader.setText("SPJ Table");
			tbSPJ.populateTableFromDB();
			table = new JTable(tbSPJ);	
		}
        table.setBackground(cellColor);
		tableScrollPane.setViewportView(table);	
	}
	
	private void makeControlPanel(JPanel panel) {
		
		JButton importButton = new JButton("IMPORT");
		JButton deleteButton = new JButton("DELETE");
		JButton addButton = new JButton("ADD");
		JButton purgeButton = new JButton("DELETE ALL");
		JButton projectBySupplierButton = new JButton("QUERY PROJECTS BY SUPLIER");
		JButton partsBySupplierButton = new JButton("QUERY PARTS BY SUPLIER");
		JButton QuantityOfPartButton = new JButton("QUERY QUANTITY OF A GIVEN PART");
		
		panel.add(importButton);
		panel.add(deleteButton);
		panel.add(addButton);
		panel.add(purgeButton);
		panel.add(projectBySupplierButton);
		panel.add(partsBySupplierButton);
		panel.add(QuantityOfPartButton);
		
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importTableFromDatabase();
			}
		});	
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItemFromDatabase();
			}
		});	
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToDatabase();
			}
		});	
		
		purgeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAllFromTable();
			}
		});	
		
		projectBySupplierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showProjectBySupplier();
			}
		});	

		partsBySupplierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPartBySuppliers();
			}
		});	
		
		QuantityOfPartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showQuantityOfPart();
			}
		});	
	}
	
	public void showPartBySuppliers() {
		System.out.println("PARTS BY SUPPLIER");
		
		JTextField sno = new JTextField();
		JTextField sname = new JTextField();
		Object[] message = {
			"FIND PARTS FROM A GIVEN SUPPLIER BY SNO OR SNAME\n\n",
		    "SNO:", sno,
		    "SNAME:", sname,
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "SEARCH FOR PARTS", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(sno.getText().equals ("") && sname.getText().equals ("")) {
				JOptionPane.showMessageDialog(frame,
					    "Inputs cannot be empty",
					    "Empty Query",
					    JOptionPane.WARNING_MESSAGE);	
			} else if(sname.getText().equals ("")) {
				System.out.println("SEARCH BY SNO");
				switchToTable(1);
				tableHeader.setText("PARTS FROM SUPPLIER " + sno.getText().toUpperCase());
				currentTableId = 4;
				tbP.showPartsBySupplierSno(sno.getText());
			} else {
				System.out.println("SEARCH BY SNAME");
				switchToTable(1);
				tableHeader.setText("PARTS FROM SUPPLIER " + sname.getText().toUpperCase());
				currentTableId = 4;
				tbP.showPartsBySupplierSnoName(sname.getText());
			} 
		}
		
	}
	
	public void showQuantityOfPart() {
		JTextField pno = new JTextField();
		Object[] message = {
			"FIND QUANTITY OF A GIVEN PART BY PNO\n\n",
		    "PNO:", pno,
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "CHECK QUANTITY OF PART", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(pno.getText().equals ("")) {
				JOptionPane.showMessageDialog(frame,
					    "Inputs cannot be empty",
					    "Empty Query",
					    JOptionPane.WARNING_MESSAGE);	
			} else {
				System.out.println("SEARCH PNO QUANTITY");
				String pnoQuantity = tbSPJ.getQuantityOfPNO(pno.getText());
				JOptionPane.showMessageDialog(frame,
					    pnoQuantity + " of \"" + pno.getText() + "\" in stock",
					    "QUANTITY QUERY",
					    JOptionPane.PLAIN_MESSAGE);	
			}
		}		
	}
	
	public void showProjectBySupplier() {
		JTextField sno = new JTextField();
		JTextField sname = new JTextField();
		Object[] message = {
			"FIND PROJECTS EMPLOYING A GIVEN SUPPLIER BY SNO OR SNAME\n\n",
		    "SNO:", sno,
		    "SNAME:", sname,
		};
		
		int option = JOptionPane.showConfirmDialog(null, message, "SEARCH FOR PROJECTS", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			if(sno.getText().equals ("") && sname.getText().equals ("")) {
				JOptionPane.showMessageDialog(frame,
					    "Inputs cannot be empty",
					    "Empty Query",
					    JOptionPane.WARNING_MESSAGE);	
			} else if(sname.getText().equals ("")) {
				System.out.println("SEARCH BY SNO");
				switchToTable(2);
				tableHeader.setText("PROJECTS FROM SUPPLIER " + sno.getText().toUpperCase());
				currentTableId = 4;
				tbJ.showProjectsBySupplierSno(sno.getText());
			} else {
				System.out.println("SEARCH BY SNAME");
				switchToTable(2);
				tableHeader.setText("PROJECTS FROM SUPPLIER " + sname.getText().toUpperCase());
				currentTableId = 4;
				tbJ.showProjectsBySupplierSnoName(sname.getText());
			} 
		}
	}
	
	public void importTableFromDatabase() {
		System.out.println("IMPORT");
		if(currentTableId == 0) { tbS.importFromTxtToDB(); }
		else if(currentTableId == 1) { tbP.importFromTxtToDB(); }
		else if(currentTableId == 2) { tbJ.importFromTxtToDB(); }
		else if(currentTableId == 3) { tbSPJ.importFromTxtToDB(); }
	}
	
	public void deleteItemFromDatabase() {
		System.out.println("DELETE");
		JTextField deleteBy = new JTextField();
		if(currentTableId == 0) {
			Object[] message = { "SNO To Delete:", deleteBy };
			int option = JOptionPane.showConfirmDialog(null, message, "Delete from Table S", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) { tbS.deleteFromDB(deleteBy.getText()); }
		} else if(currentTableId == 1) {
			Object[] message = { "PNO To Delete:", deleteBy };
			int option = JOptionPane.showConfirmDialog(null, message, "Delete from Table P", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) { tbP.deleteFromDB(deleteBy.getText()); }
		} else if(currentTableId == 2) {
			Object[] message = { "JNO To Delete:", deleteBy };
			int option = JOptionPane.showConfirmDialog(null, message, "Delete from Table J", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) { tbJ.deleteFromDB(deleteBy.getText()); }
		} else if(currentTableId == 3) {
			JTextField sno = new JTextField();
			JTextField pno = new JTextField();
			JTextField jno = new JTextField();
			Object[] message = { "SNO To Delete:", sno, "PNO To Delete:", pno, "JNO To Delete:", jno };
			int option = JOptionPane.showConfirmDialog(null, message, "Delete from Table SPJ", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) { tbSPJ.deleteFromDB(sno.getText(), pno.getText(), jno.getText()); }
		}
	}
	
	public void addToDatabase() {
		System.out.println("ADD");

		if(currentTableId == 0) {
			JTextField sno = new JTextField();
			JTextField sname = new JTextField();
			JTextField status = new JTextField();
			JTextField city = new JTextField();
			Object[] message = {
			    "SNO:", sno,
			    "SNAME:", sname,
			    "STATUS:", status,
			    "CITY:", city
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Add To S Table Database", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if(sno.getText().equals ("") || sname.getText().equals ("") || status.getText().equals ("") || city.getText().equals ("")) {
					JOptionPane.showMessageDialog(frame,
						    "Inputs cannot be empty",
						    "Empty Query",
						    JOptionPane.WARNING_MESSAGE);						
				} else {
					tbS.insertRowIntoDB(sno.getText(),sname.getText(),status.getText(),city.getText());
				}
			}
		} else if(currentTableId == 1) {
			JTextField pno = new JTextField();
			JTextField pname = new JTextField();
			JTextField color = new JTextField();
			JTextField weight = new JTextField();
			JTextField city = new JTextField();
			Object[] message = {
			    "PNO:", pno,
			    "PNAME:", pname,
			    "COLOR:", color,
			    "WEIGHT:", weight,
			    "CITY:", city
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Add To P Table Database", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if(pno.getText().equals ("") || pname.getText().equals ("") || weight.getText().equals ("") || city.getText().equals ("")) {
					JOptionPane.showMessageDialog(frame,
						    "Inputs cannot be empty",
						    "Empty Query",
						    JOptionPane.WARNING_MESSAGE);						
				} else {
					tbP.insertRowIntoDB(pno.getText(),pname.getText(),color.getText(),weight.getText(),city.getText());
				}
			}				
		} else if(currentTableId == 2) {
			JTextField jno = new JTextField();
			JTextField jname = new JTextField();
			JTextField city = new JTextField();
			Object[] message = {
			    "JNO:", jno,
			    "JNAME:", jname,
			    "CITY:", city
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Add To J Table Database", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if(jno.getText().equals ("") || jname.getText().equals ("") || city.getText().equals ("")) {
					JOptionPane.showMessageDialog(frame,
						    "Inputs cannot be empty",
						    "Empty Query",
						    JOptionPane.WARNING_MESSAGE);						
				} else {
					tbJ.insertRowIntoDB(jno.getText(),jname.getText(),city.getText());
				}
			}
		} else if(currentTableId == 3) {
			JTextField sno = new JTextField();
			JTextField pno = new JTextField();
			JTextField jno = new JTextField();
			JTextField qty = new JTextField();
			Object[] message = {
			    "SNO:", sno,
			    "PNO:", pno,
			    "JNO:", jno,
			    "QTY:", qty
			};
			
			int option = JOptionPane.showConfirmDialog(null, message, "Add To SPJ Table Database", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) {
				if(sno.getText().equals ("") || pno.getText().equals ("") || jno.getText().equals ("") || qty.getText().equals ("")) {
					JOptionPane.showMessageDialog(frame,
						    "Inputs cannot be empty",
						    "Empty Query",
						    JOptionPane.WARNING_MESSAGE);						
				} else {
					tbSPJ.insertRowIntoDB(sno.getText(),pno.getText(),jno.getText(),qty.getText());
				}
			}
		}
	}
	
	public void deleteAllFromTable() {
		System.out.println("DELETE ALL");
		if(currentTableId == 0) { tbS.deleteAllFromTable(); } 
		else if(currentTableId == 1) { tbP.deleteAllFromTable(); }
		else if(currentTableId == 2) { tbJ.deleteAllFromTable(); }
		else if(currentTableId == 3) { tbSPJ.deleteAllFromTable(); }	
	}
	
	private void makeMenuBar() {

		JMenu file = new JMenu("File");

		JMenuItem exitItem = new JMenuItem("Exit");
		file.add(exitItem);
		
		JMenu edit = new JMenu("Edit");
		
		JMenuItem importItem = new JMenuItem("Import");
		edit.add(importItem);
		
		JMenuItem deleteItem = new JMenuItem("Delete Row");
		edit.add(deleteItem);
		
		JMenuItem addItem = new JMenuItem("Add Row");
		edit.add(addItem);
		
		JMenuItem deleteAllItem = new JMenuItem("Delete All");
		edit.add(deleteAllItem);
		
		JMenu table = new JMenu("Table");
		
		JMenuItem sTableItem = new JMenuItem("S Table");
		table.add(sTableItem);
		
		JMenuItem pTableItem = new JMenuItem("P Table");
		table.add(pTableItem);

		JMenuItem jTableItem = new JMenuItem("J Table");
		table.add(jTableItem);
		
		JMenuItem spjTableItem = new JMenuItem("SPJ Table");
		table.add(spjTableItem);
		
		JMenu options = new JMenu("Options");
		
		JMenu colorItem = new JMenu("Color Scheme");
		options.add(colorItem);	
		
		JMenuItem changeToGrey = new JMenuItem("Grey");
		colorItem.add(changeToGrey);

		JMenuItem changeToLemon = new JMenuItem("Lemon");
		colorItem.add(changeToLemon);		
		
		JMenuItem changeToGreen = new JMenuItem("Forest");
		colorItem.add(changeToGreen);
		
		JMenu query = new JMenu("Query");
		
		JMenuItem projectBySupplierItem = new JMenuItem("Show project by supplier");
		query.add(projectBySupplierItem);
		
		JMenuItem partsBySupplierItem = new JMenuItem("Show parts by supplier");
		query.add(partsBySupplierItem);
		
		JMenuItem countQueryItem = new JMenuItem("Quantity of given part");
		query.add(countQueryItem);
		
		JMenu help = new JMenu("Help");
		JMenuItem helpButton = new JMenuItem("Help");
		help.add(helpButton);
		
		JMenuItem aboutButton = new JMenuItem("About");
		help.add(aboutButton);
		
		//adding action listener to menu items
		projectBySupplierItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						showProjectBySupplier();
					}
				}
		);
		partsBySupplierItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						showPartBySuppliers();
					}
				}
		);
		countQueryItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						showQuantityOfPart();
					}
				}
		);
		sTableItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						switchToTable(0);
					}
				}
		);
		pTableItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						switchToTable(1);
					}
				}
		);
		jTableItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						switchToTable(2);
					}
				}
		);
		spjTableItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						switchToTable(3);
					}
				}
		);
		importItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						importTableFromDatabase();
					}
				}
		);
		deleteItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						deleteItemFromDatabase();
					}
				}
		);
		addItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						addToDatabase();
					}
				}
		);
		deleteAllItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						deleteAllFromTable();
					}
				}
		);
		changeToGrey.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					Color lightGrey = new Color(215, 215, 215);
					Color mediumGrey = new Color(155, 155, 155);	
					Color darkGrey = new Color(110, 110, 110);
					
					topPanel.setBackground(mediumGrey);
					centerPanel.setBackground(lightGrey);
					bottomPanel.setBackground(darkGrey);
				}
			}
		);
		changeToLemon.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						Color lightYellow = new Color(255, 255, 171);	
						Color yellow = new Color(255, 255, 18);	
						
						topPanel.setBackground(yellow);
						centerPanel.setBackground(lightYellow);
						bottomPanel.setBackground(yellow);
					}
				}
		);		
		changeToGreen.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						Color lightGreen = new Color(142, 191, 138);	
						Color mediumGreen = new Color(83, 168, 76);	
						Color darkGreen = new Color(21, 115, 13);
						
						topPanel.setBackground(mediumGreen);
						centerPanel.setBackground(lightGreen);
						bottomPanel.setBackground(darkGreen);
					}
				}
		);				
		exitItem.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			}
		);	
		helpButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						JOptionPane.showMessageDialog(null, "If table is empty, click \"IMPORT\" to import from the text file into the database\n"
								+ "Delete a row from the current table by unique table ID by pressing \"DELETE\"\n"
								+ "Add a row to the current table by pressing \"ADD\"\n"
								+ "Delete every row from the table by pressing \"DELETE ALL\"", "Help", JOptionPane.INFORMATION_MESSAGE);				
					}
				}
		);		
		aboutButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null, "Assignment 3\nBy Kasper Wilkosz\nv1.0", "About", JOptionPane.INFORMATION_MESSAGE);				
				}
			}
		);		
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(file);
		bar.add(edit);
		bar.add(table);
		bar.add(query);
		bar.add(options);
		bar.add(help);		
	}
}

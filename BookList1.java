package bookprj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookList1 extends JFrame {

	private JPanel contentPane;
	private JTable table;

	
	public BookList1(String ty) {
		setTitle("Book List");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Vector<Object> columnname=new Vector<Object>();
		Vector<Vector<Object>> data=new Vector<Vector<Object>>();
		
		
		try {
			//Read data from books table
			Connection con=DatabaseConnection.initializeDatabase();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from books where type= '"+ty+"'");
			ResultSetMetaData md=rs.getMetaData();
			int column=md.getColumnCount();
			
			//get column Names from books
			for(int i=2;i<=column;i++) {
			columnname.addElement(md.getColumnName(i));
			}
			
			//Get Row
			while(rs.next()) {
				Vector<Object> row=new Vector<Object>(column);
				for(int i=2;i<=column; i++) {
					if (i==5) {
						int values=rs.getInt(i);
						row.addElement(values==0? Boolean.FALSE:Boolean.TRUE);
					}else {
						row.addElement(rs.getObject(i));
					}
				}
			data.addElement(row);
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		
		//Create Default Table
				DefaultTableModel model=new DefaultTableModel(data,columnname) {
					public Class getColumnClass(int column) {
						for(int row=0;row<getRowCount();row++) {
							Object o=getValueAt(row,column);
							if(o !=null) {
								return o.getClass();
							}
						}
						return Object.class;
					}
					
				};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 27, 382, 155);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Buy");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double sum=0;
			DefaultTableModel newmodel=new DefaultTableModel();
				JTable newtable=new JTable(newmodel);
				newtable.setModel(new DefaultTableModel(
						new Object[][] {
				
			},
			new String[] {
					"BookName","Price"
			}
		));
				for(int i=0;i<table.getModel().getRowCount();i++) {
					if(true==(Boolean)table.getModel().getValueAt(i, 3)) {
						String name=(String)table.getModel().getValueAt(i, 0);
						double price=(double)table.getModel().getValueAt(i, 1);
						sum+=price;
		((DefaultTableModel)newtable.getModel()).addRow(new Object[] {name,price});
		Price pr=new Price(newtable,sum);
		pr.setVisible(true);
					}
				}
			}
		});	
		btnNewButton.setBounds(173, 207, 89, 29);
		contentPane.add(btnNewButton);
	}
}

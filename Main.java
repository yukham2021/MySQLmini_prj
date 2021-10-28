package bookprj;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Book Type");
		lblNewLabel.setBounds(38, 64, 89, 25);
		contentPane.add(lblNewLabel);
		
		 final JComboBox <String>comboBox = new JComboBox<String>();
		comboBox.setBounds(176, 65, 166, 22);
		contentPane.add(comboBox);
		
		
		
		try {
			Connection con=DatabaseConnection.initializeDatabase();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select distinct(type) from books");
			
			while(rs.next()) {
				comboBox.addItem(rs.getString("type"));
			}
			con.close();
			rs.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b_type=String.valueOf(comboBox.getSelectedItem());
				BookList1 bl1=new BookList1(b_type);
				bl1.setVisible(true);
			}
		});
		btnNewButton.setBounds(171, 163, 89, 31);
		contentPane.add(btnNewButton);	
	}
}

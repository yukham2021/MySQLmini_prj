package bookprj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class Price extends JFrame {

	private JPanel contentPane;

	
	public Price(JTable table,double price) {
		setTitle("Book Price");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(20, 28, 385, 144);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Total Price");
		lblNewLabel.setBounds(91, 207, 86, 24);
		contentPane.add(lblNewLabel);
		
		JLabel pri = new JLabel("0");
		pri.setText(""+price);
		pri.setBounds(246, 207, 74, 19);
		contentPane.add(pri);
	}
}

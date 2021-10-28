package bookprj;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	
		protected static Connection initializeDatabase(){
			//connect java with MySQL Database
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
			System.out.println("Connected Successfully");		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	return con;

		}	// TODO Auto-generated method stub

	}



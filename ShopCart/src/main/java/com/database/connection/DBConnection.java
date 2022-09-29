//NEWWWWWW NEWWWWWWWWW

package com.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection con = null;
	
	
	public static Connection openConnection()
	{
		String driverName = "com.mysql.cj.jdbc.Driver";  //$NON-NLS-1$
		String url = "jdbc:mysql://@localhost:3306/mydb"; //$NON-NLS-1$
		String username = "root"; //$NON-NLS-1$
		String password = "C@Cg@7412"; //$NON-NLS-1$
		

		try {
				
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
				
			return con;
				
		} catch (ClassNotFoundException | SQLException e) {
				
			System.err.println("\t\tError: Database connection fail!"); //$NON-NLS-1$
			e.printStackTrace();
				
			return null;
		}
	}
	
	
	public static void closeConnection(Connection dbcon) 
	{
			
			try {
				
				if(!dbcon.isClosed()) {
					
					dbcon.close();				
				}

			} catch (SQLException e) {
				
				System.err.println("Error: Closing the database connection failed!"); //$NON-NLS-1$
				e.printStackTrace();
			}
		}
	

}

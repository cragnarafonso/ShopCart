package com.facade;

import java.sql.Connection;
import java.sql.SQLException;

import com.database.connection.DBConnection;

public class TransactionFacade {


	public TransactionFacade() {
	}

	public static void stateConnection(Connection con) throws SQLException {

		if (!con.isClosed()) {
			System.out.println("\t\t State Connection: \n" //$NON-NLS-1$
					+ "\t\t\t - isClose: " + con.isClosed() + "\n" //$NON-NLS-1$ //$NON-NLS-2$
					+ "\t\t\t - hashCode: " + con.hashCode() + "\n" //$NON-NLS-1$ //$NON-NLS-2$
					+ "\t\t\t - AutoCommit: " + con.getAutoCommit() + "\n" //$NON-NLS-1$ //$NON-NLS-2$
					+ "\t\t\t - TransactionIsolation: " + con.getTransactionIsolation() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {

			System.out.println("\t\t State Connection: \n" //$NON-NLS-1$
					+ "\t\t\t - isClose: " + con.isClosed() + "\n" //$NON-NLS-1$ //$NON-NLS-2$
					+ "\t\t\t - hashCode: " + con.hashCode()); //$NON-NLS-1$
		}
	}

	public static Connection beginTransaction() {
		
		try
		{
			System.out.println("\t->\n\tTransaction Begin!"); //$NON-NLS-1$
			
			Connection con = DBConnection.openConnection();
			
			con.setAutoCommit(false);
			
			stateConnection(con);
			
			return con;
			
		}catch (Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
		
	}

	public static void commitTransaction(Connection con) throws SQLException {
		
		System.out.println("\tTransaction Commit!"); //$NON-NLS-1$
		
		con.commit();
		
		DBConnection.closeConnection(con);
		
		stateConnection(con);
		
		System.out.println("\tTransaction End!\n\t<-\n"); //$NON-NLS-1$
	}

	public static void roolbackTransaction(Connection con) throws SQLException {
		
		System.err.println("\tTransaction Roolback! The transaction did not take place!"); //$NON-NLS-1$
		
		con.rollback();
		
		DBConnection.closeConnection(con);
		
		stateConnection(con);
		
		System.out.println("\tTransaction End!\n\t<-\n"); //$NON-NLS-1$
	}


}

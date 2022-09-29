package com.database.statments;

import java.sql.Connection;


public class Statments {
	
	protected Connection connection;
	
	
	public Statments(Connection con) {
		
		this.setConnection(con);
	}


	public Connection getConnection() {
		return this.connection;
	}



	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}

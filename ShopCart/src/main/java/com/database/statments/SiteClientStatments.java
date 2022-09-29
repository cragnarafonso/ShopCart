package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.models.SiteClientModel;

public class SiteClientStatments extends Statments {

	public SiteClientStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	
	// Retorna um SiteClient pelo client_ds
	public SiteClientModel SelectByDsClient(String dsClient) throws SQLException  {
		
		String query = "SELECT * FROM stm_site_client WHERE client_ds=?"; //$NON-NLS-1$

		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {

			SiteClientModel siteClient = new SiteClientModel();

			pStmt.setString(1, dsClient);

			try (ResultSet rst = pStmt.executeQuery()) {
				
				while (rst.next()) {

					siteClient.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					siteClient.setClient_id(rst.getInt("client_id")); //$NON-NLS-1$
					siteClient.setClient_ds(rst.getString("client_ds")); //$NON-NLS-1$

				}
			}

			return siteClient;

		} 

	}

}

package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.models.SiteSessionModel;

public class SiteSessionStatments extends Statments {

	public SiteSessionStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	// Retorna um SiteSession pelo clientId e pelo siteId
	public SiteSessionModel SelectByIdSiteAndIdClient(int idSite, int idClient) throws SQLException{

		String query = "SELECT * FROM stm_site_session WHERE site_id=? AND client_id=?"; //$NON-NLS-1$

		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {

			SiteSessionModel siteSession = new SiteSessionModel();

			pStmt.setInt(1, idSite);
			pStmt.setInt(2, idClient);

			try (ResultSet rst = pStmt.executeQuery()) {

				while (rst.next()) {

					siteSession.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					siteSession.setSession_tk(rst.getString("session_tk")); //$NON-NLS-1$
					siteSession.setClient_id(rst.getInt("client_id")); //$NON-NLS-1$
					siteSession.setCreated_dt(rst.getDate("created_dt")); //$NON-NLS-1$
					siteSession.setUpdated_dt(rst.getDate("updated_dt")); //$NON-NLS-1$

				}

			}

			return siteSession;

		}  

	}

}

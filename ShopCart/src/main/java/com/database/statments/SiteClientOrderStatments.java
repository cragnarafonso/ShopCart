package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteClientOrderModel;

public class SiteClientOrderStatments extends Statments {

	public SiteClientOrderStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	// Insert a new SiteClientOrder
	public void InsertSiteClientOrder(SiteClientOrderModel siteClientOrder) throws SQLException {

		String query = "INSERT INTO stm_site_client_order(site_id, client_id, order_jd) values (?, ?, ?);"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){

			pStmt.setInt(1, siteClientOrder.getSite_id());
			pStmt.setInt(2, siteClientOrder.getClient_id());
			pStmt.setString(3, siteClientOrder.getOrder_jd());

			pStmt.executeUpdate();

		} 
	}

	// Lista todos os SiteClientOrder por siteId e clientId
	public List<SiteClientOrderModel> SelectAllBySiteIdAndClientId(int idSite, int idClient) throws SQLException{
			
		String query = "SELECT * FROM stm_site_client_order WHERE site_id=? AND client_id=?;"; //$NON-NLS-1$
				
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
				
				List<SiteClientOrderModel> list = new ArrayList<>();
				
				pStmt.setInt(1, idSite);
				pStmt.setInt(2, idClient);
				
				try(ResultSet rst = pStmt.executeQuery())
				{

					while (rst.next()) {

						SiteClientOrderModel siteClientOrder = new SiteClientOrderModel();

						siteClientOrder.setOrder_id(rst.getInt("order_id")); //$NON-NLS-1$
						siteClientOrder.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
						siteClientOrder.setClient_id(rst.getInt("client_id")); //$NON-NLS-1$
						siteClientOrder.setOrder_jd(rst.getString("order_jd")); //$NON-NLS-1$

						list.add(siteClientOrder);
					}
					
					
				}
				
				return list;
			}
			
		}

}

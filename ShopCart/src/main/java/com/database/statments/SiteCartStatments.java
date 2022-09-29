package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteCartModel;
import com.database.models.SiteSessionModel;

public class SiteCartStatments extends Statments {

	public SiteCartStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}
	
	
	//Lista todos os siteCart por SiteId com o SessionToken a null
	public List<SiteCartModel> SelectAllBySiteIdAndSessionisNull(int idSite) throws SQLException{
		
		String query =  "SELECT * FROM stm_site_cart WHERE site_id=? AND session_tk is NULL;"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
			
			List<SiteCartModel> list = new ArrayList<>();
			
			pStmt.setInt(1, idSite);
			
			try(ResultSet rst = pStmt.executeQuery())
			{
				while(rst.next()) {
				
					SiteCartModel sitecCart = new SiteCartModel();
					
					sitecCart.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					sitecCart.setCart_tk(rst.getString("cart_tk")); //$NON-NLS-1$
					sitecCart.setSession_tk(rst.getString("session_tk")); //$NON-NLS-1$
					sitecCart.setClient_id(rst.getInt("client_id")); //$NON-NLS-1$
					
					list.add(sitecCart);
				}
			}
		
			return list;
			
		}
		
	}
	
	//Delete SiteCart pelo SiteId
	public void DeleteSiteCartBySiteId(int idSite) throws SQLException{
		
		String query = "DELETE FROM stm_site_cart WHERE site_id=? AND session_tk IS NULL;"; //$NON-NLS-1$
				
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
				
			pStmt.setInt(1, idSite);
				
			pStmt.executeUpdate();
				
		}
	}
	
	
	//Delete SiteCart pelo SiteId and Session_tk
	public void DeleteSiteCartBySiteIdAndSessionTk(int idSite, String sessionTk) throws SQLException{
		
		String query = "DELETE FROM stm_site_cart WHERE site_id=? AND session_tk =?;"; //$NON-NLS-1$
				
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {

			pStmt.setInt(1, idSite);
			pStmt.setString(2, sessionTk);
				
			pStmt.executeUpdate();
				
		}
	}
	
	
	//Insert a new SiteCart
	public void InsertSiteCart(SiteCartModel siteCart) throws SQLException{
		
		String query = "INSERT INTO stm_site_cart(site_id, cart_tk) values (?, ?);"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
			
			pStmt.setInt(1, siteCart.getSite_id());
			pStmt.setString(2, siteCart.getCart_tk());
				
			pStmt.executeUpdate();
			
		}
	}
	
	//Update a SiteCart by siteId and CartToken
		public void UpdateSiteCart(SiteCartModel siteCart) throws SQLException{
			
			String query = "UPDATE stm_site_cart SET session_tk=?, client_id=? WHERE cart_tk=?;"; //$NON-NLS-1$
			
			try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
				
				pStmt.setString(1, siteCart.getSession_tk());
				pStmt.setInt(2, siteCart.getClient_id());
				pStmt.setString(3, siteCart.getCart_tk());
					
				pStmt.executeUpdate();
				
			}
		}
		
		// Selecionar os siteCart por Session_tk e site_id
		public SiteCartModel SelectBySiteIdAndSessionToken(SiteSessionModel siteSession) throws SQLException {

			String query = "SELECT * FROM stm_site_cart WHERE site_id=? AND session_tk=?;"; //$NON-NLS-1$
					
			try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
					
				SiteCartModel siteCart = new SiteCartModel();

				pStmt.setInt(1, siteSession.getSite_id());
				pStmt.setString(2, siteSession.getSession_tk());
				
				try(ResultSet rst = pStmt.executeQuery();)
				{
					while (rst.next()) {

						siteCart.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
						siteCart.setCart_tk(rst.getString("cart_tk")); //$NON-NLS-1$
						siteCart.setSession_tk(rst.getString("session_tk")); //$NON-NLS-1$
						siteCart.setClient_id(rst.getInt("client_id")); //$NON-NLS-1$

					}
					
				}

				return siteCart;
			} 
		}

}



package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteCartProductModel;


public class SiteCartProductStatments extends Statments{

	public SiteCartProductStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}
	
	
	//Lista todos os SiteCartProduct no cart pelo cartToken
	public List<SiteCartProductModel> SelectAllByCartToken(String cartTk) throws SQLException
	{
		String query = "SELECT * FROM stm_site_cart_product WHERE cart_tk=? order by product_id ASC;"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {
			
			List<SiteCartProductModel> list = new ArrayList<>();
			
			pStmt.setString(1, cartTk);
			
			try(ResultSet rst = pStmt.executeQuery())
			{
				while(rst.next()) {
					
					SiteCartProductModel sitecCartProduct = new SiteCartProductModel();
					
					sitecCartProduct.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					sitecCartProduct.setProduct_id(rst.getInt("product_id")); //$NON-NLS-1$
					sitecCartProduct.setCart_tk(rst.getString("cart_tk")); //$NON-NLS-1$
					sitecCartProduct.setOrder_qt(rst.getInt("order_qt")); //$NON-NLS-1$
					sitecCartProduct.setUnits_tp(rst.getString("units_tp")); //$NON-NLS-1$
					
					list.add(sitecCartProduct);
				}
			}
			
			return list;
			
		}
			
	}
	

	//Insere um SiteCartProduct ao cart
	public void InsertProductCart(SiteCartProductModel siteCartProduct) throws SQLException
	{
		
		String query = "INSERT INTO stm_site_cart_product(site_id, cart_tk, product_id,order_qt,units_tp) values (?,?,?,?,?);"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
			
			pStmt.setInt(1, siteCartProduct.getSite_id());
			pStmt.setString(2, siteCartProduct.getCart_tk());
			pStmt.setInt(3, siteCartProduct.getProduct_id());
			pStmt.setInt(4, siteCartProduct.getOrder_qt());
			pStmt.setString(5, siteCartProduct.getUnits_tp());
			pStmt.executeUpdate();

		}
	}
	
	
	//Faz Update à quantidade do SiteCartProduct no cart
	public void UpdateOrder_qt(SiteCartProductModel siteCartProduct) throws SQLException 
	{
		
		String query = "UPDATE stm_site_cart_product SET order_qt=? WHERE product_id=?;"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
			
			pStmt.setInt(1, siteCartProduct.getOrder_qt());
			pStmt.setInt(2, siteCartProduct.getProduct_id());
			
			pStmt.executeUpdate();

		}
	}
	
	
	//Delete SiteCartProduct pelo cart_tk
	public void DeleteSiteCartProductByCartTk(String cartTk) throws SQLException{
		
		String query = "DELETE FROM stm_site_cart_product WHERE cart_tk=?;"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
				
			pStmt.setString(1, cartTk);
				
			pStmt.executeUpdate();
				
		}
	}
	
	
	//Delete SiteCartProduct pelo productId
	public void DeleteSiteCartProductByProductId(int productId) throws SQLException{
		
		String query = "DELETE FROM stm_site_cart_product WHERE product_id=?;"; //$NON-NLS-1$
				
			try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
					
				pStmt.setInt(1, productId);
					
				pStmt.executeUpdate();
					
			}
		}
}

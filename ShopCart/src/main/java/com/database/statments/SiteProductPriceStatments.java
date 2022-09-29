package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteProductPriceModel;

public class SiteProductPriceStatments extends Statments {

	public SiteProductPriceStatments(Connection con) {
		super(con);
	}

	// Lista todos os SiteProductPrice pelo siteId e pelo status
	public List<SiteProductPriceModel> SelectAllSiteIdAndStatusPrice(int siteId, String status) throws SQLException {
		String query = "SELECT * FROM stm_site_product_price WHERE site_id=? AND price_st=? order by product_id ASC;"; //$NON-NLS-1$

		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {

			List<SiteProductPriceModel> list = new ArrayList<>();

			pStmt.setInt(1, siteId);
			pStmt.setString(2, status);

			try (ResultSet rst = pStmt.executeQuery()) {
				while (rst.next()) {

					SiteProductPriceModel siteproductPrice = new SiteProductPriceModel();

					siteproductPrice.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					siteproductPrice.setProduct_id(rst.getInt("product_id")); //$NON-NLS-1$
					siteproductPrice.setCurrency_cd(rst.getString("currency_cd")); //$NON-NLS-1$
					siteproductPrice.setStart_dt(rst.getDate("start_dt")); //$NON-NLS-1$
					siteproductPrice.setPrice_vl(rst.getDouble("price_vl")); //$NON-NLS-1$
					siteproductPrice.setPrice_st(rst.getString("price_st")); //$NON-NLS-1$

					list.add(siteproductPrice);
				}
			}

			return list;

		} 
	}

	//Retorna o SiteProductPrice pelo ProductId
	public SiteProductPriceModel SelectByProductId(int productId) throws SQLException {

		String query = "SELECT * FROM stm_site_product_price WHERE product_id=?;"; //$NON-NLS-1$

		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {

			SiteProductPriceModel siteproductPrice = new SiteProductPriceModel();

			pStmt.setInt(1, productId);

			try (ResultSet rst = pStmt.executeQuery()) {
				
				while (rst.next()) {

					siteproductPrice.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					siteproductPrice.setProduct_id(rst.getInt("product_id")); //$NON-NLS-1$
					siteproductPrice.setCurrency_cd(rst.getString("currency_cd")); //$NON-NLS-1$
					siteproductPrice.setStart_dt(rst.getDate("start_dt")); //$NON-NLS-1$
					siteproductPrice.setPrice_vl(rst.getDouble("price_vl")); //$NON-NLS-1$
					siteproductPrice.setPrice_st(rst.getString("price_st")); //$NON-NLS-1$
				}

			}

			return siteproductPrice;

		}  

	}

}

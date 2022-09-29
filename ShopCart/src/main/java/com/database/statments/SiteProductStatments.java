package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.models.SiteProductModel;

public class SiteProductStatments extends Statments {

	public SiteProductStatments(Connection con) {
		super(con);
	}

	// Retorna SiteProduct pelo productID
	public SiteProductModel SelectByIdProduct(int idProduct) throws SQLException {

		String query = "SELECT * FROM stm_site_product WHERE product_id=?;"; //$NON-NLS-1$

		try (PreparedStatement pStmt = this.connection.prepareStatement(query)) {

			SiteProductModel siteproduct = new SiteProductModel();

			pStmt.setInt(1, idProduct);

			try (ResultSet rst = pStmt.executeQuery()) {
				
				while (rst.next()) {

					siteproduct.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					siteproduct.setProduct_id(rst.getInt("product_id")); //$NON-NLS-1$
				}

			}

			return siteproduct;

		}

	}

}

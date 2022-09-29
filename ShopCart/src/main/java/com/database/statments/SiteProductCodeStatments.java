package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.models.SiteProductCodeModel;

public class SiteProductCodeStatments extends Statments {

	public SiteProductCodeStatments(Connection con) {
		super(con);
	}
	
	//Retorna um SiteProductCodeModel pelo PorductID
	public SiteProductCodeModel SelectByProductId(int productId) throws SQLException{
		
		String query = "SELECT * FROM stm_site_product_code WHERE product_id=?;"; //$NON-NLS-1$
				
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){

			SiteProductCodeModel siteproductCode = new SiteProductCodeModel();

			pStmt.setInt(1, productId);
			
			try(ResultSet rst = pStmt.executeQuery())
			{
				while (rst.next()) {

					siteproductCode.setProduct_code_cd(rst.getString("product_code_cd")); //$NON-NLS-1$
					siteproductCode.setProduct_code_tp(rst.getString("product_code_tp")); //$NON-NLS-1$
					siteproductCode.setProduct_code_jd(rst.getString("product_code_jd")); //$NON-NLS-1$
				}
			}
			
			return siteproductCode;

		} 

	}

}

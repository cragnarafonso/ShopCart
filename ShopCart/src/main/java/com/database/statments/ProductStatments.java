package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.database.models.ProductModel;

public class ProductStatments extends Statments
{

	public ProductStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}
	

	//Retorna os Produtos por Id
	public ProductModel SelectByProductId(int idProduct) throws SQLException{
		
		String query = "SELECT * FROM stm_product WHERE product_id =?;"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
			
			ProductModel product = new ProductModel();
			
			pStmt.setInt(1, idProduct);
			
			
			try(ResultSet rst = pStmt.executeQuery();)
			{
			
				while(rst.next()) {
					
					product.setProduct_id(rst.getInt("product_id")); //$NON-NLS-1$
					product.setProduct_ds(rst.getString("product_ds")); //$NON-NLS-1$
					product.setProduct_st(rst.getString("product_st")); //$NON-NLS-1$
					product.setProduct_jd(rst.getString("product_jd")); //$NON-NLS-1$
					product.setProduct_image_cd(rst.getString("product_image_cd")); //$NON-NLS-1$
	
				}
			}
			
			return product;
			
		}
			
	}

}

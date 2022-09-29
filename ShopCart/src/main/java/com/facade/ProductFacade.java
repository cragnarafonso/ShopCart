package com.facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.ItemProduct;
import com.database.models.SiteCartProductModel;
import com.database.models.SiteProductPriceModel;
import com.database.statments.ProductStatments;
import com.database.statments.SiteCartProductStatments;
import com.database.statments.SiteProductCodeStatments;
import com.database.statments.SiteProductPriceStatments;
import com.database.statments.SiteProductStatments;

public class ProductFacade {
	

	public ProductFacade(){
		
	}
	
	
	//Retorna lista de items correspondentes aos produtos disponiveis aos clientes
	public static List<ItemProduct> listAllItemsForView(int siteId, String status) throws SQLException
	{
		
		try (Connection con = TransactionFacade.beginTransaction()){
			
			ProductStatments productStm = new ProductStatments(con);
			SiteProductCodeStatments siteProductCodeStm = new SiteProductCodeStatments(con);
			SiteProductPriceStatments siteProductPriceStm = new SiteProductPriceStatments(con);
			SiteProductStatments siteProductsStm = new SiteProductStatments(con);
			
			try {
				
				List<ItemProduct> listitemProduct = new ArrayList<>();
				List<SiteProductPriceModel> listspPrice = new ArrayList<>();
			
				//Statments
				listspPrice = siteProductPriceStm.SelectAllSiteIdAndStatusPrice(siteId, status);
				
				for(int i=0; i < listspPrice.size(); i++)
				{
					ItemProduct itemProduct = new ItemProduct();
					
					itemProduct.setSiteProductPrice(listspPrice.get(i));	
					itemProduct.setSiteproduct(siteProductsStm.SelectByIdProduct(listspPrice.get(i).getProduct_id()));
					itemProduct.setSiteProducCode(siteProductCodeStm.SelectByProductId(listspPrice.get(i).getProduct_id()));
					itemProduct.setProduct(productStm.SelectByProductId(listspPrice.get(i).getProduct_id()));
					
					listitemProduct.add(itemProduct);
				}
				
				TransactionFacade.commitTransaction(con);
				
				return listitemProduct;
				
			}catch (Exception e)
			{
				TransactionFacade.roolbackTransaction(con);
				
				e.printStackTrace();
				
				return null;
			}
		}
	}
	
	
	//Retorna lista de items correspondentes aos produtos no cart disponiveis aos clientes
		public static List<ItemProduct> listAllItemsForCartView(String cartToken) throws SQLException
		{
			
			
			try (Connection con = TransactionFacade.beginTransaction()){
				
				ProductStatments productStm = new ProductStatments(con);
				SiteProductCodeStatments siteProductCodeStm = new SiteProductCodeStatments(con);
				SiteProductPriceStatments siteProductPriceStm = new SiteProductPriceStatments(con);
				SiteProductStatments siteProductsStm = new SiteProductStatments(con);
				SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);
				
				try {
					
					List<ItemProduct> listitemProduct = new ArrayList<>();
					List<SiteCartProductModel> listspCart= new ArrayList<>();
				
					//Statments
					listspCart = siteCartProductStm.SelectAllByCartToken(cartToken);
					
					for(int i=0; i < listspCart.size(); i++)
					{
						ItemProduct itemProduct = new ItemProduct();
						
						itemProduct.setSiteProductPrice(siteProductPriceStm.SelectByProductId(listspCart.get(i).getProduct_id()));	
						itemProduct.setSiteproduct(siteProductsStm.SelectByIdProduct(listspCart.get(i).getProduct_id()));
						itemProduct.setSiteProducCode(siteProductCodeStm.SelectByProductId(listspCart.get(i).getProduct_id()));
						itemProduct.setProduct(productStm.SelectByProductId(listspCart.get(i).getProduct_id()));
						itemProduct.setSiteCartProduct(listspCart.get(i));
						
						listitemProduct.add(itemProduct);
					}
		
					TransactionFacade.commitTransaction(con);
					
					return listitemProduct;
					
				}catch (Exception e)
				{
					TransactionFacade.roolbackTransaction(con);
					
					e.printStackTrace();
					
					return null;
				}
			}
		
		}
}

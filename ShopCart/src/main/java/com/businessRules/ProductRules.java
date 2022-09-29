package com.businessRules;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.database.models.ItemProduct;
import com.database.models.SiteCartProductModel;


public class ProductRules {
	
	
	//Verefica a existencia de um determinado produto no cart
	public static SiteCartProductModel existProductCart(List<SiteCartProductModel> listscp, int productId) {
		
		for(int i = 0; i < listscp.size(); i++) {
			
			if(listscp.get(i).getProduct_id() == productId) {
				
				return listscp.get(i);
			}
		}
		
		return null;
	}
	
	
	//Calcula o Prevo Total
	public static double TotalPrice(List<ItemProduct> listItemProduct) {
		
		double total = 0.0;
		
		for(int i = 0; i < listItemProduct.size(); i++) {
			
			total += (listItemProduct.get(i).getSiteCartProduct().getOrder_qt() * listItemProduct.get(i).getSiteProductPrice().getPrice_vl());
		}
		
		return total;
	}
	
	
	//Cria um Json com a order 
	public static String saveOrder(List<ItemProduct> listItemProduct, double totalPrice) {
		
		String order = null;
		JSONObject jsObject = new JSONObject();
		JSONArray products = new JSONArray();
		
		for(int i = 0; i < listItemProduct.size(); i++)
		{
			
			products.put(i+1);
			products.put(listItemProduct.get(i).getProduct().getProduct_ds());
			products.put(listItemProduct.get(i).getSiteProducCode().getProduct_code_cd());
			products.put(listItemProduct.get(i).getSiteCartProduct().getOrder_qt());
			products.put(listItemProduct.get(i).getSiteProductPrice().getPrice_vl());
			
			jsObject.put("products", products); //$NON-NLS-1$
			
		}

		jsObject.put("totalPrice", totalPrice); //$NON-NLS-1$
		jsObject.put("cartToken", listItemProduct.get(0).getSiteCartProduct().getCart_tk());  //$NON-NLS-1$
		
		order = jsObject.toString();
		
		return order;

	}
	
}

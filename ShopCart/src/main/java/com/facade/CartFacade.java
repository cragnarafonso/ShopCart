package com.facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.businessRules.GenericRules;
import com.businessRules.ProductRules;
import com.database.models.ItemProduct;
import com.database.models.SiteCartModel;
import com.database.models.SiteCartProductModel;
import com.database.models.SiteSessionModel;
import com.database.statments.SiteCartProductStatments;
import com.database.statments.SiteCartStatments;

public class CartFacade {

	public CartFacade() {

	}

	// Retorna SiteCartProduct caso exista já no cart
	public static SiteCartProductModel existProductinCart(String cartTk, int productId) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);

			try {

				List<SiteCartProductModel> listscp = new ArrayList<>();
				SiteCartProductModel existProduct = null;

				// Statments
				listscp = siteCartProductStm.SelectAllByCartToken(cartTk);

				// Rules
				existProduct = ProductRules.existProductCart(listscp, productId);

				TransactionFacade.commitTransaction(con);

				return existProduct;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();

				return null;
			}

		}

	}

	
	// Add a Product in a Cart
	public static boolean addProductinCart(SiteCartProductModel siteCartProduct) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);

			try {
				
				// Statments
				siteCartProductStm.InsertProductCart(siteCartProduct);
	
				TransactionFacade.commitTransaction(con);
				
				return true;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();
				
				return false;
			}

		} 
	}
	
	

	// Update a Order_qt in Product Cart
	public static boolean incrementOrderQt(SiteCartProductModel siteCartProduct) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);

			try {

				int order_qt = 0;
	
				// Rules
				order_qt = GenericRules.incrementValue(siteCartProduct.getOrder_qt());
				siteCartProduct.setOrder_qt(order_qt);
	
				// Statments
				siteCartProductStm.UpdateOrder_qt(siteCartProduct);
	
				TransactionFacade.commitTransaction(con);
				
				return true;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();
				
				return false;

			} 
		}
	}
	

	// Update a Order_qt in Product Cart
	public static boolean decrementOrderQt(SiteCartProductModel siteCartProduct) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);

			try {

				int order_qt = 0;
	
				// Rules
				if (siteCartProduct.getOrder_qt() > 0) {
					
					order_qt = GenericRules.decrementValue(siteCartProduct.getOrder_qt());
					siteCartProduct.setOrder_qt(order_qt);
					
					// Statments
					siteCartProductStm.UpdateOrder_qt(siteCartProduct);
				}
	
				TransactionFacade.commitTransaction(con);
				
				return true;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();
				
				return false;

			} 
		}

	}
	
	//Elimina um SiteCartProduct pelo ProductId
	public static boolean removeProductCart(SiteCartProductModel siteCartProduct) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);

			try {

				siteCartProductStm.DeleteSiteCartProductByProductId(siteCartProduct.getProduct_id());
	
				TransactionFacade.commitTransaction(con);
				
				return true;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();
				
				return false;

			} 
		}

	}
	

	// Total Price
	public static double getTotalPrice(List<ItemProduct> listItemProduct) {

		return ProductRules.TotalPrice(listItemProduct);
	}
	

	//Limpa o Sitecart de Tokens expirados e cria um novo
	public static String cleanAndnewCart(SiteCartModel siteCart) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);
			SiteCartStatments siteCartStm = new SiteCartStatments(con);
			
			try {
			
				String newCartToken = null;
				List<SiteCartModel> listscart = new ArrayList<>();
	
				// Statments
				listscart = siteCartStm.SelectAllBySiteIdAndSessionisNull(siteCart.getSite_id());
	
				for (int i = 0; i < listscart.size(); i++) {
					
					siteCartProductStm.DeleteSiteCartProductByCartTk(listscart.get(i).getCart_tk());
				}
	
				siteCartStm.DeleteSiteCartBySiteId(siteCart.getSite_id());
	
				// Rules - Create Token
				newCartToken = GenericRules.newToken(siteCart.getSite_id());
				siteCart.setCart_tk(newCartToken);
	
				// Statment
				siteCartStm.InsertSiteCart(siteCart);
	
				TransactionFacade.commitTransaction(con);
	
				return newCartToken;
	
			} catch (Exception e) {
	
				TransactionFacade.roolbackTransaction(con);
	
				e.printStackTrace();
	
				return null;
				
	
			} 
		}
	}
	

	// Guarda e Elimina tudo do SiteCart e o SiteProdcutCart
	public static String SaveAndClean(List<ItemProduct> listItemProducts, String sessionTk, double totalPrice) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);
			SiteCartStatments siteCartStm = new SiteCartStatments(con);
			
			try {

				String order = null;
	
				//Rules
				order = ProductRules.saveOrder(listItemProducts, totalPrice);
				
				//Statments
				for (int i = 0; i < listItemProducts.size(); i++) {
	
					siteCartProductStm.DeleteSiteCartProductByCartTk(listItemProducts.get(i).getSiteCartProduct().getCart_tk());
				}
	
				siteCartStm.DeleteSiteCartBySiteIdAndSessionTk(listItemProducts.get(0).getSiteCartProduct().getSite_id(), sessionTk);
	
				TransactionFacade.commitTransaction(con);
	
				return order;
	
			} catch (Exception e) {
	
				TransactionFacade.roolbackTransaction(con);
	
				e.printStackTrace();
	
				return null;
	
			} 
		}
	}

	// Update a siteCart pelo CartToken
	public static boolean updateSiteCartByCartTk(SiteCartModel siteCart) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartStatments siteCartStm = new SiteCartStatments(con);
			
			try {

				// Statments
				siteCartStm.UpdateSiteCart(siteCart);
	
				TransactionFacade.commitTransaction(con);
				
				return true;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();
				
				return false;
			} 
		}
	}
	

	// Retorna um SiteCart de uma sessionToken já existente
	public static SiteCartModel SelectExistingSiteCartBySessionTk(SiteSessionModel siteSession) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartStatments siteCartStm = new SiteCartStatments(con);
			
			try {

				SiteCartModel siteCart = new SiteCartModel();
	
				// Statments
				siteCart = siteCartStm.SelectBySiteIdAndSessionToken(siteSession);
	
				TransactionFacade.commitTransaction(con);
	
				if (siteCart.getCart_tk() == null) {
					return null;
				}
	
				return siteCart;
	
			} catch (SQLException e) {
	
				TransactionFacade.roolbackTransaction(con);
	
				e.printStackTrace();
	
				return null;
	
			} 
		}
	}

	// Update ou Insert um Produto no carrinho dependendo se ele existe ou não, estando já associado ao session token
	public static boolean UpdateOrInsertProductinCart(String existingCartTk, List<ItemProduct> listProductsViewCookie) throws SQLException {
		
		try (Connection con = TransactionFacade.beginTransaction()) {

			SiteCartProductStatments siteCartProductStm = new SiteCartProductStatments(con);
			
			try {

				List<SiteCartProductModel> listscp = new ArrayList<>();
	
				SiteCartProductModel existProduct = null;
	
				// Statments
				listscp = siteCartProductStm.SelectAllByCartToken(existingCartTk);
	
				// Rules
				for (int i = 0; i < listProductsViewCookie.size(); i++) {
	
					existProduct = ProductRules.existProductCart(listscp, listProductsViewCookie.get(i).getSiteCartProduct().getProduct_id());
	
					if (existProduct != null) {
						
						siteCartProductStm.UpdateOrder_qt(listProductsViewCookie.get(i).getSiteCartProduct());
	
					} else {
						
						listProductsViewCookie.get(i).getSiteCartProduct().setCart_tk(existingCartTk);
						
						siteCartProductStm.InsertProductCart(listProductsViewCookie.get(i).getSiteCartProduct());
					}
				}
	
				TransactionFacade.commitTransaction(con);
				
				return true;

			} catch (Exception e) {

				TransactionFacade.roolbackTransaction(con);

				e.printStackTrace();
				
				return false;

			} 
		}
	}
	
	

}

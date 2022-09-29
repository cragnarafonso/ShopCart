package com.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.models.ItemProduct;
import com.database.models.SiteCartModel;
import com.database.models.SiteCartProductModel;
import com.facade.CartFacade;
import com.facade.ProductFacade;

/**
 * Servlet implementation class ProductController
 */
@WebServlet(name = "productController", urlPatterns = { "/product/*" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String url = "/homePages/home.jsp"; //$NON-NLS-1$

		if (requestURI.endsWith("/listproducts")) { //$NON-NLS-1$

			try {

				url = listProducts(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (requestURI.endsWith("/addproducts")) { //$NON-NLS-1$

			try {

				url = addProducts(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);

		if (rd != null) {
			rd.forward(request, response);

		}

	}

	protected static String listProducts(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$
		String stateView = "ACT"; //$NON-NLS-1$
		
		String url = null;
		String cartToken = null;
		
	
		int numberProductsView = 0;
		int numberProductsCart = 0;
		
		HttpSession session = request.getSession();

		if (idSite != null) {

			List<ItemProduct> listProductsView = new ArrayList<>();
			
			//Verefica se Existe Login feito
			SiteCartModel siteCartSession = new SiteCartModel();
			siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);
			
			if (siteCartSession == null) {
				
				System.err.println("\t\t There is no open session!"); //$NON-NLS-1$
				
				cartToken = (String) session.getAttribute(identiCartTk);

			} else {
				
				System.err.println("\t\t There is open session!"); //$NON-NLS-1$
				
				cartToken = siteCartSession.getCart_tk();

			}
			//

			
			if (cartToken != null) {

				listProductsView = ProductFacade.listAllItemsForCartView(cartToken);
				numberProductsCart = listProductsView.size();

				request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$
			}
			
			System.err.println("\t\t List the products!"); //$NON-NLS-1$
			
			listProductsView = ProductFacade.listAllItemsForView(Integer.parseInt(idSite), stateView);
			numberProductsView = listProductsView.size();

			if (!listProductsView.isEmpty()) {

				url = "/homePages/home.jsp"; //$NON-NLS-1$

			} else {

				url = "/homePages/emptyHome.jsp"; //$NON-NLS-1$
			}

			request.setAttribute("listProductsView", listProductsView); //$NON-NLS-1$
			request.setAttribute("numberProductsView", Integer.toString(numberProductsView)); //$NON-NLS-1$

		} 

		return url;

	}

	protected static String addProducts(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String idProduct = request.getParameter("productId"); //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		
		String cartToken = null;
		String url = null;
		
		int numberProductsCart = 0;
		
		SiteCartProductModel existProduct = null;
		
		HttpSession session = request.getSession();

		if (idSite != null && idProduct != null) {

			List<ItemProduct> listProductsView = new ArrayList<>();
			
			//Verefica se Existe Login feito
			SiteCartModel siteCartSession = new SiteCartModel();
			siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);
			
			if (siteCartSession == null) {
				
				System.err.println("\t\t There is no open session!"); //$NON-NLS-1$
				
				cartToken = (String) session.getAttribute(identiCartTk);

			} else {
				
				System.err.println("\t\t There is open session!"); //$NON-NLS-1$
				
				cartToken = siteCartSession.getCart_tk();

			}
			//
			
			if (cartToken != null) {
				
				// Existe session do Token
				// Verefica se o produto a ser adicionado naquele token já existe ou não
				
				existProduct = CartFacade.existProductinCart(cartToken, Integer.parseInt(idProduct));

				if (existProduct == null) {

					// Produto não existe e tem de ser adicionado

					SiteCartProductModel siteCartProduct = new SiteCartProductModel();

					siteCartProduct = addProductCart(idSite, idProduct, cartToken);

					CartFacade.addProductinCart(siteCartProduct);

					listProductsView = ProductFacade.listAllItemsForCartView(cartToken);
					numberProductsCart = listProductsView.size();

					System.err.println("\t\t Product does not exist!  Product added successfully!"); //$NON-NLS-1$ 

					url = "/product/listproducts"; //$NON-NLS-1$

				} else {

					// Produto já existe e não precisa de ser adicionado mas aumenta a quantidade
					CartFacade.incrementOrderQt(existProduct);

					listProductsView = ProductFacade.listAllItemsForCartView(cartToken);
					numberProductsCart = listProductsView.size();

					System.err.println("\t\t Product exists. The amount increased!"); //$NON-NLS-1$

					url = "/product/listproducts"; //$NON-NLS-1$
				}

			} else {
				// Não existe session de cartToken ou já expirou - para salvaguardar que não
				// ficam guardados dados de cookies expirados faz-se primeiro uma limpeza
				// ao site_cart e ao Site_cart_product e cria-se um novo cookie com um
				// novo token e adiciona o Produto

				SiteCartModel siteCart = new SiteCartModel();

				siteCart.setSite_id(Integer.parseInt(idSite));
				siteCart.setSession_tk(null);

				cartToken = CartFacade.cleanAndnewCart(siteCart);

				// Criacao da cookie com o novo cartToken
				session.setAttribute(identiCartTk, cartToken);
				System.err.println("\t\t Cart Token created successfully! ->" + session.getAttribute(identiCartTk) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

				// Produto tem de ser adicionado

				SiteCartProductModel siteCartProduct = new SiteCartProductModel();

				siteCartProduct = addProductCart(idSite, idProduct, cartToken);

				CartFacade.addProductinCart(siteCartProduct);

				listProductsView = ProductFacade.listAllItemsForCartView(cartToken);
				numberProductsCart = listProductsView.size();

				System.err.println("\t\t Product does not exist!  Product added successfully!"); //$NON-NLS-1$ 

				url = "/product/listproducts"; //$NON-NLS-1$
			}

		}

		request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$

		return url;

	}

	protected static SiteCartProductModel addProductCart(String idSite, String idProduct, String cartToken) {

		SiteCartProductModel siteCartProduct = new SiteCartProductModel();

		siteCartProduct.setSite_id(Integer.parseInt(idSite));
		siteCartProduct.setProduct_id(Integer.parseInt(idProduct));
		siteCartProduct.setCart_tk(cartToken);
		siteCartProduct.setOrder_qt(1);
		siteCartProduct.setUnits_tp("UNI"); //$NON-NLS-1$

		return siteCartProduct;
	}

}

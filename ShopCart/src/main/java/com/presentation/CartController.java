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
 * Servlet implementation class CartController
 */

@WebServlet(name = "cartController", urlPatterns = { "/cart/*" })
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestURI = request.getRequestURI();
		String url = "/cartPages/cart.jsp"; //$NON-NLS-1$

		if (requestURI.endsWith("/cartproducts")) { //$NON-NLS-1$

			try {

				url = cartProducts(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (requestURI.endsWith("/moreproducts")) { //$NON-NLS-1$

			try {

				url = moreProducts(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (requestURI.endsWith("/lessproducts")) { //$NON-NLS-1$

			try {

				url = lessProducts(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (requestURI.endsWith("/removeproducts")) { //$NON-NLS-1$

			try {

				url = removeProducts(request);

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

	protected static String cartProducts(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;
		String cartToken = null;

		double totalPrice = 0.0;
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
				
				System.err.println("\t\t List products in Cart!"); //$NON-NLS-1$
				
				// Calcula o total Preço
				totalPrice = CartFacade.getTotalPrice(listProductsView);

				if (!listProductsView.isEmpty()) {

					url = "/cartPages/cart.jsp"; //$NON-NLS-1$

				} else {

					url = "/cartPages/emptyCart.jsp"; //$NON-NLS-1$
				}

			} else {

				url = "/cartPages/emptyCart.jsp"; //$NON-NLS-1$

			}
			
			request.setAttribute("listProductsView", listProductsView); //$NON-NLS-1$
			request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$
			request.setAttribute("totalPrice", Double.toString(totalPrice)); //$NON-NLS-1$
		}

		return url;
	}
	
	

	protected static String moreProducts(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String idProduct = request.getParameter("productId"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;
		String cartToken = null;
		
		double totalPrice = 0.0;
		int numberProductsCart = 0;
		
		List<ItemProduct> listProductsView = new ArrayList<>();
		
		HttpSession session = request.getSession();

		if (idProduct != null) {
			
			SiteCartProductModel productInCart = new SiteCartProductModel();
			
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
				// Existe Cookie do Token
				// Adiciona +1 ao produto em questao:

				// Seleciona o Produto
				productInCart = CartFacade.existProductinCart(cartToken, Integer.parseInt(idProduct));

				// Adiciona + 1
				CartFacade.incrementOrderQt(productInCart);
				System.err.println("\t\t The amount increased!"); //$NON-NLS-1$
				
				// Faz Refresh as Produtos no Cart
				listProductsView = ProductFacade.listAllItemsForCartView(cartToken);

				numberProductsCart = listProductsView.size();

				// Calcula o total Preço
				totalPrice = CartFacade.getTotalPrice(listProductsView);

				url = "/cartPages/cart.jsp"; //$NON-NLS-1$
			}

		} 

		request.setAttribute("listProductsView", listProductsView); //$NON-NLS-1$
		request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$
		request.setAttribute("totalPrice", Double.toString(totalPrice)); //$NON-NLS-1$

		return url;
	}

	protected static String lessProducts(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String idProduct = request.getParameter("productId"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;
		String cartToken = null;

		double totalPrice = 0.0;
		int numberProductsCart = 0;
		
		List<ItemProduct> listProductsView = new ArrayList<>();
		HttpSession session = request.getSession();
		
		if (idProduct != null) {
			
			
			SiteCartProductModel productInCart = new SiteCartProductModel();
			
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
				// Existe Cookie do Token
				// Adiciona -1 ao produto em questao:

				// Seleciona o Produto
				productInCart = CartFacade.existProductinCart(cartToken, Integer.parseInt(idProduct));

				// Adiciona - 1
				CartFacade.decrementOrderQt(productInCart);
				System.err.println("\t\t The amount decrease!"); //$NON-NLS-1$

				// Faz Refresh as Produtos no Cart
				listProductsView = ProductFacade.listAllItemsForCartView(cartToken);

				numberProductsCart = listProductsView.size();

				// Calcula o total Preço
				totalPrice = CartFacade.getTotalPrice(listProductsView);

				url = "/cartPages/cart.jsp"; //$NON-NLS-1$
			}

		} 

		request.setAttribute("listProductsView", listProductsView); //$NON-NLS-1$
		request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$
		request.setAttribute("totalPrice", Double.toString(totalPrice)); //$NON-NLS-1$

		return url;
	}

	protected static String removeProducts(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String idProduct = request.getParameter("productId"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;
		String cartToken = null;

		double totalPrice = 0.0;
		int numberProductsCart = 0;
		
		List<ItemProduct> listProductsView = new ArrayList<>();
		HttpSession session = request.getSession();
		
		if (idProduct != null) {
			
			SiteCartProductModel productInCart = new SiteCartProductModel();
			
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
				// Existe Cookie do Token

				// Seleciona o Produto
				productInCart = CartFacade.existProductinCart(cartToken, Integer.parseInt(idProduct));

				// remove
				CartFacade.removeProductCart(productInCart);
				System.err.println("\t\t Product eliminated!"); //$NON-NLS-1$

				// Faz Refresh as Produtos no Cart
				listProductsView = ProductFacade.listAllItemsForCartView(cartToken);

				numberProductsCart = listProductsView.size();

				// Calcula o total Preço
				totalPrice = CartFacade.getTotalPrice(listProductsView);

				url = "/cartPages/cart.jsp"; //$NON-NLS-1$
			}

		} 

		request.setAttribute("listProductsView", listProductsView); //$NON-NLS-1$
		request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$
		request.setAttribute("totalPrice", Double.toString(totalPrice)); //$NON-NLS-1$

		return url;
	}

}

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
import com.database.models.SiteClientOrderModel;
import com.facade.CartFacade;
import com.facade.OrderFacade;
import com.facade.ProductFacade;

/**
 * Servlet implementation class OrderController
 */
@WebServlet(name = "orderController", urlPatterns = { "/order/*" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
		/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestURI = request.getRequestURI();
		String url = "/orderPages/order.jsp"; //$NON-NLS-1$

		if (requestURI.endsWith("/neworder")) { //$NON-NLS-1$

			try {

				url = neworder(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(requestURI.endsWith("/listorder")) //$NON-NLS-1$
		{
			try {
				
				url = listorder(request);
				
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
	
	protected static String neworder(HttpServletRequest request) throws SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;
		String cartToken = null;
		String orderJs = null;
		
		int numberOrders = 0;
		double totalPrice = 0.0;
		
		List<SiteClientOrderModel> listOrders= new ArrayList<>();
		List<ItemProduct> listProductsView = new ArrayList<>();
		HttpSession session = request.getSession();
		
		if (idSite != null) {
			
			SiteClientOrderModel siteClientOrder= new SiteClientOrderModel();
			
			//Verefica se Existe Login feito
			SiteCartModel siteCartSession = new SiteCartModel();
			siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);
			//

			if(siteCartSession != null)
			{
				System.err.println("\t\t There is open session!"); //$NON-NLS-1$
				
				cartToken = siteCartSession.getCart_tk();
				
				//Lista os Pordutos em Cart e Retorna o Preço Total
				listProductsView = ProductFacade.listAllItemsForCartView(cartToken);
				totalPrice = CartFacade.getTotalPrice(listProductsView);
				
				//Guarda a ordem de compra e Elimina todos os dados sujeitos aquele cartToken já que o cart deu seguimento para compra
				orderJs = CartFacade.SaveAndClean(listProductsView, siteCartSession.getSession_tk(), totalPrice);
					
				//Adiciona a Ordem de Compra à base de dados.
				siteClientOrder = addOrder(siteCartSession.getSite_id(), siteCartSession.getClient_id(), orderJs);
				OrderFacade.addSiteClientOrder(siteClientOrder);
				
				System.err.println("\t\t Add a new order!"); //$NON-NLS-1$
				
				//Lista a nova lista já com o order adicionado
				listOrders = OrderFacade.listAllItemsForView(siteCartSession.getSite_id(), siteCartSession.getClient_id());
				numberOrders = listOrders.size();
				
				System.err.println("\t\t List the orders!"); //$NON-NLS-1$
				
				//Refaz os valores da session, é necessário eliminar o cartToken que já não existe
				SiteCartModel newsiteCart = new SiteCartModel();
				
				newsiteCart.setSite_id(Integer.parseInt(idSite));
				newsiteCart.setSession_tk(siteCartSession.getSession_tk());
				newsiteCart.setClient_id(siteCartSession.getClient_id());
				cartToken = CartFacade.cleanAndnewCart(newsiteCart);
				newsiteCart.setCart_tk(cartToken);
				
				CartFacade.updateSiteCartByCartTk(newsiteCart);
				
				session.setAttribute(identiSessionTk, newsiteCart);
				
				url = "/orderPages/order.jsp"; //$NON-NLS-1$

			}else {
				
				//Página de erro, não é suposto entrar aqui sem uma session ativa
				url = "error/jsp"; //$NON-NLS-1$
				
			}
		}
		
		request.setAttribute("numberOrders", Integer.toString(numberOrders)); //$NON-NLS-1$
		request.setAttribute("listOrders", listOrders); //$NON-NLS-1$
		
		return url;
	}
	
	
	
	protected static SiteClientOrderModel addOrder(int idSite, int idClient, String orderJd) {

		SiteClientOrderModel siteClientOrder = new SiteClientOrderModel();

		siteClientOrder.setSite_id(idSite);
		siteClientOrder.setClient_id(idClient);
		siteClientOrder.setOrder_jd(orderJd);


		return siteClientOrder;
	}
	
	
	protected static String listorder(HttpServletRequest request) throws SQLException {
		
		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$
		
		String url = null;
		
		int numberOrders = 0;
		int numberProductsCart = 0;
		
		List<SiteClientOrderModel> listOrders= new ArrayList<>();
		List<ItemProduct> listProductsView = new ArrayList<>();
		HttpSession session = request.getSession();
		
		if (idSite != null) {
			
			//Verefica se Existe Login feito
			SiteCartModel siteCartSession = new SiteCartModel();
			siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);
			//
	
				if(siteCartSession != null)
				{
					
					listProductsView = ProductFacade.listAllItemsForCartView(siteCartSession.getCart_tk());
					numberProductsCart = listProductsView.size();
					
					//Lista a nova lista já com o order adicionado
					listOrders = OrderFacade.listAllItemsForView(siteCartSession.getSite_id(), siteCartSession.getClient_id());
					numberOrders = listOrders.size();
					
					System.err.println("\t\t List the orders!"); //$NON-NLS-1$
					
					url = "/orderPages/order.jsp"; //$NON-NLS-1$

				}else {
					
					//Página de erro, não é suposto entrar aqui sem uma session ativa
					url = "error/jsp"; //$NON-NLS-1$
					
				}
				//Fim do Código  para colocar em login feito
			}
			
			request.setAttribute("numberOrders", Integer.toString(numberOrders)); //$NON-NLS-1$
			request.setAttribute("listOrders", listOrders); //$NON-NLS-1$
			request.setAttribute("numberProductsCart", Integer.toString(numberProductsCart)); //$NON-NLS-1$
			
			return url;
		
	}
	
	




}

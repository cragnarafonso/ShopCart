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
import com.database.models.SiteSessionModel;
import com.facade.CartFacade;
import com.facade.LoginFacade;
import com.facade.ProductFacade;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "loginController", urlPatterns = { "/login/*" })
public class LoginController extends HttpServlet {
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

		String requestURI = request.getRequestURI();
		String url = "/loginPages/login.jsp"; //$NON-NLS-1$

		if (requestURI.endsWith("/checklogin")) { //$NON-NLS-1$

			url = checkLogin(request);

		} else if (requestURI.endsWith("/login")) { //$NON-NLS-1$

			try {

				url = login(request);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (requestURI.endsWith("/logout")) //$NON-NLS-1$
		{
			url = logout(request);
		}

		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);

		if (rd != null) {

			rd.forward(request, response);
		}
	}

	protected static String checkLogin(HttpServletRequest request) {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		
		String url = null;
		String cartToken = null;
		
		HttpSession session = request.getSession();
		
		if (idSite != null) {
			
			SiteCartModel siteCartSession = new SiteCartModel();
			
			cartToken = (String) session.getAttribute(identiCartTk);
			siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);
			
			
			if (siteCartSession == null) {

				if (cartToken != null) {

					request.setAttribute("siteId", idSite);//$NON-NLS-1$
					request.setAttribute("cartToken", cartToken);//$NON-NLS-1$
				}
				
				System.err.println("\t\t There is no open session!"); //$NON-NLS-1$

				url = "/loginPages/login.jsp"; //$NON-NLS-1$

			} else {
				
				System.err.println("\t\t There is open session!"); //$NON-NLS-1$
				
				url = "/order/neworder"; //$NON-NLS-1$
			}

		}

		return url;
	}

	protected static String login(HttpServletRequest request) throws NumberFormatException, SQLException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String client = request.getParameter("client"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;

		String cartToken = null;
		String sessionToken = null;

		if (idSite != null) {


			SiteSessionModel siteSession = new SiteSessionModel();
			SiteCartModel newsiteCart = new SiteCartModel();
			SiteCartModel existingsiteCart = new SiteCartModel();

			List<ItemProduct> listProductsViewCookie = new ArrayList<>();

			HttpSession session = request.getSession();

			// Retorna a existencia ou não de uma sessao de um cliente
			siteSession = LoginFacade.checkClientAndSession(client, Integer.parseInt(idSite));

			if (siteSession != null) {
				
				System.err.println("\t\t There is open session!"); //$NON-NLS-1$
				
				// Verefica se já existe um cartToken registado naquele cliente:
				existingsiteCart = CartFacade.SelectExistingSiteCartBySessionTk(siteSession);

				cartToken = (String) session.getAttribute(identiCartTk);

				sessionToken = siteSession.getSession_tk();

				if (existingsiteCart != null) {
					
					System.err.println("\t\t There is a cart for this session!"); //$NON-NLS-1$
					
					session.setAttribute(identiSessionTk, existingsiteCart);

					// Se existir então não pode ter um novo cartToken, os produtos são apenas adicionados
					// cartToken existente na sessao do cliente

					// Recuperar a lista de produtos no cart referentes ao cookie
					listProductsViewCookie = ProductFacade.listAllItemsForCartView(cartToken);

					// Depois fazemos um select ao product id a ver se existe jáa lista de cart existente
						// Se existir faz update à quandidade de forma a ficar igual a lista do cookie
						// Se não existir adiciona o novo produto do cookie mas com o cart token já existente

					CartFacade.UpdateOrInsertProductinCart(existingsiteCart.getCart_tk(), listProductsViewCookie);

				} else {
					
					// Se não existir cria-se um novo Cart
						//Se já existir cartToken na Cookie, atribui-se 
						//Se não existir cria-se um novo e atribui-se
					
					if (cartToken == null) {

						System.err.println("\t\t There is no cart for this session!"); //$NON-NLS-1$
						
						SiteCartModel siteCart = new SiteCartModel();

						siteCart.setSite_id(Integer.parseInt(idSite));
						siteCart.setSession_tk(null);

						cartToken = CartFacade.cleanAndnewCart(siteCart);

						// Criacao da cookie com o novo cartToken
						session.setAttribute(identiCartTk, cartToken);
						System.err.println("\t\t Cart Token created successfully! ->" + session.getAttribute(identiCartTk) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
					}

					
					// Atribui-se o session_tk ao site_cart 
					newsiteCart = addSiteCart(siteSession.getSite_id(), cartToken, siteSession.getSession_tk(), siteSession.getClient_id());
					CartFacade.updateSiteCartByCartTk(newsiteCart);

					session.setAttribute(identiSessionTk, newsiteCart);
					System.err.println("\t\t Cookie created successfully! Session Token is: " + session.getAttribute(siteSession.getSession_tk()) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

				}

				url = "/product/listproducts"; //$NON-NLS-1$

			} else {

				url = "/loginPages/failLogin.jsp"; //$NON-NLS-1$

			}

		}

		request.setAttribute("sessionToken", sessionToken); //$NON-NLS-1$

		return url;
	}

	protected static String logout(HttpServletRequest request) throws NumberFormatException {

		String idSite = request.getParameter("siteId"); //$NON-NLS-1$
		String identiCartTk = "cartTk" + idSite; //$NON-NLS-1$
		String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

		String url = null;
		String cartToken = null;
		
		HttpSession session = request.getSession();

		if (idSite != null) {

			//Verefica se Existe Login feito
			SiteCartModel siteCartSession = new SiteCartModel();
			siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);

			if (siteCartSession != null) {
				
				session.removeAttribute(identiSessionTk);
				
				cartToken = (String) session.getAttribute(identiCartTk);
				
				
				if(cartToken != null)
				{
					session.removeAttribute(identiCartTk);
				}
							
				System.err.println("\t\t There is open session! Close Session"); //$NON-NLS-1$
				
			}
				
			url = "/site/listsites"; //$NON-NLS-1$

		}

		return url;
	}

	protected static SiteCartModel addSiteCart(int idSite, String cartToken, String sessionToken, int idClient) {

		SiteCartModel siteCart = new SiteCartModel();

		siteCart.setSite_id(idSite);
		siteCart.setCart_tk(cartToken);
		siteCart.setSession_tk(sessionToken);
		siteCart.setClient_id(idClient);

		return siteCart;
	}

}

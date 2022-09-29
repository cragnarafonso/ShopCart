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

import com.database.models.SiteModel;
import com.facade.SiteFacade;

/**
 * Servlet implementation class SiteController
 */
@WebServlet(name = "siteController", urlPatterns = {"/site/*"})
public class SiteController extends HttpServlet {
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
		
		String requestURI = request.getRequestURI();
		String url = "/sitePages/site.jsp"; //$NON-NLS-1$
		
		if(requestURI.endsWith("/listsites")) //$NON-NLS-1$
		{
			try {
				
				url = listSites(request);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);

		 
		if(rd != null)
		{			
			rd.forward(request, response);
		}

	}	


	protected static String listSites(HttpServletRequest request) throws SQLException {
	
		String url = null;
		
		String stateSite = "ACT"; //$NON-NLS-1$
		
		int numberSitesACT = 0;
		
		List<SiteModel> listSitesACT = new ArrayList<>();
		
		//Lista os Sites Ativos e o numero total
		
		listSitesACT = SiteFacade.listAllSitesbyStatus(stateSite); 
		numberSitesACT =listSitesACT.size();
		
		System.err.println("\t\t List the sites!"); //$NON-NLS-1$
		
		if(!listSitesACT.isEmpty())
		{
			url = "/sitePages/site.jsp"; //$NON-NLS-1$
			
		}else
		{
			url = "/sitePages/emptySite.jsp"; //$NON-NLS-1$
		}
		
		 request.setAttribute("listSitesACT", listSitesACT ); //$NON-NLS-1$
		 request.setAttribute("numberSitesACT", Integer.toString(numberSitesACT)); //$NON-NLS-1$
		 
		 return url;
		 
		}

	}






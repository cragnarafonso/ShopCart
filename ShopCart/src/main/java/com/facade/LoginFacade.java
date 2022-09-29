package com.facade;

import java.sql.Connection;
import java.sql.SQLException;

import com.database.models.SiteClientModel;
import com.database.models.SiteSessionModel;
import com.database.statments.SiteClientStatments;
import com.database.statments.SiteSessionStatments;

public class LoginFacade {


	public LoginFacade() {

	}

	// Verefica se Existe o Client e Retorna o sitesession desse client
	public static SiteSessionModel checkClientAndSession(String dsClient, int siteId) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()){
			
			SiteClientStatments siteClientStm = new SiteClientStatments(con);
			SiteSessionStatments siteSessionStm = new SiteSessionStatments(con);
			
			try {

				SiteSessionModel sitesession = new SiteSessionModel();
				SiteClientModel existClient = new SiteClientModel();
	
				// Statments
				existClient = siteClientStm.SelectByDsClient(dsClient);
				
				sitesession = siteSessionStm.SelectByIdSiteAndIdClient(existClient.getClient_id(), siteId);
				
				TransactionFacade.commitTransaction(con);
				
				if(sitesession.getSession_tk() == null)
				{
					return null;
				}
	
				return sitesession;
	
			} catch (Exception e) {
	
				TransactionFacade.roolbackTransaction(con);
	
				e.printStackTrace();
	
				return null;
	
			} 
		}

	}
	

}

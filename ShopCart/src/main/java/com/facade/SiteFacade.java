package com.facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteModel;
import com.database.statments.SiteStatments;

public class SiteFacade {
	
	
	public SiteFacade() {
		
		
	}
	
	//Lista todos os Sites por status = "ACT"
	public static List<SiteModel> listAllSitesbyStatus(String status) throws SQLException 
	{

		try (Connection con = TransactionFacade.beginTransaction()){
			
			SiteStatments siteStm = new SiteStatments(con);
			
			try {
				
				List<SiteModel> list = new ArrayList<>();
				
				//statments
				list = siteStm.SelectSiteByStatus(status);
				
				TransactionFacade.commitTransaction(con);
				
				return list;
				
			}catch (Exception e)
			{
				TransactionFacade.roolbackTransaction(con);
				
				e.printStackTrace();
				
				return null;
			}
					
		} 
	}
	
		
}

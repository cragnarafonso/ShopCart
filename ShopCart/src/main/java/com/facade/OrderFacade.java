package com.facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteClientOrderModel;
import com.database.statments.SiteClientOrderStatments;

public class OrderFacade {


	public OrderFacade() {

	}

	//Lista todas as Orders executados pelos clientes
	public static List<SiteClientOrderModel> listAllItemsForView(int siteId, int clientId) throws SQLException {

		
		try (Connection con = TransactionFacade.beginTransaction()){
			
			SiteClientOrderStatments siteClientOrderStm = new SiteClientOrderStatments(con);

			try {

				List<SiteClientOrderModel> listorders = new ArrayList<>();
	
				listorders = siteClientOrderStm.SelectAllBySiteIdAndClientId(siteId, clientId);
	
				TransactionFacade.commitTransaction(con);
	
				return listorders;
	
			} catch (Exception e) {
	
				TransactionFacade.roolbackTransaction(con);
	
				e.printStackTrace();
	
				return null;
	
			} 
		}
	}

	
	//Adiciona uma nova Order, correspondendo a uma possivel compra
	public static boolean addSiteClientOrder(SiteClientOrderModel siteClientOrder) throws SQLException {

		try (Connection con = TransactionFacade.beginTransaction()){
			
			SiteClientOrderStatments siteClientOrderStm = new SiteClientOrderStatments(con);

			try {

				siteClientOrderStm.InsertSiteClientOrder(siteClientOrder);
	
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

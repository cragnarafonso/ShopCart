package com.database.statments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.models.SiteModel;

public class SiteStatments extends Statments{
	

	public SiteStatments(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}
	

	//Lista todos os Sites pelo estado "ACT"
	public List<SiteModel> SelectSiteByStatus(String status) throws SQLException
	{
		
		String query = "SELECT * FROM stm_site WHERE site_st=? order by site_id ASC;"; //$NON-NLS-1$
		
		try (PreparedStatement pStmt = this.connection.prepareStatement(query)){
			
			List<SiteModel> list = new ArrayList<>();
			
			pStmt.setString(1, status);
			
			try(ResultSet rst = pStmt.executeQuery();)
			{
				while(rst.next()) {
				
					SiteModel site = new SiteModel();
					
					site.setSite_id(rst.getInt("site_id")); //$NON-NLS-1$
					site.setSite_ds(rst.getString("site_ds")); //$NON-NLS-1$
					site.setSite_tp(rst.getString("site_tp")); //$NON-NLS-1$
					site.setSite_st(rst.getString("site_st")); //$NON-NLS-1$
					site.setSite_jd(rst.getString("site_jd")); //$NON-NLS-1$
					
					list.add(site);
				}
			}
			
			return list;
			
		}
		
	}
	
	
}

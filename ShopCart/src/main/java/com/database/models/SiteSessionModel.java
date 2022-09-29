package com.database.models;

import java.util.Date;

public class SiteSessionModel {
	
	private int site_id;
	private String session_tk;
	private int client_id;
	private Date created_dt;
	private Date updated_dt;
	
	public SiteSessionModel() {

	}

	public SiteSessionModel(int site_id, String session_tk, int client_id, Date created_dt, Date updated_dt) {
		this.site_id = site_id;
		this.session_tk = session_tk;
		this.client_id = client_id;
		this.created_dt = created_dt;
		this.updated_dt = updated_dt;
	}

	public int getSite_id() {
		return this.site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	public String getSession_tk() {
		return this.session_tk;
	}

	public void setSession_tk(String session_tk) {
		this.session_tk = session_tk;
	}

	public int getClient_id() {
		return this.client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public Date getCreated_dt() {
		return this.created_dt;
	}

	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}

	public Date getUpdated_dt() {
		return this.updated_dt;
	}

	public void setUpdated_dt(Date updated_dt) {
		this.updated_dt = updated_dt;
	}


}

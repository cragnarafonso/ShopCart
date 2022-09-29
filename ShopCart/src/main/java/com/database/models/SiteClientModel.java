package com.database.models;

public class SiteClientModel {
	
	private int site_id;
	private int client_id;
	private String client_ds;
	
	
	public SiteClientModel() {
	}


	public SiteClientModel(int site_id, int client_id, String client_ds) {
		super();
		this.site_id = site_id;
		this.client_id = client_id;
		this.client_ds = client_ds;
	}


	public int getSite_id() {
		return this.site_id;
	}


	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}


	public int getClient_id() {
		return this.client_id;
	}


	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}


	public String getClient_ds() {
		return this.client_ds;
	}


	public void setClient_ds(String client_ds) {
		this.client_ds = client_ds;
	}


}

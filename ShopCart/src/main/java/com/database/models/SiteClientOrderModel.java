package com.database.models;

public class SiteClientOrderModel {
	
	private int order_id;
	private int site_id;
	private int client_id;
	private String order_jd;
	
	
	public SiteClientOrderModel() {
	}


	public SiteClientOrderModel(int order_id, int site_id, int client_id, String order_jd) {
		super();
		this.order_id = order_id;
		this.site_id = site_id;
		this.client_id = client_id;
		this.order_jd = order_jd;
	}


	public int getOrder_id() {
		return this.order_id;
	}


	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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


	public String getOrder_jd() {
		return this.order_jd;
	}


	public void setOrder_jd(String order_jd) {
		this.order_jd = order_jd;
	}


}

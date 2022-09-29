package com.database.models;

public class SiteCartModel {
	
	private int site_id;
	private String cart_tk;
	private String session_tk;
	private int client_id;

	public SiteCartModel() {

	}

	public SiteCartModel(int site_id, String cart_tk, String session_tk, int client_id) {
		this.site_id = site_id;
		this.cart_tk = cart_tk;
		this.session_tk = session_tk;
		this.client_id = client_id;
	}

	public int getSite_id() {
		return this.site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

	public String getCart_tk() {
		return this.cart_tk;
	}

	public void setCart_tk(String cart_tk) {
		this.cart_tk = cart_tk;
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


}

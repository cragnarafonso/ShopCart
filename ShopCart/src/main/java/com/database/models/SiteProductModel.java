package com.database.models;

public class SiteProductModel {
	
	int site_id;
	int product_id;
	
	
	public SiteProductModel() {
	}


	public SiteProductModel(int site_id, int product_id) {
		this.site_id = site_id;
		this.product_id = product_id;
	}


	public int getSite_id() {
		return this.site_id;
	}


	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}


	public int getProduct_id() {
		return this.product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

}

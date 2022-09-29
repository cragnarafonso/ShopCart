package com.database.models;

import java.util.Date;

public class SiteProductPriceModel extends  SiteProductModel {
	
	private String currency_cd;
	private Date start_dt;
	private double price_vl;
	private String price_st;
	
	
	public SiteProductPriceModel() {

	}


	public SiteProductPriceModel(String currency_cd, Date start_dt, double price_vl, String price_st) {
		this.currency_cd = currency_cd;
		this.start_dt = start_dt;
		this.price_vl = price_vl;
		this.price_st = price_st;
	}


	public String getCurrency_cd() {
		return this.currency_cd;
	}


	public void setCurrency_cd(String currency_cd) {
		this.currency_cd = currency_cd;
	}


	public Date getStart_dt() {
		return this.start_dt;
	}


	public void setStart_dt(Date start_dt) {
		this.start_dt = start_dt;
	}


	public double getPrice_vl() {
		return this.price_vl;
	}


	public void setPrice_vl(double price_vl) {
		this.price_vl = price_vl;
	}


	public String getPrice_st() {
		return this.price_st;
	}


	public void setPrice_st(String price_st) {
		this.price_st = price_st;
	}

}

package com.database.models;

public class SiteModel {
	
	private int site_id;
	private String site_ds;
	private String site_tp;
	private String site_st;
	private String site_jd;
	
	
	public SiteModel() {
	}


	public SiteModel(int site_id, String site_ds, String site_tp, String site_st, String site_jd) {
		this.site_id = site_id;
		this.site_ds = site_ds;
		this.site_tp = site_tp;
		this.site_st = site_st;
		this.site_jd = site_jd;
	}


	public int getSite_id() {
		return this.site_id;
	}


	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}


	public String getSite_ds() {
		return this.site_ds;
	}


	public void setSite_ds(String site_ds) {
		this.site_ds = site_ds;
	}


	public String getSite_tp() {
		return this.site_tp;
	}


	public void setSite_tp(String site_tp) {
		this.site_tp = site_tp;
	}


	public String getSite_st() {
		return this.site_st;
	}


	public void setSite_st(String site_st) {
		this.site_st = site_st;
	}


	public String getSite_jd() {
		return this.site_jd;
	}


	public void setSite_jd(String site_jd) {
		this.site_jd = site_jd;
	}

}

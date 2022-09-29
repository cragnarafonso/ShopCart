package com.database.models;

public class SiteProductCodeModel extends SiteProductModel {
	
	private String product_code_cd;
	private String product_code_tp;
	private String product_code_jd;
	
	
	public SiteProductCodeModel() {
	}


	public SiteProductCodeModel(String product_code_cd, String product_code_tp, String product_code_jd) {

		this.product_code_cd = product_code_cd;
		this.product_code_tp = product_code_tp;
		this.product_code_jd = product_code_jd;
	}


	public String getProduct_code_cd() {
		return this.product_code_cd;
	}


	public void setProduct_code_cd(String product_code_cd) {
		this.product_code_cd = product_code_cd;
	}


	public String getProduct_code_tp() {
		return this.product_code_tp;
	}


	public void setProduct_code_tp(String product_code_tp) {
		this.product_code_tp = product_code_tp;
	}


	public String getProduct_code_jd() {
		return this.product_code_jd;
	}


	public void setProduct_code_jd(String product_code_jd) {
		this.product_code_jd = product_code_jd;
	}


}

package com.database.models;

public class ProductModel {
	
	private int product_id;
	private String product_ds;
	private String product_st;
	private String product_jd;
	private String product_image_cd;
	
	
	public ProductModel() {
	}


	public ProductModel(int product_id, String product_ds, String product_st, String product_jd,
			String product_image_cd) {
		super();
		this.product_id = product_id;
		this.product_ds = product_ds;
		this.product_st = product_st;
		this.product_jd = product_jd;
		this.product_image_cd = product_image_cd;
	}


	public int getProduct_id() {
		return this.product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getProduct_ds() {
		return this.product_ds;
	}


	public void setProduct_ds(String product_ds) {
		this.product_ds = product_ds;
	}


	public String getProduct_st() {
		return this.product_st;
	}


	public void setProduct_st(String product_st) {
		this.product_st = product_st;
	}


	public String getProduct_jd() {
		return this.product_jd;
	}


	public void setProduct_jd(String product_jd) {
		this.product_jd = product_jd;
	}


	public String getProduct_image_cd() {
		return this.product_image_cd;
	}


	public void setProduct_image_cd(String product_image_cd) {
		this.product_image_cd = product_image_cd;
	}

}

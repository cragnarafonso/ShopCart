package com.database.models;

public class SiteCartProductModel extends SiteProductModel{
	
	private String cart_tk;
	private int order_qt;
	private String units_tp;
	
	public SiteCartProductModel() {

	}

	public SiteCartProductModel(String cart_tk, int order_qt, String units_tp) {

		this.cart_tk = cart_tk;
		this.order_qt = order_qt;
		this.units_tp = units_tp;
	}

	public String getCart_tk() {
		return this.cart_tk;
	}

	public void setCart_tk(String cart_tk) {
		this.cart_tk = cart_tk;
	}

	public int getOrder_qt() {
		return this.order_qt;
	}

	public void setOrder_qt(int order_qt) {
		this.order_qt = order_qt;
	}

	public String getUnits_tp() {
		return this.units_tp;
	}

	public void setUnits_tp(String units_tp) {
		this.units_tp = units_tp;
	}

}

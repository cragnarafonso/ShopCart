package com.database.models;

public class ItemProduct {
	
	private SiteProductModel siteproduct;
	private ProductModel product;
	private SiteProductCodeModel siteProducCode;
	private SiteProductPriceModel siteProductPrice;
	private SiteCartProductModel siteCartProduct;
	
	public ItemProduct() {
	}

	public ItemProduct(SiteProductModel siteproduct, ProductModel product, SiteProductCodeModel siteProducCode,
			SiteProductPriceModel siteProductPrice, SiteCartProductModel siteCartProduct) {
		this.siteproduct = siteproduct;
		this.product = product;
		this.siteProducCode = siteProducCode;
		this.siteProductPrice = siteProductPrice;
		this.setSiteCartProduct(siteCartProduct);
	}

	public SiteProductModel getSiteproduct() {
		return this.siteproduct;
	}

	public void setSiteproduct(SiteProductModel siteproduct) {
		this.siteproduct = siteproduct;
	}

	public ProductModel getProduct() {
		return this.product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public SiteProductCodeModel getSiteProducCode() {
		return this.siteProducCode;
	}

	public void setSiteProducCode(SiteProductCodeModel siteProducCode) {
		this.siteProducCode = siteProducCode;
	}

	public SiteProductPriceModel getSiteProductPrice() {
		return this.siteProductPrice;
	}

	public void setSiteProductPrice(SiteProductPriceModel siteProductPrice) {
		this.siteProductPrice = siteProductPrice;
	}

		public SiteCartProductModel getSiteCartProduct() {
		return this.siteCartProduct;
	}

	public void setSiteCartProduct(SiteCartProductModel siteCartProduct) {
		this.siteCartProduct = siteCartProduct;
	}

}

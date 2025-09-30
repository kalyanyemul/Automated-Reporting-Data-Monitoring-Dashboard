package com.company.reports.entity;

import java.time.LocalDate;

public class Report01SOLD {
    
	private Long ID;
	private String NSECode;
	private String StockName;
	private LocalDate BuyingDate;
	private Double BuyingPrice;
	private LocalDate SellingDate;
	private Double SellingPrice;
	private Long Quantity;
	private Double BuyingValue;
	private Double SellingValue;
	private Double RealizedProfit;
	private String TaxCategory;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNSECode() {
		return NSECode;
	}
	public void setNSECode(String nSECode) {
		NSECode = nSECode;
	}
	public String getStockName() {
		return StockName;
	}
	public void setStockName(String stockName) {
		StockName = stockName;
	}
	public LocalDate getBuyingDate() {
		return BuyingDate;
	}
	public void setBuyingDate(LocalDate buyingDate) {
		BuyingDate = buyingDate;
	}
	public Double getBuyingPrice() {
		return BuyingPrice;
	}
	public void setBuyingPrice(Double buyingPrice) {
		BuyingPrice = buyingPrice;
	}
	public LocalDate getSellingDate() {
		return SellingDate;
	}
	public void setSellingDate(LocalDate sellingDate) {
		SellingDate = sellingDate;
	}
	public Double getSellingPrice() {
		return SellingPrice;
	}
	public void setSellingPrice(Double sellingPrice) {
		SellingPrice = sellingPrice;
	}
	public Long getQuantity() {
		return Quantity;
	}
	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}
	public Double getBuyingValue() {
		return BuyingValue;
	}
	public void setBuyingValue(Double buyingValue) {
		BuyingValue = buyingValue;
	}
	public Double getSellingValue() {
		return SellingValue;
	}
	public void setSellingValue(Double sellingValue) {
		SellingValue = sellingValue;
	}
	public Double getRealizedProfit() {
		return RealizedProfit;
	}
	public void setRealizedProfit(Double realizedProfit) {
		RealizedProfit = realizedProfit;
	}
	public String getTaxCategory() {
		return TaxCategory;
	}
	public void setTaxCategory(String taxCategory) {
		TaxCategory = taxCategory;
	}

	
}

package com.company.reports.entity;

import java.time.LocalDate;

public class Report02HOLD {
	
	private Long ID;
	private String NSECode;
	private String StockName;
	private LocalDate BuyingDate;
	private Double BuyingPrice;
	private Long Quantity;
	private Double CurrentPrice;
	private Double InvestmentValue;
	private Double CurrentValue;
	private Double UnrealizedProfit;
	
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
	public Long getQuantity() {
		return Quantity;
	}
	public void setQuantity(Long quantity) {
		Quantity = quantity;
	}
	public Double getCurrentPrice() {
		return CurrentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		CurrentPrice = currentPrice;
	}
	public Double getInvestmentValue() {
		return InvestmentValue;
	}
	public void setInvestmentValue(Double investmentValue) {
		InvestmentValue = investmentValue;
	}
	public Double getCurrentValue() {
		return CurrentValue;
	}
	public void setCurrentValue(Double currentValue) {
		CurrentValue = currentValue;
	}
	public Double getUnrealizedProfit() {
		return UnrealizedProfit;
	}
	public void setUnrealizedProfit(Double unrealizedProfit) {
		UnrealizedProfit = unrealizedProfit;
	}
}

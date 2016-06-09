package model;

public class Transaction {
	int quantityMoved;
	int stockId;
	int transactionType;
	String date;
	public Transaction(){}
	public Transaction(int quantityMoved, int stockId, int transactionType,String date) {
		this.quantityMoved = quantityMoved;
		this.stockId = stockId;
		this.transactionType = transactionType;
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getQuantityMoved() {
		return quantityMoved;
	}
	public void setQuantityMoved(int quantityMoved) {
		this.quantityMoved = quantityMoved;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	
}

package model;

public class Transaction {
	int quantityMoved;
	int stockId;
	int transactionType;
	
	public Transaction(int quantityMoved, int stockId, int transactionType) {
		super();
		this.quantityMoved = quantityMoved;
		this.stockId = stockId;
		this.transactionType = transactionType;
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

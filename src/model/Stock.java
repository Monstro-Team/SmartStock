package model;

public class Stock {
	
	private int idProduct;
	private int quantity;
	private String supplier;
	private float price;
	private int id;
	private boolean modified;
	
	public Stock() {

	}
	
	public Stock(int idProduct, int quantity, String supplier, float price, boolean modified) {
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.supplier = supplier;
		this.modified = modified;
		this.price = price;
	}
	
	public Stock(int idStock, int idProduct, int quantity, String supplier,
			float price, boolean modified) {
		this.modified = modified;
		this.id = idStock;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.supplier = supplier;
		this.price = price;
	}
	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public int getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getSupplier() {
		return supplier;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

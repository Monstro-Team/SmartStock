package model;

public class Product {
	private String name;
	private String description;
	private String location;
	private int quantity_min;
	private int quantity;
	private String supplier;
	
	public Product(String name, String description, String location, int quantity_min, int quantity, String supplier) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.quantity_min = quantity_min;
		this.quantity = quantity;
		this.supplier = supplier;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getQuantity_min() {
		return quantity_min;
	}
	
	public void setQuantity_min(int quantity_min) {
		this.quantity_min = quantity_min;
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
}

package model;

public class Product {
	private int id;
	private String name;
	private String description;
	private String location;
	private int quantityMin;
	private int quantity;
	private String supplier;
	private float price;
	
	public Product() {
		
	}
	
	public Product(String name, String description, String location, int quantityMin, int quantity, String supplier, float price) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.quantityMin = quantityMin;
		this.quantity = quantity;
		this.supplier = supplier;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public int getQuantityMin() {
		return quantityMin;
	}
	
	public void setQuantityMin(int quantityMin) {
		this.quantityMin = quantityMin;
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
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getPrice() {
		return price;
	}
}

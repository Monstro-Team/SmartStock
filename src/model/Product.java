package model;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private String location;
	private int quantityMin;
	
	public Product() {
		
	}
	
	public Product(String name, String description, String location,
			int quantityMin) {
		this.name = name;
		this.description = description;
		this.location = location;
		this.quantityMin = quantityMin;
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
}

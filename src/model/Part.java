package model;



public class Part extends Product {
	
	private String brand;
	private String modelCar;
	
	public Part() {
		super();
	}
	
	public Part(String name, String description, String location,
			int quantityMin, String brand, String modelCar) {
		super(name,description,location, quantityMin);
		this.brand = brand;
		this.modelCar = modelCar;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}
}

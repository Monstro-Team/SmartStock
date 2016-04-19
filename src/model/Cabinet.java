package model;

public class Cabinet {
	private String name;
	private int quantityDrawer;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cabinet(String name, int quantityDrawer) {
		super();
		this.name = name;
		this.quantityDrawer = quantityDrawer;
	}
	
	public Cabinet() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantityDrawer() {
		return quantityDrawer;
	}
	public void setQuantityDrawer(int quantityDrawer) {
		this.quantityDrawer = quantityDrawer;
	}
	
}

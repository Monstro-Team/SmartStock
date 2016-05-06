package model;

public class Cabinet {
	
	private int id;
	private String name;
	private int drawer;
	
	public Cabinet() {

	}

	public Cabinet(String name, int drawer) {
		this.name = name;
		this.drawer = drawer;
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
	
	public int getDrawer() {
		return drawer;
	}
	
	public void setDrawer(int Drawer) {
		this.drawer = Drawer;
	}
}

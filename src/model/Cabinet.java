package model;

public class Cabinet {
	private String name;
	private int drawer;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cabinet(String name, int drawer) {
		super();
		this.name = name;
		this.drawer = drawer;
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
	public int getDrawer() {
		return drawer;
	}
	public void setDrawer(int Drawer) {
		this.drawer = Drawer;
	}
	
}

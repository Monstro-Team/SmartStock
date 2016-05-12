package model;

public class Provider {
	
	private int id;
	private String conpany;
	private String salesman;
	private String salesmanPhone;
	
	public Provider() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getConpany() {
		return conpany;
	}

	public void setConpany(String conpany) {
		this.conpany = conpany;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanPhone() {
		return salesmanPhone;
	}

	public void setSalesmanPhone(String salesmanPhone) {
		this.salesmanPhone = salesmanPhone;
	}
}

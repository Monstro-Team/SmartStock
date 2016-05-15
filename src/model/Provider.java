package model;

public class Provider {
	
	private int id;
	private String company;
	private String salesman;
	private String salesmanPhone;
	
	public Provider(String company, String salesman, String salesmanPhone) {
		super();
		this.company = company;
		this.salesman = salesman;
		this.salesmanPhone = salesmanPhone;
	}
	public Provider() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

package stream.domain;

public class Traders {

	private String city;
	private String name;
	public Traders(String city, String name) {
		super();
		this.city = city;
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

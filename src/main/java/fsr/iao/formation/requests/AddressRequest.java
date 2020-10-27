package fsr.iao.formation.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressRequest {
	private long id;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 2, message = "ce champs doit prend 2 au min !")
	private String city;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 2, message = "ce champs doit prend 2 au min !")
	private String country;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 10, message = "ce champs doit prend 10 au min !")
	private String street;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 3, message = "ce champs doit prend 3 au min !")
	private String postal;
	
	private String type;
	private UserRequest user;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserRequest getUser() {
		return user;
	}
	public void setUser(UserRequest user) {
		this.user = user;
	}
	
}

package fsr.iao.formation.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserRequest {
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 3, message = "ce champs doit prend 3 au min !")
	private String firstName;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 3, message = "ce champs doit prend 3 au min !")
	private String lastName;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Email
	private String email;
	@NotNull(message = "ce champs ne doit pas etre null !")
	@Size(min = 3, message = "ce champs doit prend 3 au min !")
	@Size(max = 12, message = "ce champs doit prend 12 au max !")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "ce mot de passe doit avoir des lettres en Maj et en Min ")
	private String password;
	private ContactRequest contact;
	private List<AddressRequest> addresses;
    private Boolean admin;
	public List<AddressRequest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ContactRequest getContact() {
		return contact;
	}

	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}

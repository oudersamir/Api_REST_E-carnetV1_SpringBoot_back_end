package fsr.iao.formation.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5160839486057003920L;
	 private long id;
	 private String userId;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String password;
	 private String encryptePassword;
	 private String emailVerificationToken;
	 private List<AddressDto>  addresses;
	 private ContactDto contact;
	 private Boolean admin;

	 public List<AddressDto> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}
	private boolean emailVerificationStatus=false;
	 
	 public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getEncryptePassword() {
		return encryptePassword;
	}
	public void setEncryptePassword(String encryptePassword) {
		this.encryptePassword = encryptePassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
}

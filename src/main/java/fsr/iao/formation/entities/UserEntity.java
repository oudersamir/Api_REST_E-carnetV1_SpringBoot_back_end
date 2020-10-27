package fsr.iao.formation.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "users")
public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7701395859999214507L;
	@Id
	@GeneratedValue
	private long id;

	private String userId;

	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(nullable = false, length = 50)
	private String lastName;
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	@Column(nullable = false, length = 50)
	private String password;
	@Column(nullable = false)
	private String encryptePassword;
	@Column(nullable = true, length = 50)
	private String emailVerificationToken;
	@Column(nullable = false)
	private boolean emailVerificationStatus = false;
	@OneToMany(mappedBy = "user",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AddressEntity> addresses;
	@OneToOne(mappedBy = "user",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private ContactEntity contact;
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
	private Set<GroupEntity> groups = new HashSet<>();
	@Column(nullable=true)
	private Boolean admin;

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

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public ContactEntity getContact() {
		return contact;
	}

	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}

	public Set<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupEntity> groups) {
		this.groups = groups;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}

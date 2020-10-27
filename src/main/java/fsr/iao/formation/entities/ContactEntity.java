package fsr.iao.formation.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity(name = "contacts")
public class ContactEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 288412780655344771L;
	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	@Column(length = 30)
	private String contactId;
	@NotBlank
	@Column(length = 30)
	private String skype;
	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@NotBlank
	@Column(length = 30)
	private String mobile;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}

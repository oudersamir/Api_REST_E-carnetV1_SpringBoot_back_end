package fsr.iao.formation.shared.dto;

import java.io.Serializable;

public class ContactDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5375815863724691114L;
	private long id;
	private String contactId;
	private String skype;
	private String mobile;
	private UserDto user;
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
}

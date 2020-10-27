package fsr.iao.formation.requests;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContactRequest {

	private String mobile;
	private String skype;
	private UserRequest user;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public UserRequest getUser() {
		return user;
	}

	public void setUser(UserRequest user) {
		this.user = user;
	}

}

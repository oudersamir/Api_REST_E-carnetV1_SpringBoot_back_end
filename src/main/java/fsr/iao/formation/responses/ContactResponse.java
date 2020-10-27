package fsr.iao.formation.responses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContactResponse {
	private String mobile;
	private String skype;
	private String  contactId;

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

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}


	
}

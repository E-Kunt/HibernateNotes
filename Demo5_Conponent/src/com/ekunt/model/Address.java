package com.ekunt.model;

/**
 * 此类作为Student类的组件属性
 * @author E-Kunt
 *
 */
public class Address {
	
	private String postCode;
	private String location;
	private String phone;
	
	public Address() {
	}

	public Address(String postCode, String location, String phone) {
		super();
		this.postCode = postCode;
		this.location = location;
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}

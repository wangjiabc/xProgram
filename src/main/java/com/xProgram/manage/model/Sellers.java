package com.xProgram.manage.model;

import java.util.Date;

public class Sellers {
	private String campusAdmin;
	
	private String password;
	
	private Short type;
	
	private Integer cityId;
	
	private Integer telePhone;
	
	private String address;
	
	private Date lastLoginDate;
	
	private Date registerTime;
	
	private Integer campusId;

	public String getCampusAdmin() {
		return campusAdmin;
	}

	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = campusAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getCampusId() {
		return campusId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}

	public Integer getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(Integer telePhone) {
		this.telePhone = telePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


}

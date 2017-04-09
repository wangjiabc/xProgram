package com.xProgram.manage.model;

import java.util.Date;

public class Campus {
	private Integer campusId;
	
	private short type;
	
	private String campusAdmin;
	
	private Integer telePhone;
	
	private String address;
	
	private Date loginTime;
	
	private String loginTime2;

	private String campusName;

	private Integer cityId;
	
	private String customService;

	public Integer getCampusId() {
		return campusId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName == null ? null : campusName.trim();
	}

	public Integer getCityId() {
		return cityId;
	}

	public String getCustomService() {
		return customService;
	}

	public void setCustomService(String customService) {
		this.customService = customService;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCampusAdmin() {
		return campusAdmin;
	}

	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = campusAdmin;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginTime2() {
		try{
			loginTime2 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(loginTime);
			}catch (Exception e) {
				// TODO: handle exception
				loginTime2=null;
			}
			return loginTime2;
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

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}
	
}
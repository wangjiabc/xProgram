package com.xProgram.manage.model;

import java.util.Date;

public class NewGoods {

	private Integer foodId;
	
	private Integer campusId;
	
	private String campusAdmin;
	
	private String named;

	private Float price;

	private Integer foodCount;
	
	private Integer foodAllCount;
	
	private Date modifyTime;
	
	private String modifyTime2;

	private Short status;
	
	private Short start;

	private String imgUrl;
	
	private String foodFlag;
	
	private Integer categoryId;

	private Float primeCost;

	
	private String campusName;

	
	private String message;

	
	public void setImgUrl(String imgUrl) {
		this.imgUrl=imgUrl;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId=campusId;
	}
	
	public Integer getCampusId() {
		return campusId;
	}
	
	public String getcampusAdmin() {
		return campusAdmin;
	}

	public void setcampusAdmin(String campusAdmin) {
		this.campusAdmin = campusAdmin == null ? null : campusAdmin.trim();
	}
	
	public String getName() {
		return named;
	}

	public void setName(String name) {
		this.named = name == null ? null : name.trim();
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime2() {
		try{
		  modifyTime2 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(modifyTime);
		}catch (Exception e) {
			// TODO: handle exception
		  modifyTime2=null;
		}
		return modifyTime2;
	}


	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}



	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Float getPrimeCost() {
		return primeCost;
	}

	public String getFoodFlag() {
		return foodFlag;
	}

	public void setFoodFlag(String foodFlag) {
		this.foodFlag = foodFlag == null ? null : foodFlag.trim();
	}
	
	public void setPrimeCost(Float primeCost) {
		this.primeCost = primeCost;
	}

	public Integer getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	public void setFoodAllCount(Integer foodAllCount) {
		this.foodAllCount=foodAllCount;
	}
	
	public Integer getFoodAllCount() {
		return foodAllCount;
	}
	
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Short getStart() {
		return start;
	}

	public void setStart(Short start) {
		this.start = start;
	}
}

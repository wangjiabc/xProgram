package com.xProgram.manage.model;


public class Purchase {

	private Integer foodId;
	
	private String openId;
	
	private Integer campusId;
	
	private String campusAdmin;
	
	private Integer foodCount;
	
	private Integer amount;
	
	private Float price;
	
	private Float consume;
	
	private Short PayState;
	
	private Short used;

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getCampusId() {
		return campusId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}

	public String getCampusAdmin() {
		return campusAdmin;
	}

	public void setCampusAdmin(String campusAdmin) {
		this.campusAdmin = campusAdmin;
	}

	public Integer getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Short getPayState() {
		return PayState;
	}

	public void setPayState(Short payState) {
		PayState = payState;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Short getUsed() {
		return used;
	}

	public void setUsed(Short used) {
		this.used = used;
	}

	public Float getConsume() {
		return consume;
	}

	public void setConsume(Float consume) {
		this.consume = consume;
	}
	
	
}

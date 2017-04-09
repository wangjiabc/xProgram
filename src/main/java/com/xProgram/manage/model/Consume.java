package com.xProgram.manage.model;

import java.util.Date;

public class Consume {

    private Integer foodId;

	private Integer id;
     
	private String openId;
	
	private Integer campusId;
	
	private String campusAdmin;
	
	private Integer foodCount;
	
	private Integer amount;
	
	private Float price;
	
	private Float consume;
	
	private Float totalAmount;
	
	private Short PayState;
	
	private Short used;
	
	private Short useType;

	private Date createTime;
	
	private String createTime2;
	
	private Date consumeTime;
	
	private String consumeTime2;
	
	private String named;
	
	private Integer status;
	
	private Integer start;
	
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

	public String getName() {
		return named;
	}

	public void setName(String named) {
		this.named = named;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateTime2() {
		try{
		 createTime2 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(createTime);
		}catch (Exception e) {
			// TODO: handle exception
			createTime2=null;
		}
		return createTime2;
	}
	
	//�����ֻ�����������
	public String getcreateMobileTime() {
		try{
		 return new java.text.SimpleDateFormat("MM.dd HH:mm").format(createTime);
		}catch (Exception e) {
			// TODO: handle exception
		  return null;
		}
	}

	public Float getConsume() {
		return consume;
	}

	public void setConsume(Float consume) {
		this.consume = consume;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Short getUseType() {
		return useType;
	}

	public void setUseType(Short useType) {
		this.useType = useType;
	}

	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}
	
	public String getConsumeTime2() {
		try{
		 consumeTime2 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(consumeTime);
		}catch (Exception e) {
			// TODO: handle exception
			consumeTime2=null;
		}
		return consumeTime2;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

}

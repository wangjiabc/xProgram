package com.xProgram.manage.model;

import java.util.Date;

public class Order {

  private Integer id;
	
   private Integer foodId;
	
	private String openId;
	
	private Integer campusId;
	
	private Integer foodCount;
	
	private Integer amount;
	
     private Date createTime;
	
	private String createTime2;
	
    private Date consumeTime;
	
	private String consumeTime2;
	
	private Float price;
	
	private Float consume;
	
	private Float primeCost;
	
	private Short PayState;
	
	private Short used;
	
	private Short settle;
	
	private Short settleStatus;

	private String name;
	
	private String nickName;

    private String headImgUrl;
	
    private String pic_url;
    
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


	public Integer getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(Integer foodCount) {
		this.foodCount = foodCount;
	}

	public Float getPrice() {
		return price;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName() {
		this.nickName = nickName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

    public String getImgUrl() {    	
        return "<img src="+headImgUrl+" width='25px' height='25px'>";
        
    }

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
				e.printStackTrace();
				createTime2=null;
			}
			return createTime2;
	}

	public Float getPrimeCost() {
		return primeCost;
	}

	public void setPrimeCost(Float primeCost) {
		this.primeCost = primeCost;
	}

	public Float getConsume() {
		return consume;
	}

	public void setConsume(Float consume) {
		this.consume = consume;
	}

	public Short getSettle() {
		return settle;
	}

	public void setSettle(Short settle) {
		this.settle = settle;
	}

	public Short getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(Short settleStatus) {
		this.settleStatus = settleStatus;
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

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}

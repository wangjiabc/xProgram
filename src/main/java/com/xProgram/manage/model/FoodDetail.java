package com.xProgram.manage.model;


public class FoodDetail {

	private Integer foodId;
	
	private Integer campusId;
	
	private String useTime;
	
    private String besPoke;
    
    private String rule;
    
    private String cue;
    
    private String name;
    
    private String foodUrl;
    
    private Float price;
    
    private String imgUrl;
     
    public void setCampusId(Integer campusId) {
		this.campusId=campusId;
	}
	
	public Integer getCampusId() {
		return campusId;
	}
    
    public String getImgUrl() {
		return imgUrl;
	}
    
    public void setImgUrl(String imgUrl) {
		this.imgUrl=imgUrl;
	}
    
    public void setPrice(Float price) {
		this.price=price;
	}
    
    public Float getPrice() {
		return price;
	}
    
    public void setFoodUrl(String foodUrl) {
		this.foodUrl=foodUrl;
	}
    
    public String getFoodUrl() {
		return "<a href='"+foodUrl+"'> http://"+foodUrl+"</a>";
	}
    
    public String getUrl() {
		return foodUrl;
	}
    
    public void setFoodId(Integer foodId) {
		this.foodId=foodId;
	}
    
    public Integer getFoodId() {
		return foodId;
	}
	
    public void setUseTime(String useTime) {
		this.useTime=useTime;
	}
    
    public String getUseTime() {
		return useTime;
	}
    
    public void setRule(String rule) {
		this.rule=rule;
	}
    
    public String getRule() {
		return rule;
	}
    
    public void setCue(String cue) {
		this.cue=cue;
	}
    
    public String getCue() {
		return cue;
	}
    
    public void setBesPoke(String besPoke) {
		this.besPoke=besPoke;
	}
    
    public String getBesPoke() {
		return besPoke;
	}
    
    public void setName(String name) {
		this.name=name;
	}
    
    public String getName() {
		return name;
	}
}

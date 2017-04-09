package com.xProgram.manage.model;

import java.util.Date;
import java.util.List;

import javax.swing.text.rtf.RTFEditorKit;

import org.slf4j.Logger;

public class Food {

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

	private Date startTime;
	
	private String startTime2;
	
	private Date endTime;
	
	private String endTime2;
	
	private String type;
	
	public Food(){
		
	}

	public Food(Integer campusId2,String campusAdmin2,String campusName2,String name2,
			Float price2, String imgUrl2, 
			String foodFlag2, Integer foodCount2, 
			Integer foodAllCount2,
			Integer categoryId2, Float primeCost2) {

		price=price2;
		
		if(name2!=null){
			named=name2;
		}

		if(campusId2!=null){
			campusId=campusId2;
		}
		
		if(campusAdmin2!=null){
			campusAdmin=campusAdmin2;
		}
		
		if(campusName2!=null){
			campusName=campusName2;
		}
		
		
		if(imgUrl2!=null){
			imgUrl=imgUrl2;
		}
		
		
		if(foodFlag2!=null){
			foodFlag=foodFlag2;
		}
		
		if(categoryId2!=null){
			categoryId=Integer.valueOf(categoryId2);
		}
		
		if(primeCost2!=null){
			primeCost=Float.valueOf(primeCost2);
		}

		if(foodCount2!=null){
			foodCount=foodAllCount2;
		}
		
		if(foodAllCount2!=null){
			foodAllCount=foodAllCount2;
		}
		
		modifyTime=new Date();
		
	}

	
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
	
	public void setStartTime(Date startTime) {
		this.startTime=startTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public String getStartTime2() {
		try{
	      startTime2 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(startTime);
		}catch (Exception e) {
			// TODO: handle exception
		   startTime2=null;
		}
		return startTime2;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime=endTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public String getEndTime2() {
		try{
		  endTime2 = new java.text.SimpleDateFormat("yyyy��MM��dd�� HH:mm").format(endTime);
		}catch (Exception e) {
			// TODO: handle exception
		  endTime2=null;
		}
		return endTime2;
	}

	public Short getStart() {
		return start;
	}

	public void setStart(Short start) {
		this.start = start;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
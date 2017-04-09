package com.xProgram.manage.model;

import java.util.List;

public class FoodCategory {
    private Integer categoryId;
       
    private String category;

    private String imgUrl;
    
    private Short isOpen;

    private List<FoodCategory> child;
    
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }


	public List<FoodCategory> getChild() {
		return child;
	}

	public void setChild(List<FoodCategory> child) {
		this.child = child;
	}

	public Short getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Short isOpen) {
		this.isOpen = isOpen;
	}
}
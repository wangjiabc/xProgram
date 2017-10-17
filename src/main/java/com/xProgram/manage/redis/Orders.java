package com.xProgram.manage.redis;

import java.io.Serializable;  
import java.util.Date;  
  
public class Orders implements Serializable{  
    private static final long serialVersionUID = 1L;  
      
    private String id;  
    private String name;  
    private double price;  
    private Date createDate;  
      
    public Orders(String id,double price,Date createDate){  
        this.id = id;  
        this.price = price;  
        this.createDate = createDate;  
    }  
      
    public Orders(){  
          
    }  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  

    public double getPrice() {  
        return price;  
    }  
    public void setPrice(double price) {  
        this.price = price;  
    }  
    public Date getCreateDate() {  
        return createDate;  
    }  
    public void setCreateDate(Date createDate) {  
        this.createDate = createDate;  
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}  
}  

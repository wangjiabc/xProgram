package com.xProgram.manage.model;


public class Affair {

	private Integer id;
	
	private String val;
	
	private Integer amount;
	
	
	public void setAmount(Integer amount) {
		this.amount=amount;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setVal(String val) {
		this.val=val;
	}
	
	public String getVal() {
		return val;
	}
}

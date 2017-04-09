package com.xProgram.manage.model;


public class Users {
	
	private int id;
	
	private String openId;

	private float allConsume;
	
	private String language;
	
	private String groupId;
	
	private String city;
	
	private String country;
	
	private String remark;
	
	private Float totalAmount;
	
	private String province;
			
	private int campusId;

    private String nickname;

    private String headImgUrl;
        
    private short sex;
    
    private String name;
    
    private String phone;
    
    private String address;
    
    private Short defaultAddress;
    
    private String rank;
    
    public Integer getCampusId() {
		return campusId;
	}

	public void setCampusId(Integer campusId) {
		this.campusId = campusId;
	}
    
    
    public String getOpenId() {
		return openId;
	}
    
    public void setOpenId(String openId) {
		this.openId=openId;
	}

	public float getAllConsume() {
		return allConsume;
	}

	public void setAllConsume(float allConsume) {
		this.allConsume = allConsume;
	}
    
    public Integer getId() {
		return id;
	}
    
    public void setId(Integer id) {
		this.id=id;
	}
    

	public Users() {
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }
    
    public String getImgUrl() {
    	
        return "<img src="+headImgUrl+" width='25px' height='25px'>";
        
    }

    public void setImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

	public Short getSex() {
		return sex;
	}

	public void setSex(Short sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(Short defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
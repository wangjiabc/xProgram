package com.xProgram.manage.model;


import java.util.Date;

/**
* ����: SNSUserInfo </br>
* ����: ͨ����ҳ��Ȩ��ȡ���û���Ϣ </br>
* ������Ա�� souvc </br>
* ����ʱ�䣺  2015-11-27 </br>
* �����汾��V1.0  </br>
 */
public class SNSUserInfo {
	//���ں�ID
	private int campusId;
    // �û���ʶ
    private String openId;
    // �û��ǳ�
    private String nickname;
    // �Ա���1�����ԣ�2��Ů�ԣ�0��δ֪��
    private short sex;
    //�û�������
    private String language;
    // ����
    private String country;
    // ʡ��
    private String province;
    // ����
    private String city;
    // �û�ͷ������
    private String headImgUrl;
    //�û���עʱ�䣬Ϊʱ����
    private Date subscribeTime;
    //ֻ�����û������ںŰ󶨵�΢�ſ���ƽ̨�ʺź󣬲Ż����ָ��ֶΡ�
    private String unionid;

    
    //error����
    private boolean errorCode=false;


    public String getUnionid() {
		return unionid;
	}
    
    public void setUnionid(String unionid) {
		this.unionid=unionid;
	}
    
    
    public Date getSubScribeTime() {
		return subscribeTime;
	}
    
    public void setSubScribeTime(Date subscribeTime) {
		this.subscribeTime=subscribeTime;
	}
    
    public String getLanguage() {
		return language;
	}
    
    public void setLanguage(String language) {
		this.language=language;
	}
    
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getImgUrl() {         //����д�����ݿ�
		return headImgUrl;
	}
    
    public void setErrorCode(boolean errorCode) {
		this.errorCode=errorCode;
	}
    
    public boolean getErrorCode() {
		return errorCode;
	}

	public int getCampusId() {
		return campusId;
	}

	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

}
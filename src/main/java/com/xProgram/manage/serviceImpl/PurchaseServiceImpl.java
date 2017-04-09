package com.xProgram.manage.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xProgram.manage.mapper.FoodMapper;
import com.xProgram.manage.mapper.PurchaseMapper;
import com.xProgram.manage.service.PurchaseService;

@Service("/PurchaseService")
public class PurchaseServiceImpl implements PurchaseService{

	private FoodMapper foodMapper;
	
    private PurchaseMapper purchaseMapper;
	
	@Autowired
	public void setFoodMapper(FoodMapper foodMapper) {
		this.foodMapper = foodMapper;
	}
	
	@Autowired
	public void setPurchaseMapper(PurchaseMapper purchaseMapper){
		this.purchaseMapper=purchaseMapper;
	}
	
	//��ѯ����
	private int SelectCount(Integer foodId){
		int foodCount=foodMapper.getFoodCount(foodId);
		return foodCount;		
	}
	
	//������
	private int Decrease(Integer amount,Integer foodId){
		int flag=purchaseMapper.decreaseById(amount, foodId);
		return flag;		
	}
	
	//д���û���������

	private int InsertIntoOders(Map<String, Object> paramMap) {
		
		int flag=purchaseMapper.insertIntoOrders(paramMap);
		
		return flag;
	}
	
	
	@Override
	public int Purchased(String openId,Integer campusId,String campusAdmin,Integer foodId,
			Integer amount,Float consume) {
		// TODO Auto-generated method stub
		
	  try{
		//���治�㣬����ȡ��
		if(SelectCount(foodId)<amount){
			return 0;
		}
		
		//����������,����ȡ��
		if(Decrease(amount,foodId)==0){
			return 0;
		}
		
		Date createTime=new Date();
		
		Map<String,Object> paramMap=new HashMap<>();
		
		paramMap.put("openId", openId);
		paramMap.put("campusId", campusId);
		paramMap.put("campusAdmin", campusAdmin);
		paramMap.put("foodId", foodId);
		paramMap.put("amount", amount);
		paramMap.put("createTime", createTime);
		paramMap.put("consume", consume);
		
		System.out.println("campusId="+campusId+" openId"+openId+" foodid="+foodId+" amout="+amount+
				"  createtime="+createTime+"  consume="+consume);
		 
		//����д�����ݿ�
		int flag=InsertIntoOders(paramMap);
		
		return flag;
		
	   }catch (Exception e) {
			// TODO: handle exception
		    e.printStackTrace();
		    //�ع����ύ����
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		   //����ʧ��	
			return 2;
	  }
	}

}

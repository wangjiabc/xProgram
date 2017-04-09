package com.xProgram.manage.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xProgram.manage.mapper.ConsumeMapper;
import com.xProgram.manage.model.Consume;
import com.xProgram.manage.service.ConsumeService;

@Service("/ConsumeService")
public class ConsumeServiceImpl implements ConsumeService{
	
	private ConsumeMapper consumeMapper;

	public ConsumeMapper getConsumeMapper() {
		return consumeMapper;
	}

	@Autowired
	public void setConsumeMapper(ConsumeMapper consumeMapper) {
		this.consumeMapper = consumeMapper;
	}

	
	@Override
	public Consume selectConsumeByOpenId(Integer campusId,String campusAdmin, String openId) {
		// TODO Auto-generated method stub
		return consumeMapper.selectConsumeInfoByOpenId(campusId,campusAdmin,openId);
	}

	@Override
	public Consume selectConsumeInfoById(Integer id) {
		// TODO Auto-generated method stub
		return consumeMapper.selectConsumeInfoById(id);
	}
	
	@Override
	public List<Consume> selectAllConsumeByOpenId(Integer campusId, String openId) {
		// TODO Auto-generated method stub
		return consumeMapper.selectAllConsumeByOpenId(campusId, openId);
	}

	@Override
	public int upOrderUseType(Integer id) {
		// TODO Auto-generated method stub
		return consumeMapper.upOrderUseType(id);
	}

	//��ѯ�����Ƿ���ʹ��
	@Override
	public int selectConsumeUsedById(Integer id) {
		// TODO Auto-generated method stub
		return consumeMapper.selectConsumeUsedById(id);
	}

	//������Ʒ��ʹ��
	@Override
	public int upOrderUsedById(Integer id,Integer campusId,String openId) {
		// TODO Auto-generated method stub
		Date consumeTime=new Date();
		Consume consumeInfo=consumeMapper.selectConsumeInfoById(id);
		float consume=consumeInfo.getConsume();
		try{
		 //���¶�����ʹ��
		 int t1=consumeMapper.upOrderUsedById(id, consumeTime);    
		 /*
		  * �����û���ʹ�ý���
		  * consume�ǵ�����Ʒ�����ѽ���
		  */			 
		 int t2=consumeMapper.upUsersConsumeByOpenId(campusId,openId,consume);
		 if(t1==0||t2==0){
			 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 return 0;
		 }
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
		return 1;
	}

	
}

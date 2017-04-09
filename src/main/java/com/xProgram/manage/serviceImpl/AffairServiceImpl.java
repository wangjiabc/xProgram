package com.xProgram.manage.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.xProgram.manage.mapper.AffairMapper;
import com.xProgram.manage.model.Affair;
import com.xProgram.manage.service.AffairService;

@Service("affairService")
public class AffairServiceImpl implements AffairService{
	private static final Logger logger = LoggerFactory.getLogger("Affair");
	private AffairMapper affairMapper;
	
	@Autowired
    public void setTestMapper(AffairMapper testMapper) {
		this.affairMapper = testMapper;
	}
	
	public AffairMapper geTestMapper() {
		return affairMapper;
	}
	
	@Override
	public int insertTest(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int i=affairMapper.insertintoTest(paramMap);
		return i;
	}

	@Override
	public int insertTest2(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int i=affairMapper.insertintoTest2(paramMap);
		return i;
	}

	@Override
	public int  insertTest3(Map<String, Object> paramMap3) {
		// TODO Auto-generated method stub
		int i=affairMapper.insertintoTest3(paramMap3);
		return i;
	}

	public int  insertAll(Map<String, Object> paramMap) {
		 Map<String, Object> paramMap3 = new HashMap<String, Object>();
			
			Affair a=new Affair();
		    a=selectTest3();
		    int i;
		    
		    int j=a.getAmount();
		if(j>0){    
		    i=insertTest(paramMap);
		    i=insertTest2(paramMap);
		    
			paramMap3.put("amount", j+1);
		    
	        i=insertTest3(paramMap3);
	        if(i==0){                 //��������0��ȡ�����׻ع����ύ����
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        }
	        logger.info("i=-===="+i);
		}else{
			i=0;
		}
		 return i;
	}

	public Affair selectTest3() {
		// TODO Auto-generated method stub
		return affairMapper.selectTest3();
	}

	@Override
	public int insert1(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap3 = new HashMap<String, Object>();
		
		Affair a=new Affair();
	    a=selectTest3();
	    int i;
	    
	    int j=a.getAmount();
	if(j>0){ 
	    i=insertTest(paramMap);
	    
		paramMap3.put("amount", j+1);
	    
        i=insertTest3(paramMap3);
        if(i==0){                 //��������0��ȡ�����׻ع����ύ����
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        logger.info("i=-===="+i);
	}else{
		i=0;
	}
	
	
	 return i;
	}

	@Override
	public int insert2(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap3 = new HashMap<String, Object>();
		
		Affair a=new Affair();
	    a=selectTest3();
	    int i;
	    
	    int j=a.getAmount();
	if(j>0){    
	    i=insertTest2(paramMap);
	    
		paramMap3.put("amount", j+1);
	    
        i=insertTest3(paramMap3);
        if(i==0){                 //��������0��ȡ�����׻ع����ύ����
        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        logger.info("i=-===="+i);
	}else{
		i=0;
	}
	 return i;
	}

}

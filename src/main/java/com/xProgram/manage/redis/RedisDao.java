package com.xProgram.manage.redis;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;  
import org.springframework.data.redis.core.RedisTemplate;  
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

  
@Repository  
public class RedisDao {  
  
    @Autowired  
    private RedisTemplate<String,Object> redisTemplate;  
  
    public void save(Orders order) {  
        /*redisTemplate.opsForList(); 
        redisTemplate.opsForSet(); 
        redisTemplate.opsForHash()*/  
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();       
        valueOper.set(order.getId(), order);  
    }  
  
    
    public Object read(String id) {  
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();  
        return valueOper.get(id);
    }  
  
    public Set getAll(){
    	ListOperations<String, Object> listOper= redisTemplate.opsForList();
        RedisOperations<String, Object> redisOper=listOper.getOperations();
        Set set=redisOper.keys("*");
    	return set;
    }
    
    public void delete(String id) {  
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();  
        RedisOperations<String,Object>  RedisOperations  = valueOper.getOperations();  
        RedisOperations.delete(id);  
    }  
    
    public void deleteAll() {
    	ListOperations<String, Object> listOper= redisTemplate.opsForList();
        RedisOperations<String, Object> redisOper=listOper.getOperations();
        Set set=redisOper.keys("*");
		redisOper.delete(set);
	}
}  
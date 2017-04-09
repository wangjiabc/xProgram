package com.xProgram.manage.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xProgram.manage.model.Consume;

public interface ConsumeMapper {

  Consume selectConsumeInfoByOpenId(@Param(value="campusId")Integer campusId,@Param(value="campusAdmin")String campusAdmin,@Param(value="openId")String openId);

  Consume selectConsumeInfoById(@Param(value="id")Integer id);
  
  int selectConsumeUsedById(@Param(value="id")Integer id);
  
  List<Consume> selectAllConsumeByOpenId(@Param(value="campusId")Integer campusId,@Param(value="openId")String openId);

  //������Ʒʹ��״̬Ϊ�ѹ���
  int upOrderUseType(@Param(value="id")Integer id);
  
  //������Ʒ��ʹ��
  int upOrderUsedById(@Param(value="id")Integer id,@Param(value="consumeTime")Date consumeTime);

  int upUsersConsumeByOpenId(@Param(value="campusId")Integer campusId,@Param(value="openId")String openId,@Param(value="consume")float consume);
}

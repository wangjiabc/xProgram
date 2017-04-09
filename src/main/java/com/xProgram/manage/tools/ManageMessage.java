package com.xProgram.manage.tools;

/*
 * ���ڸ���̨������Ϣ
 */
public class ManageMessage {

	
	private static long startTime;
	
	public static boolean setMessage(String campusAdmin,Integer campusId,String openId,
			Integer id) {
		
		
		startTime=System.currentTimeMillis();
		
		String admin="{'campusId':'"+campusId+"', 'openId':'"+openId+"',"
				   + " 'startTime':'"+startTime+"',"
		           + " 'id':'"+id+"'}" ;         //id�Ƕ�����
		
		String user="{'openId':'"+openId+"','campusAdmin':'"+campusAdmin+"', "
				   + " 'startTime':'"+startTime+"',"
				   + " 'status':'0'," 
		           + " 'id':'"+id+"'}" ;         //id�Ƕ�����
		
		try{

		
		 return true;
		 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean setAffirmMessage(String campusAdmin,String openId,
			Integer status,Integer id){
		
		
		String user="{'openId':'"+openId+"','campusAdmin':'"+campusAdmin+"', "
				   + " 'startTime':'"+startTime+"',"
				   + " 'status':'"+status+"'," 
		           + " 'id':'"+id+"'}" ;         //id�Ƕ�����
		
		try{

			
			 return true;
			 
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		
	}
	
}

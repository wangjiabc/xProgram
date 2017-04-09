package com.xProgram.manage.tools;

import javax.crypto.Cipher;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.SecretKeySpec;  
  
import sun.misc.BASE64Decoder;  
import com.alibaba.fastjson.JSONObject;  
  
/** 
 * ��΢��С�����û��������ݵĽ���ʾ������. 
 *  
 * @ClassName WXBizDataCrypt 
 * @Description TODO(������һ�仰����������������) 
 * @author tf 
 * @Date 2016��11��19�� ����2:56:36 
 * @version 1.0.0 
 */  

public class WXBizDataCrypt {  
    /* 
     * �����õ�Key ������26����ĸ���������� �˴�ʹ��AES-128-CBC����ģʽ��key��ҪΪ16λ�� 
     */  
    private static WXBizDataCrypt instance = null;  
  
    private WXBizDataCrypt() {  
  
    }  
  
    public static WXBizDataCrypt getInstance() {  
        if (instance == null)  
            instance = new WXBizDataCrypt();  
        return instance;  
    }  
  
    /** 
     * ���ڹٷ��������ݣ�encryptData������˵�����£� �������ݽ����㷨 �ӿ������漰�������ݣ���wx.getUserInfo���е� 
     * openId ��unionId �����ӿڵ��������ݽ���������Щ�������ݡ�����������Ҫ��ȡ�������ݣ���Ҫ�Խӿڷ��صļ�������( 
     * encryptedData )���жԳƽ��ܡ� �����㷨���£� �Գƽ���ʹ�õ��㷨Ϊ AES-128-CBC�����ݲ���PKCS#7���䡣 
     * �Գƽ��ܵ�Ŀ������Ϊ Base64_Decode(encryptedData), �Գƽ�����Կ aeskey = 
     * Base64_Decode(session_key), aeskey ��16�ֽ� �Գƽ����㷨��ʼ���� iv �������ݽӿ��з��ء� 
     *  
     * @Description (TODO������һ�仰������������������) 
     * @param encryptedData 
     *            �������� 
     * @param sessionKey 
     *            С������¼sessionKey 
     * @param iv 
     *            �����㷨��ʼ���� iv �������ݽӿ��з��ء� 
     * @param encodingFormat 
     *            ������ʽĬ��UTF-8 
     * @return ���ؽ��ܺ����ַ��� 
     * @throws Exception 
     */  
    public String decrypt(String encryptedData, String sessionKey, String iv, String encodingFormat) throws Exception {  
        try {
        	System.out.println("encrydata="+encryptedData+"    sesskey="+sessionKey+"   iv="+iv);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");  
            BASE64Decoder base64Decoder = new BASE64Decoder();  
            byte[] _encryptedData = base64Decoder.decodeBuffer(encryptedData);  
            byte[] _sessionKey = base64Decoder.decodeBuffer(sessionKey);  
            byte[] _iv = base64Decoder.decodeBuffer(iv);  
            SecretKeySpec secretKeySpec = new SecretKeySpec(_sessionKey, "AES");  
            IvParameterSpec ivParameterSpec = new IvParameterSpec(_iv);  
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);  
            byte[] original = cipher.doFinal(_encryptedData);  
            byte[] bytes = PKCS7Encoder.decode(original);  
            String originalString = new String(bytes, "UTF-8");  
            return originalString;  
        } catch (Exception ex) {  
            return null;  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        // ��Ҫ���ܵ��ִ�  
        String appId = "wxec269fa0eec3fa83";  
    	String appSecret="d61c8f9057a8308ce6d6f57ca69c90e8";
    	
    	String jsCode="013AEpmK0Gqnu32JflpK05uumK0AEpmn";
    	
    	JSONObject aString=AdvancedUtil.getAccessToken(appId, appSecret, jsCode);
    	
    	if(aString!=null)
    	System.out.println("astring="+aString.getString("session_key"));
    	
        String sessionKey = aString.getString("session_key");
  
        String encryptedData ="DxM6Wy2dL9PU6vSL/17UdlitMB7zXTaAl70FDbo4mI0pB5rv0viEyjoJq+T4oU6AU4opplpPp6wHZZi0yuSvILVchEQkK9PX6QivFx/OYiy1Ll1mTYOVS7gZJRdTOPFOwAOIbSkTuTI5VTh1IpwsafwaZeadm9NEZyL9Pc6cRsn736Hvjng2ztUXXihfGuPOoxFNVy3n3fBQHutkm3EYnzG5Z0LZe4z4y2Cvkh1DKsOthJjmxg9HBcWH/+4/Bam9EIGdVshEktGph6+KBPAhGeijgDCeKzFQTmNmf4ZaeVYcVvyYneDRdvWi7Zb5uDiSkP2fSyMGVBg9ZFC8CKs3MBXTXsvuytdpFiVaRXCElKaNi9Y/LQJdDcJEIZs781FhaouodK84Cd6p4iuCEkSlIiQ/zEviA94gjau65NTL7TliDMdo8uzZAmwNkUXmqI1PsJdxDhh/5e6XHL91DRKMgQ==";
        
        		String iv = "Gfm7/j1MEOeEc+Ndp4G1EQ=="; 
        
        String deString = WXBizDataCrypt.getInstance().decrypt(encryptedData, sessionKey, iv, "utf-8");  
        System.out.println(deString);  
        JSONObject jsonObject = JSONObject.parseObject(deString);  
        System.out.println(jsonObject);  
    }  
}  

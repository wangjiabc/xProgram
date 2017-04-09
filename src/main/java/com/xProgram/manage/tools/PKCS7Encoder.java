package com.xProgram.manage.tools;
/** 

 * �Թ���ƽ̨���͸������˺ŵ���Ϣ�ӽ���ʾ������. 
 *  
 * @copyright Copyright (c) 1998-2014 Tencent Inc. 
 */  
  
  
import java.nio.charset.Charset;  
import java.util.Arrays;  
  
/** 
 * �ṩ����PKCS7�㷨�ļӽ��ܽӿ�. 
 */  
class PKCS7Encoder {  
    static Charset CHARSET = Charset.forName("utf-8");  
    static int BLOCK_SIZE = 32;  
  
    /** 
     * ���ö����Ľ��в�λ�������ֽ�. 
     *  
     * @param count ��Ҫ�������䲹λ�����������ֽڸ��� 
     * @return �����õ��ֽ����� 
     */  
    static byte[] encode(int count) {  
        // ������Ҫ������λ��  
        int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);  
        if (amountToPad == 0) {  
            amountToPad = BLOCK_SIZE;  
        }  
        // ���ò�λ���õ��ַ�  
        char padChr = chr(amountToPad);  
        String tmp = new String();  
        for (int index = 0; index < amountToPad; index++) {  
            tmp += padChr;  
        }  
        return tmp.getBytes(CHARSET);  
    }  
  
    /** 
     * ɾ�����ܺ����ĵĲ�λ�ַ� 
     *  
     * @param decrypted ���ܺ������� 
     * @return ɾ����λ�ַ��������� 
     */  
    static byte[] decode(byte[] decrypted) {  
        int pad = (int) decrypted[decrypted.length - 1];  
        if (pad < 1 || pad > 32) {  
            pad = 0;  
        }  
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);  
    }  
  
    /** 
     * ������ת����ASCII����Ӧ���ַ������ڶ����Ľ��в��� 
     *  
     * @param a ��Ҫת�������� 
     * @return ת���õ����ַ� 
     */  
    static char chr(int a) {  
        byte target = (byte) (a & 0xFF);  
        return (char) target;  
    }  
  
}  
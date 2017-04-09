package com.xProgram.manage.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSONObject;

public class Curl {
 public static JSONObject conn(String requestUrl) {

	StringBuffer document = new StringBuffer();
	try{
	URL url = new URL(requestUrl);//Զ��url
	URLConnection conn = url.openConnection();
	BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	String line = null;
	while ((line = reader.readLine()) != null)
	document.append(line + " ");
	reader.close();
	}catch(MalformedURLException e) {
	e.printStackTrace();
	}catch(IOException e){
	e.printStackTrace();
	}
	String xml = document.toString();//����ֵ
	System.out.println(xml);
	JSONObject jsonObject=JSONObject.parseObject(xml);
	System.out.println(jsonObject);
	
	return jsonObject;
 }
}

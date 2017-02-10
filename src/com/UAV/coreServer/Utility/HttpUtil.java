package com.UAV.coreServer.Utility;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

  	
	
	
   public static String sendHttpRequest(final String address)throws Exception{
	   
	   HttpURLConnection conn=null;
	   try{
		   
		   URL url=new URL(address);
		   conn =(HttpURLConnection)url.openConnection();
		   conn.setRequestMethod("GET");
		   conn.setConnectTimeout(8000);
		   conn.setReadTimeout(8000);
		   InputStream in= conn.getInputStream();
		   BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		   StringBuilder response=new StringBuilder();
		   String line;
		   while((line=reader.readLine())!=null){
			   response.append(line);
		   }
		   return response.toString();
		   
	   }catch(Exception e){
		   e.printStackTrace();
		   throw new Exception("Net error!");
	   }
   }
}

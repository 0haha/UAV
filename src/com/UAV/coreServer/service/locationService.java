package com.UAV.coreServer.service;

import java.net.URLEncoder;

import com.UAV.coreServer.Utility.HttpUtil;
import com.UAV.coreServer.Utility.constant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class locationService {
	
	public static String getAddress(String origin, String destination){
		StringBuilder pathBuilder=new StringBuilder(constant.ip);
		pathBuilder.append("?&output=json&origins=");
		pathBuilder.append(URLEncoder.encode(origin));
		pathBuilder.append("&destinations=");
		pathBuilder.append(URLEncoder.encode(destination));
		pathBuilder.append("&ak=");
		pathBuilder.append(constant.ak);
		
		return pathBuilder.toString();
		
		
	}
	
	public static double getDistance(String path)throws Exception{
		
		String response=HttpUtil.sendHttpRequest(path);
		System.out.println(response);
		JSONObject location = JSONObject.fromObject(response);
		JSONArray elements = location.getJSONObject("result").getJSONArray("elements");
		JSONObject destination = elements.getJSONObject(0);
		String value = destination.getJSONObject("distance").getString("value");
		return Double.parseDouble(value);
		
		
	}
	
}

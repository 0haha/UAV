package com.UAV.coreServer.Utility;

import java.lang.reflect.Field;

public class entityAnalysis {
  
	private static entityAnalysis entityAnalysis=null;
	private entityAnalysis(){}
	public static entityAnalysis getEntityAnalysis(){
		if(entityAnalysis==null)
			entityAnalysis=new entityAnalysis();
		return entityAnalysis;
	}
	
	public String getClassName(Object o){
		String name=o.getClass().getName();
		return name;
	}
	public String[] getAttributesName(Object o)throws IllegalArgumentException, IllegalAccessException{
		Field[] fields=o.getClass().getDeclaredFields();
		String[] AttributeName=new String[fields.length];
		for(int i=0;i<fields.length;i++){
			AttributeName[i]=fields[i].getName();
		}
		return AttributeName;
	}
	
	public String[] getAttributeType(Object o)throws IllegalArgumentException, IllegalAccessException{
		Field[] fields=o.getClass().getDeclaredFields();
		String[] AttributeType=new String[fields.length];
		for(int i=0;i<fields.length;i++){
			AttributeType[i]=fields[i].getType().getName();
		}
		return AttributeType;
	}
}

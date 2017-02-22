package com.UAV.coreServer.Utility;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public  class YeekuXmlApplication implements ApplicationContext{
   
	private Map<String,Object> objPool = Collections.synchronizedMap(new HashMap<String,Object>());
	private Document doc;
	private Element root;
	public YeekuXmlApplication()throws Exception{
		File directory = new File("YeekuXmlApplication.java");//设定为当前文件夹 
		String path=null;
		try{ 
		    //To get the file path about the project which is not about web 
			//String pathtmp=ContextTest.class.getClass().getResource("/").getPath();
		    //System.out.println(pathtmp);
		    // path=pathtmp.replace("build/classes/", constant.beanXmlRePath);
		    // System.out.println(path);
			
			String pathtmp=Thread.currentThread().getContextClassLoader().getResource("/").getPath();
			System.out.println(pathtmp);
			path=pathtmp.replace("classes/", "bean.xml");
			
		}catch(Exception e){e.printStackTrace();throw new Exception("error");} 
		SAXReader reader=new SAXReader();
		doc=reader.read(new File(path));
		root = doc.getRootElement();
		initPool();
		initProp();
	}
	public Object getBean(String name) throws Exception{
		Object target= objPool.get(name);
		//for the singleton bean
		if(target.getClass()!=String.class)
		{
			return target;
		}
		else
		{
			String clazz = (String)target;
			return Class.forName(clazz).newInstance();
		}
	}
	private void initPool()throws Exception{
		
		for(Object obj:root.elements()){
			Element beanEle = (Element)obj;
			String beanId = beanEle.attributeValue("id");
			String beanClazz = beanEle.attributeValue("class");
			String beanScope = beanEle.attributeValue("scope");
			if(beanScope == null || beanScope.equals("singleton")){
				objPool.put(beanId, Class.forName(beanClazz).newInstance());
			}
			else{
				objPool.put(beanId, beanClazz);
			}
					
		}
	}
	private void initProp()throws Exception{
		
		for(Object obj: root.elements()){
			Element beanEle = (Element)obj;
			String beanId = beanEle.attributeValue("id");
			String beanScope = beanEle.attributeValue("scope");
			if(beanScope==null||beanScope.equals("singleton")){
				Object bean = objPool.get(beanId);
				for(Object prop:beanEle.elements()){
					Element propEle =(Element)prop;
					String propName=propEle.attributeValue("name");
					String propValue=propEle.attributeValue("value");
					String propRef=propEle.attributeValue("ref");
					String propNameCamelize=propName.substring(0,1).toUpperCase()+propName.substring(1,propName.length());
				    if(propValue!=null&&propValue.length()>0){
				    	Method setter=bean.getClass().getMethod("set"+propNameCamelize, String.class);
				    	setter.invoke(bean, propValue);
				    }
				    if(propRef!=null&&propRef.length()>0){
				    	Object target=objPool.get(propRef);
				    	if(target==null){
				    		System.out.println("Wrong reference!");
				    		throw new Exception("target null");
				    		
				    	}
				    	Method setter =null;
				    	for(Class superInterface:target.getClass().getInterfaces()){
				    		try{
				    			setter=bean.getClass().getMethod("set"+propNameCamelize, superInterface);
				    			break;
				    		}
				    		catch(NoSuchMethodException ex){
				    			continue;
				    		}
				    	}
				    	if(setter==null){
				    		setter=bean.getClass().getMethod("set"+propNameCamelize,target.getClass());
				    	}
				    	setter.invoke(bean, target);
				    }
				}
			}
		}
		
	}
	
	
	
	
	
}

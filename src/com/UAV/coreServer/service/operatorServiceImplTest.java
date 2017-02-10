package com.UAV.coreServer.service;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.UAV.coreServer.Utility.ApplicationContext;
import com.UAV.coreServer.Utility.YeekuXmlApplication;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public class operatorServiceImplTest {

	@Test
	public void test(){
		Operator o=new Operator();
		o.setAddress("广东省-揭阳市-揭西县-棉湖镇-花园村");
		o.setBirthday("1991-01-22");
		o.setName("李泰白");
		o.setPhone("15975181202");
		o.setRange(800000.200);
		o.setSex(1);
		o.setWorktime(3);
		
		Quotation q=new Quotation();
		q.setOrderId(1);
		q.setUsrId(3);
		q.setPrice(2.5);
		
		try{
			ApplicationContext ctx=new YeekuXmlApplication();
			operatorService os=(operatorService) ctx.getBean("operatorService");
			//int id=os.validRegister(o);
			//System.out.println("id:"+id);
			List<Order> orders=new LinkedList<Order>();
			//orders=os.getOrder(1);
			
			//orders=os.getModifyOrder(1);
			//List<Integer> ids=new LinkedList<Integer>();
			//ids=os.getDelOrderId(1);
			/*for(int tmp:ids){
			System.out.println(tmp);
		}*/
			int i=os.quote(q);
			System.out.println(i);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

package com.UAV.coreServer.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public class orderServiceImplTest {

	@Test
	public void test() {
		Order o=new Order();
		o.setArea("2000");
		o.setCrops("蔬菜");
		try{
		o.setDeadline(new SimpleDateFormat("yyyy-MM-dd").parse("2017-06-09"));
		}catch(Exception e){
			e.printStackTrace();
		}
		o.setName("章杰");
		o.setNote("及时完成植保任务！");
		o.setPhone("18826077297");
		o.setPrice(15.2);
		o.setRegion("广东省-揭阳市-普宁市-赤岗镇-埔下村");
	    o.setWorkTime("2017-05-22");
	    orderServiceImpl oi=new orderServiceImpl();
	    try{
	 //   int i=oi.releaseOrders(o, 1);
	 //   System.out.println("id:"+i);
	 //   int j=oi.modifyOrders(o, 5);
	 //     int j=oi.deleteOrders(1, 1);
	       
	 //   	System.out.println("result:"+j);
	 //   	List<Quotation> qs=oi.getQuotation(1);
	 /*   	for(Quotation q:qs){
	    		System.out.println(q.getPrice());
	    	}*/
	    	List<Operator> operators=oi.getOerator(1);
	    	for(Operator tmp:operators){
	    		System.out.println(tmp.getName());
	    	}
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	}

}

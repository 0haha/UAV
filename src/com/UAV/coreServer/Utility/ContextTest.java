package com.UAV.coreServer.Utility;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.UAV.coreServer.DAO.operatorDao;
import com.UAV.coreServer.DAO.orderDao;
import com.UAV.coreServer.DAO.quotationDao;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public class ContextTest {

	@Test
	public void test() throws Exception{
		
		ApplicationContext ctx=new YeekuXmlApplication();
		operatorDao o1=(operatorDao) ctx.getBean("operatorDao");
		orderDao o2=(orderDao) ctx.getBean("orderDao");
		quotationDao o3=(quotationDao) ctx.getBean("quotationDao");
	    Operator o11=o1.get(9);
	    System.out.println("operator:"+o11.getName());
	    Order o22=o2.get(1);
	    System.out.println("order:"+o22.getName());
	    Quotation o33=o3.get(4);
	    System.out.println("quotation:"+o33.getPrice());
	}

}

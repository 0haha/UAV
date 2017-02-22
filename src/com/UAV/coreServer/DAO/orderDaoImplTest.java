package com.UAV.coreServer.DAO;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.UAV.coreServer.Utility.constant;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;

public class orderDaoImplTest {

	@Test
	public void test() {
		Order o=new Order();
		o.setArea("5");
		o.setCrops("wheat");
		o.setDeadline(new Date(1994-01-27));
		o.setName("dada");
		o.setNote("Please remember to do something great!");
		o.setPhone("18826077397");
		o.setPrice(3.3);
		o.setRegion("guangdongjieyang");
		o.setWorkTime("5");
		orderDaoImpl oai=new orderDaoImpl();
		try{
			//test save()
			//System.out.println(oai.save(o));
				
		    //test findAll()		
			/*Iterator it=oai.findAll().iterator();
			while(it.hasNext()){
				System.out.println(((Order)it.next()).getName());
			}*/
				
			//test update()
			//oai.update(2, o);
			
			//test delete()
			//oai.delete(o);
			//oai.delete(3);
			
			//test change
			oai.changeStatus(3, constant.commitedStatus);
			List<Order> os=oai.findByStatus(constant.commitedStatus);
			Iterator it2=os.iterator();
			while(it2.hasNext()){
				//System.out.println("Status:"+((Order)it2.next()).getStatus());
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error");
			}
	}

}

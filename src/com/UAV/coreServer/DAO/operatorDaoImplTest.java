package com.UAV.coreServer.DAO;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.UAV.coreServer.model.Operator;

public class operatorDaoImplTest {

	@Test
	public void test() {
		
		Operator operator=new Operator();
		operator.setAddress("Guangdongjieyang");
		operator.setBirthday("2014-01-27");
		operator.setName("haha");
		operator.setPhone("18826077397");
		operator.setRange(5.6);
		operator.setSex(1);
		operator.setWorktime(5);
		operatorDaoImpl o=new operatorDaoImpl();
		try{
		//test sava()
		//assertEquals(1,o.save(operator));
			
	    //test findAll()		
		/*Iterator it=o.findAll().iterator();
		while(it.hasNext()){
			System.out.println(((Operator)it.next()).getName());
		}*/
			
		//test update()
		//o.update(2, operator);
		
		//test delete()
		o.delete(operator);
		//o.delete(3);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("error");
		}
	}

}

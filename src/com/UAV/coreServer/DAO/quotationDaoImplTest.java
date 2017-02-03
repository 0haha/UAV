package com.UAV.coreServer.DAO;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.UAV.coreServer.model.Quotation;

public class quotationDaoImplTest {

	@Test
	public void test() {
		Quotation o=new Quotation();
		o.setOrderId(1);
		o.setPrice(3.5);
		o.setUsrId(9);
		quotationDaoImpl q=new quotationDaoImpl();
		try{
		//q.save(o);
		q.delete(1);
		q.update(3,o);
		q.delete(o);
		List<Quotation> quotations=q.findByOrderId(1);
		Iterator it=quotations.iterator();
		while(it.hasNext()){
			System.out.println(((Quotation)it.next()).getPrice());
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}

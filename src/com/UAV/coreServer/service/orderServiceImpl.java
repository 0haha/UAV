package com.UAV.coreServer.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.OrderedMapIterator;

import com.UAV.coreServer.DAO.operatorDao;
import com.UAV.coreServer.DAO.operatorDaoImpl;
import com.UAV.coreServer.DAO.orderDao;
import com.UAV.coreServer.DAO.orderDaoImpl;
import com.UAV.coreServer.DAO.quotationDao;
import com.UAV.coreServer.DAO.quotationDaoImpl;
import com.UAV.coreServer.Utility.constant;
import com.UAV.coreServer.db.UAVcoreServerDB;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public class orderServiceImpl implements orderService{
private orderDao ordao;
private quotationDao qudao;
private operatorDao opdao;
private UAVcoreServerDB coreserver;


public orderDao getOrdao() {
	return ordao;
}

public void setOrdao(orderDao ordao) {
	this.ordao = ordao;
}

public quotationDao getQudao() {
	return qudao;
}

public void setQudao(quotationDao qudao) {
	this.qudao = qudao;
}

public operatorDao getOpdao() {
	return opdao;
}

public void setOpdao(operatorDao opdao) {
	this.opdao = opdao;
}

public UAVcoreServerDB getCoreserver() {
	return coreserver;
}

public void setCoreserver(UAVcoreServerDB coreserver) {
	this.coreserver = coreserver;
}

	@Override
	public int releaseOrders(Order order, int useId) {
		// TODO Auto-generated method stub
		int orderid=0;
		try{
		orderid=ordao.save(order);
		coreserver.addOrderUser(orderid, useId);
		return orderid;
		}catch(Exception e){
			e.printStackTrace();
		    return orderid;	
		}
		
	}

	@Override
	public int modifyOrders(Order order, int orderId){
		// TODO Auto-generated method stub
		try{
		coreserver.changeStatus(orderId, constant.modifiedStatus);
		ordao.update(orderId, order);
		return 1;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int deleteOrders(int orderId, int useId) {
		// TODO Auto-generated method stub
		try{
			ordao.delete(orderId);
			coreserver.changeStatus(orderId, constant.deletedStatus);
			coreserver.clearDeletedOrderAndUsr(orderId, orderId);
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public List<Quotation> getQuotation(int usrId) throws Exception {
		// TODO Auto-generated method stub
		List<Quotation> quotations=new LinkedList<Quotation>();
		List<Integer> orderids=new LinkedList<Integer>();
		List<Integer> operators=new LinkedList<Integer>();
		orderids=coreserver.getOrderIdsByUsrId(usrId);
		for(int tmp:orderids){
	
		List<Quotation> quotationsTmp=qudao.findByOrderId(tmp);
		quotations.addAll(quotationsTmp);
		
		}
		
		return quotations;
	}

	@Override
	public List<Operator> getOerator(int usrId) throws Exception {
		// TODO Auto-generated method stub
		List<Operator> operators=new LinkedList<Operator>();
		//List<Integer> orderids=new LinkedList<Integer>();
		List<Quotation> quotations=new LinkedList<Quotation>();
		//orderids=coreserver.getOrderIdsByUsrId(usrId);
		
		quotations=this.getQuotation(usrId);
		for(Quotation tmp:quotations){
	    
		
		Operator o=opdao.get(tmp.getUsrId());
		operators.add(o);
		}
		return operators;
	}
	 
	

}

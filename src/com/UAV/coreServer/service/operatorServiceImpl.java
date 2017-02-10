package com.UAV.coreServer.service;

import java.nio.file.OpenOption;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.UAV.coreServer.DAO.operatorDao;
import com.UAV.coreServer.DAO.operatorDaoImpl;
import com.UAV.coreServer.DAO.orderDao;
import com.UAV.coreServer.DAO.orderDaoImpl;
import com.UAV.coreServer.DAO.quotationDao;
import com.UAV.coreServer.DAO.quotationDaoImpl;
import com.UAV.coreServer.Utility.ApplicationContext;
import com.UAV.coreServer.Utility.YeekuXmlApplication;
import com.UAV.coreServer.Utility.constant;
import com.UAV.coreServer.db.UAVcoreServerDB;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public class operatorServiceImpl implements operatorService, OpenOption {
    
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
	public int validRegister(Operator operator)  {
		// TODO Auto-generated method stub
		
		
		int id;
		try{
			
			
			id=opdao.save(operator);
		}
		catch(Exception e){
			return 0;
		}
		return id;
	}

	@Override
	public List<Order> getOrder(int operatorId) throws Exception {
		// TODO Auto-generated method stub
	    //In fact, change the address into coordination and calculate the distance at the localhost would help to increase the speed.  
		Operator operator=opdao.get(operatorId);
		String origin = operator.getAddress();
		double range= operator.getRange();
		List<Order> orders=new LinkedList<Order>();
		List<Order> ordersAll=ordao.findAll();
		for(Order tmp: ordersAll){
			String destination = tmp.getRegion();
			try{
			double distance=locationService.getDistance(locationService.getAddress(origin, destination));
			if(range - distance >= 0){
				coreserver.addOperatorGetOrder(operatorId, tmp.getId());
				orders.add(tmp);
				
			}
			}
			catch(Exception e){
				continue;
			}
			
		}
		return orders;
	}

	@Override
	public List<Order> getModifyOrder(int operatorId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> ids= coreserver.getModifiedOrdersId(operatorId);
		List<Order> orders=new LinkedList<Order>();
		for(int tmp:ids){
			Order o=ordao.get(tmp);
			System.out.println(tmp);
			orders.add(o);
			coreserver.changeStatus(tmp, constant.defaultStatus);
		}
		return orders;
       
	}

	@Override
	public List<Integer> getDelOrderId(int operatorId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> ids= coreserver.getDeletedOrdersId(operatorId);
	    // coreserver.clearDeletedOrders(operatorId);
		return ids;
	}

	@Override
	public int quote(Quotation quotation)  {
		// TODO Auto-generated method stub
        int id=0;
		try{
		id=qudao.save(quotation);
        }
        catch(Exception e){
        	e.printStackTrace();
        	return id; 
        }
        return 0;
	}

}

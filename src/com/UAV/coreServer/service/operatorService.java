package com.UAV.coreServer.service;

import java.util.List;

import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public interface operatorService {
   /*
    * a method used to register the operator 
    * @param name  the object for the registered operator
    * @return the num > 0 represents the successful register id of the operator and "0" represents the failure
    */
	int validRegister(Operator operator);
   
	/*
	 * a method used to get the orders which have not been released
	 * @param operatorId the id of the usr
	 * @return 
	 */
	List<Order> getOrder(int usrId);
	/*
	 * a method used to get the orders which have been modified
	 * @param operatorId the id of the usr
	 * @return 
	 */
	List<Order> getModifyOrder(int usrId);
	/*
	 * a method used to get the id of the orders which have been deleted
	 * @param usrId the id of the operator
	 * @return 
	 */
	List<Integer> getDelOrderId(int usrId);
	/*
	 * a method used to quote a price and return the result 
	 * @param the object of the quotation
	 * @return "0"represent success; "1"represent fail
	 */
	int quote(Quotation quotation)throws Exception;
}

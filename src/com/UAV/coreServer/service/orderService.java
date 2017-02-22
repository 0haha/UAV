package com.UAV.coreServer.service;

import java.util.List;

import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;

public interface orderService {
   
	/*
	 * A method used to save the release order
	 * @param order which is need to be released 
	 * @return the result "0" fail and ">1" is the orderid and success
	 */
	 int releaseOrders(Order order,int useId);
	 /*
		 * A method used to save the modify order
		 * @param order which is need to be modify and orderId which is the id of the order which need to be modified 
		 * @return the result "0" fail and "1" success
		 */
	 int modifyOrders(Order order,int orderId);
	 /*
		 * A method used to delete the modify order
		 * @param order which is need to be released and usrId which is the user who want to release the order 
		 * @return the result "0" fail and "1" success
		 */
	 int deleteOrders(int orderId,int useId);
	 /*
		 * A method used to get the quotation by the usrId
		 * @param all the usrId
		 * @return the quotation list
		 */
	 List<Quotation> getQuotation(int usrId) throws Exception;
	 
	 /*
		 * A method used to get the quotation by the usrId and operatorId
		 * @param all the usrId and the operatorId
		 * @return the quotation 
		 */
	 Quotation getQuotationByUsrIdAndOperator(int usrId,int operatorId)throws Exception;
	 
	 List<Operator> getOerator(int usrId) throws Exception;
}

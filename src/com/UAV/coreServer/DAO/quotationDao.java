package com.UAV.coreServer.DAO;

import java.util.List;

import com.UAV.coreServer.model.Quotation;

public interface quotationDao {
   
	Quotation get(int id) throws Exception;
	int save(Quotation quotation)throws Exception;
	void update(int id,Quotation quotation)throws Exception;
	void delete(Quotation quotation)throws Exception;
	void delete(int id)throws Exception;
	List<Quotation> findByOperatorId(int orderId)throws Exception;
	List<Quotation> findByOrderId(int orderId) throws Exception;
}

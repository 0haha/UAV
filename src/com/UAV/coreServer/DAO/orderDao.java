package com.UAV.coreServer.DAO;

import java.util.List;

import com.UAV.coreServer.model.Order;

public interface orderDao {

	Order get(int id) throws Exception;
	int save(Order Order)throws Exception;
	void update(int id,Order Order)throws Exception;
	void delete(Order Order) throws Exception;
	void delete(int id)throws Exception;
	List<Order> findAll() throws Exception;
	List<Order> findByOrderId(int orderId);
	
}

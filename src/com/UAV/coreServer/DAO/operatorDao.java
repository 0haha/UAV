package com.UAV.coreServer.DAO;

import java.sql.SQLException;
import java.util.List;

import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;

public interface operatorDao {
	
	Operator get(int id) throws Exception;
	int save(Operator Operator) throws Exception;
	void update(int id,Operator Operator)throws Exception;
	void delete(Operator Operator) throws Exception;
	void delete(int id) throws Exception;
	List<Operator> findAll() throws Exception;
	void changeStatus(int id,int status)throws Exception;
	List<Operator> findByStatus(int status)throws Exception;
}

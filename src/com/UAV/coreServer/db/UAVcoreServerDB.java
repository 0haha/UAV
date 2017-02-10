package com.UAV.coreServer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.UAV.coreServer.Utility.constant;
import com.UAV.coreServer.Utility.entityAnalysis;
import com.mysql.jdbc.Statement;

public class UAVcoreServerDB {
	//operator_order
	public int addOperatorGetOrder(int operatorId,int orderId) throws Exception{
		int id=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "insert into operator_order (id,operator_id,order_id) values(?,?,?)";
		
		try{
	        pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, 0);
	        pstmt.setInt(2, operatorId);
	        pstmt.setInt(3, orderId);
	        
	        
	        pstmt.executeUpdate();//get the number of the updated rows;
	        ResultSet rs =  pstmt.getGeneratedKeys();
	        if (rs.next()) { 
	             id = rs.getInt(1); 
	        	} 
		}
		
		finally{
	        pstmt.close();
	        conn.close();
		}
		
	    return id;
	}
	
	public List<Integer> getModifiedOrdersId(int operator) throws Exception{
		List<Integer> orders_ids=new LinkedList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "select * from operator_order where operator_id="+String.valueOf(operator)+" and order_status="+String.valueOf(constant.modifiedStatus)+";";
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			orders_ids.add(rs.getInt(3));
		}
		return orders_ids;
	}
	
	public List<Integer> getDeletedOrdersId(int operator)throws Exception{
		
		List<Integer> orders_ids=new LinkedList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "select order_id from operator_order where operator_id="+String.valueOf(operator)+" and order_status="+String.valueOf(constant.deletedStatus)+";";
		pstmt=(PreparedStatement)conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			orders_ids.add(rs.getInt(1));
		}
		pstmt.close();
	    conn.close();
		return orders_ids;
	}
	
	public void changeStatus(int orderid,int status)throws Exception{
		String sql="update operator_order set "
				+"order_status="+String.valueOf(status)
        		+" where order_id="+String.valueOf(orderid)
        		+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    
	    pstmt.close();
	    conn.close();
	}
	public void clearDeletedOrders(int operatorId) throws Exception{
		String sql="delete from operator_order where operatorId="+String.valueOf(operatorId)+" and order_status="+String.valueOf(constant.deletedStatus)+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    pstmt.close();
	    conn.close();
	}
	//operator_order
	public int addOrderUser(int orderid,int usrid)throws Exception{
		int id=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "insert into order_usr (id,orderid,usrid) values(?,?,?)";
		
		try{
	        pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, 0);
	        pstmt.setInt(2, orderid);
	        pstmt.setInt(3, usrid);
	        
	        
	        pstmt.executeUpdate();//get the number of the updated rows;
	        ResultSet rs =  pstmt.getGeneratedKeys();
	        if (rs.next()) { 
	             id = rs.getInt(1); 
	        	} 
		}
		
		finally{
	        pstmt.close();
	        conn.close();
		}
		
	    return id;
	}
	
	public void clearDeletedOrderAndUsr(int orderid,int usrid)throws Exception{
		String sql="delete from order_usr where orderid="+String.valueOf(orderid)+" and usrid="+String.valueOf(usrid)+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    pstmt.close();
	    conn.close();
	}
	
	public List<Integer> getOrderIdsByUsrId(int usrid)throws Exception{
		
		List<Integer> orders_ids=new LinkedList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "select * from order_usr where usrid="+String.valueOf(usrid)+";";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			orders_ids.add(rs.getInt(2));
		}
		pstmt.close();
	    conn.close();
		return orders_ids;
		
		
	}
}


package com.UAV.coreServer.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.UAV.coreServer.db.connectionOp;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Quotation;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class quotationDaoImpl implements quotationDao {

	@Override
	public Quotation get(int id) throws Exception{
		// TODO Auto-generated method stub
      Connection conn=connectionOp.getConnection();
		
		String sql="select * from quotation where id="+String.valueOf(id)+";";
		PreparedStatement pstmt;
		
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			Quotation o=new Quotation();
			o.setId(rs.getInt(1));
    		o.setOrderId(rs.getInt(2));
    		o.setPrice(rs.getDouble(3));
    		o.setUsrId(rs.getInt(4));
    		return o;
		}
		else
		return null;
	}

	@Override
	public int save(Quotation quotation)throws Exception {
		// TODO Auto-generated method stub
		
		int id=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "insert into quotation (id,orderId,price,operatorid) values(?,?,?,?)";
		
		
	        pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, 0);
	        pstmt.setInt(2, quotation.getOrderId());
	        pstmt.setDouble(3, quotation.getPrice());
	        pstmt.setInt(4, quotation.getUsrId());
	        
	        
	        pstmt.executeUpdate();//get the number of the updated rows;
	        ResultSet rs =  pstmt.getGeneratedKeys();
	        if (rs.next()) { 
	             id = rs.getInt(1); 
	        	} 
		
	        pstmt.close();
	        conn.close();
		
		    return id;
	}

	@Override
	public void update(int id,Quotation quotation)throws Exception {
		// TODO Auto-generated method stub
		String sql="update quotation set "
				+"usrid="+quotation.getUsrId()
        		+",orderid="+quotation.getOrderId()
        		+",price="+quotation.getPrice()
        		+"where id="+String.valueOf(id)
        		+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public void delete(Quotation quotation)throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from quotation where "
        		+"usrid="+String.valueOf(quotation.getUsrId())
        		+" and orderid="+String.valueOf(quotation.getOrderId())
        		+" and price="+String.valueOf(quotation.getPrice())
        		+";";
        Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public void delete(int id) throws Exception{
		// TODO Auto-generated method stub
		String sql="delete from quotation where "
        		+"id="+String.valueOf(id)+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public List<Quotation> findByOperatorId(int operatorid) throws Exception{
		// TODO Auto-generated method stub
		List<Quotation> quotations=new LinkedList<Quotation>();
		Connection conn = connectionOp.getConnection();
	    String sql = "select * from quotation where usrid="+String.valueOf(operatorid)+";";
	    PreparedStatement pstmt;
	    pstmt = (PreparedStatement)conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    int col = rs.getMetaData().getColumnCount();
	        
	        while (rs.next()) {
	        	Quotation o=new Quotation();
	            for (int i = 1; i <= col; i++) {
	                switch(i){
	                	case 1:
	                		o.setId(rs.getInt(1));break;
	                	case 2:
	                		o.setUsrId(rs.getInt(2));break;
	                	case 3:
	                		o.setOrderId(rs.getInt(3));break;
	                	case 4:
	                		o.setPrice(rs.getDouble(4));break;
	                	
	                }
	                		
	             }
	            quotations.add(o);
	            
	        }
	            
	    
		return quotations;
	}

	public List<Quotation> findByOrderId(int orderId) throws Exception{
		// TODO Auto-generated method stub
				List<Quotation> quotations=new LinkedList<Quotation>();
				Connection conn = connectionOp.getConnection();
			    String sql = "select * from quotation where orderid="+String.valueOf(orderId)+";";
			    PreparedStatement pstmt;
			    pstmt = (PreparedStatement)conn.prepareStatement(sql);
			    ResultSet rs = pstmt.executeQuery();
			    int col = rs.getMetaData().getColumnCount();
			        
			        while (rs.next()) {
			        	Quotation o=new Quotation();
			            for (int i = 1; i <= col; i++) {
			                switch(i){
			                	case 1:
			                		o.setId(rs.getInt(1));break;
			                	case 2:
			                		o.setUsrId(rs.getInt(2));break;
			                	case 3:
			                		o.setOrderId(rs.getInt(3));break;
			                	case 4:
			                		o.setPrice(rs.getDouble(4));break;
			                	
			                }
			                		
			             }
			            quotations.add(o);
			            
			        }
			            
			    
				return quotations;
	}

}

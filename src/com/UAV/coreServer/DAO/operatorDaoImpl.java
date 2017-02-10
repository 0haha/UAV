package com.UAV.coreServer.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.UAV.coreServer.db.connectionOp;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class operatorDaoImpl implements operatorDao {

	@Override
	public Operator get(int id) throws Exception{
		// TODO Auto-generated method stub
		Connection conn=connectionOp.getConnection();
		
		String sql="select * from Operator where id="+String.valueOf(id)+";";
		PreparedStatement pstmt;
		
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			Operator o=new Operator(); 
			o.setId(rs.getInt(1));
    		o.setSex(rs.getInt(2));
    		o.setName(rs.getString(3));
    		o.setBirthday(rs.getString(4));
    	    o.setWorktime(rs.getInt(5));
    		o.setPhone(rs.getString(6));
    		o.setAddress(rs.getString(7));
    		o.setRange(rs.getDouble(8));
    		return o;
		}
		else
		return null;
	}

	@Override
	public int save(Operator operator) throws Exception{
		// TODO Auto-generated method stub
		int id=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "insert into Operator (id,sex,name,birthday,worktime,phone,address,ran) values(?,?,?,?,?,?,?,?)";
		
		try{
	        pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, 0);
	        pstmt.setInt(2, operator.getSex());
	        pstmt.setString(3, operator.getName());
	        pstmt.setString(4, operator.getBirthday());
	        pstmt.setInt(5, operator.getWorktime());
	        pstmt.setString(6, operator.getPhone());
	        pstmt.setString(7, operator.getAddress());
	        pstmt.setDouble(8, operator.getRange());
	        
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

	@Override
	public void update(int id,Operator Operator) throws Exception {
		// TODO Auto-generated method stub
		String sql="update Operator set "
				+"sex="+String.valueOf(Operator.getSex())
        		+",name='"+Operator.getName()
        		+"',birthday='"+Operator.getBirthday()
        		+"',worktime="+String.valueOf(Operator.getWorktime())
        		+",phone='"+Operator.getPhone()
        		+"',address='"+Operator.getAddress()
        		+"',ran="+String.valueOf(Operator.getRange())
        		+"where id="+String.valueOf(id)
        		+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public void delete(Operator Operator) throws Exception {
		// TODO Auto-generated method stub
        String sql="delete from Operator where "
        		+"sex="+String.valueOf(Operator.getSex())
        		+" and name='"+Operator.getName()
        		+"' and birthday='"+Operator.getBirthday()
        		+"' and worktime="+String.valueOf(Operator.getWorktime())
        		+" and phone='"+Operator.getPhone()
        		+"' and address='"+Operator.getAddress()
        		+"' and ran="+String.valueOf(Operator.getRange())
        		+";";
        Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from Operator where "
        		+"id="+String.valueOf(id)+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public List<Operator> findAll() throws Exception{
		// TODO Auto-generated method stub
		List<Operator> Operators=new LinkedList<Operator>();
		Connection conn = connectionOp.getConnection();
	    String sql = "select * from Operator";
	    PreparedStatement pstmt;
	    pstmt = (PreparedStatement)conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    int col = rs.getMetaData().getColumnCount();
	        
	        while (rs.next()) {
	        	Operator o=new Operator();
	            for (int i = 1; i <= col; i++) {
	                switch(i){
	                	case 1:
	                		o.setId(rs.getInt(1));break;
	                	case 2:
	                		o.setSex(rs.getInt(2));break;
	                	case 3:
	                		o.setName(rs.getString(3));break;
	                	case 4:
	                		o.setBirthday(rs.getString(4));break;
	                	case 5:
	                	    o.setWorktime(rs.getInt(5));break;
	                	case 6:
	                		o.setPhone(rs.getString(6));break;
	                	case 7:
	                		o.setAddress(rs.getString(7));break;
	                	case 8:
	                		o.setRange(rs.getDouble(8));break;
	                }
	                		
	             }
	            Operators.add(o);
	            
	        }
	            
	    
		return Operators;
	}

	public void changeStatus(int id,int status)throws Exception{
		String sql="update Operator set "
				+"status="+String.valueOf(status)
        		+" where id="+String.valueOf(id)
        		+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    pstmt.close();
	    conn.close();
	}
	public List<Operator> findByStatus(int status)throws Exception{
		List<Operator> Operators=new LinkedList<Operator>();
		Connection conn = connectionOp.getConnection();
	    String sql = "select * from Operator where status="+String.valueOf(status)+";";
	    PreparedStatement pstmt;
	    pstmt = (PreparedStatement)conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    int col = rs.getMetaData().getColumnCount();
	        
	        while (rs.next()) {
	        	Operator o=new Operator();
	            for (int i = 1; i <= col; i++) {
	                switch(i){
	                	case 1:
	                		o.setId(rs.getInt(1));break;
	                	case 2:
	                		o.setSex(rs.getInt(2));break;
	                	case 3:
	                		o.setName(rs.getString(3));break;
	                	case 4:
	                		o.setBirthday(rs.getString(4));break;
	                	case 5:
	                	    o.setWorktime(rs.getInt(5));break;
	                	case 6:
	                		o.setPhone(rs.getString(6));break;
	                	case 7:
	                		o.setAddress(rs.getString(7));break;
	                	case 8:
	                		o.setRange(rs.getDouble(8));break;
	                	case 9:
	                		o.setStatus(rs.getInt(9));break;
	                }
	                		
	             }
	            Operators.add(o);
	            
	        }
	            
	    
		return Operators;
	}
	
	

}

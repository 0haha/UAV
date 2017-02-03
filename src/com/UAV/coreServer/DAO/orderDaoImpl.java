package com.UAV.coreServer.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.UAV.coreServer.db.connectionOp;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class orderDaoImpl implements orderDao {

	@Override
	public Order get(int id) throws Exception{
		// TODO Auto-generated method stub
        Connection conn=connectionOp.getConnection();
		
		String sql="select * from orders where id="+String.valueOf(id)+";";
		PreparedStatement pstmt;
		
		pstmt=(PreparedStatement) conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			Order o=new Order(); 
			o.setId(rs.getInt(1));
    		o.setName(rs.getString(2));
    		o.setPhone(rs.getString(3));
    		o.setRegion(rs.getString(4));
    		o.setCrops(rs.getString(5));
    		o.setArea(rs.getString(6));
    		o.setPrice(rs.getDouble(7));
    		o.setWorkTime(rs.getString(8));
    		o.setDeadline(rs.getDate(9));
    		o.setNote(rs.getString(10));
    		return o;
		}
		else
		return null;
		
	}

	@Override
	public int save(Order Order) throws Exception{
		// TODO Auto-generated method stub
		int id=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		conn=connectionOp.getConnection();
		String sql = "insert into orders (id,name,phone,region,crops,area,price,worktime,deadline,note) values(?,?,?,?,?,?,?,?,?,?)";
		
		
	        pstmt = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	        pstmt.setInt(1, 0);
	        pstmt.setString(2, Order.getName());
	        pstmt.setString(3, Order.getPhone());
	        pstmt.setString(4, Order.getRegion());
	        pstmt.setString(5, Order.getCrops());
	        pstmt.setString(6, Order.getArea());
	        pstmt.setDouble(7, Order.getPrice());
	        pstmt.setString(8, Order.getWorkTime());
	        pstmt.setDate(9, new java.sql.Date(Order.getDeadline().getTime()));
	        pstmt.setString(10, Order.getNote());
	        
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
	public void update(int id,Order Order) throws Exception{
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sql="update orders set "
				+"name='"+Order.getName()
        		+"',phone='"+Order.getPhone()
        		+"',region='"+Order.getRegion()
        		+"',crops='"+Order.getCrops()
        		+"',area='"+Order.getArea()
        		+"',price="+String.valueOf(Order.getPrice())
        		+",worktime='"+Order.getWorkTime()
        		+"',deadline='"+dateFormat.format(Order.getDeadline().getTime())
        		+"',note='"+Order.getNote()
        		+"' where id="+String.valueOf(id)
        		+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.execute();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public void delete(Order Order) throws Exception{
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sql="delete from orders where "
        		+"name='"+Order.getName()
        		+"' and phone='"+Order.getPhone()
        		+"' and region='"+Order.getRegion()
        		+"' and crops='"+Order.getCrops()
        		+"' and area="+Order.getArea()
        		+" and price="+Order.getPrice()
        		+" and worktime='"+Order.getWorkTime()
        		+"' and deadline='"+dateFormat.format(Order.getDeadline().getTime())
        		+"' and note='"+Order.getNote()
        		+"';";
        Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public void delete(int id)throws Exception {
		// TODO Auto-generated method stub
		String sql="delete from orders where "
        		+"id="+String.valueOf(id)+";";
		Connection conn=connectionOp.getConnection();
        PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
	    pstmt.executeUpdate();
	    pstmt.close();
	    conn.close();
	}

	@Override
	public List<Order> findAll()throws Exception {
		// TODO Auto-generated method stub
		List<Order> Orders=new LinkedList<Order>();
		Connection conn = connectionOp.getConnection();
	    String sql = "select * from orders";
	    PreparedStatement pstmt;
	    pstmt = (PreparedStatement)conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    int col = rs.getMetaData().getColumnCount();
	        
	        while (rs.next()) {
	        	Order o=new Order();
	            for (int i = 1; i <= col; i++) {
	                switch(i){
	                	case 1:
	                		o.setId(rs.getInt(1));break;
	                	case 2:
	                		o.setName(rs.getString(2));break;
	                	case 3:
	                		o.setPhone(rs.getString(3));;break;
	                	case 4:
	                		o.setRegion(rs.getString(4));;break;
	                	case 5:
	                	    o.setCrops(rs.getString(5));;break;
	                	case 6:
	                		o.setArea(rs.getString(6));;break;
	                	case 7:
	                		o.setPrice(rs.getDouble(7));break;
	                	case 8:
	                		o.setWorkTime(rs.getString(8));break;
	                	case 9:
	                		o.setDeadline(rs.getDate(9));break;
	                	case 10:
	                		o.setNote(rs.getString(10));break;
	                		
	                }
	                		
	             }
	            Orders.add(o);
	            
	        }
	            
	    
		return Orders;
	}

	@Override
	public List<Order> findByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}

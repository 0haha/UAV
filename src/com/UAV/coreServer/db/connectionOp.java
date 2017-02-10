package com.UAV.coreServer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.UAV.coreServer.Utility.constant;
import com.mysql.jdbc.*;

public class connectionOp {
  private static Connection conn=null;
  
  /*
   * The function use the Singleton pattern to create a connection to connect the sql.
   * The function may throw the exceptions so the user need to cope with the exceptions. 
   */
  
  public  static Connection getConnection() throws Exception{
	  
		  try{
			  Class.forName(constant.driver);
			  conn=(Connection)DriverManager.getConnection(constant.url, constant.username, constant.password);
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  System.out.println("connection error!");
			 throw new Exception("Connection error");
			  
		  }
		  
	  
		  
	  return conn;
  }
  
}

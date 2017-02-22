package com.UAV.coreServer.Controler;
import redis.clients.jedis.Jedis;
public class RedisConnect {
     
	private static RedisConnect conn=null;
	Jedis jedis=null;
    private RedisConnect(){
    	 jedis = new Jedis("localhost");
    }
    public static RedisConnect getConnection(){
    	if(conn==null)
    		conn =new RedisConnect();
    	return conn;	
    }
    public void put(String key,String value){
    	jedis.set(key,value);
    }
    public String get(String key){
    	return jedis.get(key);
    }
}

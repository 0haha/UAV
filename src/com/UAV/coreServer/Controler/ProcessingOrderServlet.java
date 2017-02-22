package com.UAV.coreServer.Controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.UAV.coreServer.Utility.ApplicationContext;
import com.UAV.coreServer.Utility.YeekuXmlApplication;
import com.UAV.coreServer.model.Operator;
import com.UAV.coreServer.model.Order;
import com.UAV.coreServer.model.Quotation;
import com.UAV.coreServer.service.operatorService;
import com.UAV.coreServer.service.operatorServiceImpl;
import com.UAV.coreServer.service.orderService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ProcessingOrderServlet
 */

public class ProcessingOrderServlet extends HttpServlet{
    String path=null;
    
    public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
    	
        request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;CharSet=utf-8");
    	PrintWriter pw = response.getWriter();
    	path=request.getRequestURL().toString();
    	BufferedReader br = request.getReader();
    	String line=null,result="";
		 while((line=br.readLine())!=null){
			  result+=line;
		  }
		 
		 JSONObject jobj;
		 try{
		 ApplicationContext ctx=new YeekuXmlApplication();
		 operatorService os=(operatorService) ctx.getBean("operatorService");
		 orderService ors=(orderService)ctx.getBean("orderService");
		 
		 
    	
		if(path.endsWith("/UAV/quote")){
			jobj= new JSONObject().fromObject(result);
			String token = jobj.getString("token");
			RedisConnect conn =RedisConnect.getConnection();
	 		String usrId=conn.get(token);
			if(usrId==null){
	    		 response.setStatus(400,"Quote fail!");
	    		 
	    		}
	        else{
	    		
    	        String price = jobj.getString("price");
			    String orderId = jobj.getString("orderId");
			
				 Quotation q = new Quotation();
				 q.setOrderId(Integer.parseInt(orderId));
				 q.setPrice(Double.parseDouble(price));
				 q.setUsrId(Integer.parseInt(usrId));
				
				 if(os.quote(q)==0){
					 response.setStatus(200,"Quote Successfully!");
				 
			     }
			     else{
				
				     response.setStatus(400,"Quote fail!");
			     }
			    
	          }	
    	}//end with if(path.endsWith("/UAV/quote"))
    	
		else if(path.endsWith("/UAV/validate")){
    		//the usrid and the token can be saved in the session as following
			jobj= new JSONObject().fromObject(result);
    		  try{
    		  
 
    		  String usrId = jobj.getString("usrId");
    		  String token = jobj.getString("token");
    		  
    		  RedisConnect conn =RedisConnect.getConnection();
    		  conn.put(token, usrId);
    		  
    		  response.setStatus(200);
    		  }
    		  catch(Exception e){
    			  e.printStackTrace(); 
    			  response.setStatus(400);
    		  }
    		
    		
    	}  
    	
		else if(path.endsWith("/UAV/delete_order")){
			jobj= new JSONObject().fromObject(result);
    		String token = jobj.getString("token");
    		
    		RedisConnect conn =RedisConnect.getConnection();
    		String usrId=conn.get(token);
    		
    		if(usrId==null){
    		 response.setStatus(400,"delete fail!");
    		 
    		}
    		else{
    		
    		  String orderId = jobj.getString("orderId");
    		  if(ors.deleteOrders(Integer.valueOf(orderId), Integer.valueOf(usrId))>0){    		
    			  response.setStatus(200);
    		  }
    		  else{
    		  response.setStatus(400);
    		  }
    		
    		}
    		
    		
    	}//end with else if(path.equals("/UAV/delete_order"))
    	
    	else if(path.endsWith("/UAV/getOperatorInfo")){
    		jobj= new JSONObject().fromObject(result);
    		String token = jobj.getString("token");
    		
    		RedisConnect conn =RedisConnect.getConnection();
    		String usrId=conn.get(token);
    		
    		if(usrId==null){
       		 response.setStatus(400,"get Info fail!");
       		 
       		}
       		else{
       		 
       		 List<Operator> operators=ors.getOerator(Integer.valueOf(usrId));
       		 if(operators!=null){
       		   JSONObject responseObj = new JSONObject();
       		   JSONArray responseArr = new JSONArray();
       		   Iterator it = operators.iterator();
       		   while(it.hasNext()){
       			  Operator o = (Operator)it.next();
       			  JSONObject jobjtmp = new JSONObject();
       			  jobjtmp.put("name", o.getName());
       			  jobjtmp.put("sex", o.getSex()==1?"男":"女");
       			  jobjtmp.put("birthdate", o.getBirthday());
       			  jobjtmp.put("workTime", o.getWorktime());
       			  jobjtmp.put("region", o.getAddress());
       			  jobjtmp.put("price", ors.getQuotationByUsrIdAndOperator(Integer.parseInt(usrId), o.getId()).getPrice());
       			  jobjtmp.put("phone", o.getPhone());
       			  
       			  System.out.println("objstr:"+jobjtmp.toString());
       			  responseArr.add(jobjtmp);   			 
       		   }
       		   
       		   responseObj.put("operators", responseArr);
       		   response.setStatus(200);
       		   String s = responseObj.toString();
       		   pw.println(s);
       		  }//end if(operators!=null)
       		 else{
       			 response.setStatus(401);
       		 }
       		}
    		
    		
    	}// end with else if(path.equals("/UAV/getOperatorInfo"))
    	
    	else if(path.endsWith("/UAV/get_order")){
    		
    		System.out.println("there get!");
    		jobj= new JSONObject().fromObject(result);
    		String token = jobj.getString("token");
    		
    		RedisConnect conn =RedisConnect.getConnection();
    		String usrId=conn.get(token);
    		
    		if(usrId==null){
       		 response.setStatus(400,"Invalid indetification");
       		}
       		else{
       			
       			List<Order> ModifiedOrders = new LinkedList<Order>();
       			List<Integer> delIds = new LinkedList<Integer>();
       			List<Order> orders = new LinkedList<Order>();
       			ModifiedOrders = os.getModifyOrder(Integer.parseInt(usrId));
       			delIds = os.getDelOrderId(Integer.parseInt(usrId));
       			orders = os.getOrder(Integer.parseInt(usrId));
       			try{
       				
       				if(ModifiedOrders==null&&delIds==null&&orders==null){
       					response.setStatus(404);
       				}
       				else if(ModifiedOrders==null&&delIds!=null&&orders==null){
       					JSONObject jobjresponse = new JSONObject();
       					JSONArray  jarrresponse = new JSONArray();
       					JSONObject jobjtmp = new JSONObject();
       					for(int tmp:delIds){
       						jobjtmp.put("delOrderId", tmp);
       						jarrresponse.add(jobjtmp);
       					}
       					jobjresponse.put("delOrdersId", jarrresponse);
       					response.setStatus(202);
       					pw.println(jobjresponse.toString());
       				}
       				else if(ModifiedOrders!=null&&delIds!=null){
       					orders.addAll(ModifiedOrders);
       					JSONObject jobjresponse = new JSONObject();
       					JSONArray  jarrresponse1 = new JSONArray();
       					JSONObject jobjtmp1 = new JSONObject();
       					JSONArray  jarrresponse2 = new JSONArray();
       					JSONObject jobjtmp2 = new JSONObject();
       					for (Order tmp:orders){
       						jobjtmp1.put("distance", tmp.getDistance());
       						jobjtmp1.put("workTime", tmp.getWorkTime());
       						jobjtmp1.put("crops", tmp.getCrops());
       						jobjtmp1.put("deadline", tmp.getDeadline().toString());
       						jobjtmp1.put("price", String.valueOf(tmp.getPrice()));
       						jobjtmp1.put("area", tmp.getArea());
       						jobjtmp1.put("place", tmp.getRegion());
       						jobjtmp1.put("info", tmp.getNote());
       						jobjtmp1.put("orderId", tmp.getId());
       						
       						jarrresponse1.add(jobjtmp1);
       						
       					}
       					for(int tmp:delIds){
       						jobjtmp2.put("delOrderId", tmp);
       						jarrresponse2.add(jobjtmp2);
       					}
       					jobjresponse.put("orders", jarrresponse1);
       					jobjresponse.put("delOrdersId", jarrresponse2);
       					
       					response.setStatus(201);
       					pw.println(jobjresponse.toString());
       				}
       				else {
       					JSONObject jobjresponse = new JSONObject();
       					JSONArray  jarrresponse1 = new JSONArray();
       					JSONObject jobjtmp1 = new JSONObject();
       					for (Order tmp:orders){
       						jobjtmp1.put("distance", tmp.getDistance());
       						jobjtmp1.put("workTime", tmp.getWorkTime());
       						jobjtmp1.put("crops", tmp.getCrops());
       						jobjtmp1.put("deadline", tmp.getDeadline().toString());
       						jobjtmp1.put("price", String.valueOf(tmp.getPrice()));
       						jobjtmp1.put("area", tmp.getArea());
       						jobjtmp1.put("place", tmp.getRegion());
       						jobjtmp1.put("info", tmp.getNote());
       						jobjtmp1.put("orderId", tmp.getId());
       						
       						jarrresponse1.add(jobjtmp1);
       						
       					}
       					jobjresponse.put("orders", jarrresponse1);
       					response.setStatus(200);
       					pw.println(jobjresponse.toString());
       				}
       				
       			}
       			catch(Exception e){
       				response.setStatus(404);
       			}
       		}
    		
    		
    	}//end with else if(path.equals("/UAV/get_order"))
		
		
    	else if(path.endsWith("/UAV/modify_order")){
    		
    		jobj= new JSONObject().fromObject(result);
    		String token = jobj.getString("token");
    		RedisConnect conn =RedisConnect.getConnection();
    		String usrId=conn.get(token);
    		if(usrId==null){
    			response.setStatus(400);
    		}
    		else{
    		JSONObject jregion =jobj.getJSONObject("region");
    		StringBuilder region=new StringBuilder();
    		region.append(jregion.getString("province")).append(jregion.getString("city"));
    		region.append(jregion.getString("county")).append(jregion.getString("twon"));
    		region.append(jregion.getString("countryside"));
    		
    		Order order = new Order();
    		order.setId(jobj.getInt("orderId"));
    		order.setArea(jobj.getString("area"));
    		order.setCrops(jobj.getString("crops"));
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		order.setDeadline(sdf.parse(jobj.getString("deadline")));
    		order.setName(jobj.getString("name"));
    		order.setNote(jobj.getString("note"));
    		order.setPhone(jobj.getString("phone"));
    		order.setPrice(jobj.getDouble("price"));
    		order.setRegion(region.toString());
    		order.setWorkTime(jobj.getString("workTime"));
    		if(ors.modifyOrders(order, jobj.getInt("orderId"))==1){
    			response.setStatus(204);
    		}
    		else{
    			response.setStatus(400);
    		}
    		}//if ... else ...
    	}//end with else if(path.equals("/UAV/modify_order"))
		
		
    	else if(path.endsWith("/UAV/registerOperator")){
    	
    	 jobj= new JSONObject().fromObject(result);
    	 String token = jobj.getString("token");
    	 RedisConnect conn =RedisConnect.getConnection();
 		 String usrId=conn.get(token);
    	 if(usrId==null){	
    	   response.setStatus(400);	
    	 }
    	 else{
    		JSONObject jregion =jobj.getJSONObject("address");
    		StringBuilder region=new StringBuilder();
    		region.append(jregion.getString("province")).append(jregion.getString("city"));
    		region.append(jregion.getString("county")).append(jregion.getString("twon"));
    		region.append(jregion.getString("countryside")).append(jregion.getString("detailplace"));
    		
    		Operator operator = new Operator();
    		operator.setAddress(region.toString());
    		operator.setBirthday(jobj.getString("birthday"));
    		operator.setName(jobj.getString("name"));
    		operator.setPhone(jobj.getString("phone"));
    		operator.setRange(jobj.getDouble("range"));
    		operator.setSex(jobj.getString("sex").equals("男")?1:0);
    		operator.setWorktime(jobj.getInt("workTime"));
    		if(os.validRegister(operator)==0){
    			response.setStatus(400);
    		}
    		else{
    			response.setStatus(200);
    		}
    	 }	//end with if(token...) else{..
    	}//end with else if(path.endsWith("/UAV/registerOperator"))
		
		
    	else if(path.endsWith("/UAV/release_order")){
    	  
    		jobj= new JSONObject().fromObject(result);
    		String token = jobj.getString("token");
    		RedisConnect conn =RedisConnect.getConnection();
    		String usrId=conn.get(token);
    		if(usrId==null){
    			response.setStatus(400);
    		}
    		
    		else{
    		
    		JSONObject jregion =jobj.getJSONObject("region");
    		StringBuilder region=new StringBuilder();
    		region.append(jregion.getString("province")).append(jregion.getString("city"));
    		region.append(jregion.getString("county")).append(jregion.getString("twon"));
    		region.append(jregion.getString("countryside"));
    		
    		Order order = new Order();
    		order.setArea(jobj.getString("area"));
    		order.setCrops(jobj.getString("crops"));
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		order.setDeadline(sdf.parse(jobj.getString("deadline")));
    		order.setName(jobj.getString("name"));
    		order.setNote(jobj.getString("note"));
    		order.setPhone(jobj.getString("phone"));
    		order.setPrice(jobj.getDouble("price"));
    		order.setRegion(region.toString());
    		order.setWorkTime(jobj.getString("workTime"));
    		System.out.println(jobj.getString("name")+" "+region.toString());
    		int orderId=ors.releaseOrders(order, Integer.parseInt(usrId));
    		System.out.println("Id:"+orderId);
    		if(orderId==0){
    			response.setStatus(400);
    		}
    		else{
    			JSONObject jresponseObj = new JSONObject();
    			jresponseObj.put("orderId", orderId);
    			pw.print(jresponseObj.toString());
    			response.setStatus(200);
    		}
    		}//end with if(token...) else{..
    	}//end with else if(path.endsWith("/UAV/release_order"))
		
    else{
    	    response.setStatus(404);
    		System.out.println(request.getRequestURI());
    	}// else with all the condition about the if .. else if ..
    	
	}//end with try{ jobj= new JSONObject().fromObject(result);...
	catch(Exception e){
			 e.printStackTrace();
			 response.setStatus(400,"Bad Request!");
   }
  }
		 
    
}

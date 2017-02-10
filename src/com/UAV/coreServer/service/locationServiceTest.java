package com.UAV.coreServer.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class locationServiceTest {

	@Test
	public void test() {
		String path = locationService.getAddress("广东省-汕头市-潮南区-仙城镇-长春村", "广东省-广州市-天河区-石牌街道-");
		try{
		double distance = locationService.getDistance(path);
		System.out.println("distance:"+distance);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}

}

package com.UAV.coreServer.model;

import java.util.Date;

public class Operator {
  private int id;
  private int sex;
  private String name;
  private String birthday;
  private int worktime;
  private String phone;
  private String address;
  private double range;
  
  
public int getSex() {
	return sex;
}
public void setSex(int sex) {
	this.sex = sex;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
public int getWorktime() {
	return worktime;
}
public void setWorktime(int worktime) {
	this.worktime = worktime;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public double getRange() {
	return range;
}
public void setRange(double range) {
	this.range = range;
}
  
  
}

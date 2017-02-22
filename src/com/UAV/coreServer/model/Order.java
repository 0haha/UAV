package com.UAV.coreServer.model;

import java.util.Date;

import com.UAV.coreServer.Utility.constant;

public class Order {
private int id;
private String name;
private String phone;
private String region;
private String crops;
private String area;
private double price;
private String workTime;
private Date deadline;
private String note;
private double distance=constant.defaultDistance;


public double getDistance() {
	return distance;
}
public void setDistance(double distance) {
	this.distance = distance;
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
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getRegion() {
	return region;
}
public void setRegion(String region) {
	this.region = region;
}
public String getCrops() {
	return crops;
}
public void setCrops(String crops) {
	this.crops = crops;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getWorkTime() {
	return workTime;
}
public void setWorkTime(String workTime) {
	this.workTime = workTime;
}
public Date getDeadline() {
	return deadline;
}
public void setDeadline(Date deadline) {
	this.deadline = deadline;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}

	
}

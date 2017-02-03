package com.UAV.coreServer.model;

public class Quotation {
private int id;
private int usrId;
private int orderId;
private double price;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUsrId() {
	return usrId;
}
public void setUsrId(int usrId) {
	this.usrId = usrId;
}
public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

}

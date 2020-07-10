package com.orderservice.dto;

import org.springframework.stereotype.Component;

@Component
public class OrderItemDetails
{



private int orderid;
public int getOrderid() {
	return orderid;
}
public void setOrderid(int orderid) {
	this.orderid = orderid;
}

private String productcode;

private String productname;

private int quantity;

public String getProductcode() {
	return productcode;
}
public void setProductcode(String productcode) {
	this.productcode = productcode;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}



}

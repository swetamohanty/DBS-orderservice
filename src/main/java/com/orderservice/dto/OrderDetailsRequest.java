package com.orderservice.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailsRequest
{

private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

private String customername;

private Date orderdate;


private String shippingaddress;

private int orderitems;

private int total;
private List<OrderItemDetails> productDetails; 


public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}

public String getShippingaddress() {
	return shippingaddress;
}
public void setShippingaddress(String shippingaddress) {
	this.shippingaddress = shippingaddress;
}
public int getOrderitems() {
	return orderitems;
}
public void setOrderitems(int orderitems) {
	this.orderitems = orderitems;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public List<OrderItemDetails> getProductDetails() {
	return productDetails;
}
public void setProductDetails(List<OrderItemDetails> productDetails) {
	this.productDetails = productDetails;
}

public Date getOrderdate() {
	return orderdate;
}
public void setOrderdate(Date orderdate) {
	this.orderdate = orderdate;
}

}

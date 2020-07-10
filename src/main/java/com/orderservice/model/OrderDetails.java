package com.orderservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class OrderDetails
{

@Id
@Column
private int id;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Column
private String customername;
@Column
private Date orderdate;
@Column
private String shippingaddress;
@Column
private int orderitems;
@Column
private int total;

public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}
public Date getOrderdate() {
	return orderdate;
}
public void setOrderdate(Date orderdate) {
	this.orderdate = orderdate;
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
}

package com.orderservice.service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.model.OrderDetails;

import com.orderservice.repository.OrderRepository;
@Service
public class OrderService 
{
@Autowired
OrderRepository orderRepository;


public List<OrderDetails> findByCustomername(String name) 
{
List<OrderDetails> Orders = new ArrayList<OrderDetails>();
orderRepository.findByCustomername(name).forEach(Order -> Orders.add(Order));
return Orders;
}
public List<OrderDetails> getAllOrder() 
{
List<OrderDetails> Orders = new ArrayList<OrderDetails>();
orderRepository.findAll().forEach(Order -> Orders.add(Order));
return Orders;
}
public OrderDetails getOrderById(int id) 
{	try {
		return orderRepository.findById(id).get(0);
	}catch(NoSuchElementException e) {
		throw new OrderNotFoundException();
	}
}
public void saveOrUpdate(OrderDetails Order) 
{
	try {
		orderRepository.save(Order);
	}catch(Exception e) {
		throw e;
	}
}

}
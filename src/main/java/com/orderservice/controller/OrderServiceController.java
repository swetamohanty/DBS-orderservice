package com.orderservice.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderservice.RestClientService;
import com.orderservice.dto.OrderDetailsRequest;
import com.orderservice.dto.OrderDetailsResponse;
import com.orderservice.dto.OrderItemDetails;
import com.orderservice.exception.OrderNotFoundException;
import com.orderservice.model.OrderDetails;
import com.orderservice.service.OrderService;


@RestController
public class OrderServiceController 
{
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceController.class);

	@Autowired
	OrderService orderService;
	
	@Autowired
	RestClientService restClientService;
	
	/**
	 * GET API to retrieve all orders
	 * @return
	 */
	@GetMapping("/order")
	private ResponseEntity<Object> getAllOrder() 
	{	OrderDetailsResponse response = new OrderDetailsResponse();
		List<OrderDetailsRequest> ordersDetails = new ArrayList<OrderDetailsRequest>();
		List<OrderDetails> orders = orderService.getAllOrder();
		
		for(OrderDetails order : orders) {
			
			OrderDetailsRequest details = new OrderDetailsRequest();
			constructResponseData(order,details);
			List<OrderItemDetails> items =  restClientService.findByOrderid(order.getId());
			details.setProductDetails(items);
			ordersDetails.add(details);
		}
		response.setOrders(ordersDetails);
		response.setMessage("SUCCESS");
		response.setStatus((long) 200);
		return  new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	/**
	 * GET API to retrieve all orders for a customer
	 * It will return multiple order details for a customer
	 * @param customername
	 * @return
	 */
	@GetMapping("/order/customer")
	private ResponseEntity<Object> findByCustomername(@RequestParam(value ="customername") String customername) 
	{
		OrderDetailsResponse response = new OrderDetailsResponse();
		List<OrderDetailsRequest> ordersDetails = new ArrayList<OrderDetailsRequest>();
		if(customername == null || customername.isEmpty()) {
			response.setMessage("FAILED : customername can not be empty");
			response.setStatus((long) 400);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		else {
			if(orderService.findByCustomername(customername).size() == 0) {
				logger.error("No order found for the given customer::" +customername);
				throw new OrderNotFoundException();
			}
			List<OrderDetails> orderDetails = orderService.findByCustomername(customername);
			for(OrderDetails o : orderDetails) {
				OrderDetailsRequest details = new OrderDetailsRequest();
				constructResponseData(o,details);
				List<OrderItemDetails> items = restClientService.findByOrderid( o.getId());
				details.setProductDetails(items);
				ordersDetails.add(details);
			}
			response.setMessage("SUCCESS");
			response.setStatus((long) 200);
			response.setOrders(ordersDetails);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
	}
	
	/**
	 * GET API to retrieve all orders for an orderid
	 * @param id
	 * @return
	 */
	@GetMapping("/order/{id}")
	private ResponseEntity<Object> getOrder(@PathVariable("id") int id) 
	{
		OrderDetailsResponse response = new OrderDetailsResponse();
		response.setMessage("SUCCESS");
		response.setStatus((long) 200);
		OrderDetailsRequest details = new OrderDetailsRequest();
		constructResponseData(orderService.getOrderById(id),details);
		if(details != null && details.getCustomername()== null) {
			logger.error("No order found for the id "+id);
			throw new OrderNotFoundException();
		}else {
			List<OrderItemDetails> order = restClientService.findByOrderid( details.getId());
			details.setProductDetails(order);
		}
		List<OrderDetailsRequest> orders = new ArrayList<OrderDetailsRequest>();
		orders.add(details);
		response.setOrders(orders);
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
	/**
	 * POST API to store data in order and orderitemdetailstable 
	 * @param order
	 * @return
	 */
	@PostMapping("/order")
	private ResponseEntity<Object> saveOrder(@RequestBody OrderDetailsRequest order)
	{
		OrderDetailsResponse response = new OrderDetailsResponse();
		OrderDetails details = new OrderDetails();
		try {
			
			List<OrderItemDetails> orderItems = order.getProductDetails();
			if(orderItems.size() > 0) {
				orderItems.forEach(orderItem ->{orderItem.setOrderid(  order.getId());});
			}else {
				logger.info("Item list can not be empty");
				response.setMessage("FAILED : No items selected");
				response.setStatus((long) 400);
				return new ResponseEntity<Object>(response, HttpStatus.OK);
			}
			details.setCustomername(order.getCustomername());
			details.setId(order.getId());
			details.setOrderdate(order.getOrderdate());
			details.setOrderitems(order.getOrderitems());
			details.setShippingaddress(order.getShippingaddress());
			details.setTotal(order.getTotal());
			orderService.saveOrUpdate(details);
			restClientService.saveOrder(orderItems);
			response.setMessage("SUCCESS");
			response.setStatus((long) 200);
			response.setOrderid(order.getId());
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}catch(Exception e) {
			logger.error("error occured while inserting record");
			response.setMessage("FAILED : error occured while inserting record");
			response.setStatus((long) 400);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
	}
	
	private void constructResponseData(OrderDetails order, OrderDetailsRequest details) {
		details.setCustomername(order.getCustomername());
		details.setId(order.getId());
		details.setOrderdate(order.getOrderdate());
		details.setOrderitems(order.getOrderitems());
		details.setShippingaddress(order.getShippingaddress());
		details.setTotal(order.getTotal());
		
	}
}

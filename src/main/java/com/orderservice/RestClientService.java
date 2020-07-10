package com.orderservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.orderservice.dto.OrderItemDetails;

@FeignClient(name="${feign.name}", url= "${feign.url}")
public interface RestClientService {
	
	@RequestMapping(method=RequestMethod.POST, value="/orderItemDetails")
	public ResponseEntity<Object> saveOrder(@RequestBody List<OrderItemDetails> orderItems);
	
	@RequestMapping(method=RequestMethod.GET, value="/orderItemDetails/{id}")
	public OrderItemDetails getOrder(@PathVariable("id") int id);
	
	/*@RequestMapping(method=RequestMethod.GET, value="/orderItemDetails/{id}")
	public List<OrderItemDetails> findByOrderid(@PathVariable("id") int id);*/
	
	@GetMapping("/orderItemDetails/{id}")
	public List<OrderItemDetails> findByOrderid(@PathVariable("id") int id);
	

}

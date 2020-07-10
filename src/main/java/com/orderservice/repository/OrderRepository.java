package com.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderservice.model.OrderDetails;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long>{ 
	List<OrderDetails> findByCustomername(String name);
	List<OrderDetails> findById(int id);
	
}

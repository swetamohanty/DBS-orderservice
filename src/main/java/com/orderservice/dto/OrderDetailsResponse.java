package com.orderservice.dto;

import java.util.List;
import java.util.Map;

public class OrderDetailsResponse {

		private Long status;
		public Long getStatus() {
			return status;
		}
		public void setStatus(Long status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		private String message;
		private int orderid;
		public int getOrderid() {
			return orderid;
		}
		public void setOrderid(int orderid) {
			this.orderid = orderid;
		}
		List<OrderDetailsRequest> orders;
		public List<OrderDetailsRequest> getOrders() {
			return orders;
		}
		public void setOrders(List<OrderDetailsRequest> orders) {
			this.orders = orders;
		}

}

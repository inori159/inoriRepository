package com.briup.app.estore.service;

import java.util.List;

import com.briup.app.estore.bean.Order;

public interface IOrderService {
	
	void saveOrder(Order record);
	
	List<Order> findOrderByCustomerId(Integer customerId);
	
	
	void deleteById(Integer orderId);
	
}

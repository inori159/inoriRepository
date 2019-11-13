package com.briup.app.estore.service;

import java.util.List;

import com.briup.app.estore.bean.Order;
import com.briup.app.estore.bean.Orderline;

public interface ILineService {
	
	List<Orderline>  findOrderLineByOrderId(Integer OrderId);
	
	void deleteByOrderId(Integer OrderId);
	
	void insert(Orderline record);
}

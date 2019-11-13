package com.briup.app.estore.test;

import java.util.List;

import com.briup.app.estore.bean.Order;
import com.briup.app.estore.service.ILineService;
import com.briup.app.estore.service.impl.LineService;
import com.briup.app.estore.service.impl.OrderService;

public class OrderTest {
	
	
	public static void main(String[] args) throws Exception {
		OrderService os = new OrderService();
		List<Order> list = os.findOrderByCustomerId(1);
		list.forEach((e)->{System.out.println(e.getId());});
		List<Order> list2 = os.findOrderByCustomerId(1);
		list2.forEach((e)->{System.out.println(e.getId());});
	}
}

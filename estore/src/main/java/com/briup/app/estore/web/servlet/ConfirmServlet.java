package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.bean.Order;
import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.impl.OrderService;
import com.briup.app.estore.shoppingcart.ShoppingCart;


@WebServlet("/Confirm")
public class ConfirmServlet extends HttpServlet{
		
	private IOrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		Customer customer = (Customer) session.getAttribute("customer");
		
		
		Order order = new Order();
		order.setCost(shoppingCart.getCost());
		order.setCustomer(customer);
		Map<Integer, Orderline> lines = shoppingCart.getLines();
		Set<Integer> keySet = lines.keySet();
		List<Orderline> orderLineList = new ArrayList<>();
		for(Integer i:keySet)
		{
			orderLineList.add(lines.get(i));
		}
		order.setOrderlines(orderLineList);
		Date orderdate = new Date();
		order.setOrderdate(orderdate);
		order.setCustomer(customer);
		String path = "/Index";
		try
		{
			orderService.saveOrder(order);
			shoppingCart.clear();
			req.getRequestDispatcher(path).forward(req, resp);
		}catch (Exception e) {
			path = "/user/confirmorder.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		
		
	}
}

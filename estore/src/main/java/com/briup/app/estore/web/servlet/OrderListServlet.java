package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.bean.Order;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.impl.OrderService;

@WebServlet("/OrderList")
public class OrderListServlet extends HttpServlet{
//	private IOrderService orderService = new OrderService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("customer")==null)
		{
			String path = "/user/order.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else
		{
			IOrderService orderService = new OrderService();
			HttpSession session = req.getSession();
			Customer customer = (Customer) session.getAttribute("customer");
			List<Order> orderList = orderService.findOrderByCustomerId(customer.getId());
			req.setAttribute("orderList", orderList);
			String path = "/user/order.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
}

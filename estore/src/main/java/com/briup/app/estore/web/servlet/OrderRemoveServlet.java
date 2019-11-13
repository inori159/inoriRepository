package com.briup.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.impl.OrderService;

@WebServlet("/OrderRemove")
public class OrderRemoveServlet extends HttpServlet{
	
	private IOrderService orderService = new OrderService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String orderId = req.getParameter("id");
		orderService.deleteById(Integer.parseInt(orderId));
		String path = "/OrderList";
		System.out.println("OrderRemove进行了跳转");
		req.getRequestDispatcher(path).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}

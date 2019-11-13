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
import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.service.ILineService;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.impl.LineService;
import com.briup.app.estore.service.impl.OrderService;

@WebServlet("/OrderInfo")
public class OrderInfoServlet extends HttpServlet{
	
	private ILineService is = new LineService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		Integer orderId = Integer.parseInt(req.getParameter("id"));
		List<Orderline> orderlines = is.findOrderLineByOrderId(orderId);
		req.setAttribute("orderlines",orderlines);
		String path = "/user/orderinfo.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}

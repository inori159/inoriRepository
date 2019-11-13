package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.bean.Order;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.service.impl.CustomerService;
import com.briup.app.estore.service.impl.OrderService;
import com.briup.app.estore.util.MD5Util;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet{

	private ICustomerService customerServic = new CustomerService();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		resp.setContentType("text/html");
		String name = null;
		String password = null;
		if(req.getAttribute("username")!=null)
		{
			name = (String) req.getAttribute("username");
			password = (String) req.getAttribute("password");
			
		}
		else
		{
			name = req.getParameter("name");
			password = req.getParameter("password");
		}
		System.out.println(name+password);
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		boolean flag = customerServic.login(customer);
		if(flag)
		{
			Customer customerFromDB = customerServic.userInfo(customer);
			HttpSession session = req.getSession();
			session.setAttribute("customer", customerFromDB);
			Cookie nameCookie = new Cookie("username",name);
			Cookie passwordCookie = new Cookie("password",password);
			resp.addCookie(nameCookie);
			resp.addCookie(passwordCookie);
			String path = "/Index";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else
		{
			resp.getWriter().write("用户名错误或密码错误，请重新登录");
		}
	}
}

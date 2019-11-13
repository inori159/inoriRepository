package com.briup.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.service.impl.CustomerService;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet{

	private ICustomerService customerServic = new CustomerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String zip = req.getParameter("zip");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setPassword(password);
		customer.setAddress(address);
		customer.setZip(zip);
		customer.setTelephone(telephone);
		customer.setEmail(email);
		
		String page = "/login.jsp";
		String msg = "注册成功! 请登录";
		try {
			/**
			 * 如果抛出异常则注册失败
			 * 如果没有抛出异常则注册成功
			 */
			customerServic.register(customer);
			
		} catch (Exception e) {
			e.printStackTrace();
			page = "/register.jsp";
			msg="注册失败"+e.getMessage();
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher(page).forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/register.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
}

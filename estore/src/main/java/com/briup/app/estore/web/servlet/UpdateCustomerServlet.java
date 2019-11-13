package com.briup.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.service.impl.CustomerService;

@WebServlet("/UpdateInfo")
public class UpdateCustomerServlet extends HttpServlet{
	
	private ICustomerService customerService = new CustomerService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = new Customer();
		String customerId = req.getParameter("id");
		String customerName = req.getParameter("name");
		String customerAddress = req.getParameter("address");
		String customerZip = req.getParameter("zip");
		String customerTelephone = req.getParameter("telephone");
		String customerEmail = req.getParameter("email");
		customer.setId(Integer.parseInt(customerId));
		customer.setName(customerName);
		customer.setAddress(customerAddress);
		customer.setZip(customerZip);
		customer.setTelephone(customerTelephone);
		customer.setEmail(customerEmail);	
		customerService.update(customer);
		HttpSession session = req.getSession();
		session.setAttribute("customer", customer);
		String path = "/Index";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}

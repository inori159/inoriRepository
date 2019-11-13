package com.briup.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.shoppingcart.ShoppingCart;

@WebServlet("/EditOrderline")
public class EditOrderlineServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		String bookid = req.getParameter("bookid");
		String num = req.getParameter("num");
		shoppingCart.update(Integer.parseInt(bookid), Integer.parseInt(num));
		String path = "user/shoppingCart.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}

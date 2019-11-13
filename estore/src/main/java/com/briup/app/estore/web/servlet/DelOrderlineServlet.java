package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.shoppingcart.ShoppingCart;

@WebServlet("/DelOrderline")
public class DelOrderlineServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");	
		String bookId = req.getParameter("bookid");
		shoppingCart.delete(Integer.parseInt(bookId));
		
		Map<Integer, Orderline> bookLines = shoppingCart.getLines();
		double cost = shoppingCart.getCost();	
		session.setAttribute("shoppingCart", shoppingCart);		
		
		String path = "user/shoppingCart.jsp";
		req.getRequestDispatcher(path).forward(req, resp);;
	}
}

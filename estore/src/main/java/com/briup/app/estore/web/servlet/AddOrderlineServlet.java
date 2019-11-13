package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.shoppingcart.ShoppingCart;


@WebServlet("/AddOrderline")
public class AddOrderlineServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Book book = new Book();
		session.getAttribute("customer");
		String bookid = req.getParameter("bookid");
		String bookName =req.getParameter("bookName");
		String bookPrice =req.getParameter("bookPrice");	
		book.setId(Integer.parseInt(bookid));
		book.setName(bookName);
		book.setPrice(Double.parseDouble(bookPrice));
		
		Orderline orderline = new Orderline();
		orderline.setBook(book);

		
		ShoppingCart shoppingCart = null;
		if(session.getAttribute("shoppingCart")==null)
		{
			shoppingCart = new ShoppingCart();
		}
		else
		{
			shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		}
		shoppingCart.add(orderline);	
		session.setAttribute("shoppingCart", shoppingCart);		

		
		String path = "user/shoppingCart.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
}

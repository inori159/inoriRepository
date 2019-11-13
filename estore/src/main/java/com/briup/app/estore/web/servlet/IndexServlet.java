package com.briup.app.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.service.IBookService;
import com.briup.app.estore.service.impl.BookService;

@WebServlet("/Index")
public class IndexServlet extends HttpServlet {

	private IBookService bookService = new BookService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("customer")==null)
		{
			String path = "/login.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else
		{
			List<Book> bookList = bookService.selectAll();
			req.setAttribute("bookList", bookList);
			String path = "/index.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
}

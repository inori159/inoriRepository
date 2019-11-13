package com.briup.app.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("customer")==null)
		{
			String path = "/login.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else
		{
			HttpSession session = req.getSession();
			session.removeAttribute("customer");
			String path = "/login.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		}
	}
}

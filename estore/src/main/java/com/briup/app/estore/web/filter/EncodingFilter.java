package com.briup.app.estore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.app.estore.bean.Customer;

@WebFilter("*.jsp")
public class EncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {


	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setDateHeader("Expires", 0);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		if(customer!=null)
		{
			String path = "/Index";
			req.getRequestDispatcher(path).forward(req, resp);
			chain.doFilter(req, resp);
		}
		else
		{
			Cookie[] cookies = req.getCookies();
			if(cookies!=null)
			{
				for(Cookie c:cookies)
				{
					if("username".equals(c.getName()))
					{
						String name = c.getValue();
						req.setAttribute("username", name);
					}
					if("password".equals(c.getName()))
					{
						String password = c.getValue();
						req.setAttribute("password", password);
					}
				}	
				String path = "/Login";
				req.getRequestDispatcher(path).forward(req, resp);
				chain.doFilter(req, resp);
			}
			else
			{
				String path = "/login.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				chain.doFilter(req, resp);
			}

			
		}
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

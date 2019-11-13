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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.mapper.CustomerMapper;
import com.briup.app.estore.util.MD5Util;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

//@WebFilter("/*.jsp")
public class LoginFilter implements Filter {

	CustomerMapper mapper = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(CustomerMapper.class);
		String name = null;
		Customer customer = mapper.selectByName(name);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setDateHeader("Expires", 0);
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Pragma", "no-cache");
		Cookie[] cookies = req.getCookies();
		String requestURI = req.getRequestURI();
		if (cookies != null) {
			if ("/login.jsp".equals(requestURI)) {
				for (Cookie c : cookies) {
					String cookieName = c.getName();
					if ("name".equals(cookieName)) {
						String cookievalue = c.getValue();
						Customer customer = mapper.selectByName(cookievalue);
						if (customer != null) {						
							String path = "/index.jsp";
							req.getRequestDispatcher(path).forward(req, resp);
							return;
						}
						chain.doFilter(req, resp);
					}

				}
			}
			else
			{
				chain.doFilter(req, resp);
			}

		} else {
			chain.doFilter(req, resp);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}

package com.briup.app.estore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.mapper.BookMapper;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession();
		BookMapper mapper = sqlSession.getMapper(BookMapper.class);
//		List<Book> list = mapper.selectAll();
//		list.forEach((e)->System.out.println(e.getName()));
		
		Book book = new Book();
		book.setName("test");
		book.setPrice(100.0);
		System.out.println("before:"+book.getId());
		mapper.insert(book);
		System.out.println("after:"+book.getId());
		sqlSession.commit();
	}
}

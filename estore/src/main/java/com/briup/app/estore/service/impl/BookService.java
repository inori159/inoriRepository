package com.briup.app.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Book;
import com.briup.app.estore.mapper.BookMapper;
import com.briup.app.estore.service.IBookService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class BookService implements IBookService{
	
	SqlSession sqlSession = null;
	BookMapper mapper = null;
	public BookService()
	{
		sqlSession = MyBatisSqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(BookMapper.class);
	}
	@Override
	public List<Book> selectAll() {
		List<Book> bookList = mapper.selectAll();
		return bookList;	
	}
}

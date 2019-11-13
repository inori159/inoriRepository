package com.briup.app.estore.service.impl;

import javax.servlet.http.Cookie;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Customer;
import com.briup.app.estore.mapper.CustomerMapper;
import com.briup.app.estore.service.ICustomerService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class CustomerService implements ICustomerService {
	
	SqlSession sqlSession = null;
	CustomerMapper mapper = null;
	public CustomerService()
	{
		sqlSession = MyBatisSqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(CustomerMapper.class);
	}

	@Override
	public void register(Customer c) throws Exception {
		Customer customerFromDB = mapper.selectByName(c.getName());
		if(customerFromDB !=null)
		{
			throw new Exception("用户名不可用"+customerFromDB.getName());
		}
		mapper.insert(c);
		sqlSession.commit();
	}

	@Override
	public boolean login(Customer c) {
		boolean flag = false;
		String name = c.getName();
		Customer customerFromDB = mapper.selectByName(name);
		if(customerFromDB!=null)
		{
			String password = c.getPassword();
			if(customerFromDB.getPassword().equals(password))
			{
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public Customer userInfo(Customer c) {
		Customer customerFromDB = mapper.selectByName(c.getName());	
		return customerFromDB;
	}

	@Override
	public void update(Customer c) {
		Customer customerFromDB = userInfo(c);
		c.setPassword(customerFromDB.getPassword());
		mapper.updateByPrimaryKey(c);
		sqlSession.commit();
		sqlSession.close();
	}

	

}

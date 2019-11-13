package com.briup.app.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.mapper.OrderlineMapper;
import com.briup.app.estore.service.ILineService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class LineService implements ILineService {

	private SqlSession sqlSession = null;
	private OrderlineMapper mapper = null;
	public LineService()
	{
		sqlSession = MyBatisSqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(OrderlineMapper.class);
	}
	@Override
	public List<Orderline> findOrderLineByOrderId(Integer OrderId) {
		List<Orderline> orderlineList = mapper.selectByOrderId(OrderId);
		return orderlineList;
	}
	@Override
	public void deleteByOrderId(Integer OrderId) {
		mapper.deleteByOrderId(OrderId);
		sqlSession.commit();
	}
	@Override
	public void insert(Orderline record) {
		mapper.insert(record);		
		sqlSession.commit();
	}
	

}

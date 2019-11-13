package com.briup.app.estore.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.briup.app.estore.bean.Order;
import com.briup.app.estore.bean.Orderline;
import com.briup.app.estore.mapper.BookMapper;
import com.briup.app.estore.mapper.OrderMapper;
import com.briup.app.estore.mapper.OrderlineMapper;
import com.briup.app.estore.service.IOrderService;
import com.briup.app.estore.util.MyBatisSqlSessionFactory;

public class OrderService implements IOrderService 
{
	private SqlSession sqlSession = null;
	private OrderMapper mapper = null;
	public OrderService()
	{
		sqlSession = MyBatisSqlSessionFactory.openSession();
		mapper = sqlSession.getMapper(OrderMapper.class);
	}
	@Override
	public void saveOrder(Order record) {
		
		try
		{
			mapper.insert(record);
			sqlSession.commit();
			Integer orderId = record.getId();
//			OrderlineMapper orderLineMapper = sqlSession.getMapper(OrderlineMapper.class);
			LineService lineService = new LineService();
			List<Orderline> orderlines = record.getOrderlines();
			for(Orderline o:orderlines)
			{
				o.setOrder(record);
				lineService.insert(o);
			}
			sqlSession.commit();
			sqlSession.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Order> findOrderByCustomerId(Integer customerId) {
		sqlSession.clearCache();
		List<Order> orderList = mapper.selectByCustomerId(customerId);
		sqlSession.close();
		return orderList;
	}

	@Override
	public void deleteById(Integer orderId) {
		LineService lineService = new LineService();
		lineService.deleteByOrderId(orderId);		
		mapper.deleteByPrimaryKey(orderId);	
		sqlSession.commit();
		sqlSession.close();
	}
}

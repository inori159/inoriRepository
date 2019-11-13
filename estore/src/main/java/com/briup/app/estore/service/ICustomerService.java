package com.briup.app.estore.service;

import com.briup.app.estore.bean.Customer;

public interface ICustomerService {
	
	void register(Customer c) throws Exception;
	
	boolean login(Customer c);
	
	Customer userInfo(Customer c);
	
	void update(Customer c);
}

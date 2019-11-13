package com.briup.app.estore.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;

    private Double cost;

    private Date orderdate;

    private Customer customer;
    
    private List<Orderline> orderlines;

    public List<Orderline> getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
	public String toString() {
		return "Order [id=" + id + ", cost=" + cost + ", orderdate=" + orderdate + ", customer=" + customer
				+ ", orderlines=" + orderlines + "]";
	}

	public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

}
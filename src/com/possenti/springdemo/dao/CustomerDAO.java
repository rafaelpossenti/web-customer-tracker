package com.possenti.springdemo.dao;

import java.util.List;
import com.possenti.springdemo.entity.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();
}

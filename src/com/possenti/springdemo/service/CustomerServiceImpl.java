package com.possenti.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.possenti.springdemo.dao.CustomerDAO;
import com.possenti.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	//inject customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional //with this annotation doesn't need the commit and begins transaction
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

}

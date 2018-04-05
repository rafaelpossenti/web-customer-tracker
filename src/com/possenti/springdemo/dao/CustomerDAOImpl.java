package com.possenti.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.possenti.springdemo.entity.Customer;

@Repository //always apply to DAO implementation
public class CustomerDAOImpl implements CustomerDAO {

	//inject the session factory 
	@Autowired
	private SessionFactory  sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		Query<Customer> query = currentSession.createQuery("from Customer",Customer.class);
		
		//execute query and get result list
		List<Customer> customers = query.getResultList();
		
		//return the results 
		return customers;
	}

}

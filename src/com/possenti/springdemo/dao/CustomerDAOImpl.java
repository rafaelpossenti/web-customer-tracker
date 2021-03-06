package com.possenti.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		
		//create a query ... sort by last name
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName",
															Customer.class);
		
		//execute query and get result list
		List<Customer> customers = query.getResultList();
		
		//return the results 
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update the customer 
		currentSession.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve/read from database using the primary key
		Customer customer = currentSession.get(Customer.class,id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete from database using the primary key
		Query query = currentSession.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId",id);
				
		query.executeUpdate();
		
	}


	@Override
	public List<Customer> searchCustomers(String searchName) {
		
		// get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (searchName != null && searchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("name", "%" + searchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

}

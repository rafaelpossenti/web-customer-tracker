package com.possenti.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.possenti.springdemo.dao.CustomerDAO;
import com.possenti.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//inject the customer DAO
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		
		//get customers from the DAO
		List<Customer> customers = customerDAO.getCustomers();
		
		//add the customers to the model
		model.addAttribute("customers",customers); //name,value
		
		return "list-customers";
	}
}

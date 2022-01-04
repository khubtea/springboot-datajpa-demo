/**
 * @author NagiReddy
 */

package com.khub.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khub.boot.model.Customer;
import com.khub.boot.repository.CustomerRepository;

@Transactional //Making Service class as @Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired
	private CustomerRepository customerDAO;
	
	@Override
//	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.findAll();
	}
	
	@Override
//	@Transactional
	public Customer getCustomer(int theId) {
		return customerDAO.findById(theId).get();
	}

	@Override
//	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.save(theCustomer);
	}

	@Override
//	@Transactional
	public void updateCustomer(Customer theCustomer) {
		if(customerDAO.existsById(theCustomer.getId()))
			customerDAO.save(theCustomer);
	}

	@Override
//	@Transactional
	public void deleteCustomer(int theId) {
		if(customerDAO.existsById(theId))
			customerDAO.deleteById(theId);
	}
	
	@Override
	public List<Customer> findByCustomerName(String fName){
		/*
		 * We can use any method
		 */
		//return customerDAO.findByCustomerName(fName);
		return customerDAO.findByFirstNameOrderById(fName);
	}

}

/**
 * @author NagiReddy
 */

package com.khub.boot.service;

import java.util.List;

import com.khub.boot.model.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();

	public Customer getCustomer(int theId);

	public void saveCustomer(Customer theCustomer);
	
	public void updateCustomer(Customer theCustomer);

	public void deleteCustomer(int theId);
	
	public List<Customer> findByCustomerName(String firstName);

}

/**
 * @author NagiReddy
 */

package com.khub.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khub.boot.model.Customer;
import com.khub.boot.service.CustomerService;

@RestController
@RequestMapping("/customerREST")
public class CustomerRESTController {
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/list")
	public List<Customer> getCustomers() {
		return cs.getCustomers();
	}
	
	@GetMapping(path = "/list-{name}", produces = {"application/xml"})
	public List<Customer> getCustomersByName(@PathVariable("name") String fName) {		
		return cs.findByCustomerName(fName);
	}
	
	@GetMapping(path = "/show/{id}", produces = {"application/xml"})
	public Customer getCustomer(@PathVariable("id") int theId) {		
		return cs.getCustomer(theId);	
	}
	
	@PostMapping(path = "/save", consumes = {"application/xml", "application/JSON"})
	public Customer saveCustomer(@RequestBody Customer theCustomer) {
		cs.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		cs.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@DeleteMapping("/delete")
	public int deleteCustomer(@RequestParam("customerId") int theId) {
		cs.deleteCustomer(theId);
		return theId;
	}

}

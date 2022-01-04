/**
 * @author NagiReddy
 */
package com.khub.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khub.boot.model.Customer;
import com.khub.boot.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/list")
	public String getCustomers(Model m) {		
		m.addAttribute("customers",cs.getCustomers());
		return "list-customers";
	}
	
	@GetMapping("/list-{name}")
	public String getCustomersByName(@PathVariable("name") String fName, Model m) {		
		m.addAttribute("customers",cs.findByCustomerName(fName));
		return "list-customers";
	}
	
	@GetMapping("/showForm")
	public String getCustomer(Model m) {		
		Customer theCustomer = new Customer();
		m.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
			cs.saveCustomer(theCustomer);		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		Customer theCustomer = cs.getCustomer(theId);	
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		cs.deleteCustomer(theId);
		return "redirect:/customer/list";
	}

}

package com.capgemini.customerapp.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exception.CustomerNotFoundException;
import com.capgemini.customerapp.repository.CustomerRepository;
import com.capgemini.customerapp.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService ;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(Customer customer)
	{
		ResponseEntity<Customer> responseEntity=new ResponseEntity<Customer>(customerService.addCustomer(customer),HttpStatus.OK) ;
		return responseEntity ;
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findCustomerById(long customerId)
	{
		try {
			ResponseEntity<Customer> responseEntity=new ResponseEntity<Customer>(customerService.findCustomerById(customerId),HttpStatus.OK) ;
			return responseEntity ;
		} catch (CustomerNotFoundException e) {
			// log errors
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND) ;
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(Customer customer)
	{
		try {
			Customer customer1=customerService.findCustomerById(customer.getCustomerId()) ;
			customer1=customerService.updateCustomer(customer) ;
			return new ResponseEntity<Customer>(customer1,HttpStatus.OK) ;
		}
		catch (CustomerNotFoundException e) {
			// log
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND) ;
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(long customerId)
	{
		try {
			Customer customer=customerService.findCustomerById(customerId) ;
			customerService.deleteCustomer(customer);
		}
		catch (CustomerNotFoundException e) {
			// TODO: handle exception
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND) ;
	}
	
	@GetMapping("customers")
	public ResponseEntity<List<Customer>> findAllCustomer()
	{
		List<Customer> customers=customerService.findAllCustomer() ;
		Iterator<Customer> iterable= customers.iterator() ;
		if(iterable.hasNext())
		{
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK) ;
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
		
	}
	
	public ResponseEntity<Customer> authenticate(long customerId,String Password)
	{
		try {
		boolean check=customerService.authenticate(customerService.findCustomerById(customerId)) ;
		return new ResponseEntity<Customer>(true,HttpStatus.OK) ;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
}
}

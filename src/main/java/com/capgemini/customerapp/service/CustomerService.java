package com.capgemini.customerapp.service;

import java.util.List;

import com.capgemini.customerapp.entity.Customer;
import com.capgemini.customerapp.exception.AuthenticationFailedException;
import com.capgemini.customerapp.exception.CustomerNotFoundException;

public interface CustomerService {

	public Customer addCustomer(Customer customer) ;
	
	public Customer updateCustomer(Customer customer) ;
	
	public void deleteCustomer(Customer customer) ;
	
	public Customer findCustomerById(long customerId) throws CustomerNotFoundException ;
	
	public List<Customer> findAllCustomer() ;
	
	public boolean authenticate(Customer customer) throws AuthenticationFailedException, CustomerNotFoundException ;
	
	public boolean updatePassword(Customer customer,String newPassword) throws AuthenticationFailedException, CustomerNotFoundException ;
}

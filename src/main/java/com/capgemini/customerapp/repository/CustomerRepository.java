package com.capgemini.customerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.customerapp.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT CASE WHEN EXISTS (SELECT * FROM Customer WHERE password = :password) THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END")
	public boolean authenticate(@Param("password") String password) ;
}

package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

public Customer findByEmail(String email);

public Customer findByName(String name);
	
	

}

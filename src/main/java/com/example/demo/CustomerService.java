package com.example.demo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService{

	@Autowired
	private CustomerRepo repo;
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Customer customer = repo.findByName(name);
		return new User(customer.getName(), customer.getPassword(), Collections.emptyList());
	}
	
	

}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JWTService;

@RestController
public class MyController {
	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTService jwtService;

	@PostMapping("/register")
	public String saveCustomer(@RequestBody Customer customer) {
		String password=customer.getPassword();
		customer.setPassword(passEncoder.encode(password));
		
		repo.save(customer);
		return "customer saved succesfully";
	}
	
	@PostMapping("/login")
	public String saveCustomer1(@RequestBody Customer customer) {
		System.out.println("Customer: "+customer);
		UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(customer.getName(),customer.getPassword());
		Authentication authenticate = manager.authenticate(token);
		if(authenticate.isAuthenticated()) {
			return jwtService.generateToken(customer.getName());
		}
		return "login failed";
	}
	
	@GetMapping("/welcome")
	
	public String getMsg() {
		return "welcome";
	}

}

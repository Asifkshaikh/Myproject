package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.example.demo.entities.Customer;
import com.example.demo.entities.CustomerReg;
import com.example.demo.entities.Login;
import com.example.demo.entities.PassBasedEnc;
import com.example.demo.entities.Role;
import com.example.demo.entities.SaltValue;
import com.example.demo.services.CustomerService;
import com.example.demo.services.LoginService;
import com.example.demo.services.RoleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CustomerController 
{
	@Autowired
	CustomerService cservice;
	
	@Autowired
	LoginService lservice;
	
	@Autowired
	RoleService rservice;
	
	@Autowired
	SaltValue saltValue;
	
	@GetMapping("/getCustomer")
	public Customer getCustomer(@RequestParam("loginid") int loginid)
	{
		Login l = lservice.getById(loginid);
		return cservice.getCustomer(l); 

	}
	
	@PostMapping("/regCustomer")
	public Customer regCustomer(@RequestBody CustomerReg cs)
	{
		Role r= rservice.getRole(2);
		System.out.println(saltValue.getSalt());
		String encrypted=PassBasedEnc.generateSecurePassword(cs.getPwd(), saltValue.getSalt());
		Login l = new Login(cs.getEmail(),encrypted,r,false);
		Login saved= lservice.save(l);
		
		//Area area=new Area(cs.getCity());
		
		Customer c=new Customer(cs.getFname(),cs.getLname(),cs.getMobile(),saved);
		return cservice.saveCustomer(c);
	}
	
}

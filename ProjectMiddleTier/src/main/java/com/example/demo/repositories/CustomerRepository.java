package com.example.demo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Login;


@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> 
{

	@Query("select c from Customer c where login_id = :l")
	public Customer getCustomer(Login l);
	
	@Modifying
	@Query("update Customer set picture = :file where cust_id = :id")
	public int uploadPicture(int id,byte[] file);
}

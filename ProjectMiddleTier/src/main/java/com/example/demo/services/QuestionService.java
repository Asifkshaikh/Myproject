package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entities.Question;
import com.example.demo.entities.Role;
import com.example.demo.repositories.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questrepo;
	
	public List<Question> getAll()
	{
		return questrepo.findAll();
	}
	
	public Question getQuestion(int id)
	{
		return questrepo.findById(id).get();
	}
}

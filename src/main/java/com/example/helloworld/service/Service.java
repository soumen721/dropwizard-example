package com.example.helloworld.service;

import java.util.List;

import com.example.helloworld.core.Person1;
import com.example.helloworld.db.PersonDAO1;

import io.dropwizard.hibernate.UnitOfWork;

public class Service {
	//private final PersonDAO personDAO;
	private final PersonDAO1 personDAO1;

	public Service(PersonDAO1 personDAO1) {
		//this.personDAO = personDAO;
		this.personDAO1 = personDAO1;
	}
	
	@UnitOfWork(value = "hibernate-B")
	public List<Person1> listPeopleFrmDB1() {
		return personDAO1.findAll();
	}

	@UnitOfWork(value = "hibernate-B")
	public List<Person1> listPeopleFrmDB2() {
		return personDAO1.findAll();
	}

}
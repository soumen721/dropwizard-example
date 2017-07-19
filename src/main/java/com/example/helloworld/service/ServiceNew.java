package com.example.helloworld.service;

import java.util.List;

import com.example.helloworld.core.Person;
import com.example.helloworld.core.Person1;
import com.example.helloworld.db.PersonDAO;
import com.example.helloworld.db.PersonDAO1;

import io.dropwizard.hibernate.UnitOfWork;

public class ServiceNew {
	private final PersonDAO personDAO;
	private final PersonDAO1 personDAO1;

	public ServiceNew(PersonDAO personDAO, PersonDAO1 personDAO1) {
		this.personDAO = personDAO;
		this.personDAO1 = personDAO1;
	}

	public List<Person1> listPeopleFrmDB() {
		listPeopleFrmDB1();
		listPeopleFrmDB2();
		
		return null;
	}
	
	@UnitOfWork(value = "hibernate-B")
	public List<Person> listPeopleFrmDB1() {
		return personDAO.findAll();
	}

	@UnitOfWork(value = "hibernate-B")
	public List<Person1> listPeopleFrmDB2() {
		return personDAO1.findAll();
	}
}
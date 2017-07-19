package com.example.helloworld.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.core.Person;
import com.example.helloworld.core.Person1;
import com.example.helloworld.db.PersonDAO;
import com.example.helloworld.db.PersonDAO1;
import com.example.helloworld.service.Service;
import com.example.helloworld.service.ServiceNew;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/peoplenew")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResourceNew {

	private ServiceNew service;

	public PeopleResourceNew(ServiceNew service) {
		this.service = service ;
	}
	
	@GET
	//@UnitOfWork(value = "hibernate-A")
	@Timed
	//@Metered 
	public List<Person1> listPeople() {
		return service.listPeopleFrmDB();
	}

}

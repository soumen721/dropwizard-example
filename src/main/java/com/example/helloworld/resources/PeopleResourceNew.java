package com.example.helloworld.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.core.Person1;
import com.example.helloworld.service.ServiceNew;

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

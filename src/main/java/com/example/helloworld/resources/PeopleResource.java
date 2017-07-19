package com.example.helloworld.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.core.Person;
import com.example.helloworld.db.PersonDAO;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {

	private final PersonDAO personDAO;
	
	public PeopleResource(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@POST
	@UnitOfWork(value = "hibernate-A")
	@Timed
	public Response createPerson(Person person) {
		Optional<Person> per = personDAO.findById(person.getId()) ;
		if(per.isPresent()) {
			return Response.ok(personDAO.update(person)).build();
			
		} else {
			return Response.ok(personDAO.create(person)).build();
		}
		//return Response.ok(personDAO.update(person)).build();
	}

	@GET
	@UnitOfWork(value = "hibernate-A")
	@Timed
	public List<Person> listPeople() {
		return personDAO.findAll();
	}

	@Path("/{id}")
	@GET
	@UnitOfWork(value = "hibernate-A")
	@Timed
	public Response findPeople(@PathParam("id") LongParam id) {
		Optional<Person> p = personDAO.findById(id.get());
		if (p.isPresent()) {
        	return Response.ok(p).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
	}
	
	@DELETE
	@Path("/{id}")
	@UnitOfWork(value = "hibernate-A")
	@Timed
    public Response removeEmployeeById(@PathParam("id") LongParam id) {
		
		Optional<Person> p = personDAO.findById(id.get());
        if (p.isPresent()) {
        	personDAO.removeById(p.get());
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
	}
	
}

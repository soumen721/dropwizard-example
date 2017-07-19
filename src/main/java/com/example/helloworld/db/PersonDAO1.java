package com.example.helloworld.db;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.example.helloworld.core.Person1;

import io.dropwizard.hibernate.AbstractDAO;

@Repository
public class PersonDAO1 extends AbstractDAO<Person1> {
	
	public PersonDAO1(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Person1> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Person1 create(Person1 person) {
    	persist(requireNonNull(person));
    	//currentSession().remove(person);
    	//int i = 0/0;
    	return person;
    }

    @SuppressWarnings("unchecked")
	public List<Person1> findAll() {
        return list(namedQuery("com.example.helloworld.core.Person1.findAll"));
    }
}

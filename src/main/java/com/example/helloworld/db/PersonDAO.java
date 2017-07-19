package com.example.helloworld.db;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.example.helloworld.core.Person;

import io.dropwizard.hibernate.AbstractDAO;

@Repository
public class PersonDAO extends AbstractDAO<Person> {
	
	public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Person update(Person person) {
   		return (Person) currentSession().merge(requireNonNull(person));
    }

    public Person create(Person person) {
   		Long id = (Long) currentSession().save(person);
   		person.setId(id);
   		return person;
    }
  

    @SuppressWarnings("unchecked")
	public List<Person> findAll() {
        return list(namedQuery("com.example.helloworld.core.Person.findAll"));
    }
    
    public void removeById(Person person) {
        currentSession().remove(person);
    }
}

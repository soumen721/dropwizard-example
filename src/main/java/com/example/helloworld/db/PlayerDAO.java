package com.example.helloworld.db;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.example.helloworld.core.Player;

import io.dropwizard.hibernate.AbstractDAO;

public class PlayerDAO extends AbstractDAO<Player> {
	
	public PlayerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Player> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Player update(Player person) {
   		return (Player) currentSession().merge(requireNonNull(person));
    }

    public Player create(Player person) {
   		Long id = (Long) currentSession().save(person);
   		person.setId(id);
   		return person;
    }
  

    @SuppressWarnings("unchecked")
	public List<Player> findAll() {
        return list(namedQuery("Player.findByName"));
    }
    
    public void removeById(Player person) {
        currentSession().remove(person);
    }
}

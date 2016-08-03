package br.com.igoraraujo91.voteNoRestaurante.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAO<T> implements DAO<T> {
	
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	@Transactional
	public void create(T entry) {
		sessionFactory.getCurrentSession().save(entry);		
	}

	@Override
	public abstract T readOne(Object keyValue);

	@Override
	public abstract List<T> readAll();

	@Override
	@Transactional
	public void update(T entry) {
		sessionFactory.getCurrentSession().update(entry);
	}

}

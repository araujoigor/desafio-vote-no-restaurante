package br.com.igoraraujo91.voteNoRestaurante.dao;

import java.util.List;

public interface DAO<T> {
	public void create(T entry);
	public T readOne(Object keyValue);
	public List<T> readAll();
	public void update(T entry);	
}

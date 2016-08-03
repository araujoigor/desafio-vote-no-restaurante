package br.com.igoraraujo91.voteNoRestaurante.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.igoraraujo91.voteNoRestaurante.models.Restaurant;
import br.com.igoraraujo91.voteNoRestaurante.models.Vote;

public class VoteDAO extends AbstractDAO<Vote> {
	
	@Transactional
	@Override
	public Vote readOne(Object keyValue){
		if(!(keyValue instanceof String))
			throw new IllegalArgumentException("Value parameter for class Vote should be of type String");
		
		String val = keyValue.toString();
		
		Query q = sessionFactory.getCurrentSession().createQuery("from Vote where userEmail='" + val + "'");
		
		return (Vote)q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Vote> readAll(){
		Session s = sessionFactory.getCurrentSession();
		Criteria c = s.createCriteria(Restaurant.class);
		Criteria c2 = c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c2.list();
	}
}

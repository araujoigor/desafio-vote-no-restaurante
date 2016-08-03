package br.com.igoraraujo91.voteNoRestaurante.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.com.igoraraujo91.voteNoRestaurante.models.Restaurant;

@Repository
public class RestaurantDAO extends AbstractDAO<Restaurant> {
	
	@Transactional
	@Override
	public Restaurant readOne(Object keyValue){
		if(!(keyValue instanceof String))
			throw new IllegalArgumentException("Value parameter for class Restaurant should be of type String");
		
		String val = keyValue.toString();
		
		Query q = sessionFactory.getCurrentSession().createQuery("from Restaurant where name='" + val + "'");
		
		return (Restaurant)q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Restaurant> readAll(){
		Session s = sessionFactory.getCurrentSession();
		Criteria c = s.createCriteria(Restaurant.class);
		Criteria c2 = c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return c2.list();
	}

}

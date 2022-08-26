package com.simplehomeinsurance.claims_management_system.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public User getUser(int userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		User user = currentSession.get(User.class, userId);
		
		return user;
	}
	
	public User getUserbyUsername(String username) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = currentSession.createQuery("from User where username = '"
															+ username +"'", User.class);
		
		User user = query.getSingleResult();
		
		return user;
	}
	
}

package com.test.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.test.modal.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User getUser(String mobile) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		return (User) criteria.add(Restrictions.eq("mobile", mobile)).uniqueResult();
	}

	@Override
	public List<User> getAllUsers() {
		Session session = getSession();
		Criteria criteria = session.createCriteria(User.class);
		return (List<User>) criteria.list();
	}

}
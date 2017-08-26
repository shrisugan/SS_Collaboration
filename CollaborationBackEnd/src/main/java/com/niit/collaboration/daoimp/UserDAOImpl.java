package com.niit.collaboration.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> list() {
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
	}

	@Transactional	
	public User create(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}
	@Transactional
	public User saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

	@Transactional
	public void delete(int userId) {
		User userToDelete = new User();
		userToDelete.setUserid(userId);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	@Transactional
	public User getByUserId(int userid) {
		
		String hql = "from User where userid ='" + userid + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) (query).list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
  
	}  

	@Transactional
	public User getByMail(String email) {
		String hql = "from User where email ='" + email + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) (query).list();

		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}
	@Transactional
	public User get(String empID) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, empID);
		return user;
	}
	

	@Transactional
	public User login(User user) {
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		String hql = "from User where email=" + "'" + user.getEmail() + "'   and password = " + "'"+ user.getPassword() +"'";
	
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();

		if (list != null && !list.isEmpty()) {
			return list.get(0);    
			
		}
		return null;
	}
}

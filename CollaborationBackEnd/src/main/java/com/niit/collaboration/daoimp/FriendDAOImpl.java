package com.niit.collaboration.daoimp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

@Repository("FriendDAO")
public class FriendDAOImpl implements FriendDAO  {
	
	@Autowired
	private SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<Friend> list() {
	
		@SuppressWarnings("unchecked")
		List<Friend> friendList = sessionFactory.getCurrentSession().createQuery("from Friend").list();
		return friendList;
	}
	
	
@Transactional
	public List<Friend> getByUser(int userid) {
		Session session=sessionFactory.openSession();
		
		String hql = "from Friend where userid ='" + userid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> friens=query.list();
		session.close();
		return friens;
	}
@Transactional
public List<Friend> getByName(String username) {
	Session session=sessionFactory.openSession();

	String hql = "from Friend where friendname =" + "'" + username + "' and status = " + "'R'";
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	List<Friend> frien=query.list();
	session.close();
	return frien;
}
@Transactional
public List<Friend> getByFriendName(String username) {
	Session session=sessionFactory.openSession();
	String hql = "from Friend where username =" + "'" + username + "' and status = " + "'A'";
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> listFriend = (List<Friend>) query.list();
	session.close();
	return listFriend;
}


@Transactional
	public void save(Friend friend) {
		
		sessionFactory.getCurrentSession().save(friend);
	}
@Transactional
public List<Friend> getByFriendAccepted(String friendname){
	String hql = "from Friend where friendname =" + "'" + friendname + "' and status = " + "'A'";
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Friend> listFriend = (List<Friend>) query.list();
	return listFriend;
}


	@Transactional
	public Friend saveOrUpdate(Friend friend) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(friend);
		return friend;
	}
	
	@Transactional
	public List<Friend> list(int userid) {
		String hql = "from Friend where userid =" + "'" + userid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();

		return listFriend;
	}
	@Transactional
	public void delete(int friendid) {
		Friend frnd = new Friend();
		frnd.setFriendid(friendid);
		sessionFactory.getCurrentSession().delete(frnd);
		}

	@Transactional
	public Friend getByFriendId(int friendid) {
	
		Friend friendListByID = (Friend) sessionFactory.getCurrentSession().get(Friend.class, friendid);

		return friendListByID;

	}
	@Transactional
	public List<Friend> getByFriendAccepted1(String name) {
		String hql = "from Friend where username =" + "'" + name + "' and status = " + "'A'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Friend> listFriend = (List<Friend>) query.list();
		return listFriend;
	}
  }

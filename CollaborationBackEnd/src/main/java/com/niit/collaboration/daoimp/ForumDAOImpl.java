package com.niit.collaboration.daoimp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.ForumDAO;
import com.niit.collaboration.model.Forum;

@Repository("ForumDAO")
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Forum> list() {
		List<Forum> listForum = sessionFactory.getCurrentSession().createQuery("from Forum").list();
		return listForum;
	}
	@Transactional
	public Forum getByForumid(int forumid) {

		Forum ForumId = (Forum) sessionFactory.getCurrentSession().get(Forum.class, forumid);

		return ForumId;
	}

	@Transactional
	public Forum getByUsername(String username) {
		Forum Name = (Forum) sessionFactory.getCurrentSession().get(Forum.class, username);

		return Name;
	}

	@Transactional
	public void delete(int forumId) {

		Forum forumToDelete = new Forum();
		forumToDelete.setForumid(forumId);
		sessionFactory.getCurrentSession().delete(forumToDelete);
	}

	@Transactional
	public Forum saveOrUpdate(Forum forum) {
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
        return forum;
	}
	@Transactional
	public List<Forum> getAcceptedList() {
		String hql = "from Forum where status = " + "'A'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Forum> list = (List<Forum>) query.list();
		
		return list;
	}
	@Transactional
	public List<Forum> getNotAcceptedList() {
		String hql = "from Forum where status = " + "'NA'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Forum> list = (List<Forum>) query.list();
		
		return list;

	}
}

package com.niit.collaboration.daoimp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.BlogcommentDAO;
import com.niit.collaboration.model.Blogcomment;

@Repository("BlogcommentDAO")
public class BlogcommentDAOImpl implements BlogcommentDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public BlogcommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Blogcomment> list() {
		@SuppressWarnings({ "unchecked" })
		List<Blogcomment> listBlogcomment = (List<Blogcomment>) sessionFactory.getCurrentSession().createCriteria(Blogcomment.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listBlogcomment;
	}
	@Transactional
	public List<Blogcomment> getBlogComments(int blogid) {
		String hql = "from Blogcomment where blogid ='" + blogid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blogcomment> listBlogcomment = (List<Blogcomment>) query.list();
		
		return listBlogcomment;
	}
	@Transactional
	public Blogcomment saveOrUpdate(Blogcomment bcomment) {
		sessionFactory.getCurrentSession().saveOrUpdate(bcomment);
		return bcomment;
		

	}
	@Transactional
	public void delete(int id) {
		Blogcomment commentToDelete = new Blogcomment();
		commentToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(commentToDelete);

	}

	@Transactional
	public Blogcomment getBlogComment(int blogCommentid) {
		String hql = "from Blogcomment where id ='" + blogCommentid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Blogcomment> listComment = (List<Blogcomment>) query.list();
		if (listComment != null && !listComment.isEmpty()) {
			return listComment.get(0);
		}
		return null;
	}
	@Transactional
	public Blogcomment save(Blogcomment bcomment) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(bcomment);
		return bcomment;
	}
}

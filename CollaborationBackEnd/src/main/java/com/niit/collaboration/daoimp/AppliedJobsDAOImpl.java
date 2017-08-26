package com.niit.collaboration.daoimp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.AppliedJobsDAO;
import com.niit.collaboration.model.AppliedJobs;

@Transactional
@Repository("AppliedJobsDAO")
public class AppliedJobsDAOImpl implements AppliedJobsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public AppliedJobsDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<AppliedJobs> list() {
		return sessionFactory.getCurrentSession().createQuery("from AppliedJobs").list();
	}

	public List<AppliedJobs> getByJobId(int jobid) {
		Session session=sessionFactory.openSession();
		
		String hql = "from AppliedJobs where jobid ='" + jobid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AppliedJobs> ajobs=query.list();
		session.close();
		return ajobs;
	}

	public List<AppliedJobs> getByUserName(String username) {
		Session session=sessionFactory.openSession();
		String hql = "from AppliedJobs where username ='" + username + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		@SuppressWarnings("unchecked")
		List<AppliedJobs> uemails=query.list();
		session.close();
		return uemails;
	}

	public void saveOrUpdate(AppliedJobs ajob) {
		sessionFactory.getCurrentSession().saveOrUpdate(ajob);
		
	}

	public AppliedJobs getByAJobId(String jobid) {   
		AppliedJobs ajobListByID = (AppliedJobs) sessionFactory.getCurrentSession().get(AppliedJobs.class, jobid);

		return ajobListByID;
	}

	public void delete(int jobid) {
		AppliedJobs ajobDelete = new AppliedJobs();
		ajobDelete.setJobid(jobid);
		sessionFactory.getCurrentSession().delete(ajobDelete);
		
	}
	public List<AppliedJobs> getByUserId(int userid) {
		
		Session session=sessionFactory.openSession();
		String hql = "from AppliedJobs where userid ='" + userid + "'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AppliedJobs> uemails=query.list();
		session.close();
		return uemails;
	}
}

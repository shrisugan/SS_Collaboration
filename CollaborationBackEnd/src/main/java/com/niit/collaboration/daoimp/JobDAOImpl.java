package com.niit.collaboration.daoimp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.AppliedJobs;
import com.niit.collaboration.model.Job;

@Repository("JobDAO")
public class JobDAOImpl implements JobDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> list() {
		List<Job> listJob = sessionFactory.getCurrentSession().createQuery("from Job").list();
		return listJob;
	}
	@Transactional
	public Job saveOrUpdate(Job job) {
		sessionFactory.getCurrentSession().saveOrUpdate(job);
		return job;
}
	@Transactional
	public void delete(int jobId) {
		Job jobToDelete = new Job();
		jobToDelete.setJobid(jobId);
		sessionFactory.getCurrentSession().delete(jobToDelete);
	}
	@Transactional
	public Job getByJobid(int jobId){ 
		Job JobId = (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);

		return JobId;
	}
	
	@Transactional
	public Job getJobDetails(int jobid) {
		
		Job job =  (Job)sessionFactory.getCurrentSession().get(Job.class, jobid);
		return job;
	}

	@Transactional
	public List<Job> getMyAppliedJobs(String email) {
		
		String hql = "from AppliedJobs where email ='"+ email +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
		
	}
	@Transactional
	public AppliedJobs getJobApplication(String userID, int jobID) {
		
		String hql = "from AppliedJobs where userid ='"+ userID + "' and jobid='"+jobID + "'";
		
		return (AppliedJobs) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		
	}

}

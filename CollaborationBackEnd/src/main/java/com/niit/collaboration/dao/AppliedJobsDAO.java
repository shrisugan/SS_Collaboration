package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.AppliedJobs;

public interface AppliedJobsDAO {

public List<AppliedJobs> getByJobId(int jobid);    
	
	public List<AppliedJobs> getByUserName(String username); 
	
	public List<AppliedJobs> getByUserId(int userid);
	
	public void saveOrUpdate(AppliedJobs ajob);

	public AppliedJobs getByAJobId(String jobid);
	
	
	public void delete(int jobid);

	public List<AppliedJobs> list();
}

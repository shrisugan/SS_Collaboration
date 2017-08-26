package com.niit.collaboration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.AppliedJobsDAO;
import com.niit.collaboration.model.AppliedJobs;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

@RestController
public class AppliedJobsController {
	@Autowired
	private AppliedJobsDAO  appliedJobsDAO;

	
public AppliedJobsDAO getAppliedJobsDAO() {
		return appliedJobsDAO;
	}

	public void setAppliedJobsDAO(AppliedJobsDAO appliedJobsDAO) {
		this.appliedJobsDAO = appliedJobsDAO;
	}

@GetMapping("/allappliedjobs")

public List<AppliedJobs> ajobs()
	{	
	return appliedJobsDAO.list();
}

@GetMapping("/alljobid/{jobid}")
public List<AppliedJobs> ajobsid(@PathVariable("jobid")int jobid)
{	
return appliedJobsDAO.getByJobId(jobid);
}

@GetMapping("/alljobuserid/{userid}") 
public List<AppliedJobs> ajobsuid(@PathVariable("userid") int userid)
{	
return appliedJobsDAO.getByUserId(userid);
}

@GetMapping("/alljobusername/{username}") 
public List<AppliedJobs> ajobsuname(@PathVariable("username")String username)
{	
return appliedJobsDAO.getByUserName(username);
}


@SuppressWarnings({ "rawtypes", "unchecked" })
@GetMapping("/appliedjobs/{ajobid}") 
public ResponseEntity getaJobs(@PathVariable("ajobid") String jobid) {

	AppliedJobs ajob = appliedJobsDAO.getByAJobId(jobid);
	if (ajob == null) {
		return new ResponseEntity("No  Applied Job found for ID " + jobid, HttpStatus.NOT_FOUND);
	}

	return new ResponseEntity(ajob, HttpStatus.OK);
}
@SuppressWarnings({ "rawtypes", "unchecked" })
@PostMapping(value = "/appliedjobs")
public ResponseEntity applyJob(@RequestBody AppliedJobs appliedJobs, HttpSession session) {
	User user = (User) session.getAttribute("user");
	appliedJobs.setDateTime(new Date());
	appliedJobs.setUserid(user.getUserid());
	appliedJobs.setEmail(user.getEmail());
	appliedJobs.setTimeStamp(user.getTimeStamp());
	appliedJobsDAO.saveOrUpdate(appliedJobs);

	return new ResponseEntity(appliedJobs, HttpStatus.OK);
}
@SuppressWarnings({ "rawtypes", "unchecked" })
@DeleteMapping("/appliedjobs/{jobid}") 
public ResponseEntity deleteaJob(@PathVariable String id) {
	AppliedJobs ajob=appliedJobsDAO.getByAJobId(id);
		if (ajob==null) {
		return new ResponseEntity("No Job found for ID " + id, HttpStatus.NOT_FOUND);
	}
		//appliedJobsDAO.delete(id);
	return new ResponseEntity(id, HttpStatus.OK);

}

}
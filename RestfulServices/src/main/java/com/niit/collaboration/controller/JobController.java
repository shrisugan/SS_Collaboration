package com.niit.collaboration.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.AppliedJobs;
import com.niit.collaboration.model.Job;

@RestController
public class JobController {

	@Autowired
	private JobDAO jobDAO;
	@Autowired
	private AppliedJobs appliedJobs;
	@Autowired
	HttpSession httpSession;
	
	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
	
	
	public AppliedJobs getAppliedJobs() {
		return appliedJobs;
	}

	public void setAppliedJobs(AppliedJobs appliedJobs) {
		this.appliedJobs = appliedJobs;
	}

	@GetMapping("/job")
	public ResponseEntity<List<Job>> getJobs() {
		List<Job> listjob = jobDAO.list();
		return new ResponseEntity<List<Job>>(listjob, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/job/{jobid}")
	public ResponseEntity getByJobId(@PathVariable("jobid") int jobid) {

		Job job = jobDAO.getByJobid(jobid);
		if (job == null) {
			return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/job")
	public ResponseEntity createjob(@RequestBody Job job) {
		job.setPostdate(new Date());

		jobDAO.saveOrUpdate(job);

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/job/{jobid}")
	public ResponseEntity deleteJob(@PathVariable int jobid) {
		Job job=jobDAO.getByJobid(jobid);
 		if (job==null) {
			return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		}
 		jobDAO.delete(jobid);
		return new ResponseEntity(jobid, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/job/{jobid}")
	public ResponseEntity saveorupdateUser(@PathVariable int jobid, @RequestBody Job job) {

		job = jobDAO.saveOrUpdate(job);
		if (null == job) {
			return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(job, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	 @GetMapping("/getJobDetails/{jobid}")
		public ResponseEntity<List<Job>>JobDetails(@PathVariable int jobid) {
		
		 Job job = jobDAO.getJobDetails(jobid);
		 if (job == null) {
			 return new ResponseEntity("No Job found for ID " + jobid, HttpStatus.NOT_FOUND);
		 }
		 return new ResponseEntity(job, HttpStatus.OK);
			}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getMyAppliedJobs/", method = RequestMethod.GET)
     public ResponseEntity<List<AppliedJobs>> getMyAppliedJobs(@PathVariable int id) {
		
		List<AppliedJobs> listappliedJobs = new ArrayList <AppliedJobs>();
		if(listappliedJobs == null||listappliedJobs.isEmpty()){
			return new ResponseEntity("No appliedJob found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<AppliedJobs>>(listappliedJobs,HttpStatus.OK);
			
		}
	
	}


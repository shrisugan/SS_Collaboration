package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;

public class JobDAOTestCase {
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static JobDAO jobDAO;

	@Autowired
	static Job job;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();


		jobDAO = (JobDAO) context.getBean("JobDAO");

		job = (Job) context.getBean("job");
	}

	@Test
	public void createJobTestCase() {

		job.setQualification("BE");
	   	job.setCompanyname("CTS");
	   	job.setTitle("software developer");
	   	job.setSkills("skills");
	   	jobDAO.saveOrUpdate(job);

	}

}

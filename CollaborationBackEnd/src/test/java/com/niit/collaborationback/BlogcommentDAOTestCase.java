package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.BlogcommentDAO;
import com.niit.collaboration.model.Blogcomment;



public class BlogcommentDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static BlogcommentDAO blogcommentDAO;

	@Autowired
	static Blogcomment blogcomment;
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();
		
		blogcommentDAO = (BlogcommentDAO) context.getBean("BlogcommentDAO");

		blogcomment = (Blogcomment) context.getBean("blogcomment");
	}
	@Test
	public void createBlogcommentTestCase() {
		
		blogcomment.setBcomment("bcomment");
		blogcomment.setEmail("mm@gmail.com");
		
		blogcommentDAO.saveOrUpdate(blogcomment);
	}
}

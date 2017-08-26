package com.niit.collaborationback;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.CommentDAO;
import com.niit.collaboration.model.Comment;

public class CommentDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static CommentDAO commentDAO;

	@Autowired
	static Comment comment;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();

		
		commentDAO = (CommentDAO) context.getBean("CommentDAO");

		comment = (Comment) context.getBean("comment");
	}
	@Test
	public void createCommentTestCase() {

		comment.setUserName("mnhjknh");
		comment.setComments("ghvgg");
		
		commentDAO.saveOrUpdate(comment);
		

	}
}
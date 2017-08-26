package com.niit.collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

public class FriendDAOTestCase {
	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static FriendDAO friendDAO;

	@Autowired
	static Friend friend;

	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();

		// get the userDAO from context
		friendDAO = (FriendDAO) context.getBean("FriendDAO");

		friend = (Friend) context.getBean("friend");
	}

	@Test
	public void createFriendTestCase() {

		friend.setStatus("friend");
		friend.setIsOnline("ON");
		friend.setFriendname("jeni");
	   	friendDAO.saveOrUpdate(friend);

	}

}

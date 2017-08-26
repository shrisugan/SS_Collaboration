package com.niit.collaborationback;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;

public class EventDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	@Autowired
	static EventDAO eventDAO;
	@Autowired
	static Event event;
	
	@BeforeClass
	public static void initialize() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration");
		context.refresh();
		
		eventDAO = (EventDAO) context.getBean("EventDAO");
		event = (Event) context.getBean("event");
   }
	@Test
	public void createEventTestCase() {
       event.setEventcategory("inagration");
       eventDAO.saveOrUpdate(event);
	}
	}

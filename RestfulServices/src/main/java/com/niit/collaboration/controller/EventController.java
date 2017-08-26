package com.niit.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;

@RestController
public class EventController {

	@Autowired
	private EventDAO eventDAO;
	
	public EventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	@GetMapping("/event")
	public ResponseEntity<List<Event>> getEvents() {
		List<Event> listevent = eventDAO.list();
		return new ResponseEntity<List<Event>>(listevent, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/event/{eventid}")
	public ResponseEntity deleteEvent(@PathVariable("eventid") int eventid) {
		
		eventDAO.delete(eventid);
		return new ResponseEntity(eventid, HttpStatus.OK);
	}

	@GetMapping("/event/{eventid}")
	public ResponseEntity<Event>getByEventid(@PathVariable("eventid") int eventid) {
  
		Event event = eventDAO.getByEventid(eventid);  
		
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	
}
	@PostMapping("/event")
	public ResponseEntity<Event> insertEvent(@RequestBody Event event)
	{
		eventDAO.insert(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/event/{eventid}")
	public ResponseEntity saveOrUpdate(@RequestBody Event event)
	{
		eventDAO.saveOrUpdate(event);
		return new ResponseEntity(event, HttpStatus.OK);
	}

}

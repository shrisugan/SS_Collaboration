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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.ForumDAO;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.User;

@RestController
public class ForumController {

	@Autowired
	private ForumDAO forumDAO;
	
	
	
	@GetMapping("/forum")
	public ResponseEntity<List<Forum>> getForum() {
		List<Forum> listforum = forumDAO.list();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/forum/{forumid}")
	public ResponseEntity getByForumid(@PathVariable("forumid") int forumid) {

		Forum forum = forumDAO.getByForumid(forumid);
		if (forum == null) {
			return new ResponseEntity("No Forum found for ID " + forumid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/forum")
	public ResponseEntity createForum(@RequestBody Forum forum,HttpSession session) {
		forum.setCreatedate(new Date());
		User user = (User) session.getAttribute("user"); 
		forum.setStatus("NA");
		forum.setUserid(user.getUserid());
		forum.setUsername(user.getUsername());
		forumDAO.saveOrUpdate(forum);
        return new ResponseEntity(forum, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/forum/{forumid}")
	public ResponseEntity deleteForum(@PathVariable("forumid") int forumid) {
		Forum forum=forumDAO.getByForumid(forumid);
 		if (forum==null) {
			return new ResponseEntity("No Forum found for ID " + forumid, HttpStatus.NOT_FOUND);
		}
 		forumDAO.delete(forumid);
		return new ResponseEntity(forumid, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/forum/{forumid}")
	public ResponseEntity saveorupdateForum(@PathVariable int forumid, @RequestBody Forum forum) {

		forum = forumDAO.saveOrUpdate(forum);
		if (null == forum) {
			return new ResponseEntity("No Forum found for ID " + forumid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(forum, HttpStatus.OK);
	}
	@GetMapping("/acceptedforum")
	public ResponseEntity<List<Forum>> acceptedForumsList() {
		List<Forum> listforum = forumDAO.getAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@GetMapping("/notAcceptedforum")
	public ResponseEntity<List<Forum>> notAcceptedForumList() {
		List<Forum> listforum = forumDAO.getNotAcceptedList();
		return new ResponseEntity<List<Forum>>(listforum, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/acceptForum")
	public ResponseEntity acceptForum(@RequestBody Forum forum){
		forum.setStatus("A");
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity(forum, HttpStatus.OK);
	}

}

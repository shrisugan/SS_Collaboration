package com.niit.collaboration.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers() {
		List<User> listuser = userDAO.list();
		return new ResponseEntity<List<User>>(listuser, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/users/{userid}")
	public ResponseEntity getByUserId(@PathVariable("userid") int userid) {

		User user = userDAO.getByUserId(userid);
		if (user == null) {
			return new ResponseEntity("No User found for ID " + userid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/user")
	public ResponseEntity createUser(@RequestBody User user) {
		userDAO.create(user);
		String sendTo = user.getEmail();
		
		String subject = "Registration Successufully";
		
		String message = "Your Email id " + user.getEmail()+ " was successfully Registred";
		
	
        /*
        	Follow these steps:

        	1.Login to Gmail. 
        	2.Access the URL as https://www.google.com/settings/security/lesssecureapps 
        	3.Select "Turn on"
        */
        
    SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(sendTo);
        email.setSubject(subject);
        email.setText(message);
       
        
        mailSender.send(email);
        
		

		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/user/{userid}")
	public ResponseEntity deleteUser(@PathVariable int userid) {
		User user=userDAO.getByUserId(userid);
 		if (user==null) {
			return new ResponseEntity("No User found for ID " + userid, HttpStatus.NOT_FOUND);
		}
 		userDAO.delete(userid);
		return new ResponseEntity(userid, HttpStatus.OK);
   }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/user/{userid}")
	public ResponseEntity saveorupdateUser(@PathVariable int userid, @RequestBody User user) {

		user = userDAO.saveOrUpdate(user);
		/*if (null == user) {
			return new ResponseEntity("No User found for ID " + userid, HttpStatus.NOT_FOUND);
		}*/

		return new ResponseEntity(user, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/user/{email}")
	public ResponseEntity<User> getByMail(@PathVariable("email") String email) {

		User user = userDAO.getByMail(email);

		if (user == null) {
			return new ResponseEntity("No User found for email " + email, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User validUser = userDAO.login(user);
		if (validUser == null) {
			Error error = new Error("Invalid credentials.. please enter valid username and password");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			
			
			
			session.setAttribute("user", validUser);

			System.out.println(validUser.getEmail());
			System.out.println(validUser.getUsername());
			User user1 = (User) session.getAttribute("user");
			System.out.println(user1.getRole());
			System.out.println(user1.getContact());
			return new ResponseEntity<User>(validUser, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error("Unauthorized user.. Please Login..");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			// user.setOnline(false);
			userDAO.saveOrUpdate(user);
			session.removeAttribute("user");
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}

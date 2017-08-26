package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDAO {
	
	public List<User> list();

	public User saveOrUpdate(User user);
	
	public User create(User user);
	
	public void delete(int userId);

	public User getByUserId(int userid);

	public User getByMail(String email);

	public User get(String empID);

	public User login(User user);
}

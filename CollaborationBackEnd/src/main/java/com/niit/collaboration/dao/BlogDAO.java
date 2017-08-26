package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Blog;

public interface BlogDAO {
	
	public List<Blog> list();

	public Blog saveOrUpdate(Blog blog);
	
	public void delete (int blogId);
	  
	public Blog getById(int blogId);
	
	public Blog getByTitle(String title);

	public void insert(Blog blog);

	public Blog getAllBlog(int blogid);
	
    public List<Blog> getAcceptedList();
	
	public List<Blog> getNotAcceptedList();
}

package com.niit.collaboration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.User;

@RestController
public class BlogController {
	
	@Autowired
	BlogDAO blogDAO;
	
  	
   @RequestMapping(value="/Blog", method=RequestMethod.GET)
   public ResponseEntity<List<Blog>> Blog() {
	List<Blog> listblog = blogDAO.list();
	  
	return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
    }
   @GetMapping("/acceptedblog")
	public ResponseEntity<List<Blog>> acceptedBlogsList() {
		List<Blog> listblog = blogDAO.getAcceptedList();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}
	@GetMapping("/notAcceptedblog")
	public ResponseEntity<List<Blog>> notAcceptedBlogList() {
		List<Blog> listblog = blogDAO.getNotAcceptedList();
		return new ResponseEntity<List<Blog>>(listblog, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/acceptBlog")
	public ResponseEntity acceptBlog(@RequestBody Blog blog){
		blog.setStatus("A");
		 blogDAO.saveOrUpdate(blog);
		return new ResponseEntity(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/insertBlog", method = RequestMethod.POST)
	public ResponseEntity<String> insertBlog(@RequestBody Blog blog,HttpSession session) {
		blog.setCreatedate(new Date());
		blog.setStatus("NA");
		
		User user = (User) session.getAttribute("user");   
		System.out.println(blog.getTitle());
		blog.setUserid(user.getUserid());
		blog.setUsername(user.getUsername());
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity(blog, HttpStatus.OK);
	}

	@RequestMapping(value ="/deleteBlog/{blogid}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteBlog(@PathVariable("blogid") int blogid) {
		blogDAO.delete(blogid);
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/updateBlog/{blogid}",method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("blogid") int blogid,@RequestBody Blog blog)
	{
		blog = blogDAO.saveOrUpdate(blog);
		if (null == blog) {
			return new ResponseEntity("No Blog found for ID " + blogid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value ="/getBlog/{blogid}", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> getAllBlog(@PathVariable("blogid") int blogid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Blog blog = blogDAO.getAllBlog(blogid);
		if (blog == null) {
			return new ResponseEntity("No Blog found for ID " + blogid, HttpStatus.NOT_FOUND);
		}
		else{
			session.setAttribute("blog", blog);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
     }
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/getByTitle/{title}")
	public ResponseEntity getByTitle(@PathVariable("title") String title) {

		Blog blog = blogDAO.getByTitle(title);
		if (blog == null) {
			return new ResponseEntity("No Blog found for title " + title, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blog, HttpStatus.OK);
	}
}

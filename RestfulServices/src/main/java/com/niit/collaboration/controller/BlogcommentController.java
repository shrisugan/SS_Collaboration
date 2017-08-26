package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.niit.collaboration.dao.BlogcommentDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Blogcomment;
import com.niit.collaboration.model.User;

@RestController
public class BlogcommentController {
	@Autowired
	private BlogcommentDAO blogcommentDAO;

	/*public BlogcommentDAO getBlogcommentDAO() {
		return blogcommentDAO;
	}

	public void setBlogCommentDAO(BlogcommentDAO blogcommentDAO) {
		this.blogcommentDAO = blogcommentDAO;
	}
*/
	@GetMapping("/blogcomment")
	public List<Blogcomment> getComments() {
		return blogcommentDAO.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/blogcomments/{blogid}")
	public ResponseEntity getBlogId(@PathVariable("blogid") int blogid, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("blogid", blogid);
		System.out.println("hasi");
		System.out.println(blogid);
		List listcomment = blogcommentDAO.getBlogComments(blogid);
		if (listcomment == null) {
			return new ResponseEntity("No Comment found for ID " + blogid, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(listcomment, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/blogcomment/{id}")
	public ResponseEntity getBlogCommentId(@PathVariable("id") int id) {

		Blogcomment blogcomment = blogcommentDAO.getBlogComment(id);
		if (blogcomment == null) {
			return new ResponseEntity("No BlogComment found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blogcomment, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/blogcomments")
	public ResponseEntity createBlogComment(@RequestBody Blogcomment blogcomment, HttpSession session) {
		System.out.println("hai");
		
		
		
	
		User user=(User) session.getAttribute("user");
		Blog blog = (Blog) session.getAttribute("blog");
		blogcomment.setEmail(user.getEmail());
		blogcomment.setUsername(user.getUsername());
		blogcomment.setUserid(user.getUserid());
		blogcomment.setBlogid(blog.getBlogid());
		
		blogcommentDAO.save(blogcomment);  
		return new ResponseEntity(blogcomment, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/blogcomment/{id}")
	public ResponseEntity deleteBlogComment(@PathVariable int id) {
		Blogcomment blogComment = blogcommentDAO.getBlogComment(id);
		if (blogComment == null) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		} else {
			blogcommentDAO.delete(id);

			return new ResponseEntity(id, HttpStatus.OK);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/blogcomment/{id}")
	public ResponseEntity updateComment(@PathVariable String id, @RequestBody Blogcomment blogcomment) {

		blogcomment = blogcommentDAO.saveOrUpdate(blogcomment);

		if (null == blogcomment) {
			return new ResponseEntity("No Comment found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(blogcomment, HttpStatus.OK);
	}
}

package com.niit.collaboration.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.AppliedJobsDAO;
import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.dao.BlogcommentDAO;
import com.niit.collaboration.dao.CommentDAO;
import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.dao.ForumDAO;
import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.daoimp.AppliedJobsDAOImpl;
import com.niit.collaboration.daoimp.BlogDAOImpl;
import com.niit.collaboration.daoimp.BlogcommentDAOImpl;
import com.niit.collaboration.daoimp.CommentDAOImpl;
import com.niit.collaboration.daoimp.EventDAOImpl;
import com.niit.collaboration.daoimp.ForumDAOImpl;
import com.niit.collaboration.daoimp.FriendDAOImpl;
import com.niit.collaboration.daoimp.JobDAOImpl;
import com.niit.collaboration.daoimp.UserDAOImpl;
import com.niit.collaboration.model.AppliedJobs;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Blogcomment;
import com.niit.collaboration.model.Comment;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");

		dataSource.setUsername("COLLAB1");
		dataSource.setPassword("oracle");

		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Blogcomment.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Comment.class);
		sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(AppliedJobs.class);

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired(required = true)
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "BlogDAO")
	public BlogDAO getBlogDAO(SessionFactory sessionFactory) {
		return new BlogDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "ForumDAO")
	public ForumDAO getForumDAO(SessionFactory sessionFactory) {
		return new ForumDAOImpl(sessionFactory);
	}
	
	
	@Autowired(required = true)
	@Bean(name = "JobDAO")
	public JobDAO getJobDAO(SessionFactory sessionFactory) {
		return new JobDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "FriendDAO")
	public FriendDAO getFriendDAO(SessionFactory sessionFactory) {
		return new FriendDAOImpl(sessionFactory);
	}
	
	@Autowired(required = true)
	@Bean(name = "CommentDAO")
	public CommentDAO getCommentDAO(SessionFactory sessionFactory) {
		return new CommentDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "EventDAO")
	public EventDAO getEventDAO(SessionFactory sessionFactory) {
		return new EventDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "BlogcommentDAO")
	public BlogcommentDAO getBlogcommentDAO(SessionFactory sessionFactory) {
		return new BlogcommentDAOImpl(sessionFactory);
	}
	@Autowired(required = true)
	@Bean(name = "AppliedJobsDAO")
	public AppliedJobsDAO getAppliedJobsDAO(SessionFactory sessionFactory) {
		return new AppliedJobsDAOImpl(sessionFactory);
	}
}

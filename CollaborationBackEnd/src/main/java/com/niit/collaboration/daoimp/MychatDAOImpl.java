/*package com.niit.collaboration.daoimp;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.MychatDAO;
import com.niit.collaboration.model.Mychat;

@Repository("MychatDAO")
public class MychatDAOImpl implements MychatDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public MychatDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Mychat> list() {
		@SuppressWarnings("unchecked")
		List<Mychat> listMychat = (List<Mychat>) sessionFactory.getCurrentSession().createCriteria(Mychat.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listMychat;
	}
	
	@Transactional
	public Mychat saveOrUpdate(Mychat mychat) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(mychat);
		return mychat;		
	}
	
	@Transactional
	public void delete(int mychatid) {
		Mychat mychatToDelete = new Mychat();
		mychatToDelete.setChatid(mychatid);
		sessionFactory.getCurrentSession().delete(mychatToDelete);
   }
	
	@Transactional
	public Mychat getByMychatid(int mychatid) {
		Mychat MychatId = (Mychat) sessionFactory.getCurrentSession().get(Mychat.class, mychatid);
		return MychatId;
	}

	@Transactional
	public Mychat getAllMychat(int mychatid) {
		
		String hql = "from Mychat where mychatid ='" + mychatid + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Mychat> listMychat = (List<Mychat>) (query).list();

		if (listMychat != null && !listMychat.isEmpty()) {
			return listMychat.get(0);
		}
		return null;
  
	}

	@Transactional
	public void insert(Mychat mychat) {
		sessionFactory.getCurrentSession().saveOrUpdate(mychat);
		
	}

	public Mychat getByFriendId(int mychatid) {
		Mychat mychatListByID = (Mychat) sessionFactory.getCurrentSession().get(Mychat.class, mychatid);

		return mychatListByID;
	}


	
}
*/
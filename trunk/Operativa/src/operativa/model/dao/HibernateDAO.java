package operativa.model.dao;

import operativa.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDAO implements DataAccessObject {
	
	@Override
	public void create(Object[] param) {	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;		
		tx = session.beginTransaction();
	}

	@Override
	public Object getByAttribute(String attr, Object value) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;		
		tx = session.beginTransaction();
		return null;
	}

	@Override
	public Object getById(int id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;		
		tx = session.beginTransaction();
		return null;
	}

	@Override
	public void remove(Object[] param) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;		
		tx = session.beginTransaction();
	}

}

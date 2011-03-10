package operativa.model.dao;

import java.util.List;

import operativa.bean.entity.Usuario;
import operativa.utils.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;

public class UsuarioDAO {

	private List<Usuario> userlist;
	private Usuario user;

	public void delete(Integer id) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			user=(Usuario)session.get(Usuario.class,id);
			session.delete(user);
			tx.commit();
			
		}catch (RuntimeException e) {
			if(tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	public List getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try
		{
			session.beginTransaction();
			userlist=session.createQuery("from Usuarios").list();
			return userlist;
			
		}
		catch(Exception e)
		{
			System.out.print("Error while fetching "+e);
			return null;
		}
		finally
		{
			session.close();
		}
		
	}
	
	public Usuario getUser(String user, String pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("username", user));
			c.add(Restrictions.eq("password", pass));
			List usuarios = c.list();
			return (Usuario) ((usuarios.size() > 0 ) ? usuarios.get(0) : null);
		}finally {
			session.close();
		}
		
	}
	
	public void insert(Usuario usr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(usr);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}
	
	public void update(Usuario usr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx=session.beginTransaction();
			session.update(usr);
			tx.commit();
		}catch (RuntimeException e) {
			if(tx != null) tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}
	
}

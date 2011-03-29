package operativa.model.dao;

import java.util.List;

import operativa.bean.entity.Fabrica;
import operativa.bean.entity.Usuario;
import operativa.utils.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.ejb.criteria.CriteriaBuilderImpl;

public class UsuarioDAO {

	//TODO: Ver como mejorar el diseño de los DAOS..., sino fue codigo repetido es OPERATIVA...
	
	private Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Usuario getUser(String user, String pass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria c = session.createCriteria(Usuario.class);
			c.add(Restrictions.eq("username", user));
			c.add(Restrictions.eq("password", pass));
			List<Usuario> usuarios = c.list();
			return (Usuario) ((usuarios.size() > 0 ) ? usuarios.get(0) : null);
		}finally {
			session.close();
		}
		
	}
	
	public void crearUsuario(Usuario usr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		tx = session.beginTransaction();
		
		session.save(usr);
		tx.commit();
	}

	public void update(Usuario user) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.merge(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public List<Usuario> list() {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		List<Usuario> usuarios = null;
		try {
			//TODO: Ese string debería ser un Final arriba, así es más facil cambiarlo.
			usuarios = (List<Usuario>) session.createQuery("from Usuario").list();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return usuarios;
	}

	public void delete(Integer id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Usuario user = (Usuario) session.get(Usuario.class, id);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
	}

	public Usuario listById(int id) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Usuario user = null;
		try {
			user = (Usuario) session.get(Usuario.class, id);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return user;
	}
	
	
}

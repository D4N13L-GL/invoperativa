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
	
	
}

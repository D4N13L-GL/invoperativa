package operativa.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import operativa.bean.entity.Usuario;

public class UserDAO extends GenericHibernateDAO<Usuario, Integer> {

	@Override
	public void makeTransient(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public Usuario findUser(String username, String password) {
		
		List<Usuario> result = this.findByCriteria(Restrictions.eq("username", username), 
				Restrictions.eq("password", password));	
		
		return (result.size() > 0 ? result.get(0) : null ); 
	}

}

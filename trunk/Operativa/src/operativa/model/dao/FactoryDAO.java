package operativa.model.dao;

import operativa.bean.entity.Factory;
import operativa.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class FactoryDAO extends GenericHibernateDAO<Factory, Integer> {

	@Override
	public void makeTransient(Integer id) {
		Transaction tx =  super.session.beginTransaction();
		Factory oldFac = (Factory) super.session.get(Factory.class, id);
		super.session.delete(oldFac);
	}
	
}

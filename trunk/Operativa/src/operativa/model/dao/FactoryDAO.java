package operativa.model.dao;

import operativa.bean.entity.Factory;

import org.hibernate.Transaction;


public class FactoryDAO extends GenericHibernateDAO<Factory, Integer> {

	@Override
	public void makeTransient(Integer id) {
		@SuppressWarnings("unused")
		Transaction tx =  super.session.beginTransaction();
		Factory oldFac = (Factory) super.session.get(Factory.class, id);
		super.session.delete(oldFac);
	}
	
}
